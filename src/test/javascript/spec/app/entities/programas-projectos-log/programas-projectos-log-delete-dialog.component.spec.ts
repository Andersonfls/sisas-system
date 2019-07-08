/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { ProgramasProjectosLogDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log-delete-dialog.component';
import { ProgramasProjectosLogService } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.service';

describe('Component Tests', () => {

    describe('ProgramasProjectosLog Management Delete Component', () => {
        let comp: ProgramasProjectosLogDeleteDialogComponent;
        let fixture: ComponentFixture<ProgramasProjectosLogDeleteDialogComponent>;
        let service: ProgramasProjectosLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProgramasProjectosLogDeleteDialogComponent],
                providers: [
                    ProgramasProjectosLogService
                ]
            })
            .overrideTemplate(ProgramasProjectosLogDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProgramasProjectosLogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasProjectosLogService);
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
