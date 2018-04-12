import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  username: string;
  password: string;
  success: boolean = false;;
    
  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  }
  
  login(){
      this.userService.login(this.username, this.password)
        .subscribe(data =>
            if (data.Record){
                this.router.navigate(['homepage']);
            }
        );
  }

}
