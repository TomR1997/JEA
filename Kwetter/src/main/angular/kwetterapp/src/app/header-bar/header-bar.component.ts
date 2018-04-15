import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header-bar',
  templateUrl: './header-bar.component.html',
  styleUrls: ['./header-bar.component.css']
})
export class HeaderBarComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) { 
  }

  ngOnInit() {
  }

  routeProfile(){
      this.router.navigate(['profile']);
  }
  
  routeHome(){
      this.router.navigate(['homepage']);
  }  
  
  logout(){
      this.authService.logout();
  }
  
   routeLogin(){
      this.router.navigate(['login']);
  }
}
