package DxcApplication.LoginApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DxcApplication.LoginApp.models.LoginRequest;
import DxcApplication.LoginApp.models.User;
import DxcApplication.LoginApp.repositories.DatabaseRepository;

@Service
public class LoginService {
    
    @Autowired
    DatabaseRepository databaseRepository;
    

    public Optional<User> checkLogin(LoginRequest request){
        if(databaseRepository.checkLoginSQL(request.getUsername(), request.getPassword())){
            Optional<User> userOptional = databaseRepository.loadUser(request.getUsername());
            return userOptional;
        }else{
            return Optional.empty();
        }
    }

}
