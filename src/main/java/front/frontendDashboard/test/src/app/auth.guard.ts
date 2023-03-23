import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router:Router) { }
  canActivate(
  ): boolean {
    // @ts-ignore
    if(localStorage.getItem('LOGGED') == null || (localStorage.getItem('LOGGED') < new Date().getTime().toString())) {

      this.router.navigate(['/signIn']).then(r => console.log(r));
    }

    return true;
  }

}
