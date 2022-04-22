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

  updateUserInfo(user : any){
    return this.httpClient.put(`http://localhost:9000/user`, {
        "userId" : user.userId,
        "username" : user.username,
        "password" : user.password,
        "firstName" : user.firstName,
        "lastName" : user.lastName,
        "email" : user.email,
        "city" : user.city,
        "state" : user.state,
        "occupation" : user.occupation,
        "profilePhoto" : user.profilePhoto
    }, {
      withCredentials: true
    });
  }
}
