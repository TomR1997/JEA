import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { Post } from '../models/post'

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
    posts: Post[];

    constructor(private postService: PostService) { 
    }

    ngOnInit() {
        this.getAllPosts();
    }

    getAllPosts(): void{
        this.postService.getAllPosts()
            .subscribe(data => this.posts = data.Records);
            //.subscribe(data => console.log(data.Records));
    }
}
