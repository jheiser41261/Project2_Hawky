import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PostServiceService } from 'src/app/services/post-service.service';

@Component({
  selector: 'app-post-prompt',
  templateUrl: './post-prompt.component.html',
  styleUrls: ['./post-prompt.component.css']
})
export class PostPromptComponent implements OnInit {

  constructor(private postService : PostServiceService, private router : Router) { }

  message : string = "";

  ngOnInit(): void {
  }

  createPost(){
    if(this.message != ""){
      this.postService.createPost(this.message).subscribe(responseBody => {
        console.log(responseBody);
      });

      location.reload();
    }
  }

}
