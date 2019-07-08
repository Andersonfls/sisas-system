/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ProgramasProjectosLogDetailComponent } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log-detail.component';
import { ProgramasProjectosLogService } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.service';
import { ProgramasProjectosLog } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.model';

describe('Component Tests', () => {

    describe('ProgramasProjectosLog Management Detail Component', () => {
        let comp: ProgramasProjectosLogDetailComponent;
        let fixture: ComponentFixture<ProgramasProjectosLogDetailComponent>;
        let service: ProgramasProjectosLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProgramasProjectosLogDetailComponent],
                providers: [
                    ProgramasProjectosLogService
                ]
            })
            .overrideTemplate(ProgramasProjectosLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProgramasProjectosLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasProjectosLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ProgramasProjectosLog(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.programasProjectosLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
