/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoLogComponent } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.component';
import { IndicadorProducaoLogService } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.service';
import { IndicadorProducaoLog } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.model';

describe('Component Tests', () => {

    describe('IndicadorProducaoLog Management Component', () => {
        let comp: IndicadorProducaoLogComponent;
        let fixture: ComponentFixture<IndicadorProducaoLogComponent>;
        let service: IndicadorProducaoLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoLogComponent],
                providers: [
                    IndicadorProducaoLogService
                ]
            })
            .overrideTemplate(IndicadorProducaoLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new IndicadorProducaoLog(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.indicadorProducaoLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
