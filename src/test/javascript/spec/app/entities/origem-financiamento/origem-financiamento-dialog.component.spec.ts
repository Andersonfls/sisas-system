/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { OrigemFinanciamentoDialogComponent } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento-dialog.component';
import { OrigemFinanciamentoService } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.service';
import { OrigemFinanciamento } from '../../../../../../main/webapp/app/entities/origem-financiamento/origem-financiamento.model';

describe('Component Tests', () => {

    describe('OrigemFinanciamento Management Dialog Component', () => {
        let comp: OrigemFinanciamentoDialogComponent;
        let fixture: ComponentFixture<OrigemFinanciamentoDialogComponent>;
        let service: OrigemFinanciamentoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [OrigemFinanciamentoDialogComponent],
                providers: [
                    OrigemFinanciamentoService
                ]
            })
            .overrideTemplate(OrigemFinanciamentoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OrigemFinanciamentoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrigemFinanciamentoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new OrigemFinanciamento(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.origemFinanciamento = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'origemFinanciamentoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new OrigemFinanciamento();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.origemFinanciamento = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'origemFinanciamentoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
