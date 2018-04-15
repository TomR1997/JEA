import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { RequestOptions } from '@angular/http';

export let baseUrl: String = 'http://localhost:8080/Kwetter/api/';

@Injectable()
export class ApiService {

    constructor(private httpClient: HttpClient) {
    }

    public get<T>(address: string): Observable<T> {
        let headers = new HttpHeaders({'Content-Type': 'application/json'});  

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }
        
        let options = new RequestOptions({headers: headers});
        let url = baseUrl + address;
        return this.httpClient.get<T>(url, { options: options });
    }
    
    public post<T>(address: string, content: any): Observable<T> {
        let headers = new HttpHeaders({'Content-Type': 'application/json'});  

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let options = new RequestOptions({headers: headers});
        let url = baseUrl + address;
        return this.httpClient.post<T>(url, content, { options: options });
    }

    public postJson<T>(address: string, content: any): Observable<T> {
        let headers = new HttpHeaders({'Content-Type': 'application/json'});

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let options = new RequestOptions({headers: headers});
        let url = baseUrl + address;
        return this.httpClient.post<T>(url, JSON.stringify(content), { options: options });
    }
    
    public put<T>(address: string, content: any): Observable<T> {
        let headers = new HttpHeaders({'Content-Type': 'application/json'});  

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let options = new RequestOptions({headers: headers});
        let url = baseUrl + address;
        return this.httpClient.put<T>(url, content, { options: options });
    }

}