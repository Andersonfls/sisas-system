/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { PublicacaoLogComponent } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log.component';
import { PublicacaoLogService } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log.service';
import { PublicacaoLog } from '../../../../../../main/webapp/app/entities/publicacao-log/publicacao-log.model';

describe('Component Tests', () => {

    describe('PublicacaoLog Management Component', () => {
        let comp: PublicacaoLogComponent;
        let fixture: ComponentFixture<PublicacaoLogComponent>;
        let service: PublicacaoLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [PublicacaoLogComponent],
                providers: [
                    PublicacaoLogService
                ]
            })
            .overrideTemplate(PublicacaoLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublicacaoLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublicacaoLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new PublicacaoLog(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.publicacaoLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
