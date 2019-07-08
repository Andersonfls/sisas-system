import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { MunicipiosAtendidos } from './municipios-atendidos.model';
import { MunicipiosAtendidosService } from './municipios-atendidos.service';

@Injectable()
export class MunicipiosAtendidosPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private municipiosAtendidosService: MunicipiosAtendidosService

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
                this.municipiosAtendidosService.find(id)
                    .subscribe((municipiosAtendidosResponse: HttpResponse<MunicipiosAtendidos>) => {
                        const municipiosAtendidos: MunicipiosAtendidos = municipiosAtendidosResponse.body;
                        this.ngbModalRef = this.municipiosAtendidosModalRef(component, municipiosAtendidos);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.municipiosAtendidosModalRef(component, new MunicipiosAtendidos());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    municipiosAtendidosModalRef(component: Component, municipiosAtendidos: MunicipiosAtendidos): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.municipiosAtendidos = municipiosAtendidos;
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
