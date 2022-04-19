import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostServiceService } from 'src/app/services/post-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  firstName : string = "";
  lastName : string = "";

  username : string = "";

  posts : Array<any> = [];

  constructor(private route : ActivatedRoute, private userService : UserServiceService, private postService : PostServiceService) {
    this.route.params.subscribe(params => {
      this.username = params['username'];
    });
  }

  ngOnInit(): void {
    this.getUserByUsername();
    this.getPostsByAuthor();
  }

  getUserByUsername(){
    this.userService.getUserByUsername(this.username).subscribe(responseBody => {
      this.firstName = responseBody.data.firstName;
      this.lastName = responseBody.data.lastName;
    })
  }

  getPostsByAuthor(){
    this.postService.getPostsByAuthor(this.username).subscribe(responseBody => {
      this.posts = responseBody.data;
    })
  }

}
