import * as XLSX from 'xlsx';

export class TableUtil {
    static exportToExcel(tableId: string, name?: string) {
        const timeSpan = new Date().toISOString();
        const prefix = name || 'ExportResult';
        const fileName = `${prefix}-${timeSpan}`;
        const targetTableElm = document.getElementById(tableId);
        const wb = XLSX.utils.table_to_book(targetTableElm, <XLSX.Table2SheetOpts>{ sheet: prefix });
        XLSX.writeFile(wb, `${fileName}.xlsx`);
    }
}
