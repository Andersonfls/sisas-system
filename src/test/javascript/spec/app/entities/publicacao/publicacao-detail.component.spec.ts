/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { PublicacaoDetailComponent } from '../../../../../../main/webapp/app/entities/publicacao/publicacao-detail.component';
import { PublicacaoService } from '../../../../../../main/webapp/app/entities/publicacao/publicacao.service';
import { Publicacao } from '../../../../../../main/webapp/app/entities/publicacao/publicacao.model';

describe('Component Tests', () => {

    describe('Publicacao Management Detail Component', () => {
        let comp: PublicacaoDetailComponent;
        let fixture: ComponentFixture<PublicacaoDetailComponent>;
        let service: PublicacaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [PublicacaoDetailComponent],
                providers: [
                    PublicacaoService
                ]
            })
            .overrideTemplate(PublicacaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublicacaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublicacaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Publicacao(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.publicacao).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
