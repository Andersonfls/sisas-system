import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { IndicadorProducao } from './indicador-producao.model';
import { IndicadorProducaoService } from './indicador-producao.service';

@Injectable()
export class IndicadorProducaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private indicadorProducaoService: IndicadorProducaoService

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
                this.indicadorProducaoService.find(id)
                    .subscribe((indicadorProducaoResponse: HttpResponse<IndicadorProducao>) => {
                        const indicadorProducao: IndicadorProducao = indicadorProducaoResponse.body;
                        if (indicadorProducao.dtLancamento) {
                            indicadorProducao.dtLancamento = {
                                year: indicadorProducao.dtLancamento.getFullYear(),
                                month: indicadorProducao.dtLancamento.getMonth() + 1,
                                day: indicadorProducao.dtLancamento.getDate()
                            };
                        }
                        if (indicadorProducao.dtUltimaAlteracao) {
                            indicadorProducao.dtUltimaAlteracao = {
                                year: indicadorProducao.dtUltimaAlteracao.getFullYear(),
                                month: indicadorProducao.dtUltimaAlteracao.getMonth() + 1,
                                day: indicadorProducao.dtUltimaAlteracao.getDate()
                            };
                        }
                        this.ngbModalRef = this.indicadorProducaoModalRef(component, indicadorProducao);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.indicadorProducaoModalRef(component, new IndicadorProducao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    indicadorProducaoModalRef(component: Component, indicadorProducao: IndicadorProducao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.indicadorProducao = indicadorProducao;
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
