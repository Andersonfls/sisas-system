/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ComunaComponent } from '../../../../../../main/webapp/app/entities/comuna/comuna.component';
import { ComunaService } from '../../../../../../main/webapp/app/entities/comuna/comuna.service';
import { Comuna } from '../../../../../../main/webapp/app/entities/comuna/comuna.model';

describe('Component Tests', () => {

    describe('Comuna Management Component', () => {
        let comp: ComunaComponent;
        let fixture: ComponentFixture<ComunaComponent>;
        let service: ComunaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ComunaComponent],
                providers: [
                    ComunaService
                ]
            })
            .overrideTemplate(ComunaComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ComunaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComunaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Comuna(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.comunas[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
