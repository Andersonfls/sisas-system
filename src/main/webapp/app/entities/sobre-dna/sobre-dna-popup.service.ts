import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SobreDna } from './sobre-dna.model';
import { SobreDnaService } from './sobre-dna.service';

@Injectable()
export class SobreDnaPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sobreDnaService: SobreDnaService

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
                this.sobreDnaService.find(id)
                    .subscribe((sobreDnaResponse: HttpResponse<SobreDna>) => {
                        const sobreDna: SobreDna = sobreDnaResponse.body;
                        this.ngbModalRef = this.sobreDnaModalRef(component, sobreDna);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.sobreDnaModalRef(component, new SobreDna());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    sobreDnaModalRef(component: Component, sobreDna: SobreDna): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.sobreDna = sobreDna;
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
