import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { HeaderBarComponent } from './header-bar/header-bar.component';
import { PostService } from './post.service';


@NgModule({
  declarations: [
    AppComponent,
    ProfilePageComponent,
    HomePageComponent,
    HeaderBarComponent
  ],
  imports: [
    BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule
  ],
  providers: [
      PostService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
