/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { RelatoriosLogComponent } from '../../../../../../main/webapp/app/entities/relatorios-log/relatorios-log.component';
import { RelatoriosLogService } from '../../../../../../main/webapp/app/entities/relatorios-log/relatorios-log.service';
import { RelatoriosLog } from '../../../../../../main/webapp/app/entities/relatorios-log/relatorios-log.model';

describe('Component Tests', () => {

    describe('RelatoriosLog Management Component', () => {
        let comp: RelatoriosLogComponent;
        let fixture: ComponentFixture<RelatoriosLogComponent>;
        let service: RelatoriosLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [RelatoriosLogComponent],
                providers: [
                    RelatoriosLogService
                ]
            })
            .overrideTemplate(RelatoriosLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelatoriosLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelatoriosLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new RelatoriosLog(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.relatoriosLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
