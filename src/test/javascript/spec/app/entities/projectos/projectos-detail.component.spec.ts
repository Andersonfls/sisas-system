/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { ProjectosDetailComponent } from '../../../../../../main/webapp/app/entities/projectos/projectos-detail.component';
import { ProjectosService } from '../../../../../../main/webapp/app/entities/projectos/projectos.service';
import { Projectos } from '../../../../../../main/webapp/app/entities/projectos/projectos.model';

describe('Component Tests', () => {

    describe('Projectos Management Detail Component', () => {
        let comp: ProjectosDetailComponent;
        let fixture: ComponentFixture<ProjectosDetailComponent>;
        let service: ProjectosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [ProjectosDetailComponent],
                providers: [
                    ProjectosService
                ]
            })
            .overrideTemplate(ProjectosDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProjectosDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProjectosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Projectos(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.projectos).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
