/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { AdjudicacaoDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao-delete-dialog.component';
import { AdjudicacaoService } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao.service';

describe('Component Tests', () => {

    describe('Adjudicacao Management Delete Component', () => {
        let comp: AdjudicacaoDeleteDialogComponent;
        let fixture: ComponentFixture<AdjudicacaoDeleteDialogComponent>;
        let service: AdjudicacaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [AdjudicacaoDeleteDialogComponent],
                providers: [
                    AdjudicacaoService
                ]
            })
            .overrideTemplate(AdjudicacaoDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AdjudicacaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdjudicacaoService);
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
