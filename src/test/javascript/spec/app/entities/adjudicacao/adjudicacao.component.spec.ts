/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { AdjudicacaoComponent } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao.component';
import { AdjudicacaoService } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao.service';
import { Adjudicacao } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao.model';

describe('Component Tests', () => {

    describe('Adjudicacao Management Component', () => {
        let comp: AdjudicacaoComponent;
        let fixture: ComponentFixture<AdjudicacaoComponent>;
        let service: AdjudicacaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [AdjudicacaoComponent],
                providers: [
                    AdjudicacaoService
                ]
            })
            .overrideTemplate(AdjudicacaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AdjudicacaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdjudicacaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Adjudicacao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.adjudicacaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
