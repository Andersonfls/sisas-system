/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { sisasTestModule } from '../../../test.module';
import { FornecedorComponent } from '../../../../../../main/webapp/app/entities/fornecedor/fornecedor.component';
import { FornecedorService } from '../../../../../../main/webapp/app/entities/fornecedor/fornecedor.service';
import { Fornecedor } from '../../../../../../main/webapp/app/entities/fornecedor/fornecedor.model';

describe('Component Tests', () => {

    describe('Fornecedor Management Component', () => {
        let comp: FornecedorComponent;
        let fixture: ComponentFixture<FornecedorComponent>;
        let service: FornecedorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [FornecedorComponent],
                providers: [
                    FornecedorService
                ]
            })
            .overrideTemplate(FornecedorComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FornecedorComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FornecedorService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Fornecedor(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.fornecedors[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
