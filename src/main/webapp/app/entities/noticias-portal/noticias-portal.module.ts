import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    NoticiasPortalService,
    NoticiasPortalPopupService,
    NoticiasPortalComponent,
    NoticiasPortalDetailComponent,
    NoticiasPortalDialogComponent,
    NoticiasPortalPopupComponent,
    NoticiasPortalDeletePopupComponent,
    NoticiasPortalDeleteDialogComponent,
    noticiasPortalRoute,
    noticiasPortalPopupRoute,
    NoticiasPortalResolvePagingParams,
} from './';
import {CKEditorModule} from 'ng2-ckeditor';
import {FormsModule} from '@angular/forms';

const ENTITY_STATES = [
    ...noticiasPortalRoute,
    ...noticiasPortalPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        CKEditorModule,
        FormsModule
    ],
    declarations: [
        NoticiasPortalComponent,
        NoticiasPortalDetailComponent,
        NoticiasPortalDialogComponent,
        NoticiasPortalDeleteDialogComponent,
        NoticiasPortalPopupComponent,
        NoticiasPortalDeletePopupComponent,
    ],
    entryComponents: [
        NoticiasPortalComponent,
        NoticiasPortalDialogComponent,
        NoticiasPortalPopupComponent,
        NoticiasPortalDeleteDialogComponent,
        NoticiasPortalDeletePopupComponent,
    ],
    providers: [
        NoticiasPortalService,
        NoticiasPortalPopupService,
        NoticiasPortalResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasNoticiasPortalModule {}
