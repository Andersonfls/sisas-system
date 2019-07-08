/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { SituacaoComponent } from '../../../../../../main/webapp/app/entities/situacao/situacao.component';
import { SituacaoService } from '../../../../../../main/webapp/app/entities/situacao/situacao.service';
import { Situacao } from '../../../../../../main/webapp/app/entities/situacao/situacao.model';

describe('Component Tests', () => {

    describe('Situacao Management Component', () => {
        let comp: SituacaoComponent;
        let fixture: ComponentFixture<SituacaoComponent>;
        let service: SituacaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SituacaoComponent],
                providers: [
                    SituacaoService
                ]
            })
            .overrideTemplate(SituacaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SituacaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SituacaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Situacao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.situacaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
