/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { NoticiasComponent } from '../../../../../../main/webapp/app/entities/noticias/noticias.component';
import { NoticiasService } from '../../../../../../main/webapp/app/entities/noticias/noticias.service';
import { Noticias } from '../../../../../../main/webapp/app/entities/noticias/noticias.model';

describe('Component Tests', () => {

    describe('Noticias Management Component', () => {
        let comp: NoticiasComponent;
        let fixture: ComponentFixture<NoticiasComponent>;
        let service: NoticiasService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [NoticiasComponent],
                providers: [
                    NoticiasService
                ]
            })
            .overrideTemplate(NoticiasComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NoticiasComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NoticiasService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Noticias(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.noticias[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
