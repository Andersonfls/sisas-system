/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { MunicipioComponent } from '../../../../../../main/webapp/app/entities/municipio/municipio.component';
import { MunicipioService } from '../../../../../../main/webapp/app/entities/municipio/municipio.service';
import { Municipio } from '../../../../../../main/webapp/app/entities/municipio/municipio.model';

describe('Component Tests', () => {

    describe('Municipio Management Component', () => {
        let comp: MunicipioComponent;
        let fixture: ComponentFixture<MunicipioComponent>;
        let service: MunicipioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [MunicipioComponent],
                providers: [
                    MunicipioService
                ]
            })
            .overrideTemplate(MunicipioComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MunicipioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MunicipioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Municipio(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.municipios[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
