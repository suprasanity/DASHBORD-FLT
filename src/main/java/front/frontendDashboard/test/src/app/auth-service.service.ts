import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {firstValueFrom, Observable} from "rxjs";
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
    const headers = new HttpHeaders({
      'Access-Control-Allow-Origin' : '*',
      'Content-Type': 'application/json',
      'Authorization': this.basicAuthHeader

    })

    this.http.get('http://192.168.1.10:5000/api/StartMc',{headers}).subscribe();
  }



  stopServer() {
    const headers = new HttpHeaders({
      'Access-Control-Allow-Origin' : '*',
      'Content-Type': 'application/json',
      'Authorization': this.basicAuthHeader

    })

    this.http.get('http://192.168.1.10:5000/api/StopMc',{headers}).subscribe();
  }

   async login(data: { password: string; login: string })  {
    const headers = new HttpHeaders({
      'Access-Control-Allow-Origin' : '*',
      'Content-Type': 'application/json',
      'Authorization': this.basicAuthHeader
    });
    try {
      const response = await firstValueFrom( this.http.post('http://192.168.1.10:5000/api/login', JSON.stringify(data), {headers}));
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
      'Access-Control-Allow-Origin' : '*',
      'Content-Type': 'application/json',
      'Authorization': this.basicAuthHeader

    })


    return this.http.get<Webservice[]>('http://192.168.1.10:5000/api/getAllService', {headers} );

  }

  async delWs(data: { id: number })  {
    const headers = new HttpHeaders({
      'Access-Control-Allow-Origin' : '*',
      'Content-Type': 'application/json',
      'Authorization': this.basicAuthHeader
    });

    try {
      const response = await firstValueFrom( this.http.post('http://192.168.1.10:5000/api/deleteService', data.id, {headers}));
      return response;
    }catch (e) {
      console.log(e);
    }
    return null;
  }

}
