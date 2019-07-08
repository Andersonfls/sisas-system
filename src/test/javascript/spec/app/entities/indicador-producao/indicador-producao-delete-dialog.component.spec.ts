/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao-delete-dialog.component';
import { IndicadorProducaoService } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.service';

describe('Component Tests', () => {

    describe('IndicadorProducao Management Delete Component', () => {
        let comp: IndicadorProducaoDeleteDialogComponent;
        let fixture: ComponentFixture<IndicadorProducaoDeleteDialogComponent>;
        let service: IndicadorProducaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoDeleteDialogComponent],
                providers: [
                    IndicadorProducaoService
                ]
            })
            .overrideTemplate(IndicadorProducaoDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoService);
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
