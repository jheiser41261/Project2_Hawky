import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private userService : UserServiceService, private router : Router) { }

  username : string = "";
  password : string = "";
  firstName : string = "";
  lastName : string = "";
  email : string = "";

  ngOnInit(): void {
  }

  register(){
    this.userService.register(this.username, this.password, this.firstName, this.lastName, this.email).subscribe(responseBody => {
      console.log(responseBody);
    });
  }

  login(){
    this.router.navigate([''])
  }
}
