import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ProgramasProjectos } from './programas-projectos.model';
import { ProgramasProjectosService } from './programas-projectos.service';

@Injectable()
export class ProgramasProjectosPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private programasProjectosService: ProgramasProjectosService

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
                this.programasProjectosService.find(id)
                    .subscribe((programasProjectosResponse: HttpResponse<ProgramasProjectos>) => {
                        const programasProjectos: ProgramasProjectos = programasProjectosResponse.body;
                        if (programasProjectos.dtLancamento) {
                            programasProjectos.dtLancamento = {
                                year: programasProjectos.dtLancamento.getFullYear(),
                                month: programasProjectos.dtLancamento.getMonth() + 1,
                                day: programasProjectos.dtLancamento.getDate()
                            };
                        }
                        if (programasProjectos.dtUltimaAlteracao) {
                            programasProjectos.dtUltimaAlteracao = {
                                year: programasProjectos.dtUltimaAlteracao.getFullYear(),
                                month: programasProjectos.dtUltimaAlteracao.getMonth() + 1,
                                day: programasProjectos.dtUltimaAlteracao.getDate()
                            };
                        }
                        this.ngbModalRef = this.programasProjectosModalRef(component, programasProjectos);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.programasProjectosModalRef(component, new ProgramasProjectos());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    programasProjectosModalRef(component: Component, programasProjectos: ProgramasProjectos): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.programasProjectos = programasProjectos;
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
