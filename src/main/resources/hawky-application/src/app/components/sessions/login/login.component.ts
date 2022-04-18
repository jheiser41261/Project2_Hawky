import { Component, OnInit } from '@angular/core';
import { SessionServiceService } from 'src/app/services/session-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private sessionService : SessionServiceService) { }

  username : string = "jah123";
  password : string = "pass123";

  ngOnInit(): void {
  }

  login(){
    this.sessionService.login(this.username, this.password).subscribe(responseBody => {
      console.log(responseBody);
    });
  }

  checkSession(){
    this.sessionService.checkSession().subscribe(responseBody => {
      console.log(responseBody);
    });
  }

  logout(){
    this.sessionService.logout().subscribe(responseBody => {
      console.log(responseBody);
    });
  }
}
