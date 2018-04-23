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
    private sub: any;
    errors: any[] = [];
        
    constructor(private postService: PostService, private userService: UserService, 
        private authService: AuthService, private routeParam: ActivatedRoute) { 
    }

    ngOnInit() {
        this.sub = this.routeParam.params.subscribe(params => {
            this.profileId = params['id'];
            this.getLatestPosts(this.profileId);
            this.findUser(this.profileId);
            //this.userService.setFollowing(this.profileId);
            //this.userService.setFollowers(this.profileId);
            this.userService.currentId = this.profileId;
            this.personalPage = this.isPersonalPage();
        });        
    }
    
    followUser(followingId: number){
        this.userService.follow(this.authService.getUserId(), followingId)
            .subscribe(data => {
                if (data.Record){
                    console.log(data);
                } else if(data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    unfollowUser(unfollowingId: number){
        this.userService.unfollow(this.authService.getUserId(), unfollowingId)
            .subscribe(data => {
                if (data.Record){
                    console.log(data);
                } else if(data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    getLatestPosts(id: number) {
        this.postService.getLatestPosts(id)
            .subscribe(data => {
                if (data.Records){
                    this.latestPosts = data.Records;
                } else if(data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    findUser(id: number) {
        this.userService.findUser(id)
            .subscribe(data => {
                if (data.Record){
                    this.user = data.Record;
                } else if (data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    getFollowerAmount(id: number){
        this.userService.getFollowerAmount(id)
            .subscribe(data => {
                if(data.Record){
                   this.followerAmount = data.Record; 
                } else if (data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    getFollowingAmount(id: number){
        this.userService.getFollowingAmount(id)
            .subscribe(data => {
                if(data.Record){
                  this.followingAmount = data.Record;  
                } else if (data.messages){
                    this.errors = data.messages;
                }
        });
    }
    
    isPersonalPage(): boolean{
        if (this.authService.getUserId() == this.profileId){
            return true;
        }
        return false;
    }
}
