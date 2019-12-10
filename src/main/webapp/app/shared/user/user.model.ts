import {Provincia} from '../../entities/provincia/provincia.model';
import {Municipio} from '../../entities/municipio/municipio.model';
import {Comuna} from '../../entities/comuna/comuna.model';

export class User {

    public id?: any;
    public login?: string;
    public nome?: string;
    public enderecoCep?: string;
    public enderecoLogadouro?: string;
    public enderecoLote?: number;
    public enderecoComplemento?: string;
    public enderecoBairro?: string;
    public enderecoCidade?: string;
    public enderecoUf?: string;
    public rfAssociado?: string;
    public brAssociado?: string;
    public qrAssociado?: string;
    public nfAssociado?: string;
    public perfilAdm?: boolean;
    public modConfig?: boolean;
    public modBalcao?: boolean;
    public modToten?: boolean;
    public modMobile?: boolean;
    public telefone?: string;
    public celular?: string;
    public email?: string;
    public provincia?: Provincia;
    public municipio?: Municipio;
    public comuna?: Comuna;
    public activated?: Boolean;
    public langKey?: string;
    public authorities?: any[];
    public createdBy?: string;
    public createdDate?: Date;
    public lastModifiedBy?: string;
    public lastModifiedDate?: Date;
    public password?: string;

    constructor(
        id?: any,
        login?: string,
        nome?: string,
        enderecoCep?: string,
        enderecoLogadouro?: string,
        enderecoLote?: number,
        enderecoComplemento?: string,
        enderecoBairro?: string,
        enderecoCidade?: string,
        enderecoUf?: string,
        rfAssociado?: string,
        brAssociado?: string,
        qrAssociado?: string,
        nfAssociado?: string,
        perfilAdm?: boolean,
        modConfig?: boolean,
        modBalcao?: boolean,
        modToten?: boolean,
        modMobile?: boolean,
        telefone?: string,
        celular?: string,
        email?: string,
        provincia?: Provincia,
        municipio?: Municipio,
        comuna?: Comuna,
        activated?: Boolean,
        langKey?: string,
        authorities?: any[],
        createdBy?: string,
        createdDate?: Date,
        lastModifiedBy?: string,
        lastModifiedDate?: Date,
        password?: string
    ) {
        this.id = id ? id : null;
        this.login = login ? login : null;
        this.nome = nome ? nome : null;
        this.enderecoCep = enderecoCep ? enderecoCep : null;
        this.enderecoLogadouro = enderecoLogadouro ? enderecoLogadouro : null;
        this.enderecoLote = enderecoLote ? enderecoLote : null;
        this.enderecoComplemento = enderecoComplemento ? enderecoComplemento : null;
        this.enderecoBairro = enderecoBairro ? enderecoBairro : null;
        this.enderecoCidade = enderecoCidade ? enderecoCidade : null;
        this.enderecoUf = enderecoUf ? enderecoUf : null;
        this.rfAssociado = rfAssociado ? rfAssociado : null;
        this.brAssociado = brAssociado ? brAssociado : null;
        this.qrAssociado = qrAssociado ? qrAssociado : null;
        this.nfAssociado = nfAssociado ? nfAssociado : null;
        this.email = email ? email : null;
        this.provincia = provincia ? provincia : null;
        this.municipio = municipio ? municipio : null;
        this.comuna = comuna ? comuna : null;
        this.activated = activated ? activated : false;
        this.langKey = langKey ? langKey : null;
        this.authorities = authorities ? authorities : null;
        this.createdBy = createdBy ? createdBy : null;
        this.createdDate = createdDate ? createdDate : null;
        this.lastModifiedBy = lastModifiedBy ? lastModifiedBy : null;
        this.lastModifiedDate = lastModifiedDate ? lastModifiedDate : null;
        this.password = password ? password : null;
    }
}
