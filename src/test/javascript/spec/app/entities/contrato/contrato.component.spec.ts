/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ContratoComponent } from '../../../../../../main/webapp/app/entities/contrato/contrato.component';
import { ContratoService } from '../../../../../../main/webapp/app/entities/contrato/contrato.service';
import { Contrato } from '../../../../../../main/webapp/app/entities/contrato/contrato.model';

describe('Component Tests', () => {

    describe('Contrato Management Component', () => {
        let comp: ContratoComponent;
        let fixture: ComponentFixture<ContratoComponent>;
        let service: ContratoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ContratoComponent],
                providers: [
                    ContratoService
                ]
            })
            .overrideTemplate(ContratoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContratoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContratoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Contrato(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.contratoes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
