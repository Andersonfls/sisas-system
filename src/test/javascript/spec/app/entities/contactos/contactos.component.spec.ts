/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ContactosComponent } from '../../../../../../main/webapp/app/entities/contactos/contactos.component';
import { ContactosService } from '../../../../../../main/webapp/app/entities/contactos/contactos.service';
import { Contactos } from '../../../../../../main/webapp/app/entities/contactos/contactos.model';

describe('Component Tests', () => {

    describe('Contactos Management Component', () => {
        let comp: ContactosComponent;
        let fixture: ComponentFixture<ContactosComponent>;
        let service: ContactosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ContactosComponent],
                providers: [
                    ContactosService
                ]
            })
            .overrideTemplate(ContactosComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContactosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContactosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Contactos(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.contactos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
