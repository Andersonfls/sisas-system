/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ProvinciaComponent } from '../../../../../../main/webapp/app/entities/provincia/provincia.component';
import { ProvinciaService } from '../../../../../../main/webapp/app/entities/provincia/provincia.service';
import { Provincia } from '../../../../../../main/webapp/app/entities/provincia/provincia.model';

describe('Component Tests', () => {

    describe('Provincia Management Component', () => {
        let comp: ProvinciaComponent;
        let fixture: ComponentFixture<ProvinciaComponent>;
        let service: ProvinciaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProvinciaComponent],
                providers: [
                    ProvinciaService
                ]
            })
            .overrideTemplate(ProvinciaComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProvinciaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinciaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Provincia(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.provincias[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
