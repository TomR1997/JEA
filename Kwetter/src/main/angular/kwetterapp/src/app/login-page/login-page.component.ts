import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { User } from '../models/user';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  username: string;
  password: string;
  success: boolean = false;
  user: User;
    
  constructor(private userService: UserService, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
  }
  
  login(){
      this.authService.login(this.username, this.password)
        .subscribe(data => {
            if (data.success){
                localStorage.setItem('token', data.Record);
                this.getUser(this.username);
                this.router.navigate(['homepage']);
                this.authService.authenticated = true;
                console.log(localStorage);
            }
        });
  }
  
  getUser(username: string){
      this.userService.find(username)
        .subscribe(data => {
            this.user = data.Record;
            localStorage.setItem('userId', this.user.id);
        });
  }

}
