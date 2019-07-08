/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ProvinciaDetailComponent } from '../../../../../../main/webapp/app/entities/provincia/provincia-detail.component';
import { ProvinciaService } from '../../../../../../main/webapp/app/entities/provincia/provincia.service';
import { Provincia } from '../../../../../../main/webapp/app/entities/provincia/provincia.model';

describe('Component Tests', () => {

    describe('Provincia Management Detail Component', () => {
        let comp: ProvinciaDetailComponent;
        let fixture: ComponentFixture<ProvinciaDetailComponent>;
        let service: ProvinciaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProvinciaDetailComponent],
                providers: [
                    ProvinciaService
                ]
            })
            .overrideTemplate(ProvinciaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProvinciaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinciaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Provincia(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.provincia).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
