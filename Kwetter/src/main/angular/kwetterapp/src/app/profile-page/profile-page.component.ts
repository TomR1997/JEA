import { Component, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
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
    profileId: number;
    personalPage: boolean;
        
    constructor(private postService: PostService, private userService: UserService, 
        private authService: AuthService, private routeParam: ActivatedRoute) { 
    }

    ngOnInit() {
        this.profileId = this.routeParam.snapshot.params['id'];
        this.getLatestPosts(this.profileId);
        this.findUser(this.profileId);
        this.userService.setFollowing(this.profileId);
        this.userService.setFollowers(this.profileId);
        this.personalPage = this.isPersonalPage();
    }
    
    followUser(followingId: number){
        this.userService.follow(this.authService.getUserId(), followingId)
            .subscribe(data => console.log(data));
    }
    
    unfollowUser(unfollowingId: number){
        this.userService.unfollow(this.authService.getUserId(), unfollowingId)
            .subscribe(data => console.log(data));
    }
    
    getLatestPosts(id: number): void {
        this.postService.getLatestPosts(id)
            .subscribe(data => this.latestPosts = data.Records);
    }
    
    findUser(id: number): void {
        this.userService.findUser(id)
            .subscribe(data => this.user = data.Record);
    }
    
    getFollowerAmount(id: number): void{
        this.userService.getFollowerAmount(id)
            .subscribe(data => this.followerAmount = data.Record);
    }
    
    getFollowingAmount(id: number): void{
        this.userService.getFollowingAmount(id)
            .subscribe(data => this.followingAmount = data.Record);
    }
    
    isPersonalPage(): boolean{
        if (this.authService.getUserId() == this.profileId){
            return true;
        }
        return false;
    }
}
