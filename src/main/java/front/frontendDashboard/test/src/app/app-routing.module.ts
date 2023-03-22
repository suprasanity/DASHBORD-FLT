import { NgModule } from '@angular/core';
import {Router, RouterModule, Routes} from '@angular/router';
import {LoginComponentComponent} from './login-component/login-component.component';
import {ServerComponent} from "./server/server.component";
import {AuthGuard} from "./auth.guard";
import {AcceuilComponent} from "./acceuil/acceuil.component";
import {WebserviceComponent} from "./webservice/webservice.component";
import {CalendarComponent} from "./calendar/calendar.component";

const routes: Routes = [
  { path: 'signIn', component: LoginComponentComponent ,
  },
  { path: 'server', component: ServerComponent ,
    canActivate: [AuthGuard]
  },
  { path: 'acceuil', component: AcceuilComponent ,
    canActivate: [AuthGuard]
  },
  { path: 'ws', component: WebserviceComponent ,
    canActivate: [AuthGuard]
  },
  { path: 'calendar', component: CalendarComponent ,
    canActivate: [AuthGuard]
  },
  { path: '**', redirectTo: 'signIn', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor(private router: Router) {
    this.router.errorHandler = (error: any) => {
      this.router.navigate(['/']); // or redirect to default route
    }
  }
  redirectToAcceuil() {
    this.router.navigate(['/acceuil']);
  }
}
