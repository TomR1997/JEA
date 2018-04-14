import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import { User } from './models/user'

@Injectable()
export class UserService {

    private baseUrl = 'http://localhost:8080/Kwetter/api/';
    private userUrl = 'users';
    public followers: User[];
    public following: User[];

    constructor(private http: HttpClient) {
    }

    getAllUsers(): Observable<User[]> {
        const url = this.baseUrl + this.userUrl;
        return this.http.get<User[]>(url)
            .pipe(
                tap(heroes => this.log('fetched users')),
                catchError(this.handleError('getAllUsers', []))
            );
    }
    
    findUser(id: number): any {
        const url = this.baseUrl + this.userUrl + '/'+ id;
        return this.http.get<User>(url);
    }
    
    find(username: String): any {
        const url = this.baseUrl + this.userUrl + '/name/'+ username;
        return this.http.get<User>(url);
    }
    
    getFollowing(id: number): Observable<User[]> {
        const url = this.baseUrl + this.userUrl + '/following/' + id;
        return this.http.get<User[]>(url)
            .pipe(
                tap(heroes => this.log('fetched following')),
                catchError(this.handleError('getFollowing', []))
            );
    }
    
    getFollowers(id: number): Observable<User[]> {
        const url = this.baseUrl + this.userUrl + '/followers/' + id;
        return this.http.get<User[]>(url)
            .pipe(
                tap(heroes => this.log('fetched followers')),
                catchError(this.handleError('getFollowers', []))
            );
    }
    
    getFollowerAmount(id: number): any {
        const url = this.baseUrl + this.userUrl + '/followerAmount/' + id;
        return this.http.get<number>(url);
    }
    
    getFollowingAmount(id: number): any {
        const url = this.baseUrl + this.userUrl + '/followingAmount/' + id;
        return this.http.get<number>(url);
    }
    
    login(username: string, password: string): any{
        const url = this.baseUrl + this.userUrl + '/login/' + username + '/' + password;
        return this.http.get<boolean>(url);
    }


    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    private log(message: string) {

    }
    
    public setFollowing(id: number): void {
        this.getFollowing(id)
            .subscribe(data => this.following = data.Records);
    }
    
    public setFollowers(id: number): void {
        this.getFollowers(id)
            .subscribe(data => this.followers = data.Records);
    }

}
