/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { IndicadorProducaoLogDialogComponent } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log-dialog.component';
import { IndicadorProducaoLogService } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.service';
import { IndicadorProducaoLog } from '../../../../../../main/webapp/app/entities/indicador-producao-log/indicador-producao-log.model';
import { IndicadorProducaoService } from '../../../../../../main/webapp/app/entities/indicador-producao';

describe('Component Tests', () => {

    describe('IndicadorProducaoLog Management Dialog Component', () => {
        let comp: IndicadorProducaoLogDialogComponent;
        let fixture: ComponentFixture<IndicadorProducaoLogDialogComponent>;
        let service: IndicadorProducaoLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [IndicadorProducaoLogDialogComponent],
                providers: [
                    IndicadorProducaoService,
                    IndicadorProducaoLogService
                ]
            })
            .overrideTemplate(IndicadorProducaoLogDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndicadorProducaoLogDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicadorProducaoLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new IndicadorProducaoLog(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.indicadorProducaoLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'indicadorProducaoLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new IndicadorProducaoLog();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.indicadorProducaoLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'indicadorProducaoLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
