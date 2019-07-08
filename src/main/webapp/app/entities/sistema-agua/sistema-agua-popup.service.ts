import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SistemaAgua } from './sistema-agua.model';
import { SistemaAguaService } from './sistema-agua.service';

@Injectable()
export class SistemaAguaPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sistemaAguaService: SistemaAguaService

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
                this.sistemaAguaService.find(id)
                    .subscribe((sistemaAguaResponse: HttpResponse<SistemaAgua>) => {
                        const sistemaAgua: SistemaAgua = sistemaAguaResponse.body;
                        if (sistemaAgua.dtLancamento) {
                            sistemaAgua.dtLancamento = {
                                year: sistemaAgua.dtLancamento.getFullYear(),
                                month: sistemaAgua.dtLancamento.getMonth() + 1,
                                day: sistemaAgua.dtLancamento.getDate()
                            };
                        }
                        if (sistemaAgua.dtUltimaAlteracao) {
                            sistemaAgua.dtUltimaAlteracao = {
                                year: sistemaAgua.dtUltimaAlteracao.getFullYear(),
                                month: sistemaAgua.dtUltimaAlteracao.getMonth() + 1,
                                day: sistemaAgua.dtUltimaAlteracao.getDate()
                            };
                        }
                        this.ngbModalRef = this.sistemaAguaModalRef(component, sistemaAgua);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.sistemaAguaModalRef(component, new SistemaAgua());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    sistemaAguaModalRef(component: Component, sistemaAgua: SistemaAgua): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.sistemaAgua = sistemaAgua;
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
