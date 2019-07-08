import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Execucao } from './execucao.model';
import { ExecucaoService } from './execucao.service';

@Injectable()
export class ExecucaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private execucaoService: ExecucaoService

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
                this.execucaoService.find(id)
                    .subscribe((execucaoResponse: HttpResponse<Execucao>) => {
                        const execucao: Execucao = execucaoResponse.body;
                        if (execucao.dtLancamento) {
                            execucao.dtLancamento = {
                                year: execucao.dtLancamento.getFullYear(),
                                month: execucao.dtLancamento.getMonth() + 1,
                                day: execucao.dtLancamento.getDate()
                            };
                        }
                        if (execucao.dtPeridoReferencia) {
                            execucao.dtPeridoReferencia = {
                                year: execucao.dtPeridoReferencia.getFullYear(),
                                month: execucao.dtPeridoReferencia.getMonth() + 1,
                                day: execucao.dtPeridoReferencia.getDate()
                            };
                        }
                        if (execucao.dtFimReferencia) {
                            execucao.dtFimReferencia = {
                                year: execucao.dtFimReferencia.getFullYear(),
                                month: execucao.dtFimReferencia.getMonth() + 1,
                                day: execucao.dtFimReferencia.getDate()
                            };
                        }
                        if (execucao.dtFactura) {
                            execucao.dtFactura = {
                                year: execucao.dtFactura.getFullYear(),
                                month: execucao.dtFactura.getMonth() + 1,
                                day: execucao.dtFactura.getDate()
                            };
                        }
                        this.ngbModalRef = this.execucaoModalRef(component, execucao);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.execucaoModalRef(component, new Execucao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    execucaoModalRef(component: Component, execucao: Execucao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.execucao = execucao;
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
