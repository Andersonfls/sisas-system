import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ProgramasProjectosService,
    ProgramasProjectosPopupService,
    ProgramasProjectosComponent,
    ProgramasProjectosDetailComponent,
    ProgramasProjectosDialogComponent,
    ProgramasProjectosPopupComponent,
    ProgramasProjectosDeletePopupComponent,
    ProgramasProjectosDeleteDialogComponent,
    programasProjectosRoute,
    programasProjectosPopupRoute,
    ProgramasProjectosResolvePagingParams,
} from './';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ConcepcaoService} from '../concepcao';
import {ConcursoService} from '../concurso';
import {AdjudicacaoService} from '../adjudicacao';
import {ContratoService} from '../contrato';
import {SistemaAguaService} from '../sistema-agua';
import {NgxCurrencyModule} from 'ngx-currency';
import {ExecucaoService} from '../execucao';
import {EmpreitadaService} from '../empreitada';
import {SituacaoService} from '../situacao';

const ENTITY_STATES = [
    ...programasProjectosRoute,
    ...programasProjectosPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        NgxCurrencyModule,
        RouterModule.forChild(ENTITY_STATES),
        NgbModule.forRoot()
    ],
    declarations: [
        ProgramasProjectosComponent,
        ProgramasProjectosDetailComponent,
        ProgramasProjectosDialogComponent,
        ProgramasProjectosDeleteDialogComponent,
        ProgramasProjectosPopupComponent,
        ProgramasProjectosDeletePopupComponent,
    ],
    entryComponents: [
        ProgramasProjectosComponent,
        ProgramasProjectosDialogComponent,
        ProgramasProjectosPopupComponent,
        ProgramasProjectosDeleteDialogComponent,
        ProgramasProjectosDeletePopupComponent,
    ],
    providers: [
        ProgramasProjectosService,
        ProgramasProjectosPopupService,
        ProgramasProjectosResolvePagingParams,
        ConcepcaoService,
        ConcursoService,
        AdjudicacaoService,
        ContratoService,
        SistemaAguaService,
        ExecucaoService,
        EmpreitadaService,
        SituacaoService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasProgramasProjectosModule {}
