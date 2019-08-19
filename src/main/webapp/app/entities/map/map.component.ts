import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Principal} from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {ActivatedRoute} from '@angular/router';
import {Municipio, MunicipioService} from '../municipio';
import {Comuna, ComunaService} from '../comuna';

@Component({
    selector: 'jhi-map',
    templateUrl: './map.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class MapComponent implements OnInit {
    municipios: Municipio[];
    comunas: Comuna[];
    currentAccount: any;
    routeData: any;

    page: any;
    previousPage: any;

    links: any;
    totalItems: any;
    queryCount: any;

    reverse: any;
    predicate: any;

    reservasUser: number;
    emprestimosUser: number;
    devolucoesUser: number;

    /*Dashboard = ROLE_ADMIN*/
    usersAdmin: number;
    obrasAdmin: number;
    emprestimosAdmin: number;
    reservasAdmin: number;

    mostrar: boolean;

    @ViewChild('chartBen') table: ElementRef;

    public pieColors: any[] = [
        {
            backgroundColor: ['#613b18', '#b87524', '#fb9e37']
        }];
    public lineChartType = 'line';
    public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    public barChartOptionsEmprestimo: any = {
        scaleShowVerticalLines: false,
        responsive: true
    };
    public donutColors: any[] = [
        {
            backgroundColor: ['#613b18', '#b87524', '#fb9e37']
        }];
    public pieChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales'];
    public pieChartData: number[] = [300, 500, 100];
    public pieChartType = 'pie';
    public doughnutChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
    public doughnutChartData: number[] = [350, 450, 100];
    public doughnutChartType = 'doughnut';
    public barChartLabelsReserva: string[] = ['JAN', 'FEV', 'MAR', 'ABR' ];
    public barChartTypeReserva = 'bar';
    public barChartLegendReserva = true;
    public barChartDataReseva: any[] = [
        {data: [68, 48, 80, 100], label: 'Reservas'}
    ];
    public barChartLabelsEmprestimo: string[] = ['JAN', 'FEV', 'MAR', 'ABR' ];
    public barChartTypeEmprestimo = 'bar';
    public barChartLegendEmprestimo = true;
    public barChartDataEmprestimo: any[] = [
        {data: [65, 59, 80, 100], label: 'Empréstimos'}
    ];

    public barColorsEmprestimo: any[] = [
        {
            backgroundColor: ['#967138', '#967138', '#967138', '#967138']
        }];

    public barChartOptionsReserva: any = {
        scaleShowVerticalLines: false,
        responsive: true
    };

    public barColorsReserva: any[] = [
        {
            backgroundColor: ['#978138', '#978138', '#978138', '#978138']
        }];

    public barColorsLine: any[] = [
        {
            backgroundColor: ['#1C502F', '#007bff']
        }];

    public lineChartData: Array<any> = [
        [65, 59, 80, 81, 56, 55, 40],
        [28, 48, 40, 19, 86, 27, 90]
    ];

    constructor(
        private municipioService: MunicipioService,
        private comunaService: ComunaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
    ) {
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        // this.carregarChart();
        // this.carregarChartBeneficiarios();
        // this.carregarBarChar();
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    carregaMunicipios() {
        this.mostrar = !this.mostrar;
        this.municipioService.query({
            page: this.page - 1,
            sort: this.sort()
        }).subscribe(
            (res: HttpResponse<Municipio[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.municipios = data;
    }

    carregarChartBeneficiarios() {
        const chart = new CanvasJS.Chart('chartContainer', {
            animationEnabled: true,
            title: {
                text: 'Cobertura de Serviços de Água e Saneamento'
            },
            axisY: {
                title: 'Beneficiários Água',
                titleFontColor: '#4F81BC',
                lineColor: '#4F81BC',
                labelFontColor: '#4F81BC',
                tickColor: '#4F81BC'
            },
            axisY2: {
                title: 'Beneficiários Saneamento',
                titleFontColor: '#C0504E',
                lineColor: '#C0504E',
                labelFontColor: '#C0504E',
                tickColor: '#C0504E'
            },
            toolTip: {
                shared: true
            },
            legend: {
                cursor: 'pointer',
            },
            data: [{
                type: 'column',
                name: 'Beneficiarios Água',
                legendText: 'Beneficiários de Serviços de água',
                showInLegend: true,
                toolTipContent: '<b>{name}</b>: ${y} (#percent%)',
                dataPoints: [
                    { label: 'Nacional', y: 30 },
                    { label: 'Urbana', y: 21 },
                    { label: 'Rural', y: 16 },
                    { label: 'Outros', y: 4 }
                ]
            },
                {
                    type: 'column',
                    name: 'Beneficiários de Serviços de saneamento)',
                    legendText: 'Beneficiários de Serviços de água',
                    axisYType: 'secondary',
                    showInLegend: true,
                    dataPoints: [
                        { label: 'Nacional', y: 30 },
                        { label: 'Urbana', y: 41 },
                        { label: 'Rural', y: 26 },
                        { label: 'Outros', y: 3 }
                    ]
                }]
        });
        chart.render();

    }

    /*--------Canvas Gráficos-------*/
    carregarChart() {
        const chart = new CanvasJS.Chart('chartContainer', {
            theme: 'light2',
            animationEnabled: true,
            exportEnabled: true,
            title: {
                text: 'Empréstimos'
            },
            data: [{
                type: 'pie',
                showInLegend: true,
                toolTipContent: '<b>{name}</b>: ${y} (#percent%)',
                indexLabel: '{name} - #percent%',
                dataPoints: [
                    { y: 450, name: 'Food' },
                    { y: 120, name: 'Insurance' },
                    { y: 300, name: 'Traveling' },
                ]
            }]
        });

        chart.render();
    }

    carregarBarChar() {
        const chart = new CanvasJS.Chart('chartContainer2', {
            animationEnabled: true,
            exportEnabled: true,
            title: {
                text: 'Empréstimos'
            },
            data: [{
                type: 'column',
                dataPoints: [
                    { y: 71, label: '2016' },
                    { y: 55, label: '2017' },
                    { y: 50, label: '2018' },
                    { y: 65, label: '2019' },
                ]
            }]
        });

        chart.render();
    }
    /*------------------------------*/

    /*-----------------------------------------BarChart-Emprestimo-------------------------------------*/

    public chartClickedEmprestimo(e: any): void {
        console.log(e);
    }
    public chartHoveredEmprestimo(e: any): void {
        console.log(e);
    }
    public randomizeEmprestimo(): void {
        // Only Change 3 values
        const data = [
            Math.round(Math.random() * 100),
            59,
            80,
            (Math.random() * 100),
            56,
            (Math.random() * 100),
            40];
        const clone = JSON.parse(JSON.stringify(this.barChartDataEmprestimo));
        clone[0].data = data;
        this.barChartDataEmprestimo = clone;
        /**
         * (My guess), for Angular to recognize the change in the dataset
         * it has to change the dataset variable directly,
         * so one way around it, is to clone the data, change it and then
         * assign it;
         */
    }

    /*----------------------------------------------------------------------------------------*/

    /*-----------------------------------------BarChart-Reserva-------------------------------------*/

    public chartClickedReserva(e: any): void {
        console.log(e);
    }
    public chartHoveredReserva(e: any): void {
        console.log(e);
    }
    public randomizeReserva(): void {
        // Only Change 3 values
        const data = [
            Math.round(Math.random() * 100),
            59,
            80,
            (Math.random() * 100),
            56,
            (Math.random() * 100),
            40];
        const clone = JSON.parse(JSON.stringify(this.barChartDataReseva));
        clone[0].data = data;
        this.barChartDataReseva = clone;
        /**
         * (My guess), for Angular to recognize the change in the dataset
         * it has to change the dataset variable directly,
         * so one way around it, is to clone the data, change it and then
         * assign it;
         */
    }

    /*----------------------------------------------------------------------------------------*/

    /*------------------------------------------Donut--------------------------------------------*/
    public chartClickedDonut(e: any): void {
        console.log(e);
    }
    public chartHoveredDonut(e: any): void {
        console.log(e);
    }
    public chartClickedPieChart(e: any): void {
        console.log(e);
    }
    public chartHoveredPieChart(e: any): void {
        console.log(e);
    }
    public chartClickedLineChart(e: any): void {
        console.log(e);
    }
    public chartHoveredLineChart(e: any): void {
        console.log(e);
    }
}
