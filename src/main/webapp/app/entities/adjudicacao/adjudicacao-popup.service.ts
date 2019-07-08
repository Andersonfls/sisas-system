import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Adjudicacao } from './adjudicacao.model';
import { AdjudicacaoService } from './adjudicacao.service';

@Injectable()
export class AdjudicacaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private adjudicacaoService: AdjudicacaoService

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
                this.adjudicacaoService.find(id)
                    .subscribe((adjudicacaoResponse: HttpResponse<Adjudicacao>) => {
                        const adjudicacao: Adjudicacao = adjudicacaoResponse.body;
                        if (adjudicacao.dtLancamento) {
                            adjudicacao.dtLancamento = {
                                year: adjudicacao.dtLancamento.getFullYear(),
                                month: adjudicacao.dtLancamento.getMonth() + 1,
                                day: adjudicacao.dtLancamento.getDate()
                            };
                        }
                        if (adjudicacao.dtComunicaoAdjudicacao) {
                            adjudicacao.dtComunicaoAdjudicacao = {
                                year: adjudicacao.dtComunicaoAdjudicacao.getFullYear(),
                                month: adjudicacao.dtComunicaoAdjudicacao.getMonth() + 1,
                                day: adjudicacao.dtComunicaoAdjudicacao.getDate()
                            };
                        }
                        if (adjudicacao.dtPrestacaoGarantBoaExec) {
                            adjudicacao.dtPrestacaoGarantBoaExec = {
                                year: adjudicacao.dtPrestacaoGarantBoaExec.getFullYear(),
                                month: adjudicacao.dtPrestacaoGarantBoaExec.getMonth() + 1,
                                day: adjudicacao.dtPrestacaoGarantBoaExec.getDate()
                            };
                        }
                        if (adjudicacao.dtSubmissaoMinutContrato) {
                            adjudicacao.dtSubmissaoMinutContrato = {
                                year: adjudicacao.dtSubmissaoMinutContrato.getFullYear(),
                                month: adjudicacao.dtSubmissaoMinutContrato.getMonth() + 1,
                                day: adjudicacao.dtSubmissaoMinutContrato.getDate()
                            };
                        }
                        this.ngbModalRef = this.adjudicacaoModalRef(component, adjudicacao);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.adjudicacaoModalRef(component, new Adjudicacao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    adjudicacaoModalRef(component: Component, adjudicacao: Adjudicacao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.adjudicacao = adjudicacao;
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
