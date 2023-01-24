import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { ServerComponent } from './server/server.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from "@angular/forms";
import { AcceuilComponent } from './acceuil/acceuil.component';
import { NgChartsModule } from 'ng2-charts';
import { CalendarComponent } from './calendar/calendar.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    ServerComponent,
    NavbarComponent,
    AcceuilComponent,
    CalendarComponent,


  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        NgChartsModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
