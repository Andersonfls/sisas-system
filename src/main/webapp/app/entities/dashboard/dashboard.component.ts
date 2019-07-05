import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';

@Component({
    selector: 'jhi-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: [
        'dashboard.css'
    ]
})

export class DashboardComponent implements OnInit {

    user: User;

    /*DASHBOARD = ROLE_USER*/
    reservasUser: number;
    emprestimosUser: number;
    devolucoesUser: number;

    /*Dashboard = ROLE_ADMIN*/
    usersAdmin: number;
    obrasAdmin: number;
    emprestimosAdmin: number;
    reservasAdmin: number;

    public barChartOptionsEmprestimo: any = {
        scaleShowVerticalLines: false,
        responsive: true
    };

    public barChartLabelsEmprestimo: string[] = ['JAN', 'FEV', 'MAR', 'ABR' ];
    public barChartTypeEmprestimo = 'bar';
    public barChartLegendEmprestimo = true;
    public barChartDataEmprestimo: any[] = [
        {data: [65, 59, 80, 100], label: 'Empréstimos'}
    ];

    public barColorsEmprestimo: any[] = [
        {
            backgroundColor:['#967138', '#967138', '#967138', '#967138']
        }];

    public barChartOptionsReserva: any = {
        scaleShowVerticalLines: false,
        responsive: true
    };

    public barColorsReserva: any[] = [
        {
            backgroundColor:['#978138', '#978138', '#978138', '#978138']
        }];

    public barColorsLine: any[] = [
        {
            backgroundColor:['#1C502F', '#007bff']
        }];

    public lineChartData: Array<any> = [
        [65, 59, 80, 81, 56, 55, 40],
        [28, 48, 40, 19, 86, 27, 90]
    ];
    
    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,

    ) {}

    ngOnInit() {
        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
            if (this.user.authorities[0] === 'ROLE_ADMIN') {
                this.userService.queryUserDashboard().subscribe(
                    (res: HttpResponse<number>) => {
                        this.usersAdmin = res.body;
                    }, (res: HttpErrorResponse) => this.onError(res.message)
                );

                // this.emprestimoService.queryEmprestimoDashboard().subscribe(
                //     (res: HttpResponse<number>) => {
                //         this.emprestimosAdmin = res.body;
                //     }, (res: HttpErrorResponse) => this.onError(res.message)
                // );

                // this.reservaService.queryReservaDashboard().subscribe(
                //     (res: HttpResponse<number>) => {
                //         this.reservasAdmin = res.body;
                //     }, (res: HttpErrorResponse) => this.onError(res.message)
                // );
            } else {
                // this.emprestimoService.queryEmprestimoUser(this.user.id).subscribe(
                //     (res: HttpResponse<number>) => {
                //         this.emprestimosUser = res.body;
                //     }, (res: HttpErrorResponse) => this.onError(res.message)
                // );

                // this.reservaService.queryReservaUser(this.user.id).subscribe(
                //     (res: HttpResponse<number>) => {
                //         this.reservasUser = res.body;
                //     }, (res: HttpErrorResponse) => this.onError(res.message)
                // );

                // this.emprestimoService.queryDevolucaoUser(this.user.id).subscribe(
                //     (res: HttpResponse<number>) => {
                //         this.devolucoesUser = res.body;
                //     }, (res: HttpErrorResponse) => this.onError(res.message)
                // );
            }

            // this.obraService.queryObraDashboard().subscribe(
            //     (res: HttpResponse<number>) => {
            //         this.obrasAdmin = res.body;
            //     }, (res: HttpErrorResponse) => this.onError(res.message)
            // );
        });
        this.carregarChart();
        this.carregarBarChar();
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    /*--------Canvas Gráficos-------*/
    carregarChart() {
        const chart = new CanvasJS.Chart('chartContainer', {
            theme: 'light2',
            animationEnabled: true,
            exportEnabled: true,
            title:{
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

    public chartClickedEmprestimo(e: any):void {
        console.log(e);
    }
    public chartHoveredEmprestimo(e: any):void {
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

    public barChartLabelsReserva: string[] = ['JAN', 'FEV', 'MAR', 'ABR' ];
    public barChartTypeReserva = 'bar';
    public barChartLegendReserva = true;
    public barChartDataReseva: any[] = [
        {data: [28, 48, 40, 55], label: 'Reservas'}
    ];
    public chartClickedReserva(e: any):void {
        console.log(e);
    }
    public chartHoveredReserva(e: any):void {
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
    public doughnutChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
    public doughnutChartData: number[] = [350, 450, 100];
    public doughnutChartType = 'doughnut';
    public chartClickedDonut(e: any):void {
        console.log(e);
    }
    public chartHoveredDonut(e: any):void {
        console.log(e);
    }
    public donutColors: any[] = [
        {
            backgroundColor:['#613b18', '#b87524', '#fb9e37']
        }];
    /*--------------------------------------------------------------------------------------------*/

    /*------------------------------------PieChart------------------------------------*/
    public pieChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales'];
    public pieChartData: number[] = [300, 500, 100];
    public pieChartType = 'pie';
    public chartClickedPieChart(e: any):void {
        console.log(e);
    }
    public chartHoveredPieChart(e: any):void {
        console.log(e);
    }
    public pieColors: any[] = [
        {
            backgroundColor:['#613b18', '#b87524', '#fb9e37',]
        }];
    /*--------------------------------------------------------------------------------*/

    /*-------------------------------------------lineChart----------------------------------------------*/

    public lineChartType:string = 'line';
    public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    public chartClickedLineChart(e: any):void {
        console.log(e);
    }
    public chartHoveredLineChart(e: any):void {
        console.log(e);
    }
    /*--------------------------------------------------------------------------------------------------*/
}
