import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http:HttpClient) {

  }

  async checkLoginFormCredentials(username:string, password:string){
    let params = new HttpParams().set('username',username).set('password',password);
    let body = {'username':username,'password':password}
    let response = await lastValueFrom(this.http.post<any>('http://localhost:8080/login',body)).then(
      (responseHttp) =>{
        return responseHttp
      }
    )
    return JSON.parse(JSON.stringify(response))
  }

  async authenticateJWT(JWT:string){
    let headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded')
    let params =  new HttpParams().set('jwt', JWT);
    let response = await lastValueFrom(this.http.post<any>('http://localhost:8080/authenticate',params.toString(), {headers:headers})).then( 
      (responseHttp) =>{
        console.log(responseHttp);
        return responseHttp; 
      }
    )
    return JSON.parse(JSON.stringify(response))
  }
}
