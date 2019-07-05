import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PtolomeuSharedModule } from '../../shared/shared.module';
import {
    AuthorityService,
    AuthorityPopupService,
    AuthorityComponent,
    AuthorityDetailComponent,
    AuthorityDialogComponent,
    AuthorityPopupComponent,
    AuthorityDeletePopupComponent,
    AuthorityDeleteDialogComponent,
    authorityRoute,
    authorityPopupRoute,
    AuthorityResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...authorityRoute,
    ...authorityPopupRoute,
];

@NgModule({
    imports: [
        PtolomeuSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AuthorityComponent,
        AuthorityDetailComponent,
        AuthorityDialogComponent,
        AuthorityDeleteDialogComponent,
        AuthorityPopupComponent,
        AuthorityDeletePopupComponent,
    ],
    entryComponents: [
        AuthorityComponent,
        AuthorityDialogComponent,
        AuthorityPopupComponent,
        AuthorityDeleteDialogComponent,
        AuthorityDeletePopupComponent,
    ],
    providers: [
        AuthorityService,
        AuthorityPopupService,
        AuthorityResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PtolomeuAuthorityModule {}
