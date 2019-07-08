/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { EntidadeGestoraDialogComponent } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora-dialog.component';
import { EntidadeGestoraService } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.service';
import { EntidadeGestora } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.model';
import { MunicipiosAtendidosService } from '../../../../../../main/webapp/app/entities/municipios-atendidos';

describe('Component Tests', () => {

    describe('EntidadeGestora Management Dialog Component', () => {
        let comp: EntidadeGestoraDialogComponent;
        let fixture: ComponentFixture<EntidadeGestoraDialogComponent>;
        let service: EntidadeGestoraService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [EntidadeGestoraDialogComponent],
                providers: [
                    MunicipiosAtendidosService,
                    EntidadeGestoraService
                ]
            })
            .overrideTemplate(EntidadeGestoraDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntidadeGestoraDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntidadeGestoraService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new EntidadeGestora(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.entidadeGestora = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'entidadeGestoraListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new EntidadeGestora();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.entidadeGestora = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'entidadeGestoraListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
