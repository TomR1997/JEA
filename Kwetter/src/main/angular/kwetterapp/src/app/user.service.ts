import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import { User } from '../models/user'

@Injectable()
export class UserService {

    private baseUrl = 'http://localhost:8080/Kwetter/api/';
    private userURL = 'users';

    constructor(private http: HttpClient) {
    }

    getAllUsers(): Observable<User[]> {
        const url = this.baseUrl + this.userURL;
        return this.http.get<User[]>(url)
            .pipe(
                tap(heroes => this.log('fetched users')),
                catchError(this.handleError('getAllUsers', []))
            );
    }
    
    findUser(id: number): any {
        const url = this.baseUrl + this.userURL + '/'+ id;
        return this.http.get<User>(url);
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

}
