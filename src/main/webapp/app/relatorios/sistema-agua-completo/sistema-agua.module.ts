import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    SistemaAguaComponent,
    sistemaAguaRoute,
    sistemaAguaPopupRoute,
    SistemaAguaResolvePagingParams,
} from './';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import {SistemaAguaService} from '../../entities/sistema-agua';
import {ComunaService} from '../../entities/comuna';
import {MunicipioService} from '../../entities/municipio';
import {ProvinciaService} from '../../entities/provincia';

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
        SistemaAguaComponent
    ],
    entryComponents: [
        SistemaAguaComponent,
    ],
    providers: [
        SistemaAguaService,
        ComunaService,
        MunicipioService,
        ProvinciaService,
        SistemaAguaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSistemaAguaExportModule {}
