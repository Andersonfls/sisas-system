/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { SobreDnaDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna-delete-dialog.component';
import { SobreDnaService } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna.service';

describe('Component Tests', () => {

    describe('SobreDna Management Delete Component', () => {
        let comp: SobreDnaDeleteDialogComponent;
        let fixture: ComponentFixture<SobreDnaDeleteDialogComponent>;
        let service: SobreDnaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SobreDnaDeleteDialogComponent],
                providers: [
                    SobreDnaService
                ]
            })
            .overrideTemplate(SobreDnaDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SobreDnaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SobreDnaService);
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
