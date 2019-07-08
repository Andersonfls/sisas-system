/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ContactosDetailComponent } from '../../../../../../main/webapp/app/entities/contactos/contactos-detail.component';
import { ContactosService } from '../../../../../../main/webapp/app/entities/contactos/contactos.service';
import { Contactos } from '../../../../../../main/webapp/app/entities/contactos/contactos.model';

describe('Component Tests', () => {

    describe('Contactos Management Detail Component', () => {
        let comp: ContactosDetailComponent;
        let fixture: ComponentFixture<ContactosDetailComponent>;
        let service: ContactosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ContactosDetailComponent],
                providers: [
                    ContactosService
                ]
            })
            .overrideTemplate(ContactosDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContactosDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContactosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Contactos(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.contactos).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
