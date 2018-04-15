import {Injectable} from '@angular/core';
import {tokenNotExpired} from 'angular2-jwt';
import {User} from './models/user';
import 'rxjs/add/operator/map';
import {ApiService} from '../api/api.service';

@Injectable()
export class AuthService {
private userUrl = 'users';

  getToken(): string {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return tokenNotExpired(null, token);
  }

  constructor(private apiService: ApiService) {
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
      this.router.navigate(['login']);
  }
}