import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';
import { UserModalService } from './user-modal.service';
import { JhiLanguageHelper, User, UserService } from '../../shared';
import {Comuna} from '../../entities/comuna/comuna.model';
import {Municipio} from '../../entities/municipio/municipio.model';
import {Provincia} from '../../entities/provincia/provincia.model';
import {ComunaService} from '../../entities/comuna/comuna.service';
import {MunicipioService} from '../../entities/municipio/municipio.service';
import {ProvinciaService} from '../../entities/provincia/provincia.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
    selector: 'jhi-user-mgmt-dialog',
    templateUrl: './user-management-dialog.component.html'
})
export class UserMgmtDialogComponent implements OnInit {

    user: User;
    languages: any[];
    authorities: any[];
    isSaving: Boolean;
    provincias: Provincia[];
    provincia: Provincia;
    municipios: Municipio[];
    municipio: Municipio;
    comunas: Comuna[];
    comuna: Comuna;

    constructor(
        private jhiAlertService: JhiAlertService,
        public activeModal: NgbActiveModal,
        private languageHelper: JhiLanguageHelper,
        private userService: UserService,
        private provinciaService: ProvinciaService,
        private municipioService: MunicipioService,
        private comunaService: ComunaService,
        private eventManager: JhiEventManager
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.authorities = [];
        this.userService.authorities().subscribe((authorities) => {
            this.authorities = authorities;
        });
        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });
        this.status();

        this.comunaService.query()
            .subscribe((res: HttpResponse<Comuna[]>) => {
                this.comunas = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        this.municipioService.query().subscribe(
            (res: HttpResponse<Municipio[]>) => {
                this.municipios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

        this.provinciaService.query().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

    }

    status() {
        if (this.user.id === undefined) {
            this.user.activated = true;
        }
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.user.id !== null) {
            this.userService.update(this.user).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
        } else {
            this.userService.create(this.user).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
        }
    }

    private onSaveSuccess(result) {
        this.eventManager.broadcast({ name: 'userListModification', content: 'OK' });
        this.isSaving = false;
        this.activeModal.dismiss(result.body);
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    onChangeMunicipios() {
        this.municipios = null;
        this.comunas = null;

        this.municipioService.queryMunicipioByProvinciaId({
            provinciaId: this.user.provincia.id
        })
            .subscribe(res => {
                this.municipios = res.body;
            });
    }

    onChangeComunas() {
        this.comunas = null;

        this.comunaService.queryComunaByMunicipioId({
            municipioId: this.user.municipio.id
        })
            .subscribe(res => {
                this.comunas = res.body;
            });
    }
}

@Component({
    selector: 'jhi-user-dialog',
    template: ''
})
export class UserDialogComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userModalService: UserModalService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['login'] ) {
                this.userModalService.open(UserMgmtDialogComponent as Component, params['login']);
            } else {
                this.userModalService.open(UserMgmtDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
