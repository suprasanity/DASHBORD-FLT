import { Component } from '@angular/core';
import {AuthServiceService} from "../auth-service.service";

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent {

  constructor(private authService : AuthServiceService) {}

  startServer() {
    this.authService.startServer();
  }
  stopServer() {
    this.authService.stopServer();
  }
}
