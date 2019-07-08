/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { AdjudicacaoDialogComponent } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao-dialog.component';
import { AdjudicacaoService } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao.service';
import { Adjudicacao } from '../../../../../../main/webapp/app/entities/adjudicacao/adjudicacao.model';
import { ProgramasProjectosService } from '../../../../../../main/webapp/app/entities/programas-projectos';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua';

describe('Component Tests', () => {

    describe('Adjudicacao Management Dialog Component', () => {
        let comp: AdjudicacaoDialogComponent;
        let fixture: ComponentFixture<AdjudicacaoDialogComponent>;
        let service: AdjudicacaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [AdjudicacaoDialogComponent],
                providers: [
                    ProgramasProjectosService,
                    SistemaAguaService,
                    AdjudicacaoService
                ]
            })
            .overrideTemplate(AdjudicacaoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AdjudicacaoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdjudicacaoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Adjudicacao(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.adjudicacao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'adjudicacaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Adjudicacao();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.adjudicacao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'adjudicacaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
