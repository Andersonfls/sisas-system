/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ExecucaoComponent } from '../../../../../../main/webapp/app/entities/execucao/execucao.component';
import { ExecucaoService } from '../../../../../../main/webapp/app/entities/execucao/execucao.service';
import { Execucao } from '../../../../../../main/webapp/app/entities/execucao/execucao.model';

describe('Component Tests', () => {

    describe('Execucao Management Component', () => {
        let comp: ExecucaoComponent;
        let fixture: ComponentFixture<ExecucaoComponent>;
        let service: ExecucaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ExecucaoComponent],
                providers: [
                    ExecucaoService
                ]
            })
            .overrideTemplate(ExecucaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ExecucaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExecucaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Execucao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.execucaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
