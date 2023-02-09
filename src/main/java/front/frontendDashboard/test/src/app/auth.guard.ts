import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthServiceService} from "./auth-service.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService : AuthServiceService,private router:Router) { }
  canActivate(
  ): boolean {
    if(!this.authService.getAuth()) {
      this.router.navigate(['/signIn']).then(r => console.log(r));
    }
    return this.authService.getAuth();
  }

}
