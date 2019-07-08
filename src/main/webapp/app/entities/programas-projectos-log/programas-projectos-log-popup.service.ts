import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ProgramasProjectosLog } from './programas-projectos-log.model';
import { ProgramasProjectosLogService } from './programas-projectos-log.service';

@Injectable()
export class ProgramasProjectosLogPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private programasProjectosLogService: ProgramasProjectosLogService

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
                this.programasProjectosLogService.find(id)
                    .subscribe((programasProjectosLogResponse: HttpResponse<ProgramasProjectosLog>) => {
                        const programasProjectosLog: ProgramasProjectosLog = programasProjectosLogResponse.body;
                        if (programasProjectosLog.dtLog) {
                            programasProjectosLog.dtLog = {
                                year: programasProjectosLog.dtLog.getFullYear(),
                                month: programasProjectosLog.dtLog.getMonth() + 1,
                                day: programasProjectosLog.dtLog.getDate()
                            };
                        }
                        this.ngbModalRef = this.programasProjectosLogModalRef(component, programasProjectosLog);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.programasProjectosLogModalRef(component, new ProgramasProjectosLog());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    programasProjectosLogModalRef(component: Component, programasProjectosLog: ProgramasProjectosLog): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.programasProjectosLog = programasProjectosLog;
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
