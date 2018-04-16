import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header-bar',
  templateUrl: './header-bar.component.html',
  styleUrls: ['./header-bar.component.css']
})
export class HeaderBarComponent implements OnInit {
  isAuthenticated: boolean;

  constructor(private router: Router, private authService: AuthService) { 
  }

  ngOnInit() {
      this.isAuthenticated = this.authService.isAuthenticated();
  }

  routeProfile(){
      this.router.navigate(['profile/'+ this.authService.getUserId()]);
  }
  
  routeHome(){
      this.router.navigate(['homepage']);
  }  
  
  logout(){
      this.authService.logout();
      this.isAuthenticated = this.authService.authenticated;
  }
  
   routeLogin(){
      this.router.navigate(['login']);
  }
}
