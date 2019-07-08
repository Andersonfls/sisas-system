/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { EntidadeGestoraComponent } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.component';
import { EntidadeGestoraService } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.service';
import { EntidadeGestora } from '../../../../../../main/webapp/app/entities/entidade-gestora/entidade-gestora.model';

describe('Component Tests', () => {

    describe('EntidadeGestora Management Component', () => {
        let comp: EntidadeGestoraComponent;
        let fixture: ComponentFixture<EntidadeGestoraComponent>;
        let service: EntidadeGestoraService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [EntidadeGestoraComponent],
                providers: [
                    EntidadeGestoraService
                ]
            })
            .overrideTemplate(EntidadeGestoraComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntidadeGestoraComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntidadeGestoraService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new EntidadeGestora(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.entidadeGestoras[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
