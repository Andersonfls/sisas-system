/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { EntidadeGestoraDetailComponent } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora-detail.component';
import { EntidadeGestoraService } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.service';
import { EntidadeGestora } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.model';

describe('Component Tests', () => {

    describe('EntidadeGestora Management Detail Component', () => {
        let comp: EntidadeGestoraDetailComponent;
        let fixture: ComponentFixture<EntidadeGestoraDetailComponent>;
        let service: EntidadeGestoraService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [EntidadeGestoraDetailComponent],
                providers: [
                    EntidadeGestoraService
                ]
            })
            .overrideTemplate(EntidadeGestoraDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntidadeGestoraDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntidadeGestoraService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new EntidadeGestora(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.entidadeGestora).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
