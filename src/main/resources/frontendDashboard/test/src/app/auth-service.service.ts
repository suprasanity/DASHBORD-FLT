import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Webservice} from "./webservice";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  auth: boolean = false;
  basicAuthHeader = 'Basic ' + btoa( "john:admin");

  constructor(private http: HttpClient) {

  }

  startServer() {
    this.http.get('https://chovy.freeboxos.fr/api/StartMc').subscribe();
  }



  stopServer() {
  this.http.get('https://chovy.freeboxos.fr/api/StopMc').subscribe();
  }

   async login(data: { password: string; login: string })  {
    const headers = new HttpHeaders({
      'Access-Control-Allow-Origin' : 'http://localhost',
      'Content-Type': 'application/json',
      'Authorization': this.basicAuthHeader
    });
    try {
      const response = await this.http.post('http://localhost/api/login', JSON.stringify(data), {headers}).toPromise();
      return response;
    }catch (e) {
      console.log(e);
    }
    return null;
  }

  public getAuth() : boolean {
    return this.auth;
  }

   getAllws() : Observable<Webservice[]> {
    const headers = new HttpHeaders({
      'Access-Control-Allow-Origin' : 'localhost',
      'Content-Type': 'application/json',
      'Authorization': this.basicAuthHeader

    })


    return this.http.get<Webservice[]>('http://localhost/api/getAllService', {headers} );

  }
}
