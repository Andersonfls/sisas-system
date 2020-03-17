import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';
import { ProfileService } from '../profiles/profile.service';
import {JhiLanguageHelper, Principal, LoginModalService, LoginService, User} from '../../shared';
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

    constructor(
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router
    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.isSidebarCollapsed = true;
    }

    ngOnInit() {
        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });

        this.profileService.getProfileInfo().then((profileInfo) => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });

        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
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

    logout() {
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['']);
    }
}
