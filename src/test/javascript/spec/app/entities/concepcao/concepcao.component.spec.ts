/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ConcepcaoComponent } from '../../../../../../main/webapp/app/entities/concepcao/concepcao.component';
import { ConcepcaoService } from '../../../../../../main/webapp/app/entities/concepcao/concepcao.service';
import { Concepcao } from '../../../../../../main/webapp/app/entities/concepcao/concepcao.model';

describe('Component Tests', () => {

    describe('Concepcao Management Component', () => {
        let comp: ConcepcaoComponent;
        let fixture: ComponentFixture<ConcepcaoComponent>;
        let service: ConcepcaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ConcepcaoComponent],
                providers: [
                    ConcepcaoService
                ]
            })
            .overrideTemplate(ConcepcaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConcepcaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConcepcaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Concepcao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.concepcaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
