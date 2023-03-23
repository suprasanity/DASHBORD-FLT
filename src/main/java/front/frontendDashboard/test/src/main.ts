import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import 'localstorage-polyfill'

global['localStorage'] = localStorage;


platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
