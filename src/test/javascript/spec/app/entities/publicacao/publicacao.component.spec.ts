/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { PublicacaoComponent } from '../../../../../../main/webapp/app/entities/publicacao/publicacao.component';
import { PublicacaoService } from '../../../../../../main/webapp/app/entities/publicacao/publicacao.service';
import { Publicacao } from '../../../../../../main/webapp/app/entities/publicacao/publicacao.model';

describe('Component Tests', () => {

    describe('Publicacao Management Component', () => {
        let comp: PublicacaoComponent;
        let fixture: ComponentFixture<PublicacaoComponent>;
        let service: PublicacaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [PublicacaoComponent],
                providers: [
                    PublicacaoService
                ]
            })
            .overrideTemplate(PublicacaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublicacaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublicacaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Publicacao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.publicacaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
