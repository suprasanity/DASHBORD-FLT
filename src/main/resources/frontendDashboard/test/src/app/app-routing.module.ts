import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponentComponent} from './login-component/login-component.component';
import {ServerComponent} from "./server/server.component";
import {AuthGuard} from "./auth.guard";

const routes: Routes = [
  { path: 'signIn', component: LoginComponentComponent ,
  },
  { path: 'server', component: ServerComponent ,
    canActivate: [AuthGuard]
  },
  { path: '**', redirectTo: 'signIn', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
