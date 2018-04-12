import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { UserService } from '../user.service'
import { Post } from '../models/post'
import { User } from '../models/user'

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
    timeline: Post[];
    foundPosts: Post[];
    tag: string;

  constructor(private postService: PostService, private userService: UserService) { 
  }

  ngOnInit() {
      this.getLatestPosts(1);
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
}
