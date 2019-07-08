/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { InicioDialogComponent } from '../../../../../../main/webapp/app/entities/inicio/inicio-dialog.component';
import { InicioService } from '../../../../../../main/webapp/app/entities/inicio/inicio.service';
import { Inicio } from '../../../../../../main/webapp/app/entities/inicio/inicio.model';
import { SituacaoService } from '../../../../../../main/webapp/app/entities/situacao';
import { SobreDnaService } from '../../../../../../main/webapp/app/entities/sobre-dna';
import { NoticiasService } from '../../../../../../main/webapp/app/entities/noticias';
import { ProjectosService } from '../../../../../../main/webapp/app/entities/projectos';
import { PublicacaoService } from '../../../../../../main/webapp/app/entities/publicacao';
import { ContactosService } from '../../../../../../main/webapp/app/entities/contactos';

describe('Component Tests', () => {

    describe('Inicio Management Dialog Component', () => {
        let comp: InicioDialogComponent;
        let fixture: ComponentFixture<InicioDialogComponent>;
        let service: InicioService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [InicioDialogComponent],
                providers: [
                    SituacaoService,
                    SobreDnaService,
                    NoticiasService,
                    ProjectosService,
                    PublicacaoService,
                    ContactosService,
                    InicioService
                ]
            })
            .overrideTemplate(InicioDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InicioDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InicioService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Inicio(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.inicio = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'inicioListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Inicio();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.inicio = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'inicioListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
