import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Contactos } from './contactos.model';
import { ContactosService } from './contactos.service';

@Injectable()
export class ContactosPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private contactosService: ContactosService

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
                this.contactosService.find(id)
                    .subscribe((contactosResponse: HttpResponse<Contactos>) => {
                        const contactos: Contactos = contactosResponse.body;
                        if (contactos.dtLancamento) {
                            contactos.dtLancamento = {
                                year: contactos.dtLancamento.getFullYear(),
                                month: contactos.dtLancamento.getMonth() + 1,
                                day: contactos.dtLancamento.getDate()
                            };
                        }
                        if (contactos.dtUltimaAlteracao) {
                            contactos.dtUltimaAlteracao = {
                                year: contactos.dtUltimaAlteracao.getFullYear(),
                                month: contactos.dtUltimaAlteracao.getMonth() + 1,
                                day: contactos.dtUltimaAlteracao.getDate()
                            };
                        }
                        this.ngbModalRef = this.contactosModalRef(component, contactos);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.contactosModalRef(component, new Contactos());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    contactosModalRef(component: Component, contactos: Contactos): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.contactos = contactos;
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
