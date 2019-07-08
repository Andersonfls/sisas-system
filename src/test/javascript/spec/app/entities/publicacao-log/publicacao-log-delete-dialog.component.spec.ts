/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { PublicacaoLogDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log-delete-dialog.component';
import { PublicacaoLogService } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log.service';

describe('Component Tests', () => {

    describe('PublicacaoLog Management Delete Component', () => {
        let comp: PublicacaoLogDeleteDialogComponent;
        let fixture: ComponentFixture<PublicacaoLogDeleteDialogComponent>;
        let service: PublicacaoLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [PublicacaoLogDeleteDialogComponent],
                providers: [
                    PublicacaoLogService
                ]
            })
            .overrideTemplate(PublicacaoLogDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublicacaoLogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublicacaoLogService);
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
