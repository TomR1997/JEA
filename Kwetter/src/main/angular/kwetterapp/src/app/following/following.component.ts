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

    constructor(private postService: PostService, private userService: UserService, private authService: AuthService,
        private router: Router) { 
    }

    ngOnInit() {
        this.getFollowing(this.authService.getUserId());
        this.getFollowers(this.authService.getUserId());
    }
    
    getFollowing(id: number): void {
        this.userService.getFollowing(id)
            .subscribe(data => this.following = data.Records);
    }
    
    getFollowers(id: number): void {
        this.userService.getFollowers(id)
            .subscribe(data => this.followers = data.Records);
    }
    
    routeProfile(username: string){
        this.userService.find(username)
            .subscribe(data => {
                this.profileUser = data.Record;
                this.router.navigate(['profile/'+ this.profileUser.id]);
            });
    }
}
