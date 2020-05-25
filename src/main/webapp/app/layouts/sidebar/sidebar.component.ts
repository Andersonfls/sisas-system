import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager, JhiLanguageService} from 'ng-jhipster';
import { ProfileService } from '../profiles/profile.service';
import {JhiLanguageHelper, Principal, LoginModalService, LoginService, User, Account} from '../../shared';
import { VERSION } from '../../app.constants';

@Component({
    selector: 'jhi-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: [
        'sidebar.css'
    ]
})
export class SidebarComponent implements OnInit {
    inProduction: boolean;
    isSidebarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    user: User;
    isMapaHuamboVisivel: boolean;
    isMapaHuilaVisivel: boolean;
    isMapaCuanzaVisivel: boolean;
    isAdmin: boolean;
    account: Account;
    activeState;

    constructor(
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.isSidebarCollapsed = true;
    }

    ngOnInit() {
        this.isMapaHuamboVisivel = false;
        this.isMapaHuilaVisivel = false;
        this.isMapaCuanzaVisivel = false;
        this.isAdmin = false;
        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });

        this.profileService.getProfileInfo().then((profileInfo) => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });

        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
            this.definirVisibilidadeMapas();
        });

        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
        this.registerInLogOutSuccess();
        this.activeState = '';
    }

    setStateAsActive(state) {
        this.activeState = state;
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then(() => {
                this.principal.identity().then((userIdentity) => {
                    this.user = userIdentity;
                    this.definirVisibilidadeMapas();
                });
            });
        });
    }

    definirVisibilidadeMapas() {
        this.removerPermissoesMapas();
        if (this.user.provincia.id === 15) {
            console.log('USUARIO DA PROVINCIA DE HUILA');
            this.isMapaHuilaVisivel = true;
        }

        if (this.user.provincia.id === 10) {
            console.log('USUARIO DA PROVINCIA DE HUAMBO');
            this.isMapaHuamboVisivel = true;
        }

        if (this.user.provincia.id === 5) {
            console.log('USUARIO DA PROVINCIA DE CUANZA');
            this.isMapaCuanzaVisivel = true;
        }

        this.user.authorities.forEach((p) => {
            if (p === 'ROLE_ADMIN') {
                this.isAdmin = true;
            }
        });
    }
    collapseNavbar() {
        this.isSidebarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    registerInLogOutSuccess() {
        this.eventManager.subscribe('logOutSuccess', (message) => {
            console.log('IDENTIFICOU LOG DE LOGOUT');
           this.removerPermissoesMapas();
        });
    }

    removerPermissoesMapas() {
        this.isMapaHuamboVisivel = false;
        this.isMapaHuilaVisivel = false;
        this.isMapaCuanzaVisivel = false;
        this.isAdmin = false;
    }

    logout() {
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['']);
    }
}
