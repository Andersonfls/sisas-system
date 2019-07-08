/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ConcursoComponent } from '../../../../../../main/webapp/app/entities/concurso/concurso.component';
import { ConcursoService } from '../../../../../../main/webapp/app/entities/concurso/concurso.service';
import { Concurso } from '../../../../../../main/webapp/app/entities/concurso/concurso.model';

describe('Component Tests', () => {

    describe('Concurso Management Component', () => {
        let comp: ConcursoComponent;
        let fixture: ComponentFixture<ConcursoComponent>;
        let service: ConcursoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ConcursoComponent],
                providers: [
                    ConcursoService
                ]
            })
            .overrideTemplate(ConcursoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConcursoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConcursoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Concurso(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.concursos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
