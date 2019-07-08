/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ConfiguracoesLogDetailComponent } from '../../../../../../main/webapp/app/entities/configuracoes-log/configuracoes-log-detail.component';
import { ConfiguracoesLogService } from '../../../../../../main/webapp/app/entities/configuracoes-log/configuracoes-log.service';
import { ConfiguracoesLog } from '../../../../../../main/webapp/app/entities/configuracoes-log/configuracoes-log.model';

describe('Component Tests', () => {

    describe('ConfiguracoesLog Management Detail Component', () => {
        let comp: ConfiguracoesLogDetailComponent;
        let fixture: ComponentFixture<ConfiguracoesLogDetailComponent>;
        let service: ConfiguracoesLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ConfiguracoesLogDetailComponent],
                providers: [
                    ConfiguracoesLogService
                ]
            })
            .overrideTemplate(ConfiguracoesLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracoesLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracoesLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ConfiguracoesLog(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.configuracoesLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
