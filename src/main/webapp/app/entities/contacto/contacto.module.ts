import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ContactoComponent, contactoPopupRoute, ContactoResolvePagingParams,
    contactoRoute,
} from './';
import {FormsModule} from '@angular/forms';
import {SobreDnaService} from '../sobre-dna';

const ENTITY_STATES = [
    ...contactoRoute,
    ...contactoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        FormsModule
    ],
    declarations: [
        ContactoComponent
    ],
    entryComponents: [
        ContactoComponent
    ],
    providers: [
        SobreDnaService,
        ContactoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasContactoDnaModule {}
