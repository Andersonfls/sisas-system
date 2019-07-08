/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ProgramasProjectosDetailComponent } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos-detail.component';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos.service';
import { ProgramasProjectos } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos.model';

describe('Component Tests', () => {

    describe('ProgramasProjectos Management Detail Component', () => {
        let comp: ProgramasProjectosDetailComponent;
        let fixture: ComponentFixture<ProgramasProjectosDetailComponent>;
        let service: ProgramasProjectosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProgramasProjectosDetailComponent],
                providers: [
                    ProgramasProjectosService
                ]
            })
            .overrideTemplate(ProgramasProjectosDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProgramasProjectosDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasProjectosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ProgramasProjectos(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.programasProjectos).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
