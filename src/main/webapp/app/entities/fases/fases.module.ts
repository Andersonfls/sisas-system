import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    FasesService,
    FasesPopupService,
    FasesComponent,
    FasesDetailComponent,
    FasesDialogComponent,
    FasesPopupComponent,
    FasesDeletePopupComponent,
    FasesDeleteDialogComponent,
    fasesRoute,
    fasesPopupRoute,
    FasesResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...fasesRoute,
    ...fasesPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FasesComponent,
        FasesDetailComponent,
        FasesDialogComponent,
        FasesDeleteDialogComponent,
        FasesPopupComponent,
        FasesDeletePopupComponent,
    ],
    entryComponents: [
        FasesComponent,
        FasesDialogComponent,
        FasesPopupComponent,
        FasesDeleteDialogComponent,
        FasesDeletePopupComponent,
    ],
    providers: [
        FasesService,
        FasesPopupService,
        FasesResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasFasesModule {}
