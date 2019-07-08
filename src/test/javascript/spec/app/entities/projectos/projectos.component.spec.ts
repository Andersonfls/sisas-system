/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { ProjectosComponent } from '../../../../../../main/webapp/app/entities/projectos/projectos.component';
import { ProjectosService } from '../../../../../../main/webapp/app/entities/projectos/projectos.service';
import { Projectos } from '../../../../../../main/webapp/app/entities/projectos/projectos.model';

describe('Component Tests', () => {

    describe('Projectos Management Component', () => {
        let comp: ProjectosComponent;
        let fixture: ComponentFixture<ProjectosComponent>;
        let service: ProjectosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProjectosComponent],
                providers: [
                    ProjectosService
                ]
            })
            .overrideTemplate(ProjectosComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProjectosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProjectosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Projectos(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.projectos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
