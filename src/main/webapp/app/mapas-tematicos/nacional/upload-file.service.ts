import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';
import {createRequestOption} from '../../shared';
import {FileResponseModel} from './uploadFileResponse.model';

@Injectable()
export class UploadFileService {
     private resourceUrl =  SERVER_API_URL + 'api/arquivos';

    constructor(private http: HttpClient) {}

    pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
        const formdata: FormData = new FormData();

        formdata.append('file', file);

        const req = new HttpRequest('POST', this.resourceUrl, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }

    query(req?: any): Observable<HttpResponse<FileResponseModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<FileResponseModel[]>(this.resourceUrl + '/getallfiles', { params: options, observe: 'response' })
            .map((res: HttpResponse<FileResponseModel[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertArrayResponse(res: HttpResponse<FileResponseModel[]>): HttpResponse<FileResponseModel[]> {
        const jsonResponse: FileResponseModel[] = res.body;
        const body: FileResponseModel[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    getFile(id): Observable<HttpEvent<{}>> {
        const req = new HttpRequest('GET', `${SERVER_API_URL + 'api/downloadFile'}/${id}`);

        return this.http.request(req);
    }

    /**
     * Convert a returned JSON object to FileResponseModel.
     */
    private convertItemFromServer(file: FileResponseModel): FileResponseModel {
        const copy: FileResponseModel = Object.assign({}, file);
        return copy;
    }
}
