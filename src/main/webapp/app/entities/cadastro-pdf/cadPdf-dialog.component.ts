import {Component, OnInit, OnDestroy, ElementRef, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CadPdfService } from './cadPdf.service';
import {UploadFileService} from './upload-file.service';
import {FileResponseModel} from './uploadFileResponse.model';
import {ItemAssinatura} from './item-assinatura.model';
import {Banner} from './cadPdf.model';
import {CadPdfPopupService} from './cadPdf-popup.service';

@Component({
    selector: 'jhi-produto-dialog',
    templateUrl: './cadPdf-dialog.component.html'
})
export class CadPdfDialogComponent implements OnInit {

    produto: Banner;
    isSaving: boolean;
    selectedFile: File;
    @ViewChild('inputFile')
    inputFile: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private bannerService: CadPdfService,
        private elementRef: ElementRef,
        private uploadService: UploadFileService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);

        if (this.produto.contentType === null || this.produto.contentType === undefined) {
            this.produto.contentType = 'image/png';
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.produto, this.elementRef, field, fieldContentType, idInput);
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;

        if (this.produto.contentType === null || this.produto.contentType === undefined) {
            this.produto.contentType = 'image/png';
        }

        if (this.produto.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bannerService.update(this.produto));
        } else {
            this.subscribeToSaveResponse(
                this.bannerService.create(this.produto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Banner>>) {
        result.subscribe((res: HttpResponse<Banner>) => {
                this.onSaveSuccess(res.body);
                this.upload(res.body.id);
            }
            , (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Banner) {
        this.eventManager.broadcast({ name: 'produtoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    compareItem(item: ItemAssinatura, target?: ItemAssinatura): boolean {
        if (target) {
            return item.id === target.id;
        }
        return false;
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

                    this.produto.uri = file.fileDownloadUri;
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    private onError(error: any) {
        this.produto.uri = null;
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
