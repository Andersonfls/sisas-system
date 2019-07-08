import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { PublicacaoLog } from './publicacao-log.model';
import { PublicacaoLogService } from './publicacao-log.service';

@Injectable()
export class PublicacaoLogPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private publicacaoLogService: PublicacaoLogService

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
                this.publicacaoLogService.find(id)
                    .subscribe((publicacaoLogResponse: HttpResponse<PublicacaoLog>) => {
                        const publicacaoLog: PublicacaoLog = publicacaoLogResponse.body;
                        if (publicacaoLog.dtLog) {
                            publicacaoLog.dtLog = {
                                year: publicacaoLog.dtLog.getFullYear(),
                                month: publicacaoLog.dtLog.getMonth() + 1,
                                day: publicacaoLog.dtLog.getDate()
                            };
                        }
                        this.ngbModalRef = this.publicacaoLogModalRef(component, publicacaoLog);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.publicacaoLogModalRef(component, new PublicacaoLog());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    publicacaoLogModalRef(component: Component, publicacaoLog: PublicacaoLog): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.publicacaoLog = publicacaoLog;
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
