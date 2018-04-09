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

  constructor(private postService: PostService, private userService: UserService) { 
  }

  ngOnInit() {
      this.getTimeline(1);
  }
  
  getTimeline(id: number): void {
      this.postService.getTimeline(id)
        .subscribe(data => this.timeline = data.Records);
  }

}
