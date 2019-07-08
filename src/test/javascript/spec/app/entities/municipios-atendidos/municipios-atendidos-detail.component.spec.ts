/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { MunicipiosAtendidosDetailComponent } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos-detail.component';
import { MunicipiosAtendidosService } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.service';
import { MunicipiosAtendidos } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.model';

describe('Component Tests', () => {

    describe('MunicipiosAtendidos Management Detail Component', () => {
        let comp: MunicipiosAtendidosDetailComponent;
        let fixture: ComponentFixture<MunicipiosAtendidosDetailComponent>;
        let service: MunicipiosAtendidosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [MunicipiosAtendidosDetailComponent],
                providers: [
                    MunicipiosAtendidosService
                ]
            })
            .overrideTemplate(MunicipiosAtendidosDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MunicipiosAtendidosDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MunicipiosAtendidosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MunicipiosAtendidos(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.municipiosAtendidos).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
