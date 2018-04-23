import { Component, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { Post } from '../models/post';
import { User } from '../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {
    following: User[];
    followers: User[];
    profileUser: User;
    errors: any[] = [];

    constructor(private postService: PostService, private userService: UserService, private authService: AuthService,
        private router: Router) { 
    }

    ngOnInit() {
        this.getFollowing(this.authService.getUserId());
        this.getFollowers(this.authService.getUserId());
    }
    
    getFollowing(id: number): void {
        this.userService.getFollowing(id)
            .subscribe(data => {
                if (data.Records){
                    this.following = data.Records;
                } else if (data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    getFollowers(id: number): void {
        this.userService.getFollowers(id)
            .subscribe(data => {
                if (data.Records){
                    this.followers = data.Records;
                } else if (data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    routeProfile(username: string){
        this.userService.find(username)
            .subscribe(data => {
                if (data.Record){
                    this.profileUser = data.Record;
                    this.router.navigate(['profile/'+ this.profileUser.id]);
                } else if(data.messages){
                    this.errors = data.messages;
                }
        });
    }
}
