import {Injectable} from '@angular/core';
import {tokenNotExpired} from 'angular2-jwt';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './models/user';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {
private baseUrl = 'http://localhost:8080/Kwetter/api/';
private userUrl = 'users';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getToken(): string {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return tokenNotExpired(null, token);
  }

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string): any {
    const url = this.baseUrl + this.userUrl + '/login/' + username + '/' + password;
    return this.http.get<String>(url);
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