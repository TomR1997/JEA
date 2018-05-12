import { Component, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { Post } from '../models/post';
import { User } from '../models/user';
import {$WebSocket} from 'angular2-websocket/angular2-websocket';

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
    currentUser: User;
    ws: any;

  constructor(private postService: PostService, private userService: UserService, private authService: AuthService) {
  }

  ngOnInit() {
      //this.getLatestPosts(this.authService.getUserId());
      this.getTimeline(this.authService.getUserId());
      this.connect();
  }
  
  getTimeline(id: number){
      this.postService.getTimeline(id)
        .subscribe(data => {
            if(data.Records){
                this.timeline = data.Records;
            } else if (data.messages){
                this.errors = data.messages;
            }
      });
  }

  public searchPost(){
      this.postService.findPosts(this.tag)
        .subscribe(data => {
            if (data.Records){
                this.foundPosts = data.Records;
            } else if (data.messages){
                this.errors = data.messages;
            }
      });
  }
  
  getLatestPosts(id: number) {
      this.postService.getLatestPosts(id)
        .subscribe(data => {
            if (data.Records){
                this.timeline = data.Records;
            } else if (data.messages){
                this.errors = data.messages;
            }
      });
  }
  
  likePost(postId: number, userId: number){
      this.postService.likePost(postId, userId)
        .subscribe(data => {
            if (data.Record){
                console.log(data);
            } else if(data.messages){
                this.errors = data.messages;
            }
      });
  }
  
  unlikePost(postId: number, userId: number){
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
  
  createPostSocket(){
      let newPost = new Post(this.currentUser.id, this.content, 'date', this.currentUser, []);
      this.ws.send(JSON.stringify(newPost))
        .subscribe(data => {
            console.log(data); 
        });
  }
  
  connect(){
      this.userService.findUser(this.authService.getUserId())
        .subscribe(data => {
            if (data.Record){
                this.currentUser = data.Record;
                this.ws = new $WebSocket('ws://localhost:8080/Kwetter/kwetterendpoint/' + this.currentUser.username);
                this.ws.onMessage(data => {
                   this.handleSocketResponse(data);
                });
            } else if (data.messages){
                this.errors = data.messages;
            }
        });
  }
  
  handleSocketResponse(data: string){
      let receivedPost: Post = Object.assign(new Post(), JSON.parse(data.data));
      this.timeline.push(receivedPost);
  }
}
