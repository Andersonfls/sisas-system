/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { SistemaAguaDetailComponent } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua-detail.component';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua.service';
import { SistemaAgua } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua.model';

describe('Component Tests', () => {

    describe('SistemaAgua Management Detail Component', () => {
        let comp: SistemaAguaDetailComponent;
        let fixture: ComponentFixture<SistemaAguaDetailComponent>;
        let service: SistemaAguaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SistemaAguaDetailComponent],
                providers: [
                    SistemaAguaService
                ]
            })
            .overrideTemplate(SistemaAguaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SistemaAguaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SistemaAguaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SistemaAgua(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.sistemaAgua).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
