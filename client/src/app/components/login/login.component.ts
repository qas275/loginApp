import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from '../../services/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm!:FormGroup;
  username=''
  hide  =true

  constructor(private formbuilder: FormBuilder, private router:Router, private loginService:LoginServiceService){

  }

  ngOnInit(): void {
      console.log("Checking for JWT token")
      if(sessionStorage.getItem("WS_JWT")){
        this.router.navigate(['/home'])
      }
      this.loginForm = this.createLoginForm();
  }

  createLoginForm(){
    return this.formbuilder.group({
      username: this.formbuilder.control(this.username, [Validators.required]),
      password: this.formbuilder.control('',[Validators.required,Validators.minLength(3)])
    })
  }

  authenticate(){
    this.username = this.loginForm.controls['username'].value;
    let password = this.loginForm.controls['password'].value;
    this.loginService.checkLoginFormCredentials(this.username, password).then((loginJWT) =>{
      if (loginJWT.login=='true'){
        sessionStorage.setItem('WS_JWT',loginJWT.jwt)
        this.router.navigate(['/home'])
      }else{
        alert("email: "+this.username+ " and password "+password+" does not exist or not match")
        this.ngOnInit();
      }
    })
  }

  

}
