/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { sisasTestModule } from '../../../test.module';
import { MunicipiosAtendidosDialogComponent } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos-dialog.component';
import { MunicipiosAtendidosService } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.service';
import { MunicipiosAtendidos } from '../../../../../../main/webapp/app/entities/municipios-atendidos/municipios-atendidos.model';
import { MunicipioService } from '../../../../../../main/webapp/app/entities/municipio';

describe('Component Tests', () => {

    describe('MunicipiosAtendidos Management Dialog Component', () => {
        let comp: MunicipiosAtendidosDialogComponent;
        let fixture: ComponentFixture<MunicipiosAtendidosDialogComponent>;
        let service: MunicipiosAtendidosService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [MunicipiosAtendidosDialogComponent],
                providers: [
                    MunicipioService,
                    MunicipiosAtendidosService
                ]
            })
            .overrideTemplate(MunicipiosAtendidosDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MunicipiosAtendidosDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MunicipiosAtendidosService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MunicipiosAtendidos(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.municipiosAtendidos = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'municipiosAtendidosListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MunicipiosAtendidos();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.municipiosAtendidos = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'municipiosAtendidosListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
