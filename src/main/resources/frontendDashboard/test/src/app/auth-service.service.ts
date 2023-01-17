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

  login(data: { password: String; login: String })  {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    this.http.post('http://localhost:80/login', JSON.stringify(data), {headers}).subscribe(response => {
      this.auth = Boolean (response);

    });
    ;
  }

  public getAuth() : boolean {
    return this.auth;
  }
}
