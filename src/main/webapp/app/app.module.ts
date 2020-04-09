import './vendor.ts';

import { NgModule, Injector } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Ng2Webstorage, LocalStorageService, SessionStorageService  } from 'ngx-webstorage';
import { JhiEventManager } from 'ng-jhipster';
import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { SisasSharedModule, UserRouteAccessService } from './shared';
import { SisasAppRoutingModule} from './app-routing.module';
import { SisasHomeModule } from './home/home.module';
import { SisasAdminModule } from './admin/admin.module';
import { SisasAccountModule } from './account/account.module';
import { SisasEntityModule } from './entities/entity.module';
import { PaginationConfig } from './blocks/config/uib-pagination.config';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent,
    SidebarComponent
} from './layouts';
import {NgbDateParserFormatter, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbDateCustomParserFormatter} from './entities/programas-projectos/NgbDateCustomParserFormatter';
import {SisasRelatoriosModule} from './Relatorios/relatorios.module';
import {SisasMapasTematicosModule} from './mapas-tematicos/mapas.module';
import {NgxCurrencyModule} from 'ngx-currency';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {FormsModule} from '@angular/forms';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';

@NgModule({
    imports: [
        BrowserModule,
        NgxCurrencyModule,
        SisasAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        SisasSharedModule,
        SisasHomeModule,
        SisasAdminModule,
        SisasAccountModule,
        SisasEntityModule,
        SisasRelatoriosModule,
        SisasMapasTematicosModule,
        NgbModule.forRoot(),
        LeafletModule.forRoot(),
        FormsModule,
        NgMultiSelectDropDownModule.forRoot()
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        SidebarComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        PaginationConfig,
        UserRouteAccessService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
            deps: [
                LocalStorageService,
                SessionStorageService
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [
                Injector
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [
                JhiEventManager
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [
                Injector
            ]
        },
        { provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter }
    ],
    bootstrap: [ JhiMainComponent ]
})
export class SisasAppModule {}
