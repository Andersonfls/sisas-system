import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Concurso } from './concurso.model';
import { ConcursoService } from './concurso.service';

@Injectable()
export class ConcursoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private concursoService: ConcursoService

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
                this.concursoService.find(id)
                    .subscribe((concursoResponse: HttpResponse<Concurso>) => {
                        const concurso: Concurso = concursoResponse.body;
                        if (concurso.dtLancamento) {
                            concurso.dtLancamento = {
                                year: concurso.dtLancamento.getFullYear(),
                                month: concurso.dtLancamento.getMonth() + 1,
                                day: concurso.dtLancamento.getDate()
                            };
                        }
                        if (concurso.dtUltimaAlteracao) {
                            concurso.dtUltimaAlteracao = {
                                year: concurso.dtUltimaAlteracao.getFullYear(),
                                month: concurso.dtUltimaAlteracao.getMonth() + 1,
                                day: concurso.dtUltimaAlteracao.getDate()
                            };
                        }
                        if (concurso.dtEntregaProposta) {
                            concurso.dtEntregaProposta = {
                                year: concurso.dtEntregaProposta.getFullYear(),
                                month: concurso.dtEntregaProposta.getMonth() + 1,
                                day: concurso.dtEntregaProposta.getDate()
                            };
                        }
                        if (concurso.dtAberturaProposta) {
                            concurso.dtAberturaProposta = {
                                year: concurso.dtAberturaProposta.getFullYear(),
                                month: concurso.dtAberturaProposta.getMonth() + 1,
                                day: concurso.dtAberturaProposta.getDate()
                            };
                        }
                        if (concurso.dtConclusaoAvaliacaoRelPrel) {
                            concurso.dtConclusaoAvaliacaoRelPrel = {
                                year: concurso.dtConclusaoAvaliacaoRelPrel.getFullYear(),
                                month: concurso.dtConclusaoAvaliacaoRelPrel.getMonth() + 1,
                                day: concurso.dtConclusaoAvaliacaoRelPrel.getDate()
                            };
                        }
                        if (concurso.dtNegociacao) {
                            concurso.dtNegociacao = {
                                year: concurso.dtNegociacao.getFullYear(),
                                month: concurso.dtNegociacao.getMonth() + 1,
                                day: concurso.dtNegociacao.getDate()
                            };
                        }
                        if (concurso.dtAprovRelAvalFinal) {
                            concurso.dtAprovRelAvalFinal = {
                                year: concurso.dtAprovRelAvalFinal.getFullYear(),
                                month: concurso.dtAprovRelAvalFinal.getMonth() + 1,
                                day: concurso.dtAprovRelAvalFinal.getDate()
                            };
                        }
                        this.ngbModalRef = this.concursoModalRef(component, concurso);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.concursoModalRef(component, new Concurso());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    concursoModalRef(component: Component, concurso: Concurso): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.concurso = concurso;
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
