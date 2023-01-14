import { Component } from '@angular/core';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent {

  constructor(private authService : AuthService) {}

  startServer() {
    this.authService.startServer();
  }
  stopServer() {
    this.authService.stopServer();
  }
}
