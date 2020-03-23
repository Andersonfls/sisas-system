import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {ProvinciaService} from '../../entities/provincia';
import {FuncAguaChafarizesComponent} from './func-agua-chafarizes.component';
import {FuncAguaChafarizesResolvePagingParams, funcAguaChafarizesRoute} from './func-agua-chafarizes.route';

const ENTITY_STATES = [
    ...funcAguaChafarizesRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FuncAguaChafarizesComponent
    ],
    entryComponents: [
        FuncAguaChafarizesComponent
    ],
    providers: [
        FuncAguaChafarizesResolvePagingParams,
        ProvinciaService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasFuncAguaChafarizesMunicipalModule {}
