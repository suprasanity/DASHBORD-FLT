import { Component } from '@angular/core';
import {AuthServiceService} from "../auth-service.service";
@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent {
  password: string ="";
  login: string="";

  constructor(private authService : AuthServiceService) { }

  loginMethode() {
    const data = {
      login: this.login,
      password: this.password
    }
   this.authService.login(data);
  };
}
