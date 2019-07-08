/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { ConcursoDialogComponent } from '../../../../../../main/webapp/app/entities/concurso/concurso-dialog.component';
import { ConcursoService } from '../../../../../../main/webapp/app/entities/concurso/concurso.service';
import { Concurso } from '../../../../../../main/webapp/app/entities/concurso/concurso.model';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua';

describe('Component Tests', () => {

    describe('Concurso Management Dialog Component', () => {
        let comp: ConcursoDialogComponent;
        let fixture: ComponentFixture<ConcursoDialogComponent>;
        let service: ConcursoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ConcursoDialogComponent],
                providers: [
                    ProgramasProjectosService,
                    SistemaAguaService,
                    ConcursoService
                ]
            })
            .overrideTemplate(ConcursoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConcursoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConcursoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Concurso(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.concurso = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'concursoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Concurso();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.concurso = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'concursoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
