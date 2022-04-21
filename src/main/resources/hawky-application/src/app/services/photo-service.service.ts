import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PhotoServiceService {

  constructor(private httpClient : HttpClient) { }
  
  uploadPhoto(postId : number, body : FormData){
    return this.httpClient.post<any>(`http://localhost:9000/photo/upload/${postId}`, body, {
      withCredentials: true
    });
  }

  getPhotosByPost(postId : number){
    return this.httpClient.get<any>(`http://localhost:9000/photo/post/${postId}`, {
      withCredentials: true
    });
  }
}
