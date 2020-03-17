import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {ProvinciaService} from '../../entities/provincia';
import {FuncAguaComponent} from './func-agua.component';
import {FuncAguaResolvePagingParams, funcAguaRoute} from './func-agua.route';

const ENTITY_STATES = [
    ...funcAguaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FuncAguaComponent
    ],
    entryComponents: [
        FuncAguaComponent
    ],
    providers: [
        FuncAguaResolvePagingParams,
        ProvinciaService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasFuncAguaComunalModule {}
