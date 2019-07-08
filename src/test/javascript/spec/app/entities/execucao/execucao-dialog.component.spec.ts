/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { ExecucaoDialogComponent } from '../../../../../../main/webapp/app/entities/execucao/execucao-dialog.component';
import { ExecucaoService } from '../../../../../../main/webapp/app/entities/execucao/execucao.service';
import { Execucao } from '../../../../../../main/webapp/app/entities/execucao/execucao.model';
import { SituacaoService } from '../../../../../../main/webapp/app/entities/situacao';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua';
import { ContratoService } from '../../../../../../main/webapp/app/entities/contrato';

describe('Component Tests', () => {

    describe('Execucao Management Dialog Component', () => {
        let comp: ExecucaoDialogComponent;
        let fixture: ComponentFixture<ExecucaoDialogComponent>;
        let service: ExecucaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ExecucaoDialogComponent],
                providers: [
                    SituacaoService,
                    ProgramasProjectosService,
                    SistemaAguaService,
                    ContratoService,
                    ExecucaoService
                ]
            })
            .overrideTemplate(ExecucaoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ExecucaoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExecucaoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Execucao(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.execucao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'execucaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Execucao();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.execucao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'execucaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
