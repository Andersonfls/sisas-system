/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { SistemaAguaLogComponent } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.component';
import { SistemaAguaLogService } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.service';
import { SistemaAguaLog } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.model';

describe('Component Tests', () => {

    describe('SistemaAguaLog Management Component', () => {
        let comp: SistemaAguaLogComponent;
        let fixture: ComponentFixture<SistemaAguaLogComponent>;
        let service: SistemaAguaLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SistemaAguaLogComponent],
                providers: [
                    SistemaAguaLogService
                ]
            })
            .overrideTemplate(SistemaAguaLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SistemaAguaLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SistemaAguaLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SistemaAguaLog(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.sistemaAguaLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
