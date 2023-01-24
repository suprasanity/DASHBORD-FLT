import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  auth: boolean = false;

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
      'Access-Control-Allow-Origin' : 'https://chovy.freeboxos.fr',
      'Content-Type': 'application/json'
    });
    try {
      const response = await this.http.post('https://chovy.freeboxos.fr/api/login', JSON.stringify(data), {headers}).toPromise();
      return response;
    }catch (e) {
      console.log(e);
    }
    return null;
  }

  public getAuth() : boolean {
    return this.auth;
  }
}
