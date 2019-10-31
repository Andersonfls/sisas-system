-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: sisas_control_acess
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adjudicacao`
--

DROP TABLE IF EXISTS `adjudicacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `adjudicacao` (
  `ID_ADJUDICACAO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) DEFAULT NULL,
  `TIPO_CONCURSO` varchar(150) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_COMUNICAO_ADJUDICACAO` date DEFAULT NULL,
  `DT_PRESTACAO_GARANT_BOA_EXEC` date DEFAULT NULL,
  `DT_SUBMISSAO_MINUT_CONTRATO` date DEFAULT NULL,
  PRIMARY KEY (`ID_ADJUDICACAO`),
  KEY `FK_ADJUDICACAO_ID_PROGRAMAS_PROJECTOS` (`ID_PROGRAMAS_PROJECTOS`),
  KEY `FK_ADJUDICACAO_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  CONSTRAINT `FK_ADJUDICACAO_ID_PROGRAMAS_PROJECTOS` FOREIGN KEY (`ID_PROGRAMAS_PROJECTOS`) REFERENCES `programas_projectos` (`id_programas_projectos`),
  CONSTRAINT `FK_ADJUDICACAO_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjudicacao`
--

LOCK TABLES `adjudicacao` WRITE;
/*!40000 ALTER TABLE `adjudicacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `adjudicacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comuna`
--

DROP TABLE IF EXISTS `comuna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comuna` (
  `ID_COMUNA` bigint(9) NOT NULL AUTO_INCREMENT,
  `NM_COMUNA` varchar(40) NOT NULL,
  `ID_MUNICIPIO` bigint(9) NOT NULL,
  PRIMARY KEY (`ID_COMUNA`),
  KEY `FK_COMUNA_MUNICIPIO` (`ID_MUNICIPIO`),
  CONSTRAINT `FK_COMUNA_MUNICIPIO` FOREIGN KEY (`ID_MUNICIPIO`) REFERENCES `municipio` (`id_municipio`)
) ENGINE=InnoDB AUTO_INCREMENT=181105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna`
--

LOCK TABLES `comuna` WRITE;
/*!40000 ALTER TABLE `comuna` DISABLE KEYS */;
INSERT INTO `comuna` VALUES (10101,'Cabinda',101),(10103,'Malembo',101),(10105,'Tando Zinze',101),(10301,'Cacongo',103),(10303,'Dinge',103),(10305,'Massabi',103),(10501,'Buco Zau',105),(10503,'Inhuca',105),(10505,'Necuto',105),(10701,'Belize',107),(10703,'Luali',107),(10705,'Miconge',107),(20101,'Mbanza Congo',201),(20103,'Luvo',201),(20105,'Madimba',201),(20107,'Quiende',201),(20109,'Caluca',201),(20111,'Calambata',201),(20301,'Soyo',203),(20303,'Pedra de Feitiço',203),(20305,'Sumba',203),(20307,'Quêlo',203),(20309,'Mangue Grande',203),(20501,'Nzeto',205),(20503,'Quideje',205),(20505,'Musserra',205),(20507,'Quibala Norte',205),(20701,'Tomboco',207),(20703,'Quinzau',207),(20705,'Quinsimba',207),(20901,'Nóqui',209),(20903,'Lufico',209),(20905,'Mpala',209),(21101,'Cuimba',211),(21103,'Buela',211),(21105,'Serra da Canda',211),(21107,'Luvaca',211),(30101,'Uige',301),(30301,'Nova Ambuíla',303),(30303,'Quipedro',303),(30501,'Songo',305),(30503,'Quinvuenga',305),(30701,'Bembe',307),(30703,'Lucunga',307),(30705,'Mabaia',307),(30901,'Negage',309),(30903,'Dimuca',309),(30905,'Quisseque',309),(31101,'Bungo',311),(31301,'Maquela do Zombo',313),(31303,'Quibocolo',313),(31305,'Béu',313),(31307,'Cuílo-Futa',313),(31309,'Sacandica',313),(31501,'Damba',315),(31503,'Nsosso',315),(31505,'Lêmboa',315),(31507,'Petecusso',315),(31509,'Camatambo',315),(31701,'Cangola',317),(31703,'Caiongo',317),(31705,'Bengo',317),(31901,'Sanza Pombo',319),(31903,'Alfândega',319),(31905,'Cuílo Pombo',319),(31907,'Uamba',319),(32101,'Quitexe',321),(32103,'Vista Alegre',321),(32105,'Aldeia de Viçosa',321),(32107,'Cambamba',321),(32301,'Quimbele',323),(32303,'Icoca',323),(32305,'Cuango',323),(32307,'Alto Zaza',323),(32501,'Milunga',325),(32503,'Macocola',325),(32505,'Macolo',325),(32507,'Massau',325),(32701,'Puri',327),(32901,'Mucaba',329),(32903,'Uando Mucaba',329),(33101,'Nova Esperança',331),(33103,'Cuílo Camboso',331),(33105,'Buenga Sul',331),(40101,'Ingombota',401),(40103,'Sambizanga',401),(40105,'Rangel',401),(40107,'Maianga',401),(40109,'Kilamba Kiaxi',401),(40111,'Samba',401),(40301,'Catete',403),(40303,'Cassoneca',403),(40305,'Cabiri',403),(40307,'Bom Jesus',403),(40309,'Caculo Cahango',403),(40501,'Muxima',405),(40503,'Demba Chio',405),(40505,'Quixingue',405),(40507,'Mumbondo',405),(40509,'Cabo Ledo',405),(40701,'Cacuaco',407),(40703,'Funda',407),(40705,'Kikolo',407),(40901,'Cazenga',409),(40903,'Hoji ya Henda',409),(40905,'Tala Hadi',409),(41101,'Viana',411),(41103,'Calumbo',411),(41105,'Zango',411),(41301,'Kilamba',413),(41303,'Mussulo',413),(41305,'Barra do Cuanza',413),(41307,'Benfica',413),(41309,'Ramiros',413),(41311,'Futungo de Belas',413),(41313,'Camama',413),(50101,'Ndalatando',501),(50103,'Canhoca',501),(50301,'Lucala',503),(50303,'Quiangombe',503),(50501,'Golungo Alto',505),(50503,'Cambondo',505),(50505,'Cerca',505),(50507,'Quiluaje',505),(50701,'Dondo',507),(50703,'Massagano',507),(50705,'S. Pedro da Quilemba',507),(50707,'Zenza do Itombe',507),(50709,'Dange ya Menha',507),(50901,'Camabatela',509),(50903,'Luínga',509),(50905,'Tango',509),(50907,'Maua',509),(50909,'Bindo',509),(51101,'Quiculungo',511),(51301,'Bolongongo',513),(51303,'Terreiro',513),(51305,'Quiquiemba',513),(51501,'Banga',515),(51503,'Aldeia Nova',515),(51505,'Caculo Cabaça',515),(51507,'Cariamba',515),(51701,'Samba Cajú',517),(51703,'Samba Lucala',507),(51901,'Quilombo dos Dembos',519),(51903,'Camame',519),(51905,'Cavunga',519),(60101,'Sumbe',601),(60103,'Gungo',601),(60105,'Gangula',601),(60107,'Quicombo',601),(60301,'Gabela',603),(60303,'Assango',603),(60501,'Quilenda',605),(60503,'Quirimbo',605),(60701,'Porto Amboim',607),(60703,'Capolo',607),(60901,'Calulo',609),(60903,'Munenga',609),(60905,'Cabuta',609),(60907,'Quissongo',609),(61101,'Quibala',611),(61103,'Dala Cachibo',611),(61105,'Cariango',611),(61107,'Lonhe',611),(61301,'Mussende',613),(61303,'Quienha',613),(61305,'São Lucas',613),(61501,'Seles',615),(61503,'Amboiva',615),(61505,'Botera',615),(61701,'Conda',617),(61703,'Cunjo',617),(61901,'Cassongue',619),(61903,'Dumbi',619),(61905,'Pambangala',619),(61907,'Atóme',619),(62101,'Waku Kungo',621),(62103,'Sanga',621),(62105,'Quissanga Cungo',621),(62301,'Ebo',623),(62303,'Condé',623),(62305,'Quissanje',623),(70101,'Malanje',701),(70103,'Cambaxe',701),(70105,'Ngola-Luiji',701),(70301,'Cacuso',703),(70303,'Pungu a Ndongo',703),(70305,'Lombe',703),(70307,'Quizenga',703),(70309,'Soqueco',703),(70501,'Calandula',705),(70503,'Cateco Cangola',705),(70505,'Cuale',705),(70507,'Cota',705),(70509,'Quinje',705),(70701,'Cabundi Catembo',707),(70703,'Talamungongo',707),(70705,'Dumba Cambango',707),(70707,'Quitapa',707),(70901,'Quela',709),(70903,'Xandel',709),(70905,'Moma',709),(70907,'Bângalas',709),(71101,'Cahombo',711),(71103,'Micanda',711),(71105,'Cambo Suinginge',711),(71107,'Mbanji ya Ngola',711),(71301,'Massango',713),(71303,'Quihuhu',713),(71305,'Quinguengue',713),(71501,'Luquembo',715),(71503,'Quimbango',715),(71505,'Dombo wa Zanga',715),(71507,'Capubda',715),(71509,'Cunga Palanca',715),(71511,'Rimba',715),(71701,'Marimba',717),(71703,'Cabombo',717),(71705,'Tempo Aluma',717),(71901,'Kunda dya Baze',719),(71903,'Milando',719),(71905,'Lemba',719),(72101,'Quirima',721),(72103,'Sautar',721),(72301,'Mucari',723),(72303,'Muquixi',723),(72305,'Caxinga',723),(72501,'Cagandala',723),(72503,'Caribo',725),(72505,'Bembo',725),(72507,'Calumagia',725),(72701,'Kiwaba Nzoji',727),(72703,'Mufuma',727),(80101,'Chitato',801),(80103,'Luachimo',801),(80105,'Dundo',801),(80107,'Mussungue',801),(80301,'Cuílo',803),(80303,'Caluango',803),(80501,'Lôvua',805),(80701,'Caungula',803),(80703,'Camaxilo',803),(80901,'Lubalo',805),(80903,'Luangue',805),(80905,'Muvuluege',805),(81101,'Capenda Camulemba',805),(81103,'Xinge',805),(81301,'Cuango',807),(81303,'Luremo',807),(81501,'Lucapa',809),(81503,'Capaia',809),(81505,'Camissombo',811),(81507,'Xa Cassau',811),(81701,'Cambulo',813),(81703,'Luia',813),(81705,'Cachimo',813),(81707,'Canzar',815),(81901,'Xá-Muteba',815),(81903,'Iongo',817),(81905,'Cassanje Calucala',817),(90101,'Benguela',901),(90301,'Baía Farta',903),(90303,'Dombe Grande',903),(90305,'Calohanga',903),(90307,'Equimina',903),(90501,'Lobito',905),(90503,'Canata',905),(90505,'Canjala',905),(90507,'Egipto Praia',905),(90701,'Cubal',907),(90703,'Iambala',907),(90705,'Capupa',907),(90707,'Tumbulo',907),(90901,'Ganda',909),(90903,'Babaera',909),(90905,'Chicuma',909),(90907,'Ebanga',909),(90909,'Casseque',909),(91101,'Balombo',911),(91103,'Chingongo',911),(91105,'Chindumbo',911),(91107,'Maca Mombolo',911),(91301,'Bocoio',913),(91303,'Chila',913),(91305,'Monte Belo',913),(91307,'Passe',913),(91309,'Cubal do Lumbo',913),(91501,'Caimbambo',915),(91503,'Catengue',915),(91505,'Canhamela',915),(91507,'Caiave',915),(91509,'Viangombe',915),(91701,'Chongoroi',917),(91703,'Bolonguera',917),(91705,'Camuine',917),(91901,'Catumbela',919),(91903,'Biópio',919),(91905,'Gama',919),(91907,'Praia Bebé',919),(100101,'Huambo',1001),(100103,'Calima',1001),(100105,'Chipipa',1001),(100301,'Chicala',1003),(100303,'Mbave',1003),(100305,'Sambo',1003),(100307,'Samboto',1003),(100501,'Cachiungo',1005),(100503,'Chiumbo',1005),(100505,'Chinhama',1005),(100701,'Bailundo',1007),(100703,'Bimbe',1007),(100705,'Lunge',1007),(100707,'Luvemba',1007),(100709,'Hengue',1007),(100901,'Caála',1009),(100903,'Cuima',1009),(100905,'Catala',1009),(100907,'Calenga',1009),(101101,'Ecunha',1011),(101103,'Quipeio',1011),(101303,'Ucuma',1013),(101305,'Cacoma',1013),(101307,'Mundundo',1013),(101501,'Longonjo',1015),(101503,'Lépi',1015),(101505,'Iava',1015),(101507,'Chilata',1015),(101701,'Mungo',1017),(101703,'Cabuengo',1017),(101901,'Londuimbali',1019),(101903,'Cumbira',1019),(101905,'Galanga',1019),(101907,'Ussoque',1019),(101909,'Alto Hama',1019),(102101,'Chinjenje',1021),(102103,'Chiaca',1021),(110101,'Cuito',1101),(110103,'Trumba',1101),(110105,'Cambândua',1101),(110107,'Chicala',1101),(110109,'Cunje',1101),(110301,'Cunhinga',1103),(110303,'Belo Horizonte',1103),(110501,'Chinguar',1105),(110503,'Cutato',1105),(110505,'Cangote',1105),(110701,'Andulo',1107),(110703,'Chivaúlo',1107),(110705,'Cassumbe',1107),(110707,'Calucinga',1107),(110901,'Nharêa',1109),(110903,'Gamba',1109),(110905,'Caiei',1109),(110907,'Lúbia',1109),(110909,'Dando',1109),(111101,'Camacupa',1111),(111103,'Ringoma',1111),(111105,'Umpulo',1111),(111107,'Muinha',1111),(111109,'Cuanza',1111),(111301,'Cuemba',1113),(111303,'Munhango',1113),(111305,'Luando',1113),(111307,'Sachinemuna',1113),(111501,'Chitembo',1115),(111503,'Cachingues',1115),(111505,'Mumbue',1115),(111507,'Mutumbo',1115),(111509,'Malengue',1115),(111511,'Soma Cuanza',1115),(111701,'Catabola',1117),(111703,'Chipeta',1117),(111705,'Sande',1117),(111707,'Caiuera',1117),(111709,'Chiuca',1117),(120101,'Luena',1201),(120103,'Cachipoque',1201),(120105,'Lucusse',1201),(120107,'Muangai',1201),(120301,'Camanongue',1203),(120501,'Léua',1205),(120503,'Liangongo',1205),(120701,'Luacano',1207),(120703,'Lago - Dilolo',1207),(120901,'Kameia',1209),(121101,'Lumbala Nguinbo',1211),(121103,'Chiume',1211),(121105,'Lutembo',1211),(121107,'Mussuma',1211),(121109,'Ninda',1211),(121111,'Sessa',1211),(121113,'Luvuei',1211),(121301,'Luchazes',1213),(121303,'Tempué',1213),(121305,'Cassamba',1213),(121307,'Muié',1213),(121309,'Cangombe',1213),(121501,'Cazombo',1215),(121503,'Caianda',1215),(121505,'Calunda',1215),(121507,'Lóvua',1215),(121509,'Caquengue',1215),(121511,'Macondo',1215),(121513,'Nana Candundo',1215),(121701,'Luau',1217),(130101,'Menongue',1301),(130103,'Cueio',1301),(130105,'Caiundo',1301),(130107,'Missombo',1301),(130301,'Cuito Cuanavale',1303),(130303,'Longa',1303),(130305,'Lupire',1303),(130307,'Baixo Longa',1303),(130501,'Cuangar',1305),(130503,'Savate',1305),(130505,'Caíla',1305),(130701,'Rivungo',1307),(130703,'Luiana',1307),(130705,'Xipundo',1307),(130901,'Mavinga',1309),(130903,'Cunjamba',1309),(130905,'Cutuile',1309),(130907,'Luengue',1309),(131101,'Cuchi',1311),(131103,'Cutato',1311),(131105,'Chinguanja',1311),(131107,'Vissati',1311),(131301,'Dirico',1313),(131303,'Mucusso',1313),(131305,'Xamavera',1313),(131501,'Nancova',1315),(131503,'Rito',1315),(131701,'Calai',1317),(131703,'Maué',1317),(131715,'Mavengue',1317),(140101,'Moçamedes',1401),(140103,'Lucira',1401),(140105,'Bentiaba',1401),(140107,'Forte Santa Rita',1401),(140301,'Tômbua',1403),(140303,'Baía dos Tigres',1403),(140305,'Iona',1403),(140501,'Virei',1405),(140503,'Cainde',1405),(140701,'Bibala',1407),(140703,'Caitou',1407),(140705,'Lola',1407),(140707,'Capangombe',1407),(140901,'Camacuio',1409),(140903,'Mamué',1409),(140905,'Chingo',1409),(150101,'Lubango',1501),(150103,'Arrimba',1501),(150105,'Huila',1501),(150107,'Hoque',1501),(150301,'Cacula',1503),(150303,'Vite Vivale',1503),(150305,'Chituto',1503),(150307,'Tchicuaqueia',1503),(150501,'Chibia',1505),(150503,'Capunda Cavilongo',1505),(150505,'Jau',1505),(150507,'Quihita',1505),(150701,'Caconda',1507),(150703,'Gungue',1507),(150704,'Cusse',1507),(150705,'Uaba',1507),(150901,'Caluquembe',1509),(150903,'Calépi',1509),(150905,'Ngola',1509),(151101,'Quilengues',1511),(151103,'Impulo',1511),(151105,'Dinde',1511),(151301,'Cuvango',1513),(151303,'Vicungo',1513),(151305,'Galangue',1513),(151501,'Quipungo',1515),(151701,'Matala',1517),(151703,'Mulondo',1517),(151705,'Capelongo',1517),(151901,'Chicomba',1519),(151903,'Cutenda',1519),(152101,'Jamba',1521),(152103,'Dongo',1521),(152105,'Cassinga',1521),(152301,'Chipindo',1523),(152303,'Bambi',1523),(152501,'Chiange',1525),(152503,'Chibemba',1525),(152701,'Humpata',1527),(160101,'Ondjiva',1601),(160103,'Môngwa',1601),(160105,'Evale',1601),(160107,'Nehone Cafima',1601),(160109,'Tchomporo Oximolo',1601),(160301,'Xangongo',1603),(160303,'Ombala yo Mungo',1603),(160305,'Humbe',1603),(160307,'Mucope',1603),(160309,'Naulila',1603),(160501,'Mukolongodjo',1605),(160503,'Calonga',1605),(160507,'Cassueca',1605),(160509,'Mupa',1605),(160701,'Oncócua',1607),(160703,'Chitato',1607),(160901,'Namacunde',1609),(160903,'Chiede',1609),(161101,'Cahama',1611),(161103,'Otchinjau',1611),(170101,'Saurimo',1701),(170102,'Mona Quimbundo',1701),(170103,'Sombo',1701),(170301,'Muconda',1703),(170303,'Cassai',1703),(170305,'Chiluage',1703),(170307,'Muriege',1703),(170501,'Dala',1705),(170503,'Cazage',1705),(170505,'Luma Cassai',1705),(170701,'Cacolo',1707),(170703,'Alto Chicapa',1707),(170705,'Cucumbi',1707),(170707,'Xassengue',1707),(180101,'Caxito',1801),(180103,'Barra do Dande',1801),(180105,'Quicabo',1801),(180107,'Úcua',1801),(180109,'Mabubas',1801),(180301,'Quibaxe',1803),(180303,'Piri',1803),(180305,'Paredes',1803),(180307,'Coxe',1803),(180501,'Pango - Aluquém',1805),(180503,'Cazuangongo',1805),(180701,'Ambriz',1807),(180703,'Bela Vista',1807),(180705,'Tabi',1807),(180901,'Muxiluando',1809),(180903,'Canacassala',1809),(180905,'Cage',1809),(180907,'Gombe',1809),(180909,'Quicunzo',1809),(180911,'Quixico',1809),(180913,'Zala',1809),(181101,'Bula - Atumba',1811),(181103,'Quiage',1811);
/*!40000 ALTER TABLE `comuna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concepcao`
--

DROP TABLE IF EXISTS `concepcao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `concepcao` (
  `ID_CONCEPCAO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) DEFAULT NULL,
  `TIPO_CONCURSO` varchar(150) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_ULTIMA_ALTERACAO` date DEFAULT NULL,
  `DT_ELABORACAO_CON` date DEFAULT NULL,
  `DT_APROVACAO_CON` date DEFAULT NULL,
  PRIMARY KEY (`ID_CONCEPCAO`),
  KEY `FK_CONCEPCAO_ID_PROGRAMAS_PROJECTOS` (`ID_PROGRAMAS_PROJECTOS`),
  KEY `FK_CONCEPCAO_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  CONSTRAINT `FK_CONCEPCAO_ID_PROGRAMAS_PROJECTOS` FOREIGN KEY (`ID_PROGRAMAS_PROJECTOS`) REFERENCES `programas_projectos` (`id_programas_projectos`),
  CONSTRAINT `FK_CONCEPCAO_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concepcao`
--

LOCK TABLES `concepcao` WRITE;
/*!40000 ALTER TABLE `concepcao` DISABLE KEYS */;
/*!40000 ALTER TABLE `concepcao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concurso`
--

DROP TABLE IF EXISTS `concurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `concurso` (
  `ID_CONCURSO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) DEFAULT NULL,
  `TIPO_CONCURSO` varchar(150) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_ULTIMA_ALTERACAO` date DEFAULT NULL,
  `DT_ENTREGA_PROPOSTA` date DEFAULT NULL,
  `DT_ABERTURA_PROPOSTA` date DEFAULT NULL,
  `DT_CONCLUSAO_AVALIACAO_REL_PREL` date DEFAULT NULL,
  `DT_NEGOCIACAO` date DEFAULT NULL,
  `DT_APROV_REL_AVAL_FINAL` date DEFAULT NULL,
  PRIMARY KEY (`ID_CONCURSO`),
  KEY `FK_CONCURSO_ID_PROGRAMAS_PROJECTOS` (`ID_PROGRAMAS_PROJECTOS`),
  KEY `FK_CONCURSO_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  CONSTRAINT `FK_CONCURSO_ID_PROGRAMAS_PROJECTOS` FOREIGN KEY (`ID_PROGRAMAS_PROJECTOS`) REFERENCES `programas_projectos` (`id_programas_projectos`),
  CONSTRAINT `FK_CONCURSO_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concurso`
--

LOCK TABLES `concurso` WRITE;
/*!40000 ALTER TABLE `concurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `concurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracoes_log`
--

DROP TABLE IF EXISTS `configuracoes_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `configuracoes_log` (
  `ID_CONFIGURACOES_LOG` bigint(9) NOT NULL AUTO_INCREMENT,
  `ACAO` varchar(50) NOT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `LOG` varchar(3500) NOT NULL,
  `DT_LOG` date NOT NULL,
  PRIMARY KEY (`ID_CONFIGURACOES_LOG`),
  KEY `FK_CONFIGURACOES_LOG_ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_CONFIGURACOES_LOG_ID_USUARIO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `sisas_control_access`.`usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracoes_log`
--

LOCK TABLES `configuracoes_log` WRITE;
/*!40000 ALTER TABLE `configuracoes_log` DISABLE KEYS */;
INSERT INTO `configuracoes_log` VALUES (1,'Situação Editar',2,'DE: teste PARA: teste','2019-06-22'),(2,'Situação Editar',2,'DE: teste PARA: teste','2019-06-22'),(3,'Fornecedor Salvar',2,'Novo Fornecedor: Teste nome Número do Contribuinte: Teste num contrib 123456 Endereço: Teste endereço Especialidade: Teste especialidade','2019-06-22'),(4,'Fornecedor Editar',2,'DE: Teste nom Número do Contribuinte: Teste num contrib 123456 Endereço: Teste endereço Especialidade: Teste especialidade PARA: Teste nom Número do Contribuinte: Teste num contrib 123456 Endereço: Teste endereço Especialidade: Teste especialidade','2019-06-22'),(5,'Fornecedor Editar',2,'DE: Teste nom Número do Contribuinte: Teste num contrib 123456 Endereço: Teste endereço Especialidade: Teste especialidade PARA: Teste nom Número do Contribuinte: Teste num contrib 123456 Endereço: Teste endereço Especialidade: Teste especialidade','2019-06-22'),(6,'Fornecedor Editar',2,'DE: Teste nome Número do Contribuinte: Teste num contrib 123456 Endereço: Teste endereço Especialidade: Teste especialidade PARA: Teste nome Número do Contribuinte: Teste num contrib 123456 Endereço: Teste endereço Especialidade: Teste especialidade','2019-06-22');
/*!40000 ALTER TABLE `configuracoes_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactos`
--

DROP TABLE IF EXISTS `contactos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contactos` (
  `ID_CONTACTOS` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `NM_CONTACTOS` varchar(150) NOT NULL,
  `TEXTO_CONTACTOS` varchar(2500) NOT NULL,
  `RESUMO_TEXTO_CONTACTOS` varchar(100) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_ULTIMA_ALTERACAO` date DEFAULT NULL,
  PRIMARY KEY (`ID_CONTACTOS`),
  KEY `FK_CONTACTOS_ID_SITUACAO` (`ID_SITUACAO`),
  CONSTRAINT `FK_CONTACTOS_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactos`
--

LOCK TABLES `contactos` WRITE;
/*!40000 ALTER TABLE `contactos` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contrato` (
  `ID_CONTRATO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) NOT NULL,
  `TIPO_EMPREITADA` varchar(150) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `NM_EMPRESA_ADJUDICITARIA` varchar(200) DEFAULT NULL,
  `VALOR_CONTRATO` decimal(15,2) NOT NULL,
  `DT_ASSINATURA` date DEFAULT NULL,
  `DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV` date DEFAULT NULL,
  `TIPO_MOEDA` varchar(150) NOT NULL,
  `VALOR_ADIANTAMENTO` decimal(15,2) NOT NULL,
  `DT_ADIANTAMENTO` date DEFAULT NULL,
  `DT_INICIO` date DEFAULT NULL,
  `PRAZO_EXECUCAO` decimal(15,0) DEFAULT NULL,
  `DT_RECEPCAO_PROVISORIA` date DEFAULT NULL,
  `DT_RECEPCAO_DEFINITIVA` date DEFAULT NULL,
  `DT_RECEPCAO_COMICIONAMENTO` date DEFAULT NULL,
  `NM_RESPOSAVEL_ANT_PROJETO` varchar(200) DEFAULT NULL,
  `NM_RESPOSAVEL_PROJETO` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID_CONTRATO`),
  KEY `FK_CONTRATO_ID_PROGRAMAS_PROJECTOS` (`ID_PROGRAMAS_PROJECTOS`),
  KEY `FK_CONTRATO_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  CONSTRAINT `FK_CONTRATO_ID_PROGRAMAS_PROJECTOS` FOREIGN KEY (`ID_PROGRAMAS_PROJECTOS`) REFERENCES `programas_projectos` (`id_programas_projectos`),
  CONSTRAINT `FK_CONTRATO_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2019-07-06 00:42:29',1,'EXECUTED','7:6dc662e5c746970b65f8fe5931655ac4','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.5.4',NULL,NULL,'2370149003');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empreitada`
--

DROP TABLE IF EXISTS `empreitada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empreitada` (
  `ID_EMPREITADA` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) DEFAULT NULL,
  `ID_CONTRATO` bigint(9) DEFAULT NULL,
  `TIPO_EMPREITADA` varchar(150) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `NUM_CAPACIDADE_CAPTACAO` decimal(15,2) NOT NULL,
  `NUM_CAPACIDADE_CAPTACAO_ETA` decimal(15,2) NOT NULL,
  `NUM_EXTENSAO_COND_ADUT_MAT` decimal(15,2) NOT NULL,
  `NUM_CAPRMAZENAMENTO` decimal(15,2) NOT NULL,
  `NUM_EXTENSAO_REDE_MAT` decimal(15,2) NOT NULL,
  `NUM_LIGACOES_DOMICILIARES` decimal(15,2) NOT NULL,
  `NUM_LIGACOES_TORNEIRA_QUINTAL` decimal(15,2) NOT NULL,
  `NUM_CHAFARIS_NOVOS` decimal(15,2) NOT NULL,
  `NUM_CHAFARIS_REABILITAR` decimal(15,2) NOT NULL,
  `NUM_CAPACIDADE_TRATAMENTO_ETA` decimal(15,2) NOT NULL,
  `NUM_EXTENSAO_REDE_MATERIAL` decimal(15,2) NOT NULL,
  `NUM_EXTENSAO_CONDUTAS_ELEL_MAT` decimal(15,2) NOT NULL,
  `NUM_LIGACOES` decimal(15,2) NOT NULL,
  `NUM_CAIXAS_VISITAS` decimal(15,2) NOT NULL,
  `NUM_ESTACOES_ELEVATORIAS` decimal(15,2) NOT NULL,
  `NUM_LATRINAS` decimal(15,2) NOT NULL,
  PRIMARY KEY (`ID_EMPREITADA`),
  KEY `FK_EMPREITADA_ID_PROGRAMAS_PROJECTOS` (`ID_PROGRAMAS_PROJECTOS`),
  KEY `FK_EMPREITADA_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  KEY `FK_EMPREITADA_ID_CONTRATO` (`ID_CONTRATO`),
  CONSTRAINT `FK_EMPREITADA_ID_CONTRATO` FOREIGN KEY (`ID_CONTRATO`) REFERENCES `contrato` (`id_contrato`),
  CONSTRAINT `FK_EMPREITADA_ID_PROGRAMAS_PROJECTOS` FOREIGN KEY (`ID_PROGRAMAS_PROJECTOS`) REFERENCES `programas_projectos` (`id_programas_projectos`),
  CONSTRAINT `FK_EMPREITADA_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empreitada`
--

LOCK TABLES `empreitada` WRITE;
/*!40000 ALTER TABLE `empreitada` DISABLE KEYS */;
/*!40000 ALTER TABLE `empreitada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidade_gestora`
--

DROP TABLE IF EXISTS `entidade_gestora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `entidade_gestora` (
  `ID_ENTIDADE_GESTORA` bigint(9) NOT NULL AUTO_INCREMENT,
  `NM_ENTIDADE_GESTORA` varchar(100) NOT NULL,
  `ID_MUNICIPIO_ATENDIDO` bigint(9) NOT NULL,
  `TP_FORMA_JURIDICA` bigint(9) NOT NULL,
  `DT_CONSTITUICAO` date DEFAULT NULL,
  `ENDERECO` varchar(100) NOT NULL,
  `EMAIL` varchar(80) DEFAULT NULL,
  `CONTACTOS` varchar(250) DEFAULT NULL,
  `TP_MODELO_GESTAO` bigint(9) NOT NULL,
  `NUM_RECURSOS_HUMANOS` bigint(9) NOT NULL,
  `NUM_POPULACAO_AREA_ATENDIMENTO` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`ID_ENTIDADE_GESTORA`),
  KEY `FK_ENTIDADE_GESTORA_ID_MUNICIPIO_ATENDIDO` (`ID_MUNICIPIO_ATENDIDO`),
  CONSTRAINT `FK_ENTIDADE_GESTORA_ID_MUNICIPIO_ATENDIDO` FOREIGN KEY (`ID_MUNICIPIO_ATENDIDO`) REFERENCES `municipios_atendidos` (`id_municipio_atendido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidade_gestora`
--

LOCK TABLES `entidade_gestora` WRITE;
/*!40000 ALTER TABLE `entidade_gestora` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidade_gestora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `execucao`
--

DROP TABLE IF EXISTS `execucao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `execucao` (
  `ID_EXECUCAO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) DEFAULT NULL,
  `ID_CONTRATO` bigint(9) DEFAULT NULL,
  `TIPO_EMPREITADA` varchar(150) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_PERIDO_REFERENCIA` date NOT NULL,
  `DT_FIM_REFERENCIA` date NOT NULL,
  `VALOR_FACTURADO_PERIODO` decimal(15,2) NOT NULL,
  `DT_FACTURA` date NOT NULL,
  `NUM_FACTURA` varchar(50) NOT NULL,
  `TX_CAMBIO` decimal(15,2) DEFAULT NULL,
  `CONSTRANGIMENTO` varchar(200) DEFAULT NULL,
  `VALOR_PAGO_PERIODO` decimal(15,2) NOT NULL,
  PRIMARY KEY (`ID_EXECUCAO`),
  KEY `FK_EXECUCAO_ID_SITUACAO` (`ID_SITUACAO`),
  KEY `FK_EXECUCAO_ID_PROGRAMAS_PROJECTOS` (`ID_PROGRAMAS_PROJECTOS`),
  KEY `FK_EXECUCAO_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  KEY `FK_EXECUCAO_ID_CONTRATO` (`ID_CONTRATO`),
  CONSTRAINT `FK_EXECUCAO_ID_CONTRATO` FOREIGN KEY (`ID_CONTRATO`) REFERENCES `contrato` (`id_contrato`),
  CONSTRAINT `FK_EXECUCAO_ID_PROGRAMAS_PROJECTOS` FOREIGN KEY (`ID_PROGRAMAS_PROJECTOS`) REFERENCES `programas_projectos` (`id_programas_projectos`),
  CONSTRAINT `FK_EXECUCAO_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`),
  CONSTRAINT `FK_EXECUCAO_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `execucao`
--

LOCK TABLES `execucao` WRITE;
/*!40000 ALTER TABLE `execucao` DISABLE KEYS */;
/*!40000 ALTER TABLE `execucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fases`
--

DROP TABLE IF EXISTS `fases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fases` (
  `ID_FASE` bigint(9) NOT NULL AUTO_INCREMENT,
  `DESCRICAO_FASE` varchar(200) NOT NULL,
  PRIMARY KEY (`ID_FASE`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fases`
--

LOCK TABLES `fases` WRITE;
/*!40000 ALTER TABLE `fases` DISABLE KEYS */;
INSERT INTO `fases` VALUES (1,'Levantamento e estudo'),(2,'Aprovação das peças de concurso'),(3,'Aprovação das peças de concurso Pelo MINEA'),(4,'Aprovação das peças de concurso Pelo Chefe do Executivo'),(5,'Em fase de Concurso'),(6,'Em fase de avaliação de Propostas'),(7,'Em fase de aprovação do Relatório Final'),(8,'Em fase de adjudicação e apresentação da caução definitiva'),(9,'Em fase de aprovação da Minuta do Contrato Remetidas ao MINEA'),(10,'Em fase de aprovação da Minuta do Contrato Remetidas  à Casa Civil'),(11,'Em fase de assinatura do contrato'),(12,'Homologação do Contrato'),(13,'Fiscalização preventiva do Tribunal de Contas'),(14,'Comunicação ao adjudicatário do Contrato Homologado e/ou Visado e prestação da garantia bancária do down payment'),(15,'Pagamento do down payment'),(16,'Consignação/Trabalhos em curso fase de projecto'),(17,'Obras em curso'),(18,'Obras em curso'),(19,'Em operação e manutenção'),(20,'Contracto concluído');
/*!40000 ALTER TABLE `fases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fornecedor` (
  `ID_FORNECEDOR` bigint(9) NOT NULL AUTO_INCREMENT,
  `NM_FORNECEDOR` varchar(100) NOT NULL,
  `NUM_CONTRIBUINTE` varchar(100) NOT NULL,
  `ENDERECO` varchar(250) NOT NULL,
  `EMAIL` varchar(80) DEFAULT NULL,
  `ESPECIALIDADE` varchar(100) DEFAULT NULL,
  `CONTATO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID_FORNECEDOR`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Teste nome','Teste num contrib 123456','Teste endereço','teste@email.com','Teste especialidade',NULL);
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicador_producao`
--

DROP TABLE IF EXISTS `indicador_producao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `indicador_producao` (
  `ID_INDICADOR_PRODUCAO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) NOT NULL,
  `ID_COMUNA` bigint(9) NOT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_ULTIMA_ALTERACAO` date DEFAULT NULL,
  `QTD_POPULACAO_COBERTA_INFRAESTRUTURA` decimal(15,2) NOT NULL,
  `QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS` decimal(15,2) NOT NULL,
  `QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA` decimal(15,2) NOT NULL,
  `QTD_MEDIA_HORAS_PARAGEM` decimal(15,2) NOT NULL,
  `QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA` decimal(15,2) NOT NULL,
  `QTD_VOLUME_AGUA_CAPTADA` decimal(15,2) NOT NULL,
  `QTD_VOLUME_AGUA_TRATADA` decimal(15,2) NOT NULL,
  `QTD_VOLUME_AGUA_DISTRIBUIDA` decimal(15,2) NOT NULL,
  `QTD_CONSUMO_ENERGIA` decimal(15,2) NOT NULL,
  `QTD_CONSUMO_COMBUSTIVEL` decimal(15,2) NOT NULL,
  `QTD_CONSUMO_HIPOCLORITRO_CALCIO` decimal(15,2) NOT NULL,
  `QTD_CONSUMO_SULFATO_ALUMINIO` decimal(15,2) NOT NULL,
  `QTD_CONSUMO_HIDROXIDO_CALCIO` decimal(15,2) NOT NULL,
  `QTD_REPARO_CAPTACAO_ETAS` bigint(20) NOT NULL,
  `QTD_REPARO_ADUTORAS` bigint(20) NOT NULL,
  `QTD_REPARO_REDE_DISTRIBUICAO` bigint(20) NOT NULL,
  `QTD_REPARO_RAMAIS` bigint(20) NOT NULL,
  `QTD_MANUTENCAO_CURATIVA` bigint(20) NOT NULL,
  `QTD_MANUTENCAO_PREVENTIVA` bigint(20) NOT NULL,
  `QTD_MANUTENCAO_VERIFICADO_SOLICITADO` bigint(20) NOT NULL,
  `QTD_RESERVATORIO_LAVADO` bigint(20) NOT NULL,
  `QTD_FUNCIONARIOS` bigint(20) NOT NULL,
  `QTD_FUNCIONARIOS_EFECTIVOS` bigint(20) NOT NULL,
  `QTD_FUNCIONARIOS_CONTRATADOS` bigint(20) NOT NULL,
  `QTD_FUNCIONARIOS_OUTRAS_ENTIDADES` bigint(20) NOT NULL,
  `QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS` bigint(20) NOT NULL,
  `QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS` bigint(20) NOT NULL,
  `QTD_LIGACOES_ILEGAIS_REGULARIZADAS` bigint(20) NOT NULL,
  `QTD_LIGACOES_FECHADAS` bigint(20) NOT NULL,
  `QTD_CORTES` bigint(20) NOT NULL,
  `QTD_RELIGACOES` bigint(20) NOT NULL,
  `QTD_LIGACOES_ACTIVAS` bigint(20) NOT NULL,
  `QTD_LIGACOES_DOMESTICAS_ACTIVAS` bigint(20) NOT NULL,
  `QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS` bigint(20) NOT NULL,
  `QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA` bigint(20) NOT NULL,
  `QTD_VOLUME_AGUA_FACTURADA` decimal(15,2) NOT NULL,
  `QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS` decimal(15,2) NOT NULL,
  `QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS` decimal(15,2) NOT NULL,
  `VLR_TOTAL_FACTURADO` decimal(15,2) NOT NULL,
  `VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS` decimal(15,2) NOT NULL,
  `VLR_REAL_FACTURADO` decimal(15,2) NOT NULL,
  `VLR_TOTAL_COBRADO` decimal(15,2) NOT NULL,
  `QTD_RECLAMACOES` bigint(20) NOT NULL,
  `QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS` bigint(20) DEFAULT NULL,
  `QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS` bigint(20) DEFAULT NULL,
  `QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS` bigint(20) DEFAULT NULL,
  `VLR_CUSTO_PESSOAL` decimal(15,2) NOT NULL,
  `VLR_CUSTO_FSE` decimal(15,2) NOT NULL,
  `VLR_CUSTO_ENERGIA` decimal(15,2) NOT NULL,
  `VLR_CUSTO_MANUTENCAO` decimal(15,2) NOT NULL,
  `VLR_CUSTO_REAGENTES` decimal(15,2) NOT NULL,
  `VLR_CUSTO_DESTINO_FINAL_LAMAS` decimal(15,2) NOT NULL,
  `VLR_CUSTO_OPERACIONAIS_OPEX` decimal(15,2) NOT NULL,
  `VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX` decimal(15,2) NOT NULL,
  `VLR_CUSTO_TOTAIS_CAPEX_OPEX` decimal(15,2) NOT NULL,
  `QTD_CAPTACOES` bigint(20) NOT NULL,
  `QTD_ETAS` bigint(20) NOT NULL,
  `QTD_RESERVATORIOS` bigint(20) NOT NULL,
  `QTD_ESTACOES_ELEVATORIAS` bigint(20) NOT NULL,
  `QTD_COMPRIMENTO_ADUTORAS` decimal(15,2) NOT NULL,
  `QTD_COMPRIMENTO_REDES` decimal(15,2) NOT NULL,
  `QTD_COMPRIMENTO_RAMAIS` decimal(15,2) NOT NULL,
  `QTD_ACOES_FORMACAO_MO_PLANEADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_MMS_PLANEADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_CMP_PLANEADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_MO_REALIZADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_MMS_REALIZADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_CMP_REALIZADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS` bigint(20) NOT NULL,
  `QTD_ACOES_FORMACAO_REALIZADAS` bigint(20) NOT NULL,
  `QTD_MANUAIS_MO_PREVISTOS` bigint(20) NOT NULL,
  `QTD_MANUAIS_MMS_PREVISTOS` bigint(20) NOT NULL,
  `QTD_MANUAIS_CMP_PREVISTOS` bigint(20) NOT NULL,
  `QTD_MANUAIS_PREVISTOS` bigint(20) NOT NULL,
  `QTD_ACOES_MANUAIS_MO_REALIZADAS` bigint(20) NOT NULL,
  `QTD_MANUAIS_MMS_REALIZADAS` bigint(20) NOT NULL,
  `QTD_MANUAIS_CMP_REALIZADAS` bigint(20) NOT NULL,
  `QTD_MANUAIS_REALIZADOS` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_INDICADOR_PRODUCAO`),
  KEY `FK_INDICADOR_PRODUCAO_ID_SITUACAO` (`ID_SITUACAO`),
  KEY `FK_INDICADOR_PRODUCAO_ID_COMUNA` (`ID_COMUNA`),
  KEY `FK_INDICADOR_PRODUCAO_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_INDICADOR_PRODUCAO_ID_COMUNA` FOREIGN KEY (`ID_COMUNA`) REFERENCES `comuna` (`id_comuna`),
  CONSTRAINT `FK_INDICADOR_PRODUCAO_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`),
  CONSTRAINT `FK_INDICADOR_PRODUCAO_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`),
  CONSTRAINT `indicador_producao_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicador_producao`
--

LOCK TABLES `indicador_producao` WRITE;
/*!40000 ALTER TABLE `indicador_producao` DISABLE KEYS */;
INSERT INTO `indicador_producao` VALUES (2,1,3,10103,1,'2019-07-19','2019-07-13',1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1,1,1,1,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1.00,1,1,1,1,1.00,1.00,1.00,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
/*!40000 ALTER TABLE `indicador_producao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicador_producao_log`
--

DROP TABLE IF EXISTS `indicador_producao_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `indicador_producao_log` (
  `ID_INDICADOR_PRODUCAO_LOG` bigint(9) NOT NULL AUTO_INCREMENT,
  `ACAO` varchar(50) NOT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `ID_INDICADOR_PRODUCAO` bigint(9) NOT NULL,
  `LOG` varchar(3500) NOT NULL,
  `DT_LOG` date NOT NULL,
  PRIMARY KEY (`ID_INDICADOR_PRODUCAO_LOG`),
  KEY `FK_INDICADOR_PRODUCAO_LOG_ID_INDICADOR_PRODUCAO` (`ID_INDICADOR_PRODUCAO`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_INDICADOR_PRODUCAO_LOG_ID_INDICADOR_PRODUCAO` FOREIGN KEY (`ID_INDICADOR_PRODUCAO`) REFERENCES `indicador_producao` (`id_indicador_producao`),
  CONSTRAINT `indicador_producao_log_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicador_producao_log`
--

LOCK TABLES `indicador_producao_log` WRITE;
/*!40000 ALTER TABLE `indicador_producao_log` DISABLE KEYS */;
INSERT INTO `indicador_producao_log` VALUES (1,'Inclusao',1,2,'Registro Incluido em banco','2019-07-08');
/*!40000 ALTER TABLE `indicador_producao_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inicio`
--

DROP TABLE IF EXISTS `inicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inicio` (
  `ID_INICIO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `ID_SOBRE_DNA` bigint(9) NOT NULL,
  `ID_NOTICIAS` bigint(9) NOT NULL,
  `ID_PROJECTOS` bigint(9) NOT NULL,
  `ID_PUBLICACAO` bigint(9) NOT NULL,
  `ID_CONTACTOS` bigint(9) NOT NULL,
  `DESTAQUES` bigint(9) NOT NULL,
  `ULTIMAS_NOTICIAS` bigint(9) NOT NULL,
  `PUBLICACOES` bigint(9) NOT NULL,
  `URL` varchar(500) NOT NULL,
  `ALT` varchar(150) NOT NULL,
  PRIMARY KEY (`ID_INICIO`),
  KEY `FK_INICIO_ID_SITUACAO` (`ID_SITUACAO`),
  KEY `FK_INICIO_ID_SOBRE_DNA` (`ID_SOBRE_DNA`),
  KEY `FK_INICIO_ID_NOTICIAS` (`ID_NOTICIAS`),
  KEY `FK_INICIO_ID_PROJECTOS` (`ID_PROJECTOS`),
  KEY `FK_INICIO_ID_PUBLICACAO` (`ID_PUBLICACAO`),
  KEY `FK_INICIO_ID_CONTACTOS` (`ID_CONTACTOS`),
  CONSTRAINT `FK_INICIO_ID_CONTACTOS` FOREIGN KEY (`ID_CONTACTOS`) REFERENCES `contactos` (`id_contactos`),
  CONSTRAINT `FK_INICIO_ID_NOTICIAS` FOREIGN KEY (`ID_NOTICIAS`) REFERENCES `noticias` (`id_noticias`),
  CONSTRAINT `FK_INICIO_ID_PROJECTOS` FOREIGN KEY (`ID_PROJECTOS`) REFERENCES `projectos` (`id_projectos`),
  CONSTRAINT `FK_INICIO_ID_PUBLICACAO` FOREIGN KEY (`ID_PUBLICACAO`) REFERENCES `publicacao` (`id_publicacao`),
  CONSTRAINT `FK_INICIO_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`),
  CONSTRAINT `FK_INICIO_ID_SOBRE_DNA` FOREIGN KEY (`ID_SOBRE_DNA`) REFERENCES `sobre_dna` (`id_sobre_dna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inicio`
--

LOCK TABLES `inicio` WRITE;
/*!40000 ALTER TABLE `inicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `inicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2019-07-06 10:13:49','AUTHENTICATION_SUCCESS'),(2,'admin','2019-07-06 10:42:43','AUTHENTICATION_SUCCESS'),(3,'admin','2019-07-07 11:08:40','AUTHENTICATION_SUCCESS'),(4,'admin','2019-07-07 11:42:45','AUTHENTICATION_SUCCESS'),(5,'admin','2019-07-07 15:12:40','AUTHENTICATION_SUCCESS'),(6,'admin','2019-07-07 18:38:53','AUTHENTICATION_SUCCESS'),(7,'admin','2019-07-07 19:15:38','AUTHENTICATION_SUCCESS'),(8,'admin','2019-07-07 20:47:04','AUTHENTICATION_SUCCESS'),(9,'admin','2019-07-08 15:37:08','AUTHENTICATION_SUCCESS'),(10,'admin','2019-07-08 21:55:46','AUTHENTICATION_SUCCESS'),(11,'admin','2019-07-10 16:36:02','AUTHENTICATION_SUCCESS'),(12,'admin','2019-07-10 17:37:06','AUTHENTICATION_SUCCESS'),(13,'admin','2019-07-10 17:40:31','AUTHENTICATION_SUCCESS'),(14,'admin','2019-07-10 18:45:35','AUTHENTICATION_SUCCESS'),(15,'admin','2019-07-18 22:03:27','AUTHENTICATION_SUCCESS'),(16,'admin','2019-07-19 20:40:33','AUTHENTICATION_SUCCESS'),(17,'admin','2019-07-20 14:56:36','AUTHENTICATION_SUCCESS'),(18,'admin','2019-07-31 14:08:07','AUTHENTICATION_SUCCESS'),(19,'admin','2019-08-18 10:55:35','AUTHENTICATION_SUCCESS'),(20,'admin','2019-08-18 13:19:03','AUTHENTICATION_SUCCESS'),(21,'admin','2019-08-18 15:57:29','AUTHENTICATION_SUCCESS'),(22,'admin','2019-08-18 16:24:36','AUTHENTICATION_SUCCESS'),(23,'admin','2019-08-22 14:13:55','AUTHENTICATION_SUCCESS'),(24,'admin','2019-08-22 23:55:00','AUTHENTICATION_SUCCESS'),(25,'admin','2019-08-23 12:08:30','AUTHENTICATION_SUCCESS'),(26,'admin','2019-08-23 18:04:57','AUTHENTICATION_SUCCESS'),(27,'admin','2019-08-23 18:16:19','AUTHENTICATION_SUCCESS'),(28,'admin','2019-08-23 18:20:49','AUTHENTICATION_SUCCESS'),(29,'admin','2019-08-23 19:59:28','AUTHENTICATION_SUCCESS'),(30,'admin','2019-08-23 22:51:25','AUTHENTICATION_SUCCESS'),(31,'admin','2019-08-24 09:25:30','AUTHENTICATION_SUCCESS'),(32,'admin','2019-08-24 14:50:08','AUTHENTICATION_SUCCESS'),(33,'admin','2019-08-24 17:16:55','AUTHENTICATION_SUCCESS'),(34,'admin','2019-10-20 02:58:29','AUTHENTICATION_SUCCESS'),(35,'admin','2019-10-22 21:48:46','AUTHENTICATION_SUCCESS'),(36,'admin','2019-10-24 23:33:37','AUTHENTICATION_SUCCESS'),(37,'admin','2019-10-24 23:33:37','AUTHENTICATION_SUCCESS'),(38,'admin','2019-10-29 00:22:07','AUTHENTICATION_SUCCESS'),(39,'admin','2019-10-29 22:38:38','AUTHENTICATION_SUCCESS'),(40,'admin','2019-10-30 17:33:24','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `endereco_cep` varchar(255) DEFAULT NULL,
  `endereco_logadouro` varchar(150) DEFAULT NULL,
  `endereco_lote` int(11) DEFAULT NULL,
  `endereco_complemento` varchar(150) DEFAULT NULL,
  `endereco_bairro` varchar(75) DEFAULT NULL,
  `endereco_cidade` varchar(75) DEFAULT NULL,
  `endereco_uf` varchar(2) DEFAULT NULL,
  `rf_associado` varchar(255) DEFAULT NULL,
  `br_associado` varchar(255) DEFAULT NULL,
  `qr_associado` varchar(255) DEFAULT NULL,
  `nf_associado` varchar(255) DEFAULT NULL,
  `st_perfil_administrador` bit(1) DEFAULT NULL,
  `st_mod_config` bit(1) DEFAULT NULL,
  `st_mod_balcao` bit(1) DEFAULT NULL,
  `st_mod_toten` bit(1) DEFAULT NULL,
  `st_mod_mobile` bit(1) DEFAULT NULL,
  `celular` varchar(11) DEFAULT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','system@localhost','',_binary '','pt-br',NULL,NULL,'system','2019-07-05 23:42:29',NULL,'system',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','anonymous@localhost','',_binary '','pt-br',NULL,NULL,'system','2019-07-05 23:42:29',NULL,'system',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','admin@localhost','',_binary '','pt-br',NULL,NULL,'system','2019-07-05 23:42:29',NULL,'system',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','user@localhost','',_binary '','pt-br',NULL,NULL,'system','2019-07-05 23:42:29',NULL,'system',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `municipio` (
  `ID_MUNICIPIO` bigint(9) NOT NULL AUTO_INCREMENT,
  `NM_MUNICIPIO` varchar(30) NOT NULL,
  `ID_PROVINCIA` bigint(9) NOT NULL,
  PRIMARY KEY (`ID_MUNICIPIO`),
  KEY `FK_MUNICIO_PROVINCIA` (`ID_PROVINCIA`),
  CONSTRAINT `FK_MUNICIO_PROVINCIA` FOREIGN KEY (`ID_PROVINCIA`) REFERENCES `provincia` (`id_provincia`)
) ENGINE=InnoDB AUTO_INCREMENT=1812 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (101,'Cabinda',1),(103,'Cacongo',1),(105,'Buco Zau',1),(107,'Belize',1),(201,'Mbanza Kongo',2),(203,'Soyo',2),(205,'Nzeto',2),(207,'Tomboco',2),(209,'Nóqui',2),(211,'Cuimba',2),(301,'Uíge',3),(303,'Ambuíla',3),(305,'Songo',3),(307,'Bembe',3),(309,'Negage',3),(311,'Bungo',3),(313,'Maquela do Zombo',3),(315,'Damba',3),(317,'Alto Cauale',3),(319,'Pombo',3),(321,'Dange-Quitexe',3),(323,'Quimbele',3),(325,'Milunga',3),(327,'Puri',3),(329,'Mucaba',3),(331,'Buengas',3),(401,'Luanda',4),(403,'Icolo e Bengo',4),(405,'Quiçama',4),(407,'Cacuaco',4),(409,'Cazenga',4),(411,'Viana',4),(413,'Belas',4),(501,'Cazengo',5),(503,'Lucala',5),(505,'Golungo Alto',5),(507,'Cambambe',5),(509,'Ambaca',5),(511,'Quiculungo',5),(513,'Bolongongo',5),(515,'Banga',5),(517,'Samba Cajú',5),(519,'Ngonguembo',5),(601,'Sumbe',6),(603,'Amboim',6),(605,'Quilenda',6),(607,'Porto Amboim',6),(609,'Libolo',6),(611,'Quibala',6),(613,'Mussende',6),(615,'Sales',6),(617,'Conda',6),(619,'Cassongue',6),(621,'Cela',6),(623,'Ebo',6),(701,'Malanje',7),(703,'Cacuso',7),(705,'Calandula',7),(707,'Cambundi Catembo',7),(709,'Quela',7),(711,'Cahombo',7),(713,'Massango',7),(715,'Luquembo',7),(717,'Marimba',7),(719,'Kunda dya Baze',7),(721,'Quirima',7),(723,'Mucari',7),(725,'Cagandala',7),(727,'Kiwaba Nzoji',7),(801,'Chitato',8),(803,'Cuílo',8),(805,'Lôvua',8),(807,'Caungula',8),(809,'Lubalo',8),(811,'Capenda Camulemba',8),(813,'Cuango',8),(815,'Lucapa',8),(817,'Cambulo',8),(819,'Xá-Muteba',8),(901,'Benguela',9),(903,'Baía Farta',9),(905,'Lobito',9),(907,'Cubal',9),(909,'Ganda',9),(911,'Balombo',9),(913,'Bocoio',9),(915,'Caimbambo',9),(917,'Chongoroi',9),(919,'Catumbela',9),(1001,'Huambo',10),(1003,'Chicala-Choloanga',10),(1005,'Cachiungo',10),(1007,'Bailundo',10),(1009,'Caála',10),(1011,'Ecunha',10),(1013,'Ucuma',10),(1015,'Longonjo',10),(1017,'Mungo',10),(1019,'Londuimbali',10),(1021,'Chinjenje',10),(1101,'Cuito',11),(1103,'Cunhinga',11),(1105,'Chinguar',11),(1107,'Andulo',11),(1109,'Nharêa',11),(1111,'Camacupa',11),(1113,'Cuemba',11),(1115,'Chitembo',11),(1117,'Catabola',11),(1201,'Moxico',12),(1203,'Camanongue',12),(1205,'Léua',12),(1207,'Luacano',12),(1209,'Cameia',12),(1211,'Bundas',12),(1213,'Luchazes',12),(1215,'Alto Zambeze',12),(1217,'Luau',12),(1301,'Menongue',13),(1303,'Cuito Cuanavale',13),(1305,'Cuangar',13),(1307,'Rivungo',13),(1309,'Mavinga',13),(1311,'Cuchi',13),(1313,'Dirico',13),(1315,'Nancova',13),(1317,'Calai',13),(1401,'Moçamedes',14),(1403,'Tômbwa',14),(1405,'Virei',14),(1407,'Bibala',14),(1409,'Camacuio',14),(1501,'Lubango',15),(1503,'Cacula',15),(1505,'Chibia',15),(1507,'Caconda',15),(1509,'Caluquembe',15),(1511,'Quilengues',15),(1513,'Cuvango',15),(1515,'Quipungo',15),(1517,'Matala',15),(1519,'Chicomba',15),(1521,'Jamba',15),(1523,'Chipindo',15),(1525,'Gambos',15),(1527,'Humpata',15),(1601,'Cuanhama',16),(1603,'Ombadja',16),(1605,'Cuvelai',16),(1607,'Curoca',16),(1609,'Namacunde',16),(1611,'Cahama',16),(1701,'Saurimo',17),(1703,'Muconda',17),(1705,'Dala',17),(1707,'Cacolo',17),(1801,'Dande',18),(1803,'Dembos',18),(1805,'Pango - Aluquém',18),(1807,'Ambriz',18),(1809,'Nambuangongo',18),(1811,'Bula - Atumba',18);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipios_atendidos`
--

DROP TABLE IF EXISTS `municipios_atendidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `municipios_atendidos` (
  `ID_MUNICIPIO_ATENDIDO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_MUNICIPIO` bigint(9) NOT NULL,
  PRIMARY KEY (`ID_MUNICIPIO_ATENDIDO`),
  KEY `FK_MUNICIPIO_ATENDIDO_ID_MUNICIPIO` (`ID_MUNICIPIO`),
  CONSTRAINT `FK_MUNICIPIO_ATENDIDO_ID_MUNICIPIO` FOREIGN KEY (`ID_MUNICIPIO`) REFERENCES `municipio` (`id_municipio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipios_atendidos`
--

LOCK TABLES `municipios_atendidos` WRITE;
/*!40000 ALTER TABLE `municipios_atendidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `municipios_atendidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticias`
--

DROP TABLE IF EXISTS `noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `noticias` (
  `ID_NOTICIAS` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `TITULO_NOTICIAS` varchar(150) NOT NULL,
  `TEXTO_NOTICIAS` varchar(2500) NOT NULL,
  `RESUMO_TEXTO_NOTICIAS` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_NOTICIAS`),
  KEY `FK_NOTICIAS_ID_SITUACAO` (`ID_SITUACAO`),
  CONSTRAINT `FK_NOTICIAS_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticias`
--

LOCK TABLES `noticias` WRITE;
/*!40000 ALTER TABLE `noticias` DISABLE KEYS */;
/*!40000 ALTER TABLE `noticias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `origem_financiamento`
--

DROP TABLE IF EXISTS `origem_financiamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `origem_financiamento` (
  `ID_ORIGEM_FINANCIAMENTO` bigint(9) NOT NULL AUTO_INCREMENT,
  `DESCRICAO_ORIGEM_FINANCIAMENTO` varchar(200) NOT NULL,
  PRIMARY KEY (`ID_ORIGEM_FINANCIAMENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `origem_financiamento`
--

LOCK TABLES `origem_financiamento` WRITE;
/*!40000 ALTER TABLE `origem_financiamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `origem_financiamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programas_projectos`
--

DROP TABLE IF EXISTS `programas_projectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `programas_projectos` (
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_COMUNA` bigint(9) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_ULTIMA_ALTERACAO` date DEFAULT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `NM_DESIGNACAO_PROJETO` varchar(250) NOT NULL,
  `NM_DESCRICAO_PROJETO` varchar(250) NOT NULL,
  `ID_SAA_ASSOCIADO` bigint(9) DEFAULT NULL,
  `TIPO_FINANCIAMENTO` varchar(150) NOT NULL,
  `ESPECIALIDADE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_PROGRAMAS_PROJECTOS`),
  KEY `FK_PROGRAMAS_PROJECTOS_ID_COMUNA` (`ID_COMUNA`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_PROGRAMAS_PROJECTOS_ID_COMUNA` FOREIGN KEY (`ID_COMUNA`) REFERENCES `comuna` (`id_comuna`),
  CONSTRAINT `programas_projectos_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programas_projectos`
--

LOCK TABLES `programas_projectos` WRITE;
/*!40000 ALTER TABLE `programas_projectos` DISABLE KEYS */;
INSERT INTO `programas_projectos` VALUES (1,10301,'2019-07-18','2019-10-31',1,'teste','projecto teste',123,'teste','ds');
/*!40000 ALTER TABLE `programas_projectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programas_projectos_log`
--

DROP TABLE IF EXISTS `programas_projectos_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `programas_projectos_log` (
  `ID_PROGRAMAS_PROJECTOS_LOG` bigint(9) NOT NULL AUTO_INCREMENT,
  `ACAO` varchar(50) NOT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `ID_PROGRAMAS_PROJECTOS` bigint(9) NOT NULL,
  `LOG` varchar(3500) NOT NULL,
  `DT_LOG` date NOT NULL,
  PRIMARY KEY (`ID_PROGRAMAS_PROJECTOS_LOG`),
  KEY `FK_PROGRAMAS_PROJECTOS_LOG_ID_PROGRAMAS_PROJECTOS` (`ID_PROGRAMAS_PROJECTOS`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_PROGRAMAS_PROJECTOS_LOG_ID_PROGRAMAS_PROJECTOS` FOREIGN KEY (`ID_PROGRAMAS_PROJECTOS`) REFERENCES `programas_projectos` (`id_programas_projectos`),
  CONSTRAINT `programas_projectos_log_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programas_projectos_log`
--

LOCK TABLES `programas_projectos_log` WRITE;
/*!40000 ALTER TABLE `programas_projectos_log` DISABLE KEYS */;
INSERT INTO `programas_projectos_log` VALUES (1,'Inclusao',1,1,'Registro Incluido em banco','2019-07-10');
/*!40000 ALTER TABLE `programas_projectos_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectos`
--

DROP TABLE IF EXISTS `projectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `projectos` (
  `ID_PROJECTOS` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `NM_PROJECTOS` varchar(150) NOT NULL,
  `TEXTO_PROJECTOS` varchar(2500) NOT NULL,
  `RESUMO_TEXTO_PROJECTOS` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_PROJECTOS`),
  KEY `FK_PROJECTOS_ID_SITUACAO` (`ID_SITUACAO`),
  CONSTRAINT `FK_PROJECTOS_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectos`
--

LOCK TABLES `projectos` WRITE;
/*!40000 ALTER TABLE `projectos` DISABLE KEYS */;
/*!40000 ALTER TABLE `projectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `provincia` (
  `ID_PROVINCIA` bigint(2) NOT NULL AUTO_INCREMENT,
  `NM_PROVINCIA` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_PROVINCIA`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'Cabinda'),(2,'Zaire'),(3,'Uíge'),(4,'Luanda'),(5,'Cuanza - Norte'),(6,'Cuanza - Sul'),(7,'Malanje'),(8,'Lunda - Norte'),(9,'Benguela'),(10,'Huambo'),(11,'Bié'),(12,'Moxico'),(13,'Cuando Cubango'),(14,'Namibe'),(15,'Huíla'),(16,'Cunene'),(17,'Lunda - Sul'),(18,'Bengo');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicacao`
--

DROP TABLE IF EXISTS `publicacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `publicacao` (
  `ID_PUBLICACAO` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `TITULO_PUBLICACAO` varchar(150) NOT NULL,
  `TEXTO_PUBLICACAO` varchar(2500) NOT NULL,
  `RESUMO_TEXTO_PUBLICACAO` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_PUBLICACAO`),
  KEY `FK_PUBLICACAO_ID_SITUACAO` (`ID_SITUACAO`),
  CONSTRAINT `FK_PUBLICACAO_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicacao`
--

LOCK TABLES `publicacao` WRITE;
/*!40000 ALTER TABLE `publicacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `publicacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicacao_log`
--

DROP TABLE IF EXISTS `publicacao_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `publicacao_log` (
  `ID_PUBLICACAO_LOG` bigint(9) NOT NULL AUTO_INCREMENT,
  `ACAO` varchar(50) NOT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `LOG` varchar(3500) NOT NULL,
  `DT_LOG` date NOT NULL,
  PRIMARY KEY (`ID_PUBLICACAO_LOG`),
  KEY `FK_PUBLICACAO_LOG_ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_PUBLICACAO_LOG_ID_USUARIO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `sisas_control_access`.`usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicacao_log`
--

LOCK TABLES `publicacao_log` WRITE;
/*!40000 ALTER TABLE `publicacao_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `publicacao_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relatorios_log`
--

DROP TABLE IF EXISTS `relatorios_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `relatorios_log` (
  `ID_RELATORIOS_LOG` bigint(9) NOT NULL AUTO_INCREMENT,
  `ACAO` varchar(50) NOT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `LOG` varchar(3500) NOT NULL,
  `DT_LOG` date NOT NULL,
  PRIMARY KEY (`ID_RELATORIOS_LOG`),
  KEY `FK_RELATORIOS_LOG_ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_RELATORIOS_LOG_ID_USUARIO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `sisas_control_access`.`usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relatorios_log`
--

LOCK TABLES `relatorios_log` WRITE;
/*!40000 ALTER TABLE `relatorios_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `relatorios_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sistema_agua`
--

DROP TABLE IF EXISTS `sistema_agua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sistema_agua` (
  `ID_SISTEMA_AGUA` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `ID_COMUNA` bigint(9) NOT NULL,
  `ID_PROVINCIA` bigint(9) DEFAULT NULL,
  `ID_MUNICIPIO` bigint(9) DEFAULT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `NM_INQUERIDOR` varchar(150) NOT NULL,
  `DT_LANCAMENTO` date NOT NULL,
  `DT_ULTIMA_ALTERACAO` date DEFAULT NULL,
  `NM_LOCALIDADE` varchar(150) DEFAULT NULL,
  `QTD_POPULACAO_ACTUAL` bigint(20) DEFAULT NULL,
  `QTD_CASAS_LOCALIDADE` bigint(20) DEFAULT NULL,
  `NM_TP_COMUNA_ALDEIA` varchar(20) DEFAULT NULL,
  `NM_TP_AREA` varchar(11) DEFAULT NULL,
  `POSSUI_SISTEMA_AGUA` bigint(1) DEFAULT '0',
  `NM_SISTEMA_AGUA` varchar(150) DEFAULT NULL,
  `NM_FONTE_AGUA` varchar(150) DEFAULT NULL,
  `LATITUDE` varchar(60) DEFAULT NULL,
  `LONGITUDE` varchar(60) DEFAULT NULL,
  `ALTITUDE` varchar(60) DEFAULT NULL,
  `NM_TP_FONTE` varchar(20) DEFAULT NULL,
  `NM_FONTE_AGUA_UTILIZADA` varchar(20) DEFAULT NULL,
  `NM_TIPO_BOMBA` varchar(11) DEFAULT NULL,
  `QTD_CASAS_AGUA_LIGADA` bigint(20) DEFAULT NULL,
  `QTD_CHAFARISES_FUNCIONANDO` bigint(20) DEFAULT NULL,
  `QTD_CONTADORES_LIGADOS` bigint(20) DEFAULT NULL,
  `QTD_BEBEDOUROS` bigint(20) DEFAULT NULL,
  `QTD_HABITANTES_ACESSO_SERVICO_AGUA` bigint(20) DEFAULT NULL,
  `ANO_CONSTRUCAO_SISTEMA` bigint(4) DEFAULT NULL,
  `NM_TP_AVARIA_SISTEMA` varchar(50) DEFAULT NULL,
  `CAUSA_AVARIA_SISTEMA` varchar(100) DEFAULT NULL,
  `STATUS_RESOLUCAO` varchar(100) DEFAULT NULL,
  `TEMPO_SERVICO_DISPONIVEL` varchar(100) DEFAULT NULL,
  `QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA` decimal(15,2) DEFAULT NULL,
  `QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA` decimal(15,2) DEFAULT NULL,
  `QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA` decimal(15,2) DEFAULT NULL,
  `QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA` decimal(15,2) DEFAULT NULL,
  `DESC_MATERIAL_UTILIZADO_CONDUTAS` varchar(150) DEFAULT NULL,
  `QTD_RESERVATORIOS_APOIADOS` bigint(20) DEFAULT NULL,
  `QTD_CAPACIDADE_RESERVATORIOS_APOIADOS` bigint(20) DEFAULT NULL,
  `QTD_RESERVATORIOS_ELEVADOS` bigint(20) DEFAULT NULL,
  `QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS` bigint(20) DEFAULT NULL,
  `ALTURA_RESERVATORIOS_ELEVADOS` decimal(15,2) DEFAULT NULL,
  `NM_TP_TRATAMENTO_AGUA` varchar(50) DEFAULT NULL,
  `NM_TP_TRATAMENTO_PADRAO_UTILIZADO` varchar(50) DEFAULT NULL,
  `NM_TP_TRATAMENTO_BASICO_UTILIZADO` varchar(50) DEFAULT NULL,
  `EXISTE_AVARIA_SISTEMA_TRATAMENTO` varchar(50) DEFAULT NULL,
  `EXISTE_MOTIVO_AUSENCIA_TRATAMENTO` varchar(50) DEFAULT NULL,
  `NM_EQUIPAMENTOS_COM_AVARIA` varchar(50) DEFAULT NULL,
  `CAUDAL_DO_SISTEMA` bigint(20) DEFAULT NULL,
  `QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA` decimal(15,2) DEFAULT NULL,
  `QTD_DOTACAO_PERCAPTA` decimal(15,2) DEFAULT NULL,
  `QTD_DIARIA_HORAS_SERVICO_SISTEMA` decimal(15,2) DEFAULT NULL,
  `ESQUEMA` varchar(50) DEFAULT NULL,
  `NM_MODELO_BOMBA_MANUAL_UTILIZADA` varchar(50) DEFAULT NULL,
  `NM_TP_BOMBA_ENERGIA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_SISTEMA_AGUA`),
  KEY `FK_SISTEMA_AGUA_ID_SITUACAO` (`ID_SITUACAO`),
  KEY `FK_SISTEMA_AGUA_ID_COMUNA` (`ID_COMUNA`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  KEY `FK_SISTEMA_AGUA_ID_PROVINCIA` (`ID_PROVINCIA`),
  KEY `FK_SISTEMA_AGUA_ID_MUNICIPIO` (`ID_MUNICIPIO`),
  CONSTRAINT `FK_SISTEMA_AGUA_ID_COMUNA` FOREIGN KEY (`ID_COMUNA`) REFERENCES `comuna` (`id_comuna`),
  CONSTRAINT `FK_SISTEMA_AGUA_ID_MUNICIPIO` FOREIGN KEY (`ID_MUNICIPIO`) REFERENCES `municipio` (`id_municipio`),
  CONSTRAINT `FK_SISTEMA_AGUA_ID_PROVINCIA` FOREIGN KEY (`ID_PROVINCIA`) REFERENCES `provincia` (`id_provincia`),
  CONSTRAINT `FK_SISTEMA_AGUA_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`),
  CONSTRAINT `sistema_agua_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sistema_agua`
--

LOCK TABLES `sistema_agua` WRITE;
/*!40000 ALTER TABLE `sistema_agua` DISABLE KEYS */;
INSERT INTO `sistema_agua` VALUES (3,1,10103,1,101,2,'teste','2019-07-20','2019-07-13','brasília',123,456,'4QUALITY','TECNOLOGIA',0,'Novo Teste','fonte nova','1234.0000','645.0000','7373.000','4QUALITY','TECNOLOGIA','4QUALITY TE',98,67,45,54,4,1987,'13dasd','dasd','dsda55','dasdadfff',764.00,664.00,633.00,987.00,'dsdfdsfsklj',8787,98723,3242,844,22.00,'fsd','fsdf','sf','sdf','lksjdfds','sdf',99823,847.00,9834.00,8723.00,'kjhjkfds','df','ds'),(4,1,10501,18,307,1,'Anderson Soares','2019-07-03','2019-07-08','Dublin',98,76,'Dispersa','Urbana',0,'Test2 ','Lagoa','124.0000','989.0000','767.000','Subterrânea','Lagoa','Diesel',5,8,8,8,8,1945,'Avaria Mecânica','Bomba deixou de Funcionar','Falta de Fundos para Reparação','Um mês',6.00,9999.00,878.00,987.00,'metal',32,323,323,323,323.00,'Tratamento Básico-Cloro','Coagulação, floculação, decantação,filtração','Cloro Líquido','Sim, Sistema Avariado','Falta de Conhecimento','Electroagitadores',797,876.00,876.00,3.00,'Furo','Volanta','Solar');
/*!40000 ALTER TABLE `sistema_agua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sistema_agua_log`
--

DROP TABLE IF EXISTS `sistema_agua_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sistema_agua_log` (
  `ID_SISTEMA_AGUA_LOG` bigint(9) NOT NULL AUTO_INCREMENT,
  `ACAO` varchar(50) NOT NULL,
  `ID_USUARIO` bigint(9) NOT NULL,
  `ID_SISTEMA_AGUA` bigint(9) NOT NULL,
  `LOG` varchar(3500) NOT NULL,
  `DT_LOG` date NOT NULL,
  PRIMARY KEY (`ID_SISTEMA_AGUA_LOG`),
  KEY `FK_SISTEMA_AGUA_LOG_ID_SISTEMA_AGUA` (`ID_SISTEMA_AGUA`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_SISTEMA_AGUA_LOG_ID_SISTEMA_AGUA` FOREIGN KEY (`ID_SISTEMA_AGUA`) REFERENCES `sistema_agua` (`id_sistema_agua`),
  CONSTRAINT `sistema_agua_log_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sistema_agua_log`
--

LOCK TABLES `sistema_agua_log` WRITE;
/*!40000 ALTER TABLE `sistema_agua_log` DISABLE KEYS */;
INSERT INTO `sistema_agua_log` VALUES (2,'inclusao',1,3,'foi criado um novo registro','2019-07-05');
/*!40000 ALTER TABLE `sistema_agua_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `situacao`
--

DROP TABLE IF EXISTS `situacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `situacao` (
  `ID_SITUACAO` bigint(9) NOT NULL AUTO_INCREMENT,
  `NM_SITUACAO` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_SITUACAO`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `situacao`
--

LOCK TABLES `situacao` WRITE;
/*!40000 ALTER TABLE `situacao` DISABLE KEYS */;
INSERT INTO `situacao` VALUES (1,'ATIVADO'),(2,'DESATIVADO');
/*!40000 ALTER TABLE `situacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sobre_dna`
--

DROP TABLE IF EXISTS `sobre_dna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sobre_dna` (
  `ID_SOBRE_DNA` bigint(9) NOT NULL AUTO_INCREMENT,
  `ID_SITUACAO` bigint(9) NOT NULL,
  `TITULO_SOBRE_DNA` varchar(150) NOT NULL,
  `TEXTO_SOBRE_DNA` varchar(2500) NOT NULL,
  `RESUMO_TEXTO_SOBRE_DNA` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_SOBRE_DNA`),
  KEY `FK_SOBRE_DNA_ID_SITUACAO` (`ID_SITUACAO`),
  CONSTRAINT `FK_SOBRE_DNA_ID_SITUACAO` FOREIGN KEY (`ID_SITUACAO`) REFERENCES `situacao` (`id_situacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sobre_dna`
--

LOCK TABLES `sobre_dna` WRITE;
/*!40000 ALTER TABLE `sobre_dna` DISABLE KEYS */;
/*!40000 ALTER TABLE `sobre_dna` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-31 12:39:31
