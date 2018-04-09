import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { UserService } from '../user.service'
import { Post } from '../models/post'
import { User } from '../models/user'

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {
    following: User[];

    constructor(private postService: PostService, private userService: UserService) { 
    }

    ngOnInit() {
        this.getFollowing(1);
    }
    
    getFollowing(id: number): void {
        this.userService.getFollowing(id)
            .subscribe(data => this.following = data.Records);
    }

}
