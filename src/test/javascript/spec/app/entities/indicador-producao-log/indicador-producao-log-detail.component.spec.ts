/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoLogDetailComponent } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log-detail.component';
import { IndicadorProducaoLogService } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.service';
import { IndicadorProducaoLog } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.model';

describe('Component Tests', () => {

    describe('IndicadorProducaoLog Management Detail Component', () => {
        let comp: IndicadorProducaoLogDetailComponent;
        let fixture: ComponentFixture<IndicadorProducaoLogDetailComponent>;
        let service: IndicadorProducaoLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoLogDetailComponent],
                providers: [
                    IndicadorProducaoLogService
                ]
            })
            .overrideTemplate(IndicadorProducaoLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new IndicadorProducaoLog(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.indicadorProducaoLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
