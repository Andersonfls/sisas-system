/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ContratoDetailComponent } from '../../../../../../main/webapp/app/entities/contrato/contrato-detail.component';
import { ContratoService } from '../../../../../../main/webapp/app/entities/contrato/contrato.service';
import { Contrato } from '../../../../../../main/webapp/app/entities/contrato/contrato.model';

describe('Component Tests', () => {

    describe('Contrato Management Detail Component', () => {
        let comp: ContratoDetailComponent;
        let fixture: ComponentFixture<ContratoDetailComponent>;
        let service: ContratoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ContratoDetailComponent],
                providers: [
                    ContratoService
                ]
            })
            .overrideTemplate(ContratoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContratoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContratoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Contrato(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.contrato).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
