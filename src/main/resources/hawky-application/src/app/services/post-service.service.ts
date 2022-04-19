import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  constructor(private httpClient : HttpClient) { }

  createPost(message : string){
    return this.httpClient.post<any>(`http://localhost:9000/post`, {
      "message" : message
    }, {
      withCredentials: true
    });
  }

  getAllPosts(){
    return this.httpClient.get<any>(`http://localhost:9000/post/all`, {
      withCredentials: true
    });
  }

  getPostsByAuthor(username : string){
    return this.httpClient.get<any>(`http://localhost:9000/post/author/${username}`, {
      withCredentials: true
    });
  }
}
