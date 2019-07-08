/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { EmpreitadaDialogComponent } from '../../../../../../main/webapp/app/entities/empreitada/empreitada-dialog.component';
import { EmpreitadaService } from '../../../../../../main/webapp/app/entities/empreitada/empreitada.service';
import { Empreitada } from '../../../../../../main/webapp/app/entities/empreitada/empreitada.model';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua';
import { ContratoService } from '../../../../../../main/webapp/app/entities/contrato';

describe('Component Tests', () => {

    describe('Empreitada Management Dialog Component', () => {
        let comp: EmpreitadaDialogComponent;
        let fixture: ComponentFixture<EmpreitadaDialogComponent>;
        let service: EmpreitadaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [EmpreitadaDialogComponent],
                providers: [
                    ProgramasProjectosService,
                    SistemaAguaService,
                    ContratoService,
                    EmpreitadaService
                ]
            })
            .overrideTemplate(EmpreitadaDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EmpreitadaDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmpreitadaService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Empreitada(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.empreitada = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'empreitadaListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Empreitada();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.empreitada = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'empreitadaListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
