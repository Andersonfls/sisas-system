import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Concepcao } from './concepcao.model';
import { ConcepcaoService } from './concepcao.service';

@Injectable()
export class ConcepcaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private concepcaoService: ConcepcaoService

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
                this.concepcaoService.find(id)
                    .subscribe((concepcaoResponse: HttpResponse<Concepcao>) => {
                        const concepcao: Concepcao = concepcaoResponse.body;
                        if (concepcao.dtLancamento) {
                            concepcao.dtLancamento = {
                                year: concepcao.dtLancamento.getFullYear(),
                                month: concepcao.dtLancamento.getMonth() + 1,
                                day: concepcao.dtLancamento.getDate()
                            };
                        }
                        if (concepcao.dtUltimaAlteracao) {
                            concepcao.dtUltimaAlteracao = {
                                year: concepcao.dtUltimaAlteracao.getFullYear(),
                                month: concepcao.dtUltimaAlteracao.getMonth() + 1,
                                day: concepcao.dtUltimaAlteracao.getDate()
                            };
                        }
                        if (concepcao.dtElaboracaoCon) {
                            concepcao.dtElaboracaoCon = {
                                year: concepcao.dtElaboracaoCon.getFullYear(),
                                month: concepcao.dtElaboracaoCon.getMonth() + 1,
                                day: concepcao.dtElaboracaoCon.getDate()
                            };
                        }
                        if (concepcao.dtAprovacaoCon) {
                            concepcao.dtAprovacaoCon = {
                                year: concepcao.dtAprovacaoCon.getFullYear(),
                                month: concepcao.dtAprovacaoCon.getMonth() + 1,
                                day: concepcao.dtAprovacaoCon.getDate()
                            };
                        }
                        this.ngbModalRef = this.concepcaoModalRef(component, concepcao);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.concepcaoModalRef(component, new Concepcao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    concepcaoModalRef(component: Component, concepcao: Concepcao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.concepcao = concepcao;
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
