import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    MunicipioService,
    MunicipioPopupService,
    MunicipioComponent,
    MunicipioDetailComponent,
    MunicipioDialogComponent,
    MunicipioPopupComponent,
    MunicipioDeletePopupComponent,
    MunicipioDeleteDialogComponent,
    municipioRoute,
    municipioPopupRoute,
    MunicipioResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...municipioRoute,
    ...municipioPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MunicipioComponent,
        MunicipioDetailComponent,
        MunicipioDialogComponent,
        MunicipioDeleteDialogComponent,
        MunicipioPopupComponent,
        MunicipioDeletePopupComponent,
    ],
    entryComponents: [
        MunicipioComponent,
        MunicipioDialogComponent,
        MunicipioPopupComponent,
        MunicipioDeleteDialogComponent,
        MunicipioDeletePopupComponent,
    ],
    providers: [
        MunicipioService,
        MunicipioPopupService,
        MunicipioResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMunicipioModule {}
