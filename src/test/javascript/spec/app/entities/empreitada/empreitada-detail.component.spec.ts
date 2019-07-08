/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { EmpreitadaDetailComponent } from '../../../../../../main/webapp/app/entities/empreitada/empreitada-detail.component';
import { EmpreitadaService } from '../../../../../../main/webapp/app/entities/empreitada/empreitada.service';
import { Empreitada } from '../../../../../../main/webapp/app/entities/empreitada/empreitada.model';

describe('Component Tests', () => {

    describe('Empreitada Management Detail Component', () => {
        let comp: EmpreitadaDetailComponent;
        let fixture: ComponentFixture<EmpreitadaDetailComponent>;
        let service: EmpreitadaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [EmpreitadaDetailComponent],
                providers: [
                    EmpreitadaService
                ]
            })
            .overrideTemplate(EmpreitadaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EmpreitadaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmpreitadaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Empreitada(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.empreitada).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
