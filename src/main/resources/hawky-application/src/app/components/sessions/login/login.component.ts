import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionServiceService } from 'src/app/services/session-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private sessionService : SessionServiceService, private router : Router) { }

  username : string = "";
  password : string = "";

  ngOnInit(): void {
    this.logout();
  }

  login(){
    this.sessionService.login(this.username, this.password).subscribe(responseBody => {
      console.log(responseBody);

      if(responseBody.success == true) this.router.navigate(['/dashboard']);
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

  register(){
    this.router.navigate(['/register'])
  }
}
