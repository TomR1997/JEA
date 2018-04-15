import { Component, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { Post } from '../models/post';
import { User } from '../models/user';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
    timeline: Post[];
    foundPosts: Post[];
    tag: string;
    content: string;

  constructor(private postService: PostService, private userService: UserService, private authService: AuthService) { 
  }

  ngOnInit() {
      this.getLatestPosts(this.authService.getUserId());
  }
  
  getTimeline(id: number): void {
      this.postService.getTimeline(id)
        .subscribe(data => this.timeline = data.Records);
  }

  public searchPost(): void{
      this.postService.findPosts(this.tag)
        .subscribe(data => this.foundPosts = data.Records);
  }
  
  getLatestPosts(id: number): void {
      this.postService.getLatestPosts(id)
        .subscribe(data => this.timeline = data.Records);
  }
  
  likePost(postId: number, userId: number): void{
      this.postService.likePost(postId, userId)
        .subscribe(data => console.log(data));
  }
  
  createPost(){
      this.postService.createPost(this.authService.getUserId(), this.content)
        .subscribe(data => console.log(data));
  }
}
