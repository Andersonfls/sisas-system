import {Component, OnInit, OnDestroy, ElementRef, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CadPdfService } from './cadPdf.service';
import {UploadFileService} from './upload-file.service';
import {FileResponseModel} from './uploadFileResponse.model';
import {ArquivosPortal} from './cadPdf.model';
import {CadPdfPopupService} from './cadPdf-popup.service';

@Component({
    selector: 'jhi-produto-dialog',
    templateUrl: './cadPdf-dialog.component.html'
})
export class CadPdfDialogComponent implements OnInit {

    arquivo: ArquivosPortal;
    isSaving: boolean;
    selectedFile: File;
    @ViewChild('inputFile')
    inputFile: any;
    tipos: string[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private cadPdfService: CadPdfService,
        private elementRef: ElementRef,
        private uploadService: UploadFileService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.montarListaTipos();
    }

    montarListaTipos() {
        this.tipos = new Array();
        this.tipos.push('PUBLICACÕES PÁGINA INICIAL');
        this.tipos.push('PUBLICACÕES');
        this.tipos.push('PROJECTOS');
        this.tipos.push('OUTROS');
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.arquivo, this.elementRef, field, fieldContentType, idInput);
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        console.log(this.arquivo);
        if (this.arquivo.id !== undefined) {
            console.log('UPDATE');
            this.subscribeToSaveResponse(
                this.cadPdfService.update(this.arquivo));
        } else {
            console.log('CREATE');
            this.subscribeToSaveResponse(
                this.cadPdfService.create(this.arquivo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ArquivosPortal>>) {
        result.subscribe((res: HttpResponse<ArquivosPortal>) => {
                this.onSaveSuccess(res.body);
                this.upload(res.body.id);
            }
            , (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ArquivosPortal) {
        this.eventManager.broadcast({ name: 'produtoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    resetInputFile() {
        this.inputFile.nativeElement.value = '';
        console.log(this.inputFile.nativeElement.files);
    }

    selectFile(event) {
        const file = event.target.files.item(0);

        if (file.type.match('audio.*|video.*')) {
            alert('formato Inválido!');
            this.resetInputFile();
        } else {
            this.selectedFile = event.target.files.item(0);
        }
    }

    upload(id: number) {
        if (this.selectedFile) {
            const arrayBlob = new Array<Blob>();
            arrayBlob.push(this.selectedFile);
            const novoArquivo = new File(arrayBlob, String(id) + '.pdf');
            this.uploadService.pushFileToStorage(novoArquivo).subscribe((event) => {
                if (event instanceof HttpResponse) {
                    console.log('File is completely uploaded!');
                    const jsonObj: any = JSON.parse(event.body.toString());
                    const file: FileResponseModel = <FileResponseModel>jsonObj;
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    private onError(error: any) {
    }
}

@Component({
    selector: 'jhi-produto-popup',
    template: ''
})
export class ProdutoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produtoPopupService: CadPdfPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.produtoPopupService
                    .open(CadPdfDialogComponent as Component, params['id']);
            } else {
                this.produtoPopupService
                    .open(CadPdfDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
