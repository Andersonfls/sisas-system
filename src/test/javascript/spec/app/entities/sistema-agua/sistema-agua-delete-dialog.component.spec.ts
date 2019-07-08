/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { SistemaAguaDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua-delete-dialog.component';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua.service';

describe('Component Tests', () => {

    describe('SistemaAgua Management Delete Component', () => {
        let comp: SistemaAguaDeleteDialogComponent;
        let fixture: ComponentFixture<SistemaAguaDeleteDialogComponent>;
        let service: SistemaAguaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SistemaAguaDeleteDialogComponent],
                providers: [
                    SistemaAguaService
                ]
            })
            .overrideTemplate(SistemaAguaDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SistemaAguaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SistemaAguaService);
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
