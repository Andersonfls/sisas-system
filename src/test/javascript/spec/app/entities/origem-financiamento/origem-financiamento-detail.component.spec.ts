/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { OrigemFinanciamentoDetailComponent } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento-detail.component';
import { OrigemFinanciamentoService } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.service';
import { OrigemFinanciamento } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.model';

describe('Component Tests', () => {

    describe('OrigemFinanciamento Management Detail Component', () => {
        let comp: OrigemFinanciamentoDetailComponent;
        let fixture: ComponentFixture<OrigemFinanciamentoDetailComponent>;
        let service: OrigemFinanciamentoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [OrigemFinanciamentoDetailComponent],
                providers: [
                    OrigemFinanciamentoService
                ]
            })
            .overrideTemplate(OrigemFinanciamentoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OrigemFinanciamentoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrigemFinanciamentoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new OrigemFinanciamento(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.origemFinanciamento).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
