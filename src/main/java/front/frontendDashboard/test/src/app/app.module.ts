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
import { WebserviceComponent } from './webservice/webservice.component';
import { MatCardModule } from '@angular/material/card';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import {MatDividerModule} from '@angular/material/divider';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {
  CalendarCommonModule,
  CalendarDayModule,
  CalendarModule,
  CalendarMonthModule,
  CalendarWeekModule, DateAdapter
} from "angular-calendar";
import {FlatpickrModule} from "angularx-flatpickr";
import {adapterFactory} from "calendar-utils/date-adapters/moment";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    ServerComponent,
    NavbarComponent,
    AcceuilComponent,
    CalendarComponent,
    WebserviceComponent,
    CalendarComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgChartsModule,
    MatCardModule,
    BrowserAnimationsModule,
    MatDividerModule,
    MatProgressBarModule,
    CalendarCommonModule,
    CalendarMonthModule,
    CalendarWeekModule,
    CalendarDayModule,
    FlatpickrModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
