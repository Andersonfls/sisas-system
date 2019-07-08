/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { EntidadeGestoraDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora-delete-dialog.component';
import { EntidadeGestoraService } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.service';

describe('Component Tests', () => {

    describe('EntidadeGestora Management Delete Component', () => {
        let comp: EntidadeGestoraDeleteDialogComponent;
        let fixture: ComponentFixture<EntidadeGestoraDeleteDialogComponent>;
        let service: EntidadeGestoraService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [EntidadeGestoraDeleteDialogComponent],
                providers: [
                    EntidadeGestoraService
                ]
            })
            .overrideTemplate(EntidadeGestoraDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntidadeGestoraDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntidadeGestoraService);
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
