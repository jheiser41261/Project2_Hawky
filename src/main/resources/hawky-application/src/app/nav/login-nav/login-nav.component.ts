import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionServiceService } from 'src/app/services/session-service.service';

@Component({
  selector: 'app-login-nav',
  templateUrl: './login-nav.component.html',
  styleUrls: ['./login-nav.component.css']
})
export class LoginNavComponent implements OnInit {

  constructor(private sessionService : SessionServiceService, private router : Router) { }

  username : string = "";

  ngOnInit(): void {
    this.checkSession();
  }

  checkSession(){
    this.sessionService.checkSession().subscribe(responseBody => {
      this.username = responseBody.data.username;
    });
  }

  goToProfile(e : any){
    this.router.navigate([`/profile/${e.target.innerText}`]);
  }
}
