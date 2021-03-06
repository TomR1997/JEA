import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

export let baseUrl: string = 'http://localhost:8080/Kwetter/api/';

@Injectable()
export class ApiService {

    constructor(private httpClient: HttpClient) {
    }

    public get<T>(address: string): Observable<any> {
        let headers: HttpHeaders = new HttpHeaders()
            .append('Content-Type', 'application/json');

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }
        
        let url = baseUrl + address;
        return this.httpClient.get<T>(url, { headers: headers });
    }
    
    public post<T>(address: string, content: any): Observable<any> {
        let headers: HttpHeaders = new HttpHeaders()
            .append('Content-Type', 'application/json');

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let url = baseUrl + address;
        return this.httpClient.post<T>(url, content, { headers: headers });
    }

    public postJson<T>(address: string, content: any): Observable<any> {
       let headers: HttpHeaders = new HttpHeaders()
            .append('Content-Type', 'application/json');

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }
        
        let url = baseUrl + address;
        return this.httpClient.post<T>(url, JSON.stringify(content), { headers: headers });
    }
    
    public put<T>(address: string, content: any): Observable<any> {
        let headers: HttpHeaders = new HttpHeaders()
            .append('Content-Type', 'application/json');

        if (localStorage.getItem('token')){
            headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        let url = baseUrl + address;
        return this.httpClient.put<T>(url, content, { headers: headers });
    }

}