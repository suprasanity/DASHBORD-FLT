import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {
  password: String ="";
  login: String="";

  constructor(private authService : AuthService) { }
  ngOnInit(): void {
  }
  loginMethode() {
    const data = {
      login: this.login,
      password: this.password
    }
    this.authService.login(data);
  };

}
