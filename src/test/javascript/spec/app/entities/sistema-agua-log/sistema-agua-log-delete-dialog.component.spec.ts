/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { SistemaAguaLogDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log-delete-dialog.component';
import { SistemaAguaLogService } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.service';

describe('Component Tests', () => {

    describe('SistemaAguaLog Management Delete Component', () => {
        let comp: SistemaAguaLogDeleteDialogComponent;
        let fixture: ComponentFixture<SistemaAguaLogDeleteDialogComponent>;
        let service: SistemaAguaLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SistemaAguaLogDeleteDialogComponent],
                providers: [
                    SistemaAguaLogService
                ]
            })
            .overrideTemplate(SistemaAguaLogDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SistemaAguaLogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SistemaAguaLogService);
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
