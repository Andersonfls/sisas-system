/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { FasesComponent } from '../../../../../../main/webapp/app/entities/fases/fases.component';
import { FasesService } from '../../../../../../main/webapp/app/entities/fases/fases.service';
import { Fases } from '../../../../../../main/webapp/app/entities/fases/fases.model';

describe('Component Tests', () => {

    describe('Fases Management Component', () => {
        let comp: FasesComponent;
        let fixture: ComponentFixture<FasesComponent>;
        let service: FasesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [FasesComponent],
                providers: [
                    FasesService
                ]
            })
            .overrideTemplate(FasesComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FasesComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FasesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Fases(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.fases[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
