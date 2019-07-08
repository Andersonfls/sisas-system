/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { ProgramasProjectosDialogComponent } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos-dialog.component';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos.service';
import { ProgramasProjectos } from '../../../../../../main/webapp/app/entities/programas-projectos/programas-projectos.model';
import { ComunaService } from '../../../../../../main/webapp/app/entities/comuna';

describe('Component Tests', () => {

    describe('ProgramasProjectos Management Dialog Component', () => {
        let comp: ProgramasProjectosDialogComponent;
        let fixture: ComponentFixture<ProgramasProjectosDialogComponent>;
        let service: ProgramasProjectosService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProgramasProjectosDialogComponent],
                providers: [
                    ComunaService,
                    ProgramasProjectosService
                ]
            })
            .overrideTemplate(ProgramasProjectosDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProgramasProjectosDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasProjectosService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ProgramasProjectos(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.programasProjectos = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'programasProjectosListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ProgramasProjectos();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.programasProjectos = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'programasProjectosListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
