import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';
import { Post } from '../models/post';
import { User } from '../models/user';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
    posts: Post[];
    users: User[];
    latestPosts: Post[];
    user: User;
    followerAmount: number = 0;
    followingAmount: number = 0;
    following: User[];
    profileId: number;
        
    constructor(private postService: PostService, private userService: UserService, 
        private authService: AuthService, private routeParam: ActivatedRoute) { 
    }

    ngOnInit() {
        this.profileId = this.routeParam.snapshot.params['id'];
        this.getLatestPosts(this.profileId);
        this.findUser(this.profileId);
        this.userService.setFollowing(this.profileId);
        this.userService.setFollowers(this.profileId);
    }

    getAllPosts(): void {
        this.postService.getAllPosts()
            .subscribe(data => this.posts = data.Records);
    }
    
    getAllUsers(): void {
        this.userService.getAllUsers()
            .subscribe(data => this.users = data.Records);
    }
    
    getLatestPosts(id: number): void {
        this.postService.getLatestPosts(id)
            .subscribe(data => this.latestPosts = data.Records);
    }
    
    findUser(id: number): void {
        this.userService.findUser(id)
            .subscribe(data => this.user = data.Record);
    }
    
    getFollowing(id: number): void {
        this.userService.getFollowing(id)
            .subscribe(data => this.following = data.Records);
    }
    
    getFollowers(id: number): void {
        this.userService.getFollowers(id)
            .subscribe(data => this.followers = data.Records);
    }
    
    getFollowerAmount(id: number): void{
        this.userService.getFollowerAmount(id)
            .subscribe(data => this.followerAmount = data.Record);
    }
    
    getFollowingAmount(id: number): void{
        this.userService.getFollowingAmount(id)
            .subscribe(data => this.followingAmount = data.Record);
    }
}
