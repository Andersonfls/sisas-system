import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ProgramasProjectosComponent,
    programasProjectosRoute,
    programasProjectosPopupRoute,
    ProgramasProjectosResolvePagingParams,
} from './';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ProgramasProjectosService} from '../../entities/programas-projectos';
import {ComunaService} from '../../entities/comuna';
import {MunicipioService} from '../../entities/municipio';
import {ProvinciaService} from '../../entities/provincia';

const ENTITY_STATES = [
    ...programasProjectosRoute,
    ...programasProjectosPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        NgbModule.forRoot()
    ],
    declarations: [
        ProgramasProjectosComponent
    ],
    entryComponents: [
        ProgramasProjectosComponent
    ],
    providers: [
        ProgramasProjectosResolvePagingParams,
        ProgramasProjectosService,
        ComunaService,
        MunicipioService,
        ProvinciaService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasProgramasProjectosExportModule {}
