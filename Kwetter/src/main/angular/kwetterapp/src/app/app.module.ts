import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';

import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule, MatMenuModule, MatToolbarModule, MatIconModule, 
        MatTableModule, MatListModule} from '@angular/material';
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatInputModule} from '@angular/material/input'
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

import {AppComponent} from './app.component';
import {ProfilePageComponent} from './profile-page/profile-page.component';
import {HomePageComponent} from './home-page/home-page.component';
import {HeaderBarComponent} from './header-bar/header-bar.component';
import {PostService} from './post.service';
import {UserService} from './user.service';
import { FollowingComponent } from './following/following.component';


@NgModule({
  declarations: [
    AppComponent,
    ProfilePageComponent,
    HomePageComponent,
    HeaderBarComponent,
    FollowingComponent
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
    MatFormFieldModule
  ],
  providers: [
      PostService,
      UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
