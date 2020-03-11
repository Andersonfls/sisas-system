import {Component, OnInit, OnDestroy, ViewChild} from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import {JhiEventManager, JhiAlertService} from 'ng-jhipster';

import {ITEMS_PER_PAGE, Principal} from '../../shared';
import {ArquivosPortal} from '../cadastro-pdf';
import {SistemaAgua, SistemaAguaService} from '../sistema-agua';
import {Observable} from 'rxjs';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
    selector: 'jhi-sis-arquivo',
    templateUrl: './sistemaAguaArquivo.component.html',
    styleUrls: ['./sistemaAguaArquivo.css']
})
export class SistemaAguaArquivoComponent implements OnInit, OnDestroy {

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
    sistema: SistemaAgua;

    arquivo: ArquivosPortal;
    isSaving: boolean;
    selectedFile: File;
    @ViewChild('inputFile')
    inputFile: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sistemaAguaService: SistemaAguaService,
        private eventManager: JhiEventManager,
        private principal: Principal,
    ) {
        this.produtos = [];
        this.produtosFilt = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.predicate = 'id';
        this.reverse = true;
    }

    loadAll() {
    }

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
            this.sistema = Object.assign({}, data);
            this.sistema.dtLancamento = new Date();
            this.sistema.dtUltimaAlteracao = new Date();
            console.log(this.sistema);
            this.sistema.id = null;

            this.subscribeToSaveResponse(
                this.sistemaAguaService.createFromArquivo(this.sistema));
        };
        reader.readAsText(this.selectedFile, 'UTF-8');

        alert('Dados Inseridos com sucesso!');
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SistemaAgua>>) {
        result.subscribe((res: HttpResponse<SistemaAgua>) => {
                this.onSaveSuccess(res.body);
                console.log(res.body);
        }, (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SistemaAgua) {
        this.eventManager.broadcast({name: 'sistemaAguaListModification', content: 'OK'});
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
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
