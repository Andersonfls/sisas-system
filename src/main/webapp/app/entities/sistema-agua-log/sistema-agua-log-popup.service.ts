import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SistemaAguaLog } from './sistema-agua-log.model';
import { SistemaAguaLogService } from './sistema-agua-log.service';

@Injectable()
export class SistemaAguaLogPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sistemaAguaLogService: SistemaAguaLogService

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
                this.sistemaAguaLogService.find(id)
                    .subscribe((sistemaAguaLogResponse: HttpResponse<SistemaAguaLog>) => {
                        const sistemaAguaLog: SistemaAguaLog = sistemaAguaLogResponse.body;
                        if (sistemaAguaLog.dtLog) {
                            sistemaAguaLog.dtLog = {
                                year: sistemaAguaLog.dtLog.getFullYear(),
                                month: sistemaAguaLog.dtLog.getMonth() + 1,
                                day: sistemaAguaLog.dtLog.getDate()
                            };
                        }
                        this.ngbModalRef = this.sistemaAguaLogModalRef(component, sistemaAguaLog);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.sistemaAguaLogModalRef(component, new SistemaAguaLog());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    sistemaAguaLogModalRef(component: Component, sistemaAguaLog: SistemaAguaLog): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.sistemaAguaLog = sistemaAguaLog;
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
