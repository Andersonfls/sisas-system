package com.minea.sisas.service;

import com.minea.sisas.domain.Provincia;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.service.dto.*;
import com.minea.sisas.service.mapper.ProvinciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Service Implementation for managing Provincia.
 */
@Service
@Transactional
public class RelatorioService {

    private final Logger log = LoggerFactory.getLogger(RelatorioService.class);

    private final ProvinciaRepository provinciaRepository;

    private final ProvinciaMapper provinciaMapper;

    public RelatorioService(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    public List<SectorAguaDadosDTO> montaListaEstáticaParaTeste(){
        List<SectorAguaDadosDTO> retorno = new ArrayList<>();

        retorno.add(new SectorAguaDadosDTO("Bengo", 8, 20, 140, 271942l, 104045l, 38));
        retorno.add(new SectorAguaDadosDTO("Benguela", 9, 38, 415, 1597296l, 1256251l, 79));
        retorno.add(new SectorAguaDadosDTO("Bié", 9, 39, 396, 901120l, 119327l, 13));
        retorno.add(new SectorAguaDadosDTO("Cabinda", 4, 12, 47, 349501l, 90860l, 26));
        retorno.add(new SectorAguaDadosDTO("Cunene", 6, 31, 140, 507559l, 69387l, 14));
        retorno.add(new SectorAguaDadosDTO("Huambo", 11, 36, 140, 1239776l, 276844l, 22));
        retorno.add(new SectorAguaDadosDTO("Huíla", 14, 33, 140, 1683569l, 503533l, 30));
        retorno.add(new SectorAguaDadosDTO("Kuando Kubango", 9, 24, 140, 306214l, 32456l, 11));
        retorno.add(new SectorAguaDadosDTO("Kuanza Norte", 10, 14, 140, 291250l, 103952l, 36));
        retorno.add(new SectorAguaDadosDTO("Kuanza Sul", 12, 51, 140, 1036517l, 274980l, 27));
        retorno.add(new SectorAguaDadosDTO("Luanda", 9, 29, 140, 4749423l, 1851281l, 39));
        retorno.add(new SectorAguaDadosDTO("Luanda Norte", 9, 24, 140, 604976l, 222375l, 37));
        retorno.add(new SectorAguaDadosDTO("Luanda Sul", 4, 14, 140, 300318l, 50334l, 17));
        retorno.add(new SectorAguaDadosDTO("Malanje", 14, 51, 140, 598097l, 128478l, 21));
        retorno.add(new SectorAguaDadosDTO("Moxico", 9, 29, 140, 444233l, 135841l, 31));
        retorno.add(new SectorAguaDadosDTO("Namibe", 5, 14, 140, 289144l, 184006l, 64));
        retorno.add(new SectorAguaDadosDTO("Uíge", 16, 47, 140, 890821l, 214186l, 24));
        retorno.add(new SectorAguaDadosDTO("Zaire", 6, 25, 140, 306122l, 41631l, 14));

        return retorno;
    }

    public List<SectorAguaSaneamentoDadosDTO> montaListaEstáticaParaTesteSaneamento(){
        List<SectorAguaSaneamentoDadosDTO> retorno = new ArrayList<>();

        retorno.add(new SectorAguaSaneamentoDadosDTO("NACIONAL", 16367878l, 6033618l, 37, 3791035l, 23, 100));
        retorno.add(new SectorAguaSaneamentoDadosDTO("URBANA", 8969846l, 3092073l, 31, 1438965l, 14, 55));
        retorno.add(new SectorAguaSaneamentoDadosDTO("RURAL", 7398033l, 2941545l, 46, 2352070l, 37, 45));
        return retorno;
    }


    public List<FuncAguaChafarizesDadosDTO> montaListaEstáticaParaTesteAguaChafarizes(){
        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();

        retorno.add(new FuncAguaChafarizesDadosDTO("Bengo", 140, 98, 42, 100, 0, 226, 188, 38, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Benguela", 415, 411, 4, 100, 0, 896, 606, 290, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Bié", 396, 329, 67, 100, 0, 140, 131, 9, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Cabinda", 47, 32, 15, 100, 0, 369, 236, 133, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Cunene", 348, 293, 55, 100, 0, 56, 20, 36, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Huambo", 551, 417, 134, 100, 0, 562, 525, 37, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Hulia", 979, 840, 139, 100, 0, 370, 349, 21, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Kuando Kubango", 39, 33, 6, 100, 0, 64, 59, 5, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Kuanza Norte", 90, 71, 19, 100, 0, 255, 196, 59, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Kuanza Sul", 42, 31, 11, 100, 0, 210, 135, 75, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Luanda", 36, 22, 14, 100, 0, 822, 515, 307, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Luanda Norte", 38, 38, 0, 100, 0, 182, 126, 56, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Luanda Sul", 25, 15, 10, 100, 0, 91, 84, 7, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Malanje", 548, 233, 315, 100, 0, 114, 67, 47, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Moxico", 165, 125, 40, 100, 0, 190, 143, 47, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Namibe", 173, 110, 63, 100, 0, 322, 297, 25, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Uíge", 137, 76, 61, 100, 0, 525, 326, 199, 0, 0));
        retorno.add(new FuncAguaChafarizesDadosDTO("Zaíre", 17, 14, 3, 100, 0, 76, 73, 3, 0, 0));
        return retorno;
    }


    public List<InqueritosPreenchidosDadosDTO> montaListaEstáticaParaTesteInqueritoAguas(){
        List<InqueritosPreenchidosDadosDTO> retorno = new ArrayList<>();
        retorno.add(new InqueritosPreenchidosDadosDTO("Bengo", 8, 33, 140, 286, 426, 40, 0, 40, 486,0, 0, 0, 0, 0));
        retorno.add(new InqueritosPreenchidosDadosDTO("Benguela", 9, 38, 415, 1740, 2155, 6, 36, 42, 2197,0, 0, 0, 0, 0));
        retorno.add(new InqueritosPreenchidosDadosDTO("Bié", 9, 39, 396, 2221, 2617, 115, 50, 165, 2782,0, 0, 0, 0, 0));
        retorno.add(new InqueritosPreenchidosDadosDTO("Cabinda", 4, 12, 47, 248, 295, 17, 0, 17, 312,0, 0, 0, 0, 0));
        retorno.add(new InqueritosPreenchidosDadosDTO("Cunene", 6, 20, 348, 274, 622, 32, 0, 32, 654,0, 0, 0, 0, 0));
        retorno.add(new InqueritosPreenchidosDadosDTO("Huambo", 11, 36, 551, 1266, 1817, 23, 0, 23, 1840,0, 0, 0, 0, 0));

        return retorno;
    }

    public List<TratamentoSistemasAguaDadosDTO> montaListaTratamentoSistemasAguas(){
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();

        retorno.add(new TratamentoSistemasAguaDadosDTO("Bengo", 140, 10, 3, 127, 0));
        retorno.add(new TratamentoSistemasAguaDadosDTO("Benguela", 415, 16, 6, 386, 0));
        retorno.add(new TratamentoSistemasAguaDadosDTO("Bié", 396, 1, 8, 444, 0));
        retorno.add(new TratamentoSistemasAguaDadosDTO("Cabinda", 47, 7, 13, 323, 0));
        retorno.add(new TratamentoSistemasAguaDadosDTO("Cunene", 398, 0, 31, 563, 0));
        retorno.add(new TratamentoSistemasAguaDadosDTO("Huambo", 551, 4, 33, 127, 0));

        return retorno;
    }



}
