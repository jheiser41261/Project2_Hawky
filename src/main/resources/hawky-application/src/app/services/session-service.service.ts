import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionServiceService {

  constructor(private httpClient : HttpClient) { }

  login(username : string, password : string){
    return this.httpClient.post<any>(`http://localhost:9000/session`, {
      "username" : username,
      "password" : password
    }, {
      withCredentials: true
    });
  }

  checkSession(){
    return this.httpClient.get<any>(`http://localhost:9000/session`, {
      withCredentials: true
    });
  }

  logout(){
    return this.httpClient.delete<any>(`http://localhost:9000/session`, {
      withCredentials: true
    });
  }
}
