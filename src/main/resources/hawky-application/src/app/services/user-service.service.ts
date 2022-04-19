import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private httpClient : HttpClient) { }

  register(username : string, password : string, firstName : string, lastName : string, email : string){
    return this.httpClient.post<any>(`http://localhost:9000/user`, {
      "username" : username,
      "password" : password,
      "firstName" : firstName,
      "lastName" : lastName,
      "email" : email
    }, {
      withCredentials: true
    });
  }

  getUserByUsername(username : string){
    return this.httpClient.get<any>(`http://localhost:9000/user/username/${username}`, {
      withCredentials: true
    });
  }
}
