import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { NoticiasPortal } from './noticias-portal.model';
import { NoticiasPortalService } from './noticias-portal.service';

@Injectable()
export class NoticiasPortalPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private noticiasPortalService: NoticiasPortalService

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
                this.noticiasPortalService.find(id)
                    .subscribe((noticiasPortalResponse: HttpResponse<NoticiasPortal>) => {
                        const noticiasPortal: NoticiasPortal = noticiasPortalResponse.body;
                        if (noticiasPortal.dataCriacao) {
                            noticiasPortal.dataCriacao = {
                                year: noticiasPortal.dataCriacao.getFullYear(),
                                month: noticiasPortal.dataCriacao.getMonth() + 1,
                                day: noticiasPortal.dataCriacao.getDate()
                            };
                        }
                        if (noticiasPortal.dataAlteracao) {
                            noticiasPortal.dataAlteracao = {
                                year: noticiasPortal.dataAlteracao.getFullYear(),
                                month: noticiasPortal.dataAlteracao.getMonth() + 1,
                                day: noticiasPortal.dataAlteracao.getDate()
                            };
                        }
                        this.ngbModalRef = this.noticiasPortalModalRef(component, noticiasPortal);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    const nt = new NoticiasPortal();
                    nt.status = true;

                    const date = new Date();
                    nt.dataCriacao = {
                        year: date.getFullYear(),
                        month: date.getMonth(),
                        day: date.getDay()
                    };

                    this.ngbModalRef = this.noticiasPortalModalRef(component, nt);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    noticiasPortalModalRef(component: Component, noticiasPortal: NoticiasPortal): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.noticiasPortal = noticiasPortal;
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
