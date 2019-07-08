/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoComponent } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.component';
import { IndicadorProducaoService } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.service';
import { IndicadorProducao } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.model';

describe('Component Tests', () => {

    describe('IndicadorProducao Management Component', () => {
        let comp: IndicadorProducaoComponent;
        let fixture: ComponentFixture<IndicadorProducaoComponent>;
        let service: IndicadorProducaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoComponent],
                providers: [
                    IndicadorProducaoService
                ]
            })
            .overrideTemplate(IndicadorProducaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new IndicadorProducao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.indicadorProducaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
