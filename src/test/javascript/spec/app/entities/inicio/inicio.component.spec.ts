/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { InicioComponent } from '../../../../../../main/webapp/app/entities/inicio/inicio.component';
import { InicioService } from '../../../../../../main/webapp/app/entities/inicio/inicio.service';
import { Inicio } from '../../../../../../main/webapp/app/entities/inicio/inicio.model';

describe('Component Tests', () => {

    describe('Inicio Management Component', () => {
        let comp: InicioComponent;
        let fixture: ComponentFixture<InicioComponent>;
        let service: InicioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [InicioComponent],
                providers: [
                    InicioService
                ]
            })
            .overrideTemplate(InicioComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InicioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InicioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Inicio(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.inicios[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
