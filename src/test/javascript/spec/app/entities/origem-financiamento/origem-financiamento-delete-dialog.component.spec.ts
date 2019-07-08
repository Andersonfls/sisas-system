/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { OrigemFinanciamentoDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento-delete-dialog.component';
import { OrigemFinanciamentoService } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.service';

describe('Component Tests', () => {

    describe('OrigemFinanciamento Management Delete Component', () => {
        let comp: OrigemFinanciamentoDeleteDialogComponent;
        let fixture: ComponentFixture<OrigemFinanciamentoDeleteDialogComponent>;
        let service: OrigemFinanciamentoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [OrigemFinanciamentoDeleteDialogComponent],
                providers: [
                    OrigemFinanciamentoService
                ]
            })
            .overrideTemplate(OrigemFinanciamentoDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OrigemFinanciamentoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrigemFinanciamentoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
