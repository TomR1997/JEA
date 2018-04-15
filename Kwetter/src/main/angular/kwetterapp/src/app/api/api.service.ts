import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { RequestOptions } from '@angular/http';

export let baseUrl: String = 'http://localhost:8080/Kwetter/api/';

@Injectable()
export class ApiService {

    constructor(private httpClient: HttpClient) {
    }

    public get<T>(path: string): Observable<T> {
        let headers = new Headers({'Content-Type': 'application/json'});  

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }
        
        let options = new RequestOptions({headers: headers});
        let url = baseUrl + path;
        return this.httpClient.get<T>(url, { options: options });
    }
    
    public post<T>(postAddress: string, postContent: any): Observable<T> {
        let headers = new Headers({'Content-Type': 'application/json'});  

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let options = new RequestOptions({headers: headers});
        let url = baseUrl + postAddress;
        return this.httpClient.post<T>(url, postContent, { options: options });
    }

    public postJson<T>(postAddress: string, postContent: any): Observable<T> {
        let headers = new Headers({'Content-Type': 'application/json'});  

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let options = new RequestOptions({headers: headers});
        let url = baseUrl + postAddress;
        return this.httpClient.post<T>(url, JSON.stringify(postContent), { options: options });
    }
    
    public putJson<T>(postAddress: string, postContent: any): Observable<T> {
        let headers = new Headers({'Content-Type': 'application/json'});  

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let options = new RequestOptions({headers: headers});
        let url = baseUrl + postAddress;
        return this.httpClient.put<T>(url, JSON.stringify(postContent), { options: options });
    }

}