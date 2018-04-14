import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import { Post } from './models/post'

@Injectable()
export class PostService {

    private baseUrl = 'http://localhost:8080/Kwetter/api/';
    private postUrl = 'posts';

    constructor(private http: HttpClient) {
    }

    getAllPosts(): Observable<Post[]> {
        const url = this.baseUrl + this.postUrl;
        return this.http.get<Post[]>(url)
            .pipe(
                tap(heroes => this.log('fetched posts')),
                catchError(this.handleError('getAllPosts', []))
            );
    }
    
    getLatestPosts(id: number): Observable<Post[]>{
        const url = this.baseUrl + this.postUrl + '/latest/'+ id;
        return this.http.get<Post[]>(url)
            .pipe(
                tap(heroes => this.log('fetched posts')),
                catchError(this.handleError('getLatestPosts', []))
            );
    }
    
    getTimeline(id: number): Observable<Post[]>{
        const url = this.baseUrl + this.postUrl + '/timeline/' + id;
        return this.http.get<Post[]>(url)
            .pipe(
                tap(heroes => this.log('fetched timeline')),
                catchError(this.handleError('getTimeline', []))
            );
    }
    
    findPosts(tag: string): Observable<Post[]>{
        const url = this.baseUrl + this.postUrl + '/find/' + tag;
        return this.http.get<Post[]>(url)
            .pipe(
                tap(heroes => this.log('fetched posts')),
                catchError(this.handleError('findPosts', []))
            );
    }
    
    likePost(postId: number, userId: number){
        const url = this.baseUrl + this.postUrl + 'like/'+postId +'/'+ userId;
        return this.http.put<any>(url);
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error); // log to console instead
            this.log(`${operation} failed: ${error.message}`);
            return of(result as T);
        };
    }

    private log(message: string) {

    }

}
