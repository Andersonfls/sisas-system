import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OrigemFinanciamento } from './origem-financiamento.model';
import { OrigemFinanciamentoService } from './origem-financiamento.service';

@Injectable()
export class OrigemFinanciamentoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private origemFinanciamentoService: OrigemFinanciamentoService

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
                this.origemFinanciamentoService.find(id)
                    .subscribe((origemFinanciamentoResponse: HttpResponse<OrigemFinanciamento>) => {
                        const origemFinanciamento: OrigemFinanciamento = origemFinanciamentoResponse.body;
                        this.ngbModalRef = this.origemFinanciamentoModalRef(component, origemFinanciamento);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.origemFinanciamentoModalRef(component, new OrigemFinanciamento());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    origemFinanciamentoModalRef(component: Component, origemFinanciamento: OrigemFinanciamento): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.origemFinanciamento = origemFinanciamento;
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
