/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { SituacaoDetailComponent } from '../../../../../../main/webapp/app/entities/situacao/situacao-detail.component';
import { SituacaoService } from '../../../../../../main/webapp/app/entities/situacao/situacao.service';
import { Situacao } from '../../../../../../main/webapp/app/entities/situacao/situacao.model';

describe('Component Tests', () => {

    describe('Situacao Management Detail Component', () => {
        let comp: SituacaoDetailComponent;
        let fixture: ComponentFixture<SituacaoDetailComponent>;
        let service: SituacaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SituacaoDetailComponent],
                providers: [
                    SituacaoService
                ]
            })
            .overrideTemplate(SituacaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SituacaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SituacaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Situacao(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.situacao).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
