import {Injectable} from '@angular/core';
import {catchError, tap} from 'rxjs/operators';
import {User} from '../models/user';
import {ApiService} from './api.service';

@Injectable()
export class UserService {

    private baseUrl = 'http://localhost:8080/Kwetter/api/';
    private userUrl = 'users';
    public followers: User[];
    public following: User[];

    constructor(private apiService: ApiService) {
    }

    getAll(){
        return this.apiService.get<User[]>(this.userUrl);
    }
    
    findUser(id: number){
        const url = this.userUrl + '/'+ id;
        return this.apiService.get<User>(url);
    }
    
    find(username: string){
        const url = this.userUrl + '/name/'+ username;
        return this.apiService.get<User>(url);
    }
    
    getFollowing(id: number){
        const url = this.userUrl + '/following/' + id;
        return this.apiService.get<User[]>(url);
    }
    
    getFollowers(id: number){
        const url = this.userUrl + '/followers/' + id;
        return this.apiService.get<User[]>(url);
    }
    
    getFollowerAmount(id: number) {
        const url = this.userUrl + '/followerAmount/' + id;
        return this.apiService.get<number>(url);
    }
    
    getFollowingAmount(id: number) {
        const url = this.userUrl + '/followingAmount/' + id;
        return this.apiService.get<number>(url);
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
