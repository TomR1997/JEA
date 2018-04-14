import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';
import { Post } from '../models/post';
import { User } from '../models/user';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {
    following: User[];
    followers: User[];

    constructor(private postService: PostService, private userService: UserService, private authService: AuthService) { 
    }

    ngOnInit() {
        /*this.userService.setFollowing(this.authService.getUserId());
        this.userService.setFollowers(this.authService.getUserId());
        this.following = this.userService.following;
        this.followers = this.userService.followers;*/
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
}
