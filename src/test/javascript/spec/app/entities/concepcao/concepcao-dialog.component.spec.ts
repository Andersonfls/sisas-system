/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { ConcepcaoDialogComponent } from '../../../../../../main/webapp/app/entities/concepcao/concepcao-dialog.component';
import { ConcepcaoService } from '../../../../../../main/webapp/app/entities/concepcao/concepcao.service';
import { Concepcao } from '../../../../../../main/webapp/app/entities/concepcao/concepcao.model';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua';

describe('Component Tests', () => {

    describe('Concepcao Management Dialog Component', () => {
        let comp: ConcepcaoDialogComponent;
        let fixture: ComponentFixture<ConcepcaoDialogComponent>;
        let service: ConcepcaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ConcepcaoDialogComponent],
                providers: [
                    ProgramasProjectosService,
                    SistemaAguaService,
                    ConcepcaoService
                ]
            })
            .overrideTemplate(ConcepcaoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConcepcaoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConcepcaoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Concepcao(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.concepcao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'concepcaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Concepcao();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.concepcao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'concepcaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
