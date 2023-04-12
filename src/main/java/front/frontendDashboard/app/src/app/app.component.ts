import { Component } from '@angular/core';
import {AuthServiceService} from "./auth-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private monService: AuthServiceService) {}


  title = 'angular-app';

  navbarShow() {
    return this.monService.getNavBar();

  }
}
