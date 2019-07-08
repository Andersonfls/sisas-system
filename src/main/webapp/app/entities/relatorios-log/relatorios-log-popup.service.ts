import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { RelatoriosLog } from './relatorios-log.model';
import { RelatoriosLogService } from './relatorios-log.service';

@Injectable()
export class RelatoriosLogPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private relatoriosLogService: RelatoriosLogService

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
                this.relatoriosLogService.find(id)
                    .subscribe((relatoriosLogResponse: HttpResponse<RelatoriosLog>) => {
                        const relatoriosLog: RelatoriosLog = relatoriosLogResponse.body;
                        if (relatoriosLog.dtLog) {
                            relatoriosLog.dtLog = {
                                year: relatoriosLog.dtLog.getFullYear(),
                                month: relatoriosLog.dtLog.getMonth() + 1,
                                day: relatoriosLog.dtLog.getDate()
                            };
                        }
                        this.ngbModalRef = this.relatoriosLogModalRef(component, relatoriosLog);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.relatoriosLogModalRef(component, new RelatoriosLog());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    relatoriosLogModalRef(component: Component, relatoriosLog: RelatoriosLog): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.relatoriosLog = relatoriosLog;
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
