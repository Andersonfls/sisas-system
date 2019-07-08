/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ProgramasProjectosLogComponent } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.component';
import { ProgramasProjectosLogService } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.service';
import { ProgramasProjectosLog } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.model';

describe('Component Tests', () => {

    describe('ProgramasProjectosLog Management Component', () => {
        let comp: ProgramasProjectosLogComponent;
        let fixture: ComponentFixture<ProgramasProjectosLogComponent>;
        let service: ProgramasProjectosLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProgramasProjectosLogComponent],
                providers: [
                    ProgramasProjectosLogService
                ]
            })
            .overrideTemplate(ProgramasProjectosLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProgramasProjectosLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasProjectosLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ProgramasProjectosLog(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.programasProjectosLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
