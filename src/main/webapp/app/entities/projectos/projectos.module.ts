import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ProjectosService,
    ProjectosPopupService,
    ProjectosComponent,
    ProjectosDetailComponent,
    ProjectosDialogComponent,
    ProjectosPopupComponent,
    ProjectosDeletePopupComponent,
    ProjectosDeleteDialogComponent,
    projectosRoute,
    projectosPopupRoute,
    ProjectosResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...projectosRoute,
    ...projectosPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProjectosComponent,
        ProjectosDetailComponent,
        ProjectosDialogComponent,
        ProjectosDeleteDialogComponent,
        ProjectosPopupComponent,
        ProjectosDeletePopupComponent,
    ],
    entryComponents: [
        ProjectosComponent,
        ProjectosDialogComponent,
        ProjectosPopupComponent,
        ProjectosDeleteDialogComponent,
        ProjectosDeletePopupComponent,
    ],
    providers: [
        ProjectosService,
        ProjectosPopupService,
        ProjectosResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasProjectosModule {}
