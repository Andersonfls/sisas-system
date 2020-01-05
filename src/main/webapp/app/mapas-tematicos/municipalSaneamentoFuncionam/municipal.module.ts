import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared/index';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {MunicipalComponent} from './municipal.component';
import {MapResolvePagingParams, municipalRoute} from './municipal.route';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {MunicipioService} from '../../entities/municipio';

const ENTITY_STATES = [
    ...municipalRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        LeafletModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MunicipalComponent
    ],
    entryComponents: [
        MunicipalComponent
    ],
    providers: [
        MapResolvePagingParams,
        MunicipioService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMunicipalSaneamentoFuncionamModule {}
