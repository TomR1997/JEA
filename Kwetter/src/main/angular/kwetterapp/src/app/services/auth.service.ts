import {Injectable} from '@angular/core';
import {tokenNotExpired} from 'angular2-jwt';
import {User} from '../models/user';
import 'rxjs/add/operator/map';
import {ApiService} from './api.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {
  private userUrl = 'users';
  authenticated: boolean;

  getToken(): string {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return tokenNotExpired(null, token);
  }

  constructor(private apiService: ApiService, private router: Router) {
  }

  public login(username: string, password: string): any {
    const url = this.userUrl + '/login/' + username + '/' + password;
    return this.apiService.get<String>(url);
  }
  
  public getUserId(): any{
      return localStorage.getItem('userId');
  }
  
  public logout(): void{
      localStorage.removeItem('userId');
      localStorage.removeItem('token');
      this.authenticated = false;
      this.router.navigate(['login']);
  }
}