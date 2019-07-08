/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { SistemaAguaLogDialogComponent } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log-dialog.component';
import { SistemaAguaLogService } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.service';
import { SistemaAguaLog } from '../../../../../../main/webapp/app/entities/sistema-agua-log/sistema-agua-log.model';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua';

describe('Component Tests', () => {

    describe('SistemaAguaLog Management Dialog Component', () => {
        let comp: SistemaAguaLogDialogComponent;
        let fixture: ComponentFixture<SistemaAguaLogDialogComponent>;
        let service: SistemaAguaLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SistemaAguaLogDialogComponent],
                providers: [
                    SistemaAguaService,
                    SistemaAguaLogService
                ]
            })
            .overrideTemplate(SistemaAguaLogDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SistemaAguaLogDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SistemaAguaLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SistemaAguaLog(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.sistemaAguaLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'sistemaAguaLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SistemaAguaLog();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.sistemaAguaLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'sistemaAguaLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
