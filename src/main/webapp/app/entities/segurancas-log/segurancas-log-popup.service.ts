import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SegurancasLogService } from './segurancas-log.service';
import {SegurancasLog} from './segurancas-log.model';

@Injectable()
export class SegurancasLogPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private segurancasLogService: SegurancasLogService

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
                this.segurancasLogService.find(id)
                    .subscribe((relatoriosLogResponse: HttpResponse<SegurancasLog>) => {
                        const relatoriosLog: SegurancasLog = relatoriosLogResponse.body;
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
                    this.ngbModalRef = this.relatoriosLogModalRef(component, new SegurancasLog());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    relatoriosLogModalRef(component: Component, relatoriosLog: SegurancasLog): NgbModalRef {
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
