/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ExecucaoDetailComponent } from '../../../../../../main/webapp/app/entities/execucao/execucao-detail.component';
import { ExecucaoService } from '../../../../../../main/webapp/app/entities/execucao/execucao.service';
import { Execucao } from '../../../../../../main/webapp/app/entities/execucao/execucao.model';

describe('Component Tests', () => {

    describe('Execucao Management Detail Component', () => {
        let comp: ExecucaoDetailComponent;
        let fixture: ComponentFixture<ExecucaoDetailComponent>;
        let service: ExecucaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ExecucaoDetailComponent],
                providers: [
                    ExecucaoService
                ]
            })
            .overrideTemplate(ExecucaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ExecucaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExecucaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Execucao(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.execucao).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
