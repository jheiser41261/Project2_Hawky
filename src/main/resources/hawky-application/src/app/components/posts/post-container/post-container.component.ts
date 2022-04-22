import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PhotoServiceService } from 'src/app/services/photo-service.service';
import { PostServiceService } from 'src/app/services/post-service.service';

@Component({
  selector: 'app-post-container',
  templateUrl: './post-container.component.html',
  styleUrls: ['./post-container.component.css']
})
export class PostContainerComponent implements OnInit {

  posts : Array<any> = [];
  photos : Array<any> = [];

  hasPhoto : boolean = false;

  photoUrl : string = "";
  photoPost : number = 0;

  constructor(private postService : PostServiceService, private photoService : PhotoServiceService, private router : Router) { }

  ngOnInit(): void {
    this.getAllPosts();
  }

  getAllPosts(){
    this.postService.getAllPosts().subscribe(responseBody => {
      this.posts = responseBody.data;
      console.log(this.posts);
    });
  }

  getPhotosByPost(postId : number){
    this.photoService.getPhotosByPost(postId).subscribe(responseBody => {
      this.photos = responseBody.data;

      if(this.photos.length > 0) {
        this.hasPhoto = true;
        this.photoUrl = this.photos[0].photo;
        this.photoPost = this.photos[0].post.postId;
      } else {
        this.photoPost = 0;
        this.hasPhoto = false;
      }
    });
  }

  goToProfile(e : any){
    this.router.navigate([`/profile/${e.target.innerText}`]);
  }
}
