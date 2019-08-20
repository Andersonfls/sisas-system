import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Subscription} from 'rxjs';
// import {Assinatura, AssinaturaService} from '../../../entities/assinatura';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {ITEMS_PER_PAGE} from '../../../shared';
// import {ItemAssinatura, ItemAssinaturaService} from '../../../entities/item-assinatura';
// import * as XLSX from 'xlsx';
import {CoberturaServicos} from '../coberturaServicos.model';
//
// @Component({
//     selector: 'jhi-dashboard-vencimentos',
//     templateUrl: './vencimentos.component.html',
//     styleUrls: [
//     ]
// })
// export class DashboardVencimentosComponent implements OnInit {
//
//     total: any;
//     data: any;
//     pt: any;
//     subscription: Subscription;
//     assinaturas: Assinatura[];
//     page: any;
//     itemsPerPage: number;
//     predicate: any;
//     reverse: any;
//     totalItems: number;
//     links: any;
//     esconderFiltros: boolean;
//     itensAssinatura: ItemAssinatura[];
//     revista: number;
//     status: number;
//     @ViewChild('table') table: ElementRef;
//
//     coberturas: CoberturaServicos[];
//
//     constructor(
//         private assinaturaService: AssinaturaService,
//         private jhiAlertService: JhiAlertService,
//         private itemAssinaturaService: ItemAssinaturaService,
//         private parseLinks: JhiParseLinks
//     ) {
//         this.assinaturas = [];
//         this.itemsPerPage = ITEMS_PER_PAGE;
//         this.page = 0;
//         this.links = {
//             last: 0
//         };
//         this.predicate = 'dataVencimento';
//         this.reverse = true;
//         this.esconderFiltros = true;
//     }
//
//     ngOnInit() {
//         this.assinaturaService.query({
//             page: this.page,
//             size: this.itemsPerPage,
//             sort: this.sort()
//         }).subscribe(
//             (res: HttpResponse<Assinatura[]>) => this.onSuccess(res.body, res.headers),
//             (res: HttpErrorResponse) => this.onError(res.message)
//         );
//         this.itemAssinaturaService.query()
//             .subscribe((res: HttpResponse<ItemAssinatura[]>) => { this.itensAssinatura = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
//     }
//
//     exportAsExcel() {
//         const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(this.table.nativeElement);
//         const wb: XLSX.WorkBook = XLSX.utils.book_new();
//         XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
//
//         XLSX.writeFile(wb, 'RelatorioVencimentos.xlsx');
//     }
//
//     sort() {
//         const result = [this.predicate + ',' + (this.reverse ? 'desc' : 'asc')];
//         if (this.predicate !== 'id') {
//             result.push('id');
//         }
//         return result;
//     }
//
//     reset() {
//         this.page = 0;
//         this.assinaturas = [];
//     }
//
//     pesquisar() {
//         if (this.revista === undefined) {
//             this.revista = null;
//         }
//
//         if (this.status === undefined) {
//             this.status = null;
//         }
//         this.assinaturaService.pesquisa(
//             {
//                 page: this.page - 1,
//                 size: this.itemsPerPage,
//                 sort: this.sort(),
//                 categoriaId: this.revista,
//                 status: this.status,
//             }
//         ).subscribe(
//             (retornados: HttpResponse<Assinatura[]>) => {
//                 console.log(retornados);
//                 this.assinaturas = retornados.body;
//             }
//         );
//     }
//
//     private onSuccess(data, headers) {
//         this.links = this.parseLinks.parse(headers.get('link'));
//         this.totalItems = headers.get('X-Total-Count');
//         for (let i = 0; i < data.length; i++) {
//             this.assinaturas.push(data[i]);
//         }
//     }
//
//     private onError(error) {
//         this.jhiAlertService.error(error.message, null, null);
//     }
//
// }
