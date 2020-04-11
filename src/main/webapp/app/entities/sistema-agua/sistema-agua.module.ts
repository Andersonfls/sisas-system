import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    SistemaAguaService,
    SistemaAguaPopupService,
    SistemaAguaComponent,
    SistemaAguaDetailComponent,
    SistemaAguaDialogComponent,
    SistemaAguaPopupComponent,
    SistemaAguaDeletePopupComponent,
    SistemaAguaDeleteDialogComponent,
    sistemaAguaRoute,
    sistemaAguaPopupRoute,
    SistemaAguaResolvePagingParams,
} from './';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';

const ENTITY_STATES = [
    ...sistemaAguaRoute,
    ...sistemaAguaPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        ReactiveFormsModule,
        NgbDropdownModule,
        NgMultiSelectDropDownModule
    ],
    declarations: [
        SistemaAguaComponent,
        SistemaAguaDetailComponent,
        SistemaAguaDialogComponent,
        SistemaAguaDeleteDialogComponent,
        SistemaAguaPopupComponent,
        SistemaAguaDeletePopupComponent,
    ],
    entryComponents: [
        SistemaAguaComponent,
        SistemaAguaDialogComponent,
        SistemaAguaPopupComponent,
        SistemaAguaDeleteDialogComponent,
        SistemaAguaDeletePopupComponent,
    ],
    providers: [
        SistemaAguaService,
        SistemaAguaPopupService,
        SistemaAguaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSistemaAguaModule {}
