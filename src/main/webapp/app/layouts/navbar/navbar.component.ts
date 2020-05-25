import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager, JhiLanguageService} from 'ng-jhipster';
import { ProfileService } from '../profiles/profile.service';
import { JhiLanguageHelper, Principal, LoginModalService, LoginService } from '../../shared';
import { VERSION } from '../../app.constants';
import { User } from '../../shared/user/user.model';
import {ArquivosPortal, CadPdfService} from '../../entities/cadastro-pdf';
import {UploadFileService} from '../../entities/cadastro-pdf/upload-file.service';
import {HttpResponse} from '@angular/common/http';
import {SobreDna} from '../../entities/sobre-dna';

@Component({
    selector: 'jhi-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: [
        'navbar.css'
    ]
})
export class NavbarComponent implements OnInit {

    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    user: User;
    manual: ArquivosPortal;

    constructor(
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router,
        private arquivosService: CadPdfService,
        private eventManager: JhiEventManager,
    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.isNavbarCollapsed = true;
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.user = account;
        });

        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });

        this.profileService.getProfileInfo().then((profileInfo) => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });

        this.arquivosService.find(1).subscribe(
            (res: HttpResponse<ArquivosPortal>) => {
                this.manual = res.body;
                console.log(this.manual);
            }
        );
    }

    changeLanguage(languageKey: string) {
      this.languageService.changeLanguage(languageKey);
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {

        this.eventManager.broadcast({
            name: 'logOutSuccess',
            content: 'Usuario deslogado'
        });

        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['']);
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }
}
