/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { ProgramasProjectosLogDialogComponent } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log-dialog.component';
import { ProgramasProjectosLogService } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.service';
import { ProgramasProjectosLog } from '../../../../../../main/webapp/app/entities/programas-projectos-log/programas-projectos-log.model';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos';

describe('Component Tests', () => {

    describe('ProgramasProjectosLog Management Dialog Component', () => {
        let comp: ProgramasProjectosLogDialogComponent;
        let fixture: ComponentFixture<ProgramasProjectosLogDialogComponent>;
        let service: ProgramasProjectosLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProgramasProjectosLogDialogComponent],
                providers: [
                    ProgramasProjectosService,
                    ProgramasProjectosLogService
                ]
            })
            .overrideTemplate(ProgramasProjectosLogDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProgramasProjectosLogDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasProjectosLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ProgramasProjectosLog(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.programasProjectosLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'programasProjectosLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ProgramasProjectosLog();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.programasProjectosLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'programasProjectosLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
