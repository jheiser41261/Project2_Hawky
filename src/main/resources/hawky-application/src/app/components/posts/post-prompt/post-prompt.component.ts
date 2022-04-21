import { Component, OnInit } from '@angular/core';
import { Form } from '@angular/forms';
import { Router } from '@angular/router';
import { PhotoServiceService } from 'src/app/services/photo-service.service';
import { PostServiceService } from 'src/app/services/post-service.service';

@Component({
  selector: 'app-post-prompt',
  templateUrl: './post-prompt.component.html',
  styleUrls: ['./post-prompt.component.css']
})
export class PostPromptComponent implements OnInit {

  constructor(private postService : PostServiceService, private photoService : PhotoServiceService, private router : Router) { }

  message : string = "";
  photo : string | Blob = "";

  hasPhoto : boolean = false;

  ngOnInit(): void {
  }

  selectFile(event : any){
    this.photo = event.target.files[0];
    this.hasPhoto = true;
  }

  createPost(event : any){
    event.preventDefault();

    if(this.message != ""){
      this.postService.createPost(this.message).subscribe(responseBody => {
        console.log(responseBody);
        let postId = responseBody.data.postId;

        if(this.hasPhoto){
          let formData : FormData = new FormData();
          formData.append("photo", this.photo);

          this.photoService.uploadPhoto(postId, formData).subscribe(responseBody => {
            console.log(responseBody.data);
          });
        }

        //location.reload();
      });
    }
  }
}
