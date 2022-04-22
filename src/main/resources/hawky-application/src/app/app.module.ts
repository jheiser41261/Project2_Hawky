import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostContainerComponent } from './components/posts/post-container/post-container.component';
import { ProfilePageComponent } from './components/profiles/profile-page/profile-page.component';
import { LoginComponent } from './components/sessions/login/login.component';
import { RegisterComponent } from './components/sessions/register/register.component';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PostPromptComponent } from './components/posts/post-prompt/post-prompt.component';
import { NavComponent } from './nav/nav/nav.component';
import { LoginNavComponent } from './nav/login-nav/login-nav.component';

@NgModule({
  declarations: [
    AppComponent,
    PostContainerComponent,
    ProfilePageComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    PostPromptComponent,
    NavComponent,
    LoginNavComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
