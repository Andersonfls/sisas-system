import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Contrato } from './contrato.model';
import { ContratoService } from './contrato.service';

@Injectable()
export class ContratoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private contratoService: ContratoService

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
                this.contratoService.find(id)
                    .subscribe((contratoResponse: HttpResponse<Contrato>) => {
                        const contrato: Contrato = contratoResponse.body;
                        if (contrato.dtLancamento) {
                            contrato.dtLancamento = {
                                year: contrato.dtLancamento.getFullYear(),
                                month: contrato.dtLancamento.getMonth() + 1,
                                day: contrato.dtLancamento.getDate()
                            };
                        }
                        if (contrato.dtAssinatura) {
                            contrato.dtAssinatura = {
                                year: contrato.dtAssinatura.getFullYear(),
                                month: contrato.dtAssinatura.getMonth() + 1,
                                day: contrato.dtAssinatura.getDate()
                            };
                        }
                        if (contrato.dtFinalizacaoProcessoHomologAprov) {
                            contrato.dtFinalizacaoProcessoHomologAprov = {
                                year: contrato.dtFinalizacaoProcessoHomologAprov.getFullYear(),
                                month: contrato.dtFinalizacaoProcessoHomologAprov.getMonth() + 1,
                                day: contrato.dtFinalizacaoProcessoHomologAprov.getDate()
                            };
                        }
                        if (contrato.dtAdiantamento) {
                            contrato.dtAdiantamento = {
                                year: contrato.dtAdiantamento.getFullYear(),
                                month: contrato.dtAdiantamento.getMonth() + 1,
                                day: contrato.dtAdiantamento.getDate()
                            };
                        }
                        if (contrato.dtInicio) {
                            contrato.dtInicio = {
                                year: contrato.dtInicio.getFullYear(),
                                month: contrato.dtInicio.getMonth() + 1,
                                day: contrato.dtInicio.getDate()
                            };
                        }
                        if (contrato.dtRecepcaoProvisoria) {
                            contrato.dtRecepcaoProvisoria = {
                                year: contrato.dtRecepcaoProvisoria.getFullYear(),
                                month: contrato.dtRecepcaoProvisoria.getMonth() + 1,
                                day: contrato.dtRecepcaoProvisoria.getDate()
                            };
                        }
                        if (contrato.dtRecepcaoDefinitiva) {
                            contrato.dtRecepcaoDefinitiva = {
                                year: contrato.dtRecepcaoDefinitiva.getFullYear(),
                                month: contrato.dtRecepcaoDefinitiva.getMonth() + 1,
                                day: contrato.dtRecepcaoDefinitiva.getDate()
                            };
                        }
                        if (contrato.dtRecepcaoComicionamento) {
                            contrato.dtRecepcaoComicionamento = {
                                year: contrato.dtRecepcaoComicionamento.getFullYear(),
                                month: contrato.dtRecepcaoComicionamento.getMonth() + 1,
                                day: contrato.dtRecepcaoComicionamento.getDate()
                            };
                        }
                        this.ngbModalRef = this.contratoModalRef(component, contrato);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.contratoModalRef(component, new Contrato());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    contratoModalRef(component: Component, contrato: Contrato): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.contrato = contrato;
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
