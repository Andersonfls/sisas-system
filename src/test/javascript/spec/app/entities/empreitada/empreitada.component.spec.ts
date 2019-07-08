/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { EmpreitadaComponent } from '../../../../../../main/webapp/app/entities/empreitada/empreitada.component';
import { EmpreitadaService } from '../../../../../../main/webapp/app/entities/empreitada/empreitada.service';
import { Empreitada } from '../../../../../../main/webapp/app/entities/empreitada/empreitada.model';

describe('Component Tests', () => {

    describe('Empreitada Management Component', () => {
        let comp: EmpreitadaComponent;
        let fixture: ComponentFixture<EmpreitadaComponent>;
        let service: EmpreitadaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [EmpreitadaComponent],
                providers: [
                    EmpreitadaService
                ]
            })
            .overrideTemplate(EmpreitadaComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EmpreitadaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmpreitadaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Empreitada(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.empreitadas[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
