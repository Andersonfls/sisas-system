/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { OrigemFinanciamentoComponent } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.component';
import { OrigemFinanciamentoService } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.service';
import { OrigemFinanciamento } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.model';

describe('Component Tests', () => {

    describe('OrigemFinanciamento Management Component', () => {
        let comp: OrigemFinanciamentoComponent;
        let fixture: ComponentFixture<OrigemFinanciamentoComponent>;
        let service: OrigemFinanciamentoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [OrigemFinanciamentoComponent],
                providers: [
                    OrigemFinanciamentoService
                ]
            })
            .overrideTemplate(OrigemFinanciamentoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OrigemFinanciamentoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrigemFinanciamentoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new OrigemFinanciamento(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.origemFinanciamentos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
