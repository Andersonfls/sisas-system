
export class FileResponseModel {
    constructor(
        public fileName?: string,
        public fileDownloadUri?: string,
        public fileType?: string,
        public size?: number,
        public documentoId?: number,
    ) {
    }
}
