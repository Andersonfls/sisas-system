/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ComunaDetailComponent } from '../../../../../../main/webapp/app/entities/comuna/comuna-detail.component';
import { ComunaService } from '../../../../../../main/webapp/app/entities/comuna/comuna.service';
import { Comuna } from '../../../../../../main/webapp/app/entities/comuna/comuna.model';

describe('Component Tests', () => {

    describe('Comuna Management Detail Component', () => {
        let comp: ComunaDetailComponent;
        let fixture: ComponentFixture<ComunaDetailComponent>;
        let service: ComunaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ComunaDetailComponent],
                providers: [
                    ComunaService
                ]
            })
            .overrideTemplate(ComunaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ComunaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComunaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Comuna(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.comuna).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
