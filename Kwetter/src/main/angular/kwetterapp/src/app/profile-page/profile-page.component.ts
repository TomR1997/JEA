import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { UserService } from '../user.service'
import { Post } from '../models/post'
import { User } from '../models/user'

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
    posts: Post[];
    users: User[];
    latestPosts: Post[];

    constructor(private postService: PostService, private userService: UserService) { 
    }

    ngOnInit() {
        this.getAllPosts();
        this.getAllUsers();
        this.getLatestPosts();
    }

    getAllPosts(): void {
        this.postService.getAllPosts()
            .subscribe(data => this.posts = data.Records);
    }
    
    getAllUsers(): void {
        this.userService.getAllUsers()
            .subscribe(data => this.users = data.Records);
    }
    
    getLatestPosts(): void {
        this.postService.getLatestPosts()
            .subscribe(data => this.latestPosts = data.Records);
    }
}
