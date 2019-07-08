/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { sisasTestModule } from '../../../test.module';
import { FornecedorDetailComponent } from '../../../../../../main/webapp/app/entities/fornecedor/fornecedor-detail.component';
import { FornecedorService } from '../../../../../../main/webapp/app/entities/fornecedor/fornecedor.service';
import { Fornecedor } from '../../../../../../main/webapp/app/entities/fornecedor/fornecedor.model';

describe('Component Tests', () => {

    describe('Fornecedor Management Detail Component', () => {
        let comp: FornecedorDetailComponent;
        let fixture: ComponentFixture<FornecedorDetailComponent>;
        let service: FornecedorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [sisasTestModule],
                declarations: [FornecedorDetailComponent],
                providers: [
                    FornecedorService
                ]
            })
            .overrideTemplate(FornecedorDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FornecedorDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FornecedorService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Fornecedor(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.fornecedor).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
