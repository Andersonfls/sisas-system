/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ProgramasProjectosComponent } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos.component';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos.service';
import { ProgramasProjectos } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos.model';

describe('Component Tests', () => {

    describe('ProgramasProjectos Management Component', () => {
        let comp: ProgramasProjectosComponent;
        let fixture: ComponentFixture<ProgramasProjectosComponent>;
        let service: ProgramasProjectosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProgramasProjectosComponent],
                providers: [
                    ProgramasProjectosService
                ]
            })
            .overrideTemplate(ProgramasProjectosComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProgramasProjectosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasProjectosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ProgramasProjectos(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.programasProjectos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
