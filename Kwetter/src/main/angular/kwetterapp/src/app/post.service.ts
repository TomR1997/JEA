import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import { Post } from '../models/post'

@Injectable()
export class PostService {

    private baseUrl = 'http://localhost:8080/Kwetter/api/';
    private postURL = 'posts';

    constructor(private http: HttpClient) {
    }

    getAllPosts(): Observable<Post[]> {
        const url = this.baseUrl + this.postURL;
        return this.http.get<Post[]>(url)
            .pipe(
                tap(heroes => this.log('fetched posts')),
                catchError(this.handleError('getAllPosts', []))
            );
    }
    
    getLatestPosts(id: number): Observable<Post[]>{
        const url = this.baseUrl + this.postURL + '/latest/'+ id;
        return this.http.get<Post[]>(url)
            .pipe(
                tap(heroes => this.log('fetched posts')),
                catchError(this.handleError('getLatestPosts', []))
            );
    }

    newPriceCategory(price: PriceCategory) {
        this.http.post(this.baseUrl + this.pricesCategoriesUrl, price
            , {responseType: 'text'}).subscribe(
                data => console.log(data)
        );
    }

    newLocationPrice(price: LocationPrice): Observable<string> {
        return this.http.post(this.baseUrl + this.locationpricesUrl, price
            , { responseType: 'text' });
    }

    changePriceCategory(price: PriceCategory) {
        const url = this.baseUrl + this.pricesCategoriesUrl;
        const headers = { 'Content-Type': 'application/json' };
        this.http.put(url, JSON.stringify(price), {responseType: 'text', headers: headers})
            .subscribe( data => {console.log(data); });
    }

    changeLocationPrice(price: LocationPrice) {
        const url = this.baseUrl + this.locationpricesUrl;
        const headers = { 'Content-Type': 'application/json' };
        this.http.put(url, JSON.stringify(price), {responseType: 'text', headers: headers})
            .subscribe( data => {console.log(data); });
    }

    getAllPriceCategories(): Observable<any[]> {
        const url = this.baseUrl + this.pricesCategoriesUrl;
        return this.http.get<any[]>(url)
            .pipe(
                tap(heroes => this.log(`fetched prices`)),
                catchError(this.handleError('getUserByName', []))
            );
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    private log(message: string) {

    }

}
