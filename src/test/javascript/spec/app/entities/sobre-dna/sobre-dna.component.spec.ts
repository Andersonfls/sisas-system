/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { SobreDnaComponent } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna.component';
import { SobreDnaService } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna.service';
import { SobreDna } from '../../../../../../main/webapp/app/entities/sobre-dna/sobre-dna.model';

describe('Component Tests', () => {

    describe('SobreDna Management Component', () => {
        let comp: SobreDnaComponent;
        let fixture: ComponentFixture<SobreDnaComponent>;
        let service: SobreDnaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [SobreDnaComponent],
                providers: [
                    SobreDnaService
                ]
            })
            .overrideTemplate(SobreDnaComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SobreDnaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SobreDnaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SobreDna(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.sobreDnas[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
