/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { FasesDetailComponent } from '../../../../../../main/webapp/app/entities/fases/fases-detail.component';
import { FasesService } from '../../../../../../main/webapp/app/entities/fases/fases.service';
import { Fases } from '../../../../../../main/webapp/app/entities/fases/fases.model';

describe('Component Tests', () => {

    describe('Fases Management Detail Component', () => {
        let comp: FasesDetailComponent;
        let fixture: ComponentFixture<FasesDetailComponent>;
        let service: FasesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [FasesDetailComponent],
                providers: [
                    FasesService
                ]
            })
            .overrideTemplate(FasesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FasesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FasesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Fases(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.fases).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
