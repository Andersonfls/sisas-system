/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoDetailComponent } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao-detail.component';
import { IndicadorProducaoService } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.service';
import { IndicadorProducao } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.model';

describe('Component Tests', () => {

    describe('IndicadorProducao Management Detail Component', () => {
        let comp: IndicadorProducaoDetailComponent;
        let fixture: ComponentFixture<IndicadorProducaoDetailComponent>;
        let service: IndicadorProducaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoDetailComponent],
                providers: [
                    IndicadorProducaoService
                ]
            })
            .overrideTemplate(IndicadorProducaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new IndicadorProducao(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.indicadorProducao).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
