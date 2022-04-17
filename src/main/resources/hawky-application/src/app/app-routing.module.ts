import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { ProfilePageComponent } from './components/profiles/profile-page/profile-page.component';
import { LoginComponent } from './components/sessions/login/login.component';
import { RegisterComponent } from './components/sessions/register/register.component';

const routes: Routes = [
  {path: "dashboard", component: DashboardComponent},
  {path: "register", component: RegisterComponent},
  {path: "profile", component: ProfilePageComponent},

  {path: "", component: LoginComponent},
  
  {path: "**", redirectTo: ""}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
