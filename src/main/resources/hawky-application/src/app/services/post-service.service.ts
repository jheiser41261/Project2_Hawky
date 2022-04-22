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

  getPostById(postId : number){
    return this.httpClient.get<any>(`http://localhost:9000/post/${postId}`, {
      withCredentials: true
    });
  }

  editPost(postId : number, author : any, message : string, likeCount : number, datePosted : Date, userHasLiked : boolean, photoUrl : string){
    return this.httpClient.put<any>(`http://localhost:9000/post`, {
      "postId" : postId,
      "author" : author,
      "message" : message,
      "likeCount" : likeCount,
      "datePosted" : datePosted,
      "userHasLiked" : userHasLiked,
      "photoUrl" : photoUrl
    }, {
      withCredentials: true
    });
  }

  likePost(postId : number){
    return this.httpClient.patch<any>(`http://localhost:9000/post/like/${postId}`, {
      withCredentials: true
    });
  }
}
