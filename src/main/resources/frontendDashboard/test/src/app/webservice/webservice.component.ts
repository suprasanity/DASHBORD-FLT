import {Component, OnInit} from '@angular/core';
import {AuthServiceService} from "../auth-service.service";
import {Router} from "@angular/router";
import {Webservice} from "../webservice";

@Component({
  selector: 'app-webservice',
  templateUrl: './webservice.component.html',
  styleUrls: ['./webservice.component.css']
})
export class WebserviceComponent implements OnInit{
    w : Webservice[]= [];

  constructor(private authService : AuthServiceService,private router:Router) {
  }

  getWs(): void {
    this.authService.getAllws().subscribe((result)=>{
      this.w = result;
    })
  }

  ngOnInit(): void {
    this.getWs()
  }
}
