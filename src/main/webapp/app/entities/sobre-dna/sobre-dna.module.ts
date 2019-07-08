import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    SobreDnaService,
    SobreDnaPopupService,
    SobreDnaComponent,
    SobreDnaDetailComponent,
    SobreDnaDialogComponent,
    SobreDnaPopupComponent,
    SobreDnaDeletePopupComponent,
    SobreDnaDeleteDialogComponent,
    sobreDnaRoute,
    sobreDnaPopupRoute,
    SobreDnaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...sobreDnaRoute,
    ...sobreDnaPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SobreDnaComponent,
        SobreDnaDetailComponent,
        SobreDnaDialogComponent,
        SobreDnaDeleteDialogComponent,
        SobreDnaPopupComponent,
        SobreDnaDeletePopupComponent,
    ],
    entryComponents: [
        SobreDnaComponent,
        SobreDnaDialogComponent,
        SobreDnaPopupComponent,
        SobreDnaDeleteDialogComponent,
        SobreDnaDeletePopupComponent,
    ],
    providers: [
        SobreDnaService,
        SobreDnaPopupService,
        SobreDnaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasSobreDnaModule {}
