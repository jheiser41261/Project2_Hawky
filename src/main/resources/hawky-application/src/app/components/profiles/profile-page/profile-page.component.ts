import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PhotoServiceService } from 'src/app/services/photo-service.service';
import { PostServiceService } from 'src/app/services/post-service.service';
import { SessionServiceService } from 'src/app/services/session-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  currentUser : any = null;
  user : any = null;

  isCurrentUser : boolean = false;

  username : string = "User";

  firstName : string = "";
  lastName : string = "";

  occupation : string = "";
  city : string = "";
  state : string = "";

  profilePicUrl : String = "";

  posts : Array<any> = [];

  photo : string | Blob = "";
  hasPhoto : boolean = false;

  constructor(private route : ActivatedRoute, 
    private userService : UserServiceService, 
    private postService : PostServiceService, 
    private sessionService : SessionServiceService,
    private photoService : PhotoServiceService,
    private router : Router
    ) {
    this.route.params.subscribe(params => {
      this.username = params['username'];
    });
  }

  ngOnInit(): void {
    this.checkSession();
  }

  selectFile(event : any){
    this.photo = event.target.files[0];
    this.hasPhoto = true;
  }

  checkSession(){
    this.sessionService.checkSession().subscribe(responseBody => {
      if(responseBody.success == false) this.router.navigate(['']);

      this.currentUser = responseBody.data;

      this.getUserByUsername();
      this.getPostsByAuthor();
    });
  }

  getUserByUsername(){
    this.userService.getUserByUsername(this.username).subscribe(responseBody => {
      this.user = responseBody.data;
      
      this.firstName = responseBody.data.firstName;
      this.lastName = responseBody.data.lastName;

      this.occupation = (responseBody.data.occupation == "" || responseBody.data.occupation == null) ? "Occupation" : responseBody.data.occupation;
      this.city = (responseBody.data.city == "" || responseBody.data.city == null) ? "City" : responseBody.data.city;
      this.state = (responseBody.data.state == "" || responseBody.data.state == null) ? "State" : responseBody.data.state;

      this.profilePicUrl = (responseBody.data.profilePhoto == "" || responseBody.data.profilePhoto == null) ? "https://hawky-photos-bucket.s3.amazonaws.com/3c56ca1c-37b8-4c0d-aa00-5baf9aca863c.png" : responseBody.data.profilePhoto;

      if(this.currentUser.userId == responseBody.data.userId) this.isCurrentUser = true;
    })
  }

  getPostsByAuthor(){
    this.postService.getPostsByAuthor(this.username).subscribe(responseBody => {
      this.posts = responseBody.data;
    })
  }

  editInfo(event : any){
    this.user.occupation = this.occupation;
    this.user.city = this.city;
    
    if(this.state.length > 2) this.state = "State";

    this.user.state = this.state;

    if(this.hasPhoto){
      let formData : FormData = new FormData();
      formData.append("photo", this.photo);

      this.photoService.uploadPhoto(this.user.userId, formData).subscribe(responseBody => {
        console.log(responseBody.data);
        this.user.profilePhoto = responseBody.data;
        
        this.updateInfo();

        location.reload();
      });
    } else {
      this.userService.updateUserInfo(this.user).subscribe(responseBody => {
        console.log(responseBody);
        location.reload();
      });
    }
  }

  updateInfo(){
    this.userService.updateUserInfo(this.user).subscribe(responseBody => {
      console.log(responseBody);
    });
  }
}
