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
    errors: any[] = [];

  constructor(private postService: PostService, private userService: UserService, private authService: AuthService) { 
  }

  ngOnInit() {
      this.getLatestPosts(this.authService.getUserId());
  }
  
  getTimeline(id: number): void {
      this.postService.getTimeline(id)
        .subscribe(data => {
            if(data.Records){
                this.timeline = data.Records;
            } else if (data.messages){
                this.errors = data.messages;
            }
      });
  }

  public searchPost(): void{
      this.postService.findPosts(this.tag)
        .subscribe(data => {
            if (data.Records){
                this.foundPosts = data.Records;
            } else if (data.messages){
                this.errors = data.messages;
            }
      });
  }
  
  getLatestPosts(id: number): void {
      this.postService.getLatestPosts(id)
        .subscribe(data => {
            if (data.Records){
                this.timeline = data.Records;
            } else if (data.messages){
                this.errors = data.messages;
            }
      });
  }
  
  likePost(postId: number, userId: number): void{
      this.postService.likePost(postId, userId)
        .subscribe(data => {
            if (data.Record){
                console.log(data);
            } else if(data.messages){
                this.errors = data.messages;
            }
      });
  }
  
  unlikePost(postId: number, userId: number): void{
      this.postService.unlikePost(postId, userId)
        .subscribe(data => {
            if (data.Record){
                console.log(data);
            } else if(data.messages){
                this.errors = data.messages;
            }
      });
  }
  
  createPost(){
      this.postService.createPost(this.authService.getUserId(), this.content)
        .subscribe(data => {
            this.getLatestPosts(this.authService.getUserId());
            if(data.messages){
                this.errors = data.messages;
            }
      });
  }
}
