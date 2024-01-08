import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../../services/login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  constructor( private loginService:LoginServiceService, private router:Router){

  }

  username = ""
  role = ""

  ngOnInit(): void {
    const retrievedString = sessionStorage.getItem("WS_JWT");
    let jwt: string | undefined;
    if (retrievedString !== null) {
      jwt = retrievedString;
      console.log(jwt);
      this.loginService.authenticateJWT(jwt).then((response)=>{
        this.username=response.username;
        this.role=response.role;
      });
    } else {
      console.log("Item not found in sessionStorage");
      this.router.navigate(['/login'])
    }

  }

  logout(){
    sessionStorage.removeItem("WS_JWT")
    this.router.navigate(['/login'])
  }

}
