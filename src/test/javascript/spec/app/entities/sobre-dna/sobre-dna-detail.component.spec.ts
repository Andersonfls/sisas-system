/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { SobreDnaDetailComponent } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna-detail.component';
import { SobreDnaService } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna.service';
import { SobreDna } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna.model';

describe('Component Tests', () => {

    describe('SobreDna Management Detail Component', () => {
        let comp: SobreDnaDetailComponent;
        let fixture: ComponentFixture<SobreDnaDetailComponent>;
        let service: SobreDnaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SobreDnaDetailComponent],
                providers: [
                    SobreDnaService
                ]
            })
            .overrideTemplate(SobreDnaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SobreDnaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SobreDnaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SobreDna(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.sobreDna).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
