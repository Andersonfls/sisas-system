/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoLogDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log-delete-dialog.component';
import { IndicadorProducaoLogService } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.service';

describe('Component Tests', () => {

    describe('IndicadorProducaoLog Management Delete Component', () => {
        let comp: IndicadorProducaoLogDeleteDialogComponent;
        let fixture: ComponentFixture<IndicadorProducaoLogDeleteDialogComponent>;
        let service: IndicadorProducaoLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoLogDeleteDialogComponent],
                providers: [
                    IndicadorProducaoLogService
                ]
            })
            .overrideTemplate(IndicadorProducaoLogDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoLogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoLogService);
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
