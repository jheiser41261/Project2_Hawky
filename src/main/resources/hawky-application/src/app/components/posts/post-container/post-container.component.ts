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

  postId : Array<any> = [];

  constructor(private postService : PostServiceService, private photoService : PhotoServiceService, private router : Router) { }

  ngOnInit(): void {
    this.getAllPosts();
  }

  getAllPosts(){
    this.postService.getAllPosts().subscribe(responseBody => {
      this.posts = responseBody.data;
    });
  }

  goToProfile(e : any){
    this.router.navigate([`/profile/${e.target.innerText}`]);
  }
}
