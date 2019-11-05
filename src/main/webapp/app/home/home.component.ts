import { Component, OnInit, Renderer, ElementRef } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Account, LoginModalService, Principal } from '../shared';
import { LoginService } from '../shared/login/login.service';
import { Router } from '@angular/router';
import { StateStorageService } from '../shared/auth/state-storage.service';
@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {

    account: Account;
    modalRef: NgbModalRef;
    authenticationError: boolean;
    password: string;
    rememberMe: boolean;
    username: string;
    credentials: any;

    constructor(
        private principal: Principal,
        private eventManager: JhiEventManager,
        private renderer: Renderer,
        private loginService: LoginService,
        private elementRef: ElementRef,
        private router: Router,
        private stateStorageService: StateStorageService,
    ) {
        this.credentials = {};
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        // console.log(this.principal);
        // console.log(this.principal.isAuthenticated());
        return this.principal.isAuthenticated();
    }

    login() {
        this.loginService.login({
            username: this.username,
            password: this.password,
            rememberMe: this.rememberMe
        }).then(() => {
            this.authenticationError = false;
            if (this.router.url === '/register' || (/^\/activate\//.test(this.router.url)) ||
                (/^\/reset\//.test(this.router.url))) {
                this.router.navigate(['']);
            }

            this.eventManager.broadcast({
                name: 'authenticationSuccess',
                content: 'Sending Authentication Success'
            });

            // // previousState was set in the authExpiredInterceptor before being redirected to login modal.
            // // since login is succesful, go to stored previousState and clear previousState
            const redirect = this.stateStorageService.getUrl();
            if (redirect) {
                this.stateStorageService.storeUrl(null);
                this.router.navigate([redirect]);
            } else {
                this.router.navigate(['dashboard']);
            }

        }).catch(() => {
            this.authenticationError = true;
        });
    }

    register() {
        this.router.navigate(['/register']);
    }

    requestResetPassword() {
        this.router.navigate(['/reset', 'request']);
    }
}
