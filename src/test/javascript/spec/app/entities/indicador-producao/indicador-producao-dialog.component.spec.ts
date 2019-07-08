/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoDialogComponent } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao-dialog.component';
import { IndicadorProducaoService } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.service';
import { IndicadorProducao } from '../../../../../../main/webapp/app/entities/indicador-producao/indicador-producao.model';
import { SituacaoService } from '../../../../../../main/webapp/app/entities/situacao';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua';
import { ComunaService } from '../../../../../../main/webapp/app/entities/comuna';

describe('Component Tests', () => {

    describe('IndicadorProducao Management Dialog Component', () => {
        let comp: IndicadorProducaoDialogComponent;
        let fixture: ComponentFixture<IndicadorProducaoDialogComponent>;
        let service: IndicadorProducaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoDialogComponent],
                providers: [
                    SituacaoService,
                    SistemaAguaService,
                    ComunaService,
                    IndicadorProducaoService
                ]
            })
            .overrideTemplate(IndicadorProducaoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new IndicadorProducao(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.indicadorProducao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'indicadorProducaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new IndicadorProducao();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.indicadorProducao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'indicadorProducaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
