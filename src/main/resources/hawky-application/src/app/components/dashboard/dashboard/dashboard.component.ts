import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionServiceService } from 'src/app/services/session-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private sessionService : SessionServiceService, private router : Router) { }

  ngOnInit(): void {
    this.checkSession();
  }

  checkSession(){
    this.sessionService.checkSession().subscribe(responseBody => {
      if(responseBody.success == false) this.router.navigate(['']);
    });
  }

}
