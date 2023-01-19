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
    this.http.get('http://localhost:80/StartMc').subscribe();
  }



  stopServer() {
  this.http.get('http://localhost:80/StopMc').subscribe();
  }

   async login(data: { password: string; login: string })  {

    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    try {
      const response = await this.http.post('http://localhost:80/login', JSON.stringify(data), {headers}).toPromise();
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
