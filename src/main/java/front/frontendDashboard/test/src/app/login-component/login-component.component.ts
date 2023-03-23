import { Component } from '@angular/core';
import {AuthServiceService} from "../auth-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent {
  password: string ="";
  login: string="";

  constructor(private authService : AuthServiceService,private router:Router) { }

   async loginMethode() {
    const data = {
      login: this.login,
      password: this.password
    }
   try {
      const response = await this.authService.login(data);
      if (response) {
        this.authService.auth = true;

        const expiration = new Date().getTime() + 3600000;
        localStorage.setItem('LOGGED', expiration.toString());
        await this.router.navigate(['/acceuil']);
      }else {
        alert("Wrong login or password");
      }
    }catch (e) {
      console.log(e);
    }
   }
}
