import { Routes } from '@angular/router';
import {WelcomeViewComponent} from "./welcome-view/welcome-view.component";
import {LoginFormComponent} from "./login-form/login-form.component";
import {RegisterFormComponent} from "./register-form/register-form.component";

export class AppRoutes {
  public static get ROUTES(): Routes { return [
      { path: '', component: WelcomeViewComponent,
        children: [
          { path: 'login', component: LoginFormComponent },
          { path: 'register', component: RegisterFormComponent }
        ]
      }
    ];
  }
}
