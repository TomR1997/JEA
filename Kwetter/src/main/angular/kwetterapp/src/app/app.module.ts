import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';

import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule, MatMenuModule, MatToolbarModule, MatIconModule, 
        MatTableModule, MatListModule, MatFormFieldModule, MatInputModule, MatDividerModule, MatButtonModule,
        MatTabsModule} from '@angular/material';

import {AppComponent} from './app.component';
import {ProfilePageComponent} from './profile-page/profile-page.component';
import {HomePageComponent} from './home-page/home-page.component';
import {HeaderBarComponent} from './header-bar/header-bar.component';
import {PostService} from './services/post.service';
import {UserService} from './services/user.service';
import {AuthService} from './services/auth.service';
import {ApiService} from './services/api.service';
import { FollowingComponent } from './following/following.component';
import { LoginPageComponent } from './login-page/login-page.component';


@NgModule({
  declarations: [
    AppComponent,
    ProfilePageComponent,
    HomePageComponent,
    HeaderBarComponent,
    FollowingComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatDividerModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatMenuModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule,
    MatListModule,
    MatInputModule,
    MatFormFieldModule,
    MatTabsModule
  ],
  providers: [
      PostService,
      UserService,
      AuthService,
      ApiService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
