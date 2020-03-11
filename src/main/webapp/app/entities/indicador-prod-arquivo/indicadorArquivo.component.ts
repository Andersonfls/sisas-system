import {Component, OnInit, OnDestroy, ViewChild} from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import {JhiEventManager, JhiAlertService, JhiDateUtils} from 'ng-jhipster';

import {ITEMS_PER_PAGE, Principal} from '../../shared';
import {ArquivosPortal} from '../cadastro-pdf';
import {IndicadorProducao, IndicadorProducaoService} from '../indicador-producao';
import {DatePipe} from '@angular/common';

@Component({
    selector: 'jhi-produto',
    templateUrl: './indicadorArquivo.component.html',
    styleUrls: ['./indicadorArquivo.css']
})
export class IndicadorArquivoComponent implements OnInit, OnDestroy {

    produtos: ArquivosPortal[];
    produtosFilt: ArquivosPortal[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    reverse: any;
    totalItems: number;
    indicador: IndicadorProducao;

    arquivo: ArquivosPortal;
    isSaving: boolean;
    selectedFile: File;
    @ViewChild('inputFile')
    inputFile: any;
    datepipe = new DatePipe('en-US');

    constructor(
        private jhiAlertService: JhiAlertService,
        private indicadorProducaoService: IndicadorProducaoService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private dateUtils: JhiDateUtils
    ) {
        this.produtos = [];
        this.produtosFilt = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.predicate = 'id';
        this.reverse = true;
    }

    loadAll() {
        // this.bannerService.query({
        //     page: this.page,
        //     size: this.itemsPerPage,
        //     sort: this.sort()
        // }).subscribe(
        //     (res: HttpResponse<ArquivosPortal[]>) => {
        //         console.log(res.body);
        //         this.produtosFilt = res.body;
        //
        //     }, (res: HttpErrorResponse) => this.onError(res.message)
        // );
    }

    // upload(id: number) {
    //     if (this.selectedFile) {
    //         const arrayBlob = new Array<Blob>();
    //         arrayBlob.push(this.selectedFile);
    //         const novoArquivo = new File(arrayBlob, String(id) + '.pdf');
    //         this.uploadService.pushFileToStorage(novoArquivo).subscribe((event) => {
    //             if (event instanceof HttpResponse) {
    //                 console.log('File is completely uploaded!');
    //                 const jsonObj: any = JSON.parse(event.body.toString());
    //                 const file: FileResponseModel = <FileResponseModel>jsonObj;
    //             }
    //         }, (res: HttpErrorResponse) => this.onError(res.message));
    //     }
    // }

    resetInputFile() {
        this.inputFile.nativeElement.value = '';
        console.log(this.inputFile.nativeElement.files);
    }

    selectFile(event) {
        const file = event.target.files.item(0);
        this.selectedFile = event.target.files.item(0);
    }

    save() {
        const reader = new FileReader();
        reader.onload = (e) => {
            console.log(reader.result);
            const data = JSON.parse(reader.result);
            this.indicador = Object.assign({}, data);
            this.indicador.dtLancamento = new Date();
            this.indicador.dtUltimaAlteracao = new Date();
            console.log(this.indicador);
            this.indicador.id = null;
            this.indicadorProducaoService.createFromArquivo(this.indicador);
        };
        reader.readAsText(this.selectedFile, 'UTF-8');

        alert('Dados Inseridos com sucesso!');
    }

    reset() {
        this.page = 0;
        this.produtos = [];
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.arquivo = new ArquivosPortal();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInProdutos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    previousState() {
        window.history.back();
    }

    registerChangeInProdutos() {
        this.eventSubscriber = this.eventManager.subscribe('produtoListModification', (response) => this.reset());
    }

}
