import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import {Post} from './models/post';
import {ApiService} from '../api/api.service';

@Injectable()
export class PostService {
    
    private postUrl = 'posts';

    constructor(private http: HttpClient, private apiService: ApiService) {
    }
    
    getAll(){
        return this.apiService.get<User[]>(postUrl);
    }
    
    getLatestPosts(id: number){
        const url = this.postUrl + '/latest/'+ id;
        return this.apiService.get<User[]>(url);
    }
    
    getTimeline(id: number){
        const url = this.postUrl + '/timeline/' + id;
        return this.apiService.get<User[]>(url);
    }
    
    findPosts(tag: string){
        const url = this.postUrl + '/find/' + tag;
        return this.apiService.get<Post[]>(url);
    }

    likePost(postId: number, userId: number): any{
        const url = this.postUrl + '/like/';
        return this.apiService.put<any>(url, {'postId': postId, 'userId': userId});
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
