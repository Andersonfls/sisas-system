/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ConcursoDetailComponent } from '../../../../../../main/webapp/app/entities/concurso/concurso-detail.component';
import { ConcursoService } from '../../../../../../main/webapp/app/entities/concurso/concurso.service';
import { Concurso } from '../../../../../../main/webapp/app/entities/concurso/concurso.model';

describe('Component Tests', () => {

    describe('Concurso Management Detail Component', () => {
        let comp: ConcursoDetailComponent;
        let fixture: ComponentFixture<ConcursoDetailComponent>;
        let service: ConcursoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ConcursoDetailComponent],
                providers: [
                    ConcursoService
                ]
            })
            .overrideTemplate(ConcursoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConcursoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConcursoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Concurso(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.concurso).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
