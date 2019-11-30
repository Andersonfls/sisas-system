import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { FinalidadeProjeto } from './finalidade-projeto.model';
import {FinalidadeProjetoService} from './finalidade-projeto.service';

@Injectable()
export class FinalidadeProjetoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private finalidadeProjetoService: FinalidadeProjetoService

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
                this.finalidadeProjetoService.find(id)
                    .subscribe((finalidadeProjetoResponse: HttpResponse<FinalidadeProjeto>) => {
                        const finalidadeProjeto: FinalidadeProjeto = finalidadeProjetoResponse.body;
                        this.ngbModalRef = this.finalidadeProjetoModalRef(component, finalidadeProjeto);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.finalidadeProjetoModalRef(component, new FinalidadeProjeto());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    finalidadeProjetoModalRef(component: Component, finalidadeProjeto: FinalidadeProjeto): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.finalidadeProjeto = finalidadeProjeto;
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
