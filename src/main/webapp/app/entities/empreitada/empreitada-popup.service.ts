import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Empreitada } from './empreitada.model';
import { EmpreitadaService } from './empreitada.service';

@Injectable()
export class EmpreitadaPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private empreitadaService: EmpreitadaService

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
                this.empreitadaService.find(id)
                    .subscribe((empreitadaResponse: HttpResponse<Empreitada>) => {
                        const empreitada: Empreitada = empreitadaResponse.body;
                        if (empreitada.dtLancamento) {
                            empreitada.dtLancamento = {
                                year: empreitada.dtLancamento.getFullYear(),
                                month: empreitada.dtLancamento.getMonth() + 1,
                                day: empreitada.dtLancamento.getDate()
                            };
                        }
                        this.ngbModalRef = this.empreitadaModalRef(component, empreitada);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.empreitadaModalRef(component, new Empreitada());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    empreitadaModalRef(component: Component, empreitada: Empreitada): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.empreitada = empreitada;
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
