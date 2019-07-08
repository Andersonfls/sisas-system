/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { SistemaAguaComponent } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua.component';
import { SistemaAguaService } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua.service';
import { SistemaAgua } from '../../../../../../main/webapp/app/entities/sistema-agua/sistema-agua.model';

describe('Component Tests', () => {

    describe('SistemaAgua Management Component', () => {
        let comp: SistemaAguaComponent;
        let fixture: ComponentFixture<SistemaAguaComponent>;
        let service: SistemaAguaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SistemaAguaComponent],
                providers: [
                    SistemaAguaService
                ]
            })
            .overrideTemplate(SistemaAguaComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SistemaAguaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SistemaAguaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SistemaAgua(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.sistemaAguas[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
