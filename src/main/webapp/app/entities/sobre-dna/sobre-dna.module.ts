import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    SobreDnaService,
    SobreDnaPopupService,
    SobreDnaComponent,
    sobreDnaRoute,
    sobreDnaPopupRoute,
    SobreDnaResolvePagingParams,
} from './';
import {CKEditorModule} from 'ng2-ckeditor';
import {FormsModule} from '@angular/forms';

const ENTITY_STATES = [
    ...sobreDnaRoute,
    ...sobreDnaPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        CKEditorModule,
        FormsModule
    ],
    declarations: [
        SobreDnaComponent
    ],
    entryComponents: [
        SobreDnaComponent
    ],
    providers: [
        SobreDnaService,
        SobreDnaPopupService,
        SobreDnaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSobreDnaModule {}
