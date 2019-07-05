import { Route } from '@angular/router';
import { NavbarComponent } from './layouts';
import {SidebarComponent} from './layouts';

export const navbarRoute: Route = {
    path: '',
    component: NavbarComponent,
    outlet: 'navbar'
};
export const sidebarRoute: Route = {
    path: '',
    component: SidebarComponent,
    outlet: 'sidebar'
};
