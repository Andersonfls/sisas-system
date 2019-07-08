import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    NoticiasService,
    NoticiasPopupService,
    NoticiasComponent,
    NoticiasDetailComponent,
    NoticiasDialogComponent,
    NoticiasPopupComponent,
    NoticiasDeletePopupComponent,
    NoticiasDeleteDialogComponent,
    noticiasRoute,
    noticiasPopupRoute,
    NoticiasResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...noticiasRoute,
    ...noticiasPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NoticiasComponent,
        NoticiasDetailComponent,
        NoticiasDialogComponent,
        NoticiasDeleteDialogComponent,
        NoticiasPopupComponent,
        NoticiasDeletePopupComponent,
    ],
    entryComponents: [
        NoticiasComponent,
        NoticiasDialogComponent,
        NoticiasPopupComponent,
        NoticiasDeleteDialogComponent,
        NoticiasDeletePopupComponent,
    ],
    providers: [
        NoticiasService,
        NoticiasPopupService,
        NoticiasResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasNoticiasModule {}
