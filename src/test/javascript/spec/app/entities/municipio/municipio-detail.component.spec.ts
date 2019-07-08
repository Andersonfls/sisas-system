/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { MunicipioDetailComponent } from '../../../../../../main/webapp/app/entities/municipio/municipio-detail.component';
import { MunicipioService } from '../../../../../../main/webapp/app/entities/municipio/municipio.service';
import { Municipio } from '../../../../../../main/webapp/app/entities/municipio/municipio.model';

describe('Component Tests', () => {

    describe('Municipio Management Detail Component', () => {
        let comp: MunicipioDetailComponent;
        let fixture: ComponentFixture<MunicipioDetailComponent>;
        let service: MunicipioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [MunicipioDetailComponent],
                providers: [
                    MunicipioService
                ]
            })
            .overrideTemplate(MunicipioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MunicipioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MunicipioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Municipio(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.municipio).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
