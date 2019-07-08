/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { SistemaAguaLogDetailComponent } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log-detail.component';
import { SistemaAguaLogService } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.service';
import { SistemaAguaLog } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.model';

describe('Component Tests', () => {

    describe('SistemaAguaLog Management Detail Component', () => {
        let comp: SistemaAguaLogDetailComponent;
        let fixture: ComponentFixture<SistemaAguaLogDetailComponent>;
        let service: SistemaAguaLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SistemaAguaLogDetailComponent],
                providers: [
                    SistemaAguaLogService
                ]
            })
            .overrideTemplate(SistemaAguaLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SistemaAguaLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SistemaAguaLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SistemaAguaLog(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.sistemaAguaLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
