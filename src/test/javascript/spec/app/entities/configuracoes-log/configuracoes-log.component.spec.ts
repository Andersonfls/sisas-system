/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ConfiguracoesLogComponent } from '../../../../../../main/webapp/app/entities/configuracoes-log/configuracoes-log.component';
import { ConfiguracoesLogService } from '../../../../../../main/webapp/app/entities/configuracoes-log/configuracoes-log.service';
import { ConfiguracoesLog } from '../../../../../../main/webapp/app/entities/configuracoes-log/configuracoes-log.model';

describe('Component Tests', () => {

    describe('ConfiguracoesLog Management Component', () => {
        let comp: ConfiguracoesLogComponent;
        let fixture: ComponentFixture<ConfiguracoesLogComponent>;
        let service: ConfiguracoesLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ConfiguracoesLogComponent],
                providers: [
                    ConfiguracoesLogService
                ]
            })
            .overrideTemplate(ConfiguracoesLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracoesLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracoesLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ConfiguracoesLog(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.configuracoesLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
