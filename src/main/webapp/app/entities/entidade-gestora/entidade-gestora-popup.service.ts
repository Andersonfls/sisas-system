import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { EntidadeGestora } from './entidade-gestora.model';
import { EntidadeGestoraService } from './entidade-gestora.service';

@Injectable()
export class EntidadeGestoraPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entidadeGestoraService: EntidadeGestoraService

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
                this.entidadeGestoraService.find(id)
                    .subscribe((entidadeGestoraResponse: HttpResponse<EntidadeGestora>) => {
                        const entidadeGestora: EntidadeGestora = entidadeGestoraResponse.body;
                        if (entidadeGestora.dtConstituicao) {
                            entidadeGestora.dtConstituicao = {
                                year: entidadeGestora.dtConstituicao.getFullYear(),
                                month: entidadeGestora.dtConstituicao.getMonth() + 1,
                                day: entidadeGestora.dtConstituicao.getDate()
                            };
                        }
                        this.ngbModalRef = this.entidadeGestoraModalRef(component, entidadeGestora);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.entidadeGestoraModalRef(component, new EntidadeGestora());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    entidadeGestoraModalRef(component: Component, entidadeGestora: EntidadeGestora): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entidadeGestora = entidadeGestora;
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
