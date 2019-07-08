import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ConfiguracoesLog } from './configuracoes-log.model';
import { ConfiguracoesLogService } from './configuracoes-log.service';

@Injectable()
export class ConfiguracoesLogPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private configuracoesLogService: ConfiguracoesLogService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.configuracoesLogService.find(id)
                    .subscribe((configuracoesLogResponse: HttpResponse<ConfiguracoesLog>) => {
                        const configuracoesLog: ConfiguracoesLog = configuracoesLogResponse.body;
                        if (configuracoesLog.dtLog) {
                            configuracoesLog.dtLog = {
                                year: configuracoesLog.dtLog.getFullYear(),
                                month: configuracoesLog.dtLog.getMonth() + 1,
                                day: configuracoesLog.dtLog.getDate()
                            };
                        }
                        this.ngbModalRef = this.configuracoesLogModalRef(component, configuracoesLog);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.configuracoesLogModalRef(component, new ConfiguracoesLog());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    configuracoesLogModalRef(component: Component, configuracoesLog: ConfiguracoesLog): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.configuracoesLog = configuracoesLog;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
