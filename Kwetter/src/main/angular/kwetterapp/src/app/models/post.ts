import { User } from './user';

export class Post{
    id: number;
    message: string;
    messageSent: string
    owner: User;
    likedBy: User[];
    
    constructor(id: number, message: string, messageSent: string, owner: User, likedBy: User[]){
        this.id = id;
        this.message = message;
        this.messageSent = messageSent;
        this.owner = owner;
        this.likedBy = likedBy;
    }
}