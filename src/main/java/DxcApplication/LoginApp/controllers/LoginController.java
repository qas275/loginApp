package DxcApplication.LoginApp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DxcApplication.LoginApp.models.JWTRequest;
import DxcApplication.LoginApp.models.LoginRequest;
import DxcApplication.LoginApp.models.User;
import DxcApplication.LoginApp.services.JwtUtil;
import DxcApplication.LoginApp.services.LoginService;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

@RestController
public class LoginController {
    
    @Autowired
    LoginService loginService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        try{
            System.out.println("CONTROLLER LOGIN USER >>> " + loginRequest.getUsername());
            Optional<User> userOptional = loginService.checkLogin(loginRequest);
            System.out.println(userOptional.isPresent());
            if(userOptional.isPresent()){
                // User user = userOptional.get();
                String jwt = jwtUtil.generateToken(userOptional.get());
                System.out.println(jwt);
                builder.add("jwt", jwt);
                // builder.add("username", user.getUsername());
                // builder.add("role", user.getRole());
                builder.add("login", "true");
            }else{
                builder.add("login", "false");
            }
        }catch(Exception e){
            builder.add("login", "false");
        }
        return ResponseEntity.status(HttpStatus.OK).body(builder.build().toString());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> register(@RequestBody MultiValueMap<String,String> jwtRequestString){
        
        // Extract values from JsonObject
        String jwt = jwtRequestString.get("jwt").get(0);
        JsonObjectBuilder builder = Json.createObjectBuilder();
        System.out.println(jwtRequestString);
        try{
            if(jwtUtil.validateToken(jwt)){
                String username = jwtUtil.getUsernameFromToken(jwt);
                String role = jwtUtil.getRoleFromToken(jwt);
                builder.add("username", username);
                builder.add("role", role);
                builder.add("login", "true");
            }else{
                builder.add("login", "false");
            }
        }catch(Exception e){
            builder.add("login", "false");
        }
        return ResponseEntity.status(HttpStatus.OK).body(builder.build().toString());
    }
}
