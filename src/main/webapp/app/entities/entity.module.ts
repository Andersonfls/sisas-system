import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { PtolomeuAuthorityModule } from './authority/authority.module';
import { PtolomeuDashboardModule } from './dashboard/dashboard.module';

@NgModule({
    imports: [
        PtolomeuDashboardModule,
        PtolomeuAuthorityModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PtolomeuEntityModule {}
