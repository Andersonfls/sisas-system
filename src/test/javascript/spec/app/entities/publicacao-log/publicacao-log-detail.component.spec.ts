/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { PublicacaoLogDetailComponent } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log-detail.component';
import { PublicacaoLogService } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log.service';
import { PublicacaoLog } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log.model';

describe('Component Tests', () => {

    describe('PublicacaoLog Management Detail Component', () => {
        let comp: PublicacaoLogDetailComponent;
        let fixture: ComponentFixture<PublicacaoLogDetailComponent>;
        let service: PublicacaoLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [PublicacaoLogDetailComponent],
                providers: [
                    PublicacaoLogService
                ]
            })
            .overrideTemplate(PublicacaoLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublicacaoLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublicacaoLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new PublicacaoLog(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.publicacaoLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
