import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'
@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {
  password: any;
  login: any;
  private result: any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }
  loginMethode() {
    console.log('login:', this.login);
    console.log('Password:', this.password);
    this.http.post('http://localhost:8080/login', this.login,this.password).subscribe(data => {
      this.result = data;});
    console.log('result:', this.result);
  };

}
