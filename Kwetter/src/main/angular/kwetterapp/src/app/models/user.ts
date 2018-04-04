export class User {
    id: number;
    username: string;
    location: string;
    bio: string;
    name: string;
    web: string;
    role: string;
    
    constructor(id: number, username: string, location: string, bio: string, name: string, web: string, role: string){
        this.id = id;
        this.username = username;
        this.location = location;
        this.bio = bio;
        this.name = name;
        this.web = web;
        this.role = role;
    }
}
