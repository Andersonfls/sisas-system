/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { MunicipiosAtendidosDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos-delete-dialog.component';
import { MunicipiosAtendidosService } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.service';

describe('Component Tests', () => {

    describe('MunicipiosAtendidos Management Delete Component', () => {
        let comp: MunicipiosAtendidosDeleteDialogComponent;
        let fixture: ComponentFixture<MunicipiosAtendidosDeleteDialogComponent>;
        let service: MunicipiosAtendidosService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [MunicipiosAtendidosDeleteDialogComponent],
                providers: [
                    MunicipiosAtendidosService
                ]
            })
            .overrideTemplate(MunicipiosAtendidosDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MunicipiosAtendidosDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MunicipiosAtendidosService);
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
