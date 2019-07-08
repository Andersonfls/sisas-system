/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { InicioDetailComponent } from '../../../../../../main/webapp/app/entities/inicio/inicio-detail.component';
import { InicioService } from '../../../../../../main/webapp/app/entities/inicio/inicio.service';
import { Inicio } from '../../../../../../main/webapp/app/entities/inicio/inicio.model';

describe('Component Tests', () => {

    describe('Inicio Management Detail Component', () => {
        let comp: InicioDetailComponent;
        let fixture: ComponentFixture<InicioDetailComponent>;
        let service: InicioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [InicioDetailComponent],
                providers: [
                    InicioService
                ]
            })
            .overrideTemplate(InicioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InicioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InicioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Inicio(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.inicio).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
