import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { IndicadorProducaoLog } from './indicador-producao-log.model';
import { IndicadorProducaoLogService } from './indicador-producao-log.service';

@Injectable()
export class IndicadorProducaoLogPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private indicadorProducaoLogService: IndicadorProducaoLogService

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
                this.indicadorProducaoLogService.find(id)
                    .subscribe((indicadorProducaoLogResponse: HttpResponse<IndicadorProducaoLog>) => {
                        const indicadorProducaoLog: IndicadorProducaoLog = indicadorProducaoLogResponse.body;
                        if (indicadorProducaoLog.dtLog) {
                            indicadorProducaoLog.dtLog = {
                                year: indicadorProducaoLog.dtLog.getFullYear(),
                                month: indicadorProducaoLog.dtLog.getMonth() + 1,
                                day: indicadorProducaoLog.dtLog.getDate()
                            };
                        }
                        this.ngbModalRef = this.indicadorProducaoLogModalRef(component, indicadorProducaoLog);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.indicadorProducaoLogModalRef(component, new IndicadorProducaoLog());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    indicadorProducaoLogModalRef(component: Component, indicadorProducaoLog: IndicadorProducaoLog): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.indicadorProducaoLog = indicadorProducaoLog;
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
