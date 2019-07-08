import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    MunicipiosAtendidosService,
    MunicipiosAtendidosPopupService,
    MunicipiosAtendidosComponent,
    MunicipiosAtendidosDetailComponent,
    MunicipiosAtendidosDialogComponent,
    MunicipiosAtendidosPopupComponent,
    MunicipiosAtendidosDeletePopupComponent,
    MunicipiosAtendidosDeleteDialogComponent,
    municipiosAtendidosRoute,
    municipiosAtendidosPopupRoute,
    MunicipiosAtendidosResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...municipiosAtendidosRoute,
    ...municipiosAtendidosPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MunicipiosAtendidosComponent,
        MunicipiosAtendidosDetailComponent,
        MunicipiosAtendidosDialogComponent,
        MunicipiosAtendidosDeleteDialogComponent,
        MunicipiosAtendidosPopupComponent,
        MunicipiosAtendidosDeletePopupComponent,
    ],
    entryComponents: [
        MunicipiosAtendidosComponent,
        MunicipiosAtendidosDialogComponent,
        MunicipiosAtendidosPopupComponent,
        MunicipiosAtendidosDeleteDialogComponent,
        MunicipiosAtendidosDeletePopupComponent,
    ],
    providers: [
        MunicipiosAtendidosService,
        MunicipiosAtendidosPopupService,
        MunicipiosAtendidosResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasMunicipiosAtendidosModule {}
