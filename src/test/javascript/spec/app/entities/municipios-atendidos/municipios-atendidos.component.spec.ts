/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { MunicipiosAtendidosComponent } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.component';
import { MunicipiosAtendidosService } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.service';
import { MunicipiosAtendidos } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.model';

describe('Component Tests', () => {

    describe('MunicipiosAtendidos Management Component', () => {
        let comp: MunicipiosAtendidosComponent;
        let fixture: ComponentFixture<MunicipiosAtendidosComponent>;
        let service: MunicipiosAtendidosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [MunicipiosAtendidosComponent],
                providers: [
                    MunicipiosAtendidosService
                ]
            })
            .overrideTemplate(MunicipiosAtendidosComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MunicipiosAtendidosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MunicipiosAtendidosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MunicipiosAtendidos(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.municipiosAtendidos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
