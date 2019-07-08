/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { RelatoriosLogDetailComponent } from '../../../../../../main/webapp/app/entities/relatorios-log/relatorios-log-detail.component';
import { RelatoriosLogService } from '../../../../../../main/webapp/app/entities/relatorios-log/relatorios-log.service';
import { RelatoriosLog } from '../../../../../../main/webapp/app/entities/relatorios-log/relatorios-log.model';

describe('Component Tests', () => {

    describe('RelatoriosLog Management Detail Component', () => {
        let comp: RelatoriosLogDetailComponent;
        let fixture: ComponentFixture<RelatoriosLogDetailComponent>;
        let service: RelatoriosLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [RelatoriosLogDetailComponent],
                providers: [
                    RelatoriosLogService
                ]
            })
            .overrideTemplate(RelatoriosLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelatoriosLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelatoriosLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new RelatoriosLog(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.relatoriosLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
