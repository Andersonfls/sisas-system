/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { NoticiasDetailComponent } from '../../../../../../main/webapp/app/entities/noticias/noticias-detail.component';
import { NoticiasService } from '../../../../../../main/webapp/app/entities/noticias/noticias.service';
import { Noticias } from '../../../../../../main/webapp/app/entities/noticias/noticias.model';

describe('Component Tests', () => {

    describe('Noticias Management Detail Component', () => {
        let comp: NoticiasDetailComponent;
        let fixture: ComponentFixture<NoticiasDetailComponent>;
        let service: NoticiasService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [NoticiasDetailComponent],
                providers: [
                    NoticiasService
                ]
            })
            .overrideTemplate(NoticiasDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NoticiasDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NoticiasService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Noticias(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.noticias).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
