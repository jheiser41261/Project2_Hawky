import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PhotoServiceService } from 'src/app/services/photo-service.service';
import { PostServiceService } from 'src/app/services/post-service.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-post-prompt',
  templateUrl: './post-prompt.component.html',
  styleUrls: ['./post-prompt.component.css']
})
export class PostPromptComponent implements OnInit {

  constructor(private postService : PostServiceService, private photoService : PhotoServiceService, private router : Router) { }

  message : string = "";
  postId : number = 0;

  ngOnInit(): void {
  }

  createPost(){
    if(this.message != ""){
      this.postService.createPost(this.message).subscribe(responseBody => {
        console.log(responseBody);
        this.postId = responseBody.data.postId;
      });

      location.reload();
    }
  }
}
