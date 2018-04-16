import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import {Post} from '../models/post';
import {User} from '../models/user';
import {ApiService} from './api.service';

@Injectable()
export class PostService {
    
    private postUrl = 'posts';

    constructor(private http: HttpClient, private apiService: ApiService) {
    }
    
    getAll(){
        return this.apiService.get<User[]>(this.postUrl);
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

    likePost(postId: number, userId: number){
        const url = this.postUrl + '/like/';
        return this.apiService.put<any>(url, {'postId': postId, 'userId': userId});
    }
    
    unlikePost(postId: number, userId: number){
        const url = this.postUrl + '/unlike/';
        return this.apiService.put<any>(url, {'postId': postId, 'userId': userId});
    }
    
    createPost(userId: number, content: string){
        const url = this.postUrl + '/create/';
        return this.apiService.postJson<any>(url, {'userId': userId, 'content': content});
    }
}
