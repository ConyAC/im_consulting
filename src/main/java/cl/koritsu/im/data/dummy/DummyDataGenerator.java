package cl.koritsu.im.data.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.koritsu.im.domain.Pregunta;
import cl.koritsu.im.domain.Respondente;
import cl.koritsu.im.domain.Segmento;
import cl.koritsu.im.domain.Stakeholder;
import cl.koritsu.im.domain.SubSegmento;

public abstract class DummyDataGenerator {

    static String randomFirstName() {
        String[] names = { "Dave", "Mike", "Katherine", "Jonas", "Linus",
                "Bob", "Anne", "Minna", "Elisa", "George", "Mathias", "Pekka",
                "Fredrik", "Kate", "Teppo", "Kim", "Samatha", "Sam", "Linda",
                "Jo", "Sarah", "Ray", "Michael", "Steve" };
        return names[(int) (Math.random() * names.length)];
    }

    static String randomLastName() {
        String[] names = { "Smith", "Lehtinen", "Chandler", "Hewlett",
                "Packard", "Jobs", "Buffet", "Reagan", "Carthy", "Wu",
                "Johnson", "Williams", "Jones", "Brown", "Davis", "Moore",
                "Wilson", "Taylor", "Anderson", "Jackson", "White", "Harris",
                "Martin", "King", "Lee", "Walker", "Wright", "Clark",
                "Robinson", "Garcia", "Thomas", "Hall", "Lopez", "Scott",
                "Adams", "Barker", "Morris", "Cook", "Rogers", "Rivera",
                "Gray", "Price", "Perry", "Powell", "Russell", "Diaz" };
        return names[(int) (Math.random() * names.length)];
    }

    static String randomCompanyName() {

        String name = randomName();
        if (Math.random() < 0.03) {
            name += " Technologies";
        } else if (Math.random() < 0.02) {
            name += " Investment";
        }
        if (Math.random() < 0.3) {
            name += " Inc";
        } else if (Math.random() < 0.2) {
            name += " Ltd.";
        }

        return name;
    }

    public static String randomWord(int len, boolean capitalized) {
        String[] part = { "ger", "ma", "isa", "app", "le", "ni", "ke", "mic",
                "ro", "soft", "wa", "re", "lo", "gi", "is", "acc", "el", "tes",
                "la", "ko", "ni", "ka", "so", "ny", "mi", "nol", "ta", "pa",
                "na", "so", "nic", "sa", "les", "for", "ce" };
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            String p = part[(int) (Math.random() * part.length)];
            if (i == 0 && capitalized) {
                p = Character.toUpperCase(p.charAt(0)) + p.substring(1);
            }
            sb.append(p);
        }
        return sb.toString();

    }

    public static String randomText(int words) {
        StringBuffer sb = new StringBuffer();
        int sentenceWordsLeft = 0;
        while (words-- > 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            if (sentenceWordsLeft == 0 && words > 0) {
                sentenceWordsLeft = (int) (Math.random() * 15);
                sb.append(randomWord(1 + (int) (Math.random() * 3), true));
            } else {
                sentenceWordsLeft--;
                sb.append(randomWord(1 + (int) (Math.random() * 3), false));
                if (words > 0 && sentenceWordsLeft > 2 && Math.random() < 0.2) {
                    sb.append(',');
                } else if (sentenceWordsLeft == 0 || words == 0) {
                    sb.append('.');
                }
            }
        }
        return sb.toString();
    }

    static String randomName() {
        int len = (int) (Math.random() * 4) + 1;
        return randomWord(len, true);
    }

    static String randomTitle(int words) {
        StringBuffer sb = new StringBuffer();
        int len = (int) (Math.random() * 4) + 1;
        sb.append(randomWord(len, true));
        while (--words > 0) {
            len = (int) (Math.random() * 4) + 1;
            sb.append(' ');
            sb.append(randomWord(len, false));
        }
        return sb.toString();
    }

    static String randomHTML(int words) {
        StringBuffer sb = new StringBuffer();
        while (words > 0) {
            sb.append("<h2>");
            int len = (int) (Math.random() * 4) + 1;
            sb.append(randomTitle(len));
            sb.append("</h2>");
            words -= len;
            int paragraphs = 1 + (int) (Math.random() * 3);
            while (paragraphs-- > 0 && words > 0) {
                sb.append("<p>");
                len = (int) (Math.random() * 40) + 3;
                sb.append(randomText(len));
                sb.append("</p>");
                words -= len;
            }
        }
        return sb.toString();
    }

    public static int[] randomSparklineValues(int howMany, int min, int max) {
        int[] values = new int[howMany];

        for (int i = 0; i < howMany; i++) {
            values[i] = (int) (min + (Math.random() * (max - min)));
        }

        return values;
    }
    public static List<Stakeholder> getStakeHolder(){
    	List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
    	
    	Stakeholder stakeholder = new Stakeholder(); stakeholder.setNombre("Asociaciones");stakeholder.setId(1L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Autoridades");stakeholder.setId(2L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Clase Política");stakeholder.setId(3L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Cliente");stakeholder.setId(4L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Colaboradores");stakeholder.setId(5L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Competidores");stakeholder.setId(6L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Comunidades");stakeholder.setId(7L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Cuidadanos");stakeholder.setId(8L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Inversionistas");stakeholder.setId(9L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Investigación/Educación");stakeholder.setId(10L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Líderes de opinión");stakeholder.setId(11L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("ONG's");stakeholder.setId(12L);
    	stakeholders.add(stakeholder);
    	stakeholder = new Stakeholder(); stakeholder.setNombre("Proveedor");stakeholder.setId(13L);
    	stakeholders.add(stakeholder);

    	return stakeholders;
    }
    
    public static List<Pregunta> getPreguntas(){
    	List<Pregunta> preguntas = new ArrayList<Pregunta>();
    
    	Pregunta pregunta = new Pregunta(); pregunta.setPregunta("Conocimiento");pregunta.setId(1L);
    	preguntas.add(pregunta);
    	pregunta = new Pregunta(); pregunta.setPregunta("Relevancia");pregunta.setId(2L);
    	preguntas.add(pregunta);
    	pregunta = new Pregunta(); pregunta.setPregunta("Actitud");pregunta.setId(3L);
    	preguntas.add(pregunta);
    	pregunta = new Pregunta(); pregunta.setPregunta("Recomendación");pregunta.setId(4L);
    	preguntas.add(pregunta);
    	pregunta = new Pregunta(); pregunta.setPregunta("Esfuerzo");pregunta.setId(5L);
    	preguntas.add(pregunta);
    	pregunta = new Pregunta(); pregunta.setPregunta("Renovación/Recompra");pregunta.setId(6L);
    	preguntas.add(pregunta);
    	
    	return preguntas;
    }
    
    public static List<Segmento> getSegmentos(){
    	List<Segmento> segmentos = new ArrayList<Segmento>();
    	
    	Segmento segmento = new Segmento(); segmento.setNombre("A. Empresariales");segmento.setId(1L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("A. Gremiales");segmento.setId(2L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("A. Industriales");segmento.setId(3L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("A. Profesionales");segmento.setId(4L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("A. Gobierno central");segmento.setId(5L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("A. Locales");segmento.setId(6L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("A. Regionales");segmento.setId(7L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("CP. Diputados");segmento.setId(8L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("CP. Líderes de partidos políticos");segmento.setId(9L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("CP. Senadores");segmento.setId(10L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Cliente medio");segmento.setId(11L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Cliente prioritario");segmento.setId(12L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Cliente secundario");segmento.setId(13L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("C. Distribuidores/Retailers");segmento.setId(14L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("C. Prospectos");segmento.setId(15L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("C. Ejecutivos y Adm.");segmento.setId(16L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("C. Gerencias");segmento.setId(17L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("C. Operativos");segmento.setId(18L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("C. Sindicatos");segmento.setId(19L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("C. Subcontratados");segmento.setId(20L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Competidores");segmento.setId(21L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Comunidad 3");segmento.setId(22L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Comunidad 1");segmento.setId(23L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Comunidad 2");segmento.setId(24L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Cuidadanos");segmento.setId(25L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("I. Directores");segmento.setId(26L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("I. Accionistas Empresa");segmento.setId(27L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("I. Accionistas Individuales");segmento.setId(28L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("I. Dueños");segmento.setId(29L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Centros de estudio e investigación");segmento.setId(30L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Institutos");segmento.setId(31L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Universidades");segmento.setId(32L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("Líderes de opinión");segmento.setId(33L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("ONG's");segmento.setId(34L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("P. Crítico");segmento.setId(35L);
    	segmentos.add(segmento);
    	segmento = new Segmento(); segmento.setNombre("P. No crítico");segmento.setId(36L);
    	segmentos.add(segmento);
    	
    	return segmentos;
    }
    
    public static List<SubSegmento> getSubsegmentos(){
    	List<SubSegmento> subsegmentos = new ArrayList<SubSegmento>();
    	
    	SubSegmento subsegmento = new SubSegmento(); subsegmento.setNombre("Juntas vecinales");subsegmento.setId(1L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("Alcaldes");subsegmento.setId(2L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("Consejales");subsegmento.setId(3L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("Otros Gobierno Municipal");subsegmento.setId(4L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("Gobierno Regional");subsegmento.setId(5L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("SEREMIS");subsegmento.setId(6L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("Ministerios");subsegmento.setId(7L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("Subsecretarías");subsegmento.setId(8L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("Presidencia");subsegmento.setId(9L);
    	subsegmentos.add(subsegmento);
    	
    	return subsegmentos;
    }
/*
    public static Color[] chartColors = new Color[] {
            new SolidColor("#3090F0"), new SolidColor("#18DDBB"),
            new SolidColor("#98DF58"), new SolidColor("#F9DD51"),
            new SolidColor("#F09042"), new SolidColor("#EC6464") };
            **/

	public static List<String> getCriticidades() {
		return Arrays.asList("Blackout", "Muy Critico", "Critico","Neutro","Baja Criticidad");
	}

	public static List<String> getActitudes() {
		return Arrays.asList("Problematico", "No Problematico");
	}
	
	public static List<String> getIndustrias() {
		return Arrays.asList("Aerospace & Defense",
				"Agriculture",
				"Automotive & Assembly",
				"Capital Projects & Infrastructure",
				"Chemicals",
				"Consumer Packaged Goods",
				"Electric Power & Natural Gas",
				"Financial Services",
				"Healthcare Systems & Services",
				"High Tech",
				"Media & Entertainment",
				"Metals & Mining",
				"Oil & Gas",
				"Paper & Forest Products",
				"Pharmaceuticals & Medical Products",
				"Private Equity & Principal Investors",
				"Public Sector",
				"Retail",
				"Semiconductors",
				"Social Sector",
				"Telecommunications",
				"Travel, Transport & Logistics");
	}
	
	public static List<String> getSegmentosUS() {
		return Arrays.asList("Total","Business Comunity Asociations",
				"Trade Unions",
				"Industrial Asociations",
				"Professional Asociations",
				"Federal Government ",
				"Local Government",
				"Regional Government",
				"Representative",
				"Political Parties Leaders",
				"Senators",
				"Average Customer",
				"Priority Customer",
				"Nonpriority Customer",
				"Distributors/Retailers",
				"Prospects",
				"KAM and back office supporters",
				"Managers",
				"Operational personnel",
				"Unions",
				"Third party personnel",
				"Competitors",
				"Community 1",
				"Community 2",
				"Community 3",
				"Citizens",
				"Board Directors",
				"Shareholders",
				"Investors",
				"R&D centers",
				"Institutes",
				"Universities",
				"Opinion Leaders",
				"NGO's",
				"Critical suppliers",
				"Non critical suppliers");
	}
	
	public static List<String> getStakeHolderUS(){
		return Arrays.asList("Total","Asociations",
				"Authorities",
				"Politicians",
				"Customer",
				"Employees",
				"Competitors",
				"Communities",
				"Citizens",
				"Investors",
				"Research/Education",
				"Opinion Leaders",
				"NGO's",
				"Suppliers");
	}
	
	public static List<String> getSubsegmentosUS(){
		return Arrays.asList("Total","Neighborhood meetings",
				"Mayors",
				"Councilors",
				"Others in Local Government",
				"Regional Government Authorities",
				"SEREMIS",
				"Ministers",
				"Subministers",
				"Presidency");
	}
	
	public static List<String> getCriticidadUS(){
		return Arrays.asList("Critical",
				"Very critical",
				"Neutral",
				"Low criticality",
				"Non critical",
				"Black out");
	}
	
	public static List<String> getActitudesUS() {
		return Arrays.asList("Problematic", "Non Problematic");
	}
	
	public static List<String> getTipoEstudioUS() {
		return Arrays.asList("Marketing Management", "Studies or Market Research Management","Corporate Relations Management","Risk Management");
	}
	
	public static List<Respondente> getRespondentesUS(){	
		return Arrays.asList(new Respondente(1L,"Asociations","Business Comunity Asociations","","Critical","Problematic","Respondent entered for..."),
				new Respondente(2L,"Asociations","Business Comunity Asociations","","Black out","Problematic"," "),
				new Respondente(3L,"Asociations","Trade Unions","","Non critical","Non Problematic"," "),
				new Respondente(4L,"Asociations","Trade Unions","","Very critical","Non Problematic"," "),
				new Respondente(5L,"Asociations","Industrial Asociations","","Critical","Problematic"," "),
				new Respondente(6L,"Asociations","Industrial Asociations","","Very critical","Non Problematic"," "),
				new Respondente(7L,"Asociations","Professional Asociations","","Neutral","Non Problematic"," "),
				new Respondente(8L,"Asociations","Professional Asociations","","Non critical","Non Problematic"," "),
				new Respondente(9L,"Authorities","Federal Government","Ministers","Critical","Problematic"," "),
				new Respondente(10L,"Authorities","Federal Government","Ministers","Critical","Problematic"," "),
				new Respondente(11L,"Authorities","Federal Government","Presidency","Neutral","Problematic"," "),
				new Respondente(12L,"Authorities","Federal Government","Presidency","Critical","Problematic"," "),
				new Respondente(13L,"Authorities","Federal Government","Subministers","Very critical","Problematic"," "),
				new Respondente(14L,"Authorities","Federal Government","Subministers","Neutral","Non Problematic"," "),
				new Respondente(15L,"Authorities","Local Government","Mayors","Black out","Problematic"," "),
				new Respondente(16L,"Authorities","Local Government","Mayors","Low criticality","Problematic"," "),
				new Respondente(17L,"Authorities","Local Government","Councilors","Neutral","Problematic"," "),
				new Respondente(18L,"Authorities","Local Government","Councilors","Very critical","Problematic"," "),
				new Respondente(19L,"Authorities","Local Government","Neighborhood meetings","Critical","Non Problematic"," "),
				new Respondente(20L,"Authorities","Local Government","Neighborhood meetings","Low criticality","Non Problematic"," "),
				new Respondente(21L,"Authorities","Local Government","Others in Local Government","Black out","Non Problematic"," "),
				new Respondente(22L,"Authorities","Local Government","Others in Local Government","Low criticality","Non Problematic"," "),
				new Respondente(23L,"Authorities","Regional Government","Regional Government Authorities","Black out","Problematic"," "),
				new Respondente(24L,"Authorities","Regional Government","Regional Government Authorities","Low criticality","Problematic"," "),
				new Respondente(25L,"Authorities","Regional Government","SEREMIS","Neutral","Problematic"," "),
				new Respondente(26L,"Authorities","Regional Government","SEREMIS","Neutral","Problematic"," "),
				new Respondente(27L,"Authorities","Regional Government","SEREMIS","Black out","Problematic"," "),
				new Respondente(28L,"Authorities","Regional Government","SEREMIS","Neutral","Non Problematic"," "),
				new Respondente(29L,"Politicians","Representative","","Neutral","Problematic"," "),
				new Respondente(30L,"Politicians","Representative","","Non critical","Non Problematic"," "),
				new Respondente(31L,"Politicians","Political Parties Leaders","","Very critical","Problematic"," "),
				new Respondente(32L,"Politicians","Political Parties Leaders","","Low criticality","Non Problematic"," "),
				new Respondente(33L,"Politicians","Senators","","Neutral","Non Problematic"," "),
				new Respondente(34L,"Politicians","Senators","","Non critical","Problematic"," "),
				new Respondente(35L,"Customer","Average Customer","","Black out","Problematic"," "),
				new Respondente(36L,"Customer","Average Customer","","Very critical","Non Problematic"," "),
				new Respondente(37L,"Customer","Average Customer","","Critical","Problematic"," "),
				new Respondente(38L,"Customer","Average Customer","","Non critical","Non Problematic"," "),
				new Respondente(39L,"Customer","Average Customer","","Very critical","Non Problematic"," "),
				new Respondente(40L,"Customer","Average Customer","","Non critical","Non Problematic"," "),
				new Respondente(41L,"Customer","Priority Customer","","Neutral","Problematic"," "),
				new Respondente(42L,"Customer","Priority Customer","","Black out","Non Problematic"," "),
				new Respondente(43L,"Customer","Priority Customer","","Neutral","Non Problematic"," "),
				new Respondente(44L,"Customer","Priority Customer","","Neutral","Non Problematic"," "),
				new Respondente(45L,"Customer","Priority Customer","","Black out","Non Problematic"," "),
				new Respondente(46L,"Customer","Priority Customer","","Black out","Problematic"," "),
				new Respondente(47L,"Customer","Priority Customer","","Black out","Non Problematic"," "),
				new Respondente(48L,"Customer","Priority Customer","","Non critical","Problematic"," "),
				new Respondente(49L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(50L,"Customer","Nonpriority Customer","","Non critical","Non Problematic"," "),
				new Respondente(51L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(52L,"Customer","Average Customer","","Neutral","Problematic"," "),
				new Respondente(53L,"Customer","Average Customer","","Critical","Non Problematic"," "),
				new Respondente(54L,"Customer","Average Customer","","Low criticality","Problematic"," "),
				new Respondente(55L,"Customer","Average Customer","","Non critical","Problematic"," "),
				new Respondente(56L,"Customer","Average Customer","","Very critical","Non Problematic"," "),
				new Respondente(57L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(58L,"Customer","Nonpriority Customer","","Low criticality","Problematic"," "),
				new Respondente(59L,"Customer","Nonpriority Customer","","Neutral","Non Problematic"," "),
				new Respondente(60L,"Customer","Priority Customer","","Black out","Problematic"," "),
				new Respondente(61L,"Customer","Priority Customer","","Neutral","Non Problematic"," "),
				new Respondente(62L,"Customer","Priority Customer","","Black out","Problematic"," "),
				new Respondente(63L,"Customer","Priority Customer","","Neutral","Problematic"," "),
				new Respondente(64L,"Customer","Distributors/Retailers","","Black out","Non Problematic"," "),
				new Respondente(65L,"Customer","Distributors/Retailers","","Critical","Non Problematic"," "),
				new Respondente(66L,"Customer","Distributors/Retailers","","Low criticality","Problematic"," "),
				new Respondente(67L,"Customer","Distributors/Retailers","","Critical","Non Problematic"," "),
				new Respondente(68L,"Customer","Distributors/Retailers","","Very critical","Non Problematic"," "),
				new Respondente(69L,"Customer","Distributors/Retailers","","Critical","Non Problematic"," "),
				new Respondente(70L,"Customer","Distributors/Retailers","","Low criticality","Problematic"," "),
				new Respondente(71L,"Customer","Distributors/Retailers","","Very critical","Non Problematic"," "),
				new Respondente(72L,"Customer","Distributors/Retailers","","Very critical","Problematic"," "),
				new Respondente(73L,"Customer","Priority Customer","","Black out","Non Problematic"," "),
				new Respondente(74L,"Customer","Priority Customer","","Non critical","Problematic"," "),
				new Respondente(75L,"Customer","Priority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(76L,"Customer","Priority Customer","","Non critical","Problematic"," "),
				new Respondente(77L,"Customer","Priority Customer","","Very critical","Non Problematic"," "),
				new Respondente(78L,"Customer","Priority Customer","","Critical","Problematic"," "),
				new Respondente(79L,"Customer","Average Customer","","Critical","Problematic"," "),
				new Respondente(80L,"Customer","Average Customer","","Critical","Problematic"," "),
				new Respondente(81L,"Customer","Average Customer","","Neutral","Non Problematic"," "),
				new Respondente(82L,"Customer","Average Customer","","Non critical","Non Problematic"," "),
				new Respondente(83L,"Customer","Average Customer","","Very critical","Non Problematic"," "),
				new Respondente(84L,"Customer","Average Customer","","Very critical","Problematic"," "),
				new Respondente(85L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(86L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(87L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(88L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(89L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(90L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(91L,"Customer","Nonpriority Customer","","Neutral","Non Problematic"," "),
				new Respondente(92L,"Customer","Nonpriority Customer","","Neutral","Problematic"," "),
				new Respondente(93L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(94L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(95L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(96L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(97L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(98L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(99L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(100L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(101L,"Customer","Nonpriority Customer","","Non critical","Problematic"," "),
				new Respondente(102L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(103L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(104L,"Customer","Nonpriority Customer","","Non critical","Problematic"," "),
				new Respondente(105L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(106L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(107L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(108L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(109L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(110L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(111L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(112L,"Customer","Nonpriority Customer","","Neutral","Problematic"," "),
				new Respondente(113L,"Customer","Nonpriority Customer","","Non critical","Problematic"," "),
				new Respondente(114L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(115L,"Customer","Nonpriority Customer","","Non critical","Non Problematic"," "),
				new Respondente(116L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(117L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(118L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(119L,"Customer","Nonpriority Customer","","Neutral","Problematic"," "),
				new Respondente(120L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(121L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(122L,"Customer","Nonpriority Customer","","Non critical","Problematic"," "),
				new Respondente(123L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(124L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(125L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(126L,"Customer","Prospects","","Black out","Problematic"," "),
				new Respondente(127L,"Customer","Prospects","","Very critical","Problematic"," "),
				new Respondente(128L,"Customer","Prospects","","Low criticality","Non Problematic"," "),
				new Respondente(129L,"Customer","Prospects","","Low criticality","Problematic"," "),
				new Respondente(130L,"Customer","Prospects","","Non critical","Problematic"," "),
				new Respondente(131L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(132L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(133L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(134L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(135L,"Customer","Nonpriority Customer","","Neutral","Problematic"," "),
				new Respondente(136L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(137L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(138L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(139L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(140L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(141L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(142L,"Customer","Nonpriority Customer","","Non critical","Non Problematic"," "),
				new Respondente(143L,"Customer","Nonpriority Customer","","Non critical","Problematic"," "),
				new Respondente(144L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(145L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(146L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(147L,"Customer","Nonpriority Customer","","Low criticality","Problematic"," "),
				new Respondente(148L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(149L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(150L,"Customer","Nonpriority Customer","","Non critical","Non Problematic"," "),
				new Respondente(151L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(152L,"Customer","Nonpriority Customer","","Non critical","Problematic"," "),
				new Respondente(153L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(154L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(155L,"Customer","Nonpriority Customer","","Non critical","Non Problematic"," "),
				new Respondente(156L,"Customer","Nonpriority Customer","","Neutral","Non Problematic"," "),
				new Respondente(157L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(158L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(159L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(160L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(161L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(162L,"Customer","Nonpriority Customer","","Low criticality","Problematic"," "),
				new Respondente(163L,"Customer","Nonpriority Customer","","Neutral","Non Problematic"," "),
				new Respondente(164L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(165L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(166L,"Customer","Nonpriority Customer","","Critical","Problematic"," "),
				new Respondente(167L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(168L,"Customer","Nonpriority Customer","","Low criticality","Problematic"," "),
				new Respondente(169L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(170L,"Customer","Nonpriority Customer","","Non critical","Problematic"," "),
				new Respondente(171L,"Customer","Nonpriority Customer","","Neutral","Non Problematic"," "),
				new Respondente(172L,"Customer","Nonpriority Customer","","Very critical","Problematic"," "),
				new Respondente(173L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(174L,"Customer","Nonpriority Customer","","Neutral","Non Problematic"," "),
				new Respondente(175L,"Customer","Nonpriority Customer","","Low criticality","Non Problematic"," "),
				new Respondente(176L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(177L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(178L,"Customer","Nonpriority Customer","","Neutral","Problematic"," "),
				new Respondente(179L,"Customer","Nonpriority Customer","","Neutral","Problematic"," "),
				new Respondente(180L,"Customer","Nonpriority Customer","","Black out","Non Problematic"," "),
				new Respondente(181L,"Customer","Nonpriority Customer","","Low criticality","Problematic"," "),
				new Respondente(182L,"Customer","Nonpriority Customer","","Neutral","Problematic"," "),
				new Respondente(183L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(184L,"Customer","Nonpriority Customer","","Critical","Non Problematic"," "),
				new Respondente(185L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(186L,"Customer","Nonpriority Customer","","Very critical","Non Problematic"," "),
				new Respondente(187L,"Customer","Nonpriority Customer","","Black out","Problematic"," "),
				new Respondente(188L,"Customer","Nonpriority Customer","","Low criticality","Problematic"," "),
				new Respondente(189L,"Customer","Nonpriority Customer","","Neutral","Non Problematic"," "),
				new Respondente(190L,"Customer","Distributors/Retailers","","Neutral","Problematic"," "),
				new Respondente(191L,"Customer","Distributors/Retailers","","Very critical","Non Problematic"," "),
				new Respondente(192L,"Customer","Distributors/Retailers","","Black out","Problematic"," "),
				new Respondente(193L,"Customer","Distributors/Retailers","","Critical","Non Problematic"," "),
				new Respondente(194L,"Customer","Distributors/Retailers","","Non critical","Problematic"," "),
				new Respondente(195L,"Customer","Distributors/Retailers","","Non critical","Non Problematic"," "),
				new Respondente(196L,"Customer","Distributors/Retailers","","Critical","Problematic"," "),
				new Respondente(197L,"Customer","Distributors/Retailers","","Neutral","Problematic"," "),
				new Respondente(198L,"Customer","Distributors/Retailers","","Black out","Non Problematic"," "),
				new Respondente(199L,"Customer","Distributors/Retailers","","Low criticality","Non Problematic"," "),
				new Respondente(200L,"Customer","Prospects","","Non critical","Problematic"," "),
				new Respondente(201L,"Customer","Prospects","","Very critical","Problematic"," "),
				new Respondente(202L,"Customer","Prospects","","Critical","Problematic"," "),
				new Respondente(203L,"Customer","Prospects","","Low criticality","Problematic"," "),
				new Respondente(204L,"Customer","Prospects","","Low criticality","Non Problematic"," "),
				new Respondente(205L,"Customer","Prospects","","Black out","Non Problematic"," "),
				new Respondente(206L,"Customer","Prospects","","Low criticality","Problematic"," "),
				new Respondente(207L,"Customer","Prospects","","Neutral","Non Problematic"," "),
				new Respondente(208L,"Customer","Prospects","","Very critical","Problematic"," "),
				new Respondente(209L,"Customer","Prospects","","Black out","Non Problematic"," "),
				new Respondente(210L,"Customer","Prospects","","Critical","Problematic"," "),
				new Respondente(211L,"Customer","Prospects","","Black out","Non Problematic"," "),
				new Respondente(212L,"Employees","KAM and back office supporters","","Neutral","Problematic"," "),
				new Respondente(213L,"Employees","KAM and back office supporters","","Non critical","Problematic"," "),
				new Respondente(214L,"Employees","KAM and back office supporters","","Low criticality","Problematic"," "),
				new Respondente(215L,"Employees","KAM and back office supporters","","Very critical","Non Problematic"," "),
				new Respondente(216L,"Employees","KAM and back office supporters","","Neutral","Problematic"," "),
				new Respondente(217L,"Employees","Managers","","Critical","Non Problematic"," "),
				new Respondente(218L,"Employees","Managers","","Neutral","Problematic"," "),
				new Respondente(219L,"Employees","Managers","","Critical","Non Problematic"," "),
				new Respondente(220L,"Employees","Operational personnel","","Neutral","Non Problematic"," "),
				new Respondente(221L,"Employees","Operational personnel","","Black out","Non Problematic"," "),
				new Respondente(222L,"Employees","Operational personnel","","Non critical","Non Problematic"," "),
				new Respondente(223L,"Employees","Operational personnel","","Low criticality","Problematic"," "),
				new Respondente(224L,"Employees","Operational personnel","","Critical","Problematic"," "),
				new Respondente(225L,"Employees","Operational personnel","","Low criticality","Problematic"," "),
				new Respondente(226L,"Employees","Unions","","Critical","Problematic"," "),
				new Respondente(227L,"Employees","Unions","","Very critical","Non Problematic"," "),
				new Respondente(228L,"Employees","Third party personnel","","Neutral","Problematic"," "),
				new Respondente(229L,"Employees","Third party personnel","","Very critical","Non Problematic"," "),
				new Respondente(230L,"Employees","Third party personnel","","Black out","Problematic"," "),
				new Respondente(231L,"Competitors","Competitors","","Very critical","Non Problematic"," "),
				new Respondente(232L,"Competitors","Competitors","","Low criticality","Problematic"," "),
				new Respondente(233L,"Competitors","Competitors","","Critical","Problematic"," "),
				new Respondente(234L,"Competitors","Competitors","","Critical","Problematic"," "),
				new Respondente(235L,"Competitors","Competitors","","Critical","Non Problematic"," "),
				new Respondente(236L,"Competitors","Competitors","","Very critical","Non Problematic"," "),
				new Respondente(237L,"Competitors","Competitors","","Neutral","Non Problematic"," "),
				new Respondente(238L,"Competitors","Competitors","","Very critical","Problematic"," "),
				new Respondente(239L,"Competitors","Competitors","","Neutral","Problematic"," "),
				new Respondente(240L,"Competitors","Competitors","","Very critical","Non Problematic"," "),
				new Respondente(241L,"Competitors","Competitors","","Non critical","Problematic"," "),
				new Respondente(242L,"Competitors","Competitors","","Very critical","Problematic"," "),
				new Respondente(243L,"Competitors","Competitors","","Low criticality","Problematic"," "),
				new Respondente(244L,"Competitors","Competitors","","Neutral","Problematic"," "),
				new Respondente(245L,"Competitors","Competitors","","Non critical","Non Problematic"," "),
				new Respondente(246L,"Competitors","Competitors","","Neutral","Problematic"," "),
				new Respondente(247L,"Competitors","Competitors","","Neutral","Problematic"," "),
				new Respondente(248L,"Competitors","Competitors","","Critical","Problematic"," "),
				new Respondente(249L,"Competitors","Competitors","","Very critical","Non Problematic"," "),
				new Respondente(250L,"Competitors","Competitors","","Neutral","Problematic"," "),
				new Respondente(251L,"Competitors","Competitors","","Neutral","Problematic"," "),
				new Respondente(252L,"Competitors","Competitors","","Very critical","Non Problematic"," "),
				new Respondente(253L,"Communities","Community 1","","Critical","Non Problematic"," "),
				new Respondente(254L,"Communities","Community 1","","Critical","Non Problematic"," "),
				new Respondente(255L,"Communities","Community 1","","Black out","Problematic"," "),
				new Respondente(256L,"Communities","Community 1","","Very critical","Problematic"," "),
				new Respondente(257L,"Communities","Community 1","","Low criticality","Non Problematic"," "),
				new Respondente(258L,"Communities","Community 1","","Neutral","Non Problematic"," "),
				new Respondente(259L,"Communities","Community 1","","Non critical","Non Problematic"," "),
				new Respondente(260L,"Communities","Community 1","","Non critical","Non Problematic"," "),
				new Respondente(261L,"Communities","Community 1","","Black out","Non Problematic"," "),
				new Respondente(262L,"Communities","Community 2","","Non critical","Non Problematic"," "),
				new Respondente(263L,"Communities","Community 2","","Critical","Problematic"," "),
				new Respondente(264L,"Communities","Community 2","","Black out","Problematic"," "),
				new Respondente(265L,"Communities","Community 2","","Black out","Non Problematic"," "),
				new Respondente(266L,"Communities","Community 2","","Neutral","Problematic"," "),
				new Respondente(267L,"Communities","Community 2","","Critical","Non Problematic"," "),
				new Respondente(268L,"Communities","Community 2","","Non critical","Non Problematic"," "),
				new Respondente(269L,"Communities","Community 2","","Non critical","Problematic"," "),
				new Respondente(270L,"Communities","Community 2","","Very critical","Problematic"," "),
				new Respondente(271L,"Communities","Community 2","","Black out","Problematic"," "),
				new Respondente(272L,"Communities","Community 2","","Black out","Problematic"," "),
				new Respondente(273L,"Communities","Community 2","","Non critical","Problematic"," "),
				new Respondente(274L,"Communities","Community 2","","Neutral","Problematic"," "),
				new Respondente(275L,"Communities","Community 2","","Critical","Problematic"," "),
				new Respondente(276L,"Communities","Community 2","","Black out","Problematic"," "),
				new Respondente(277L,"Communities","Community 2","","Black out","Non Problematic"," "),
				new Respondente(278L,"Communities","Community 2","","Very critical","Non Problematic"," "),
				new Respondente(279L,"Communities","Community 2","","Neutral","Non Problematic"," "),
				new Respondente(280L,"Communities","Community 2","","Black out","Non Problematic"," "),
				new Respondente(281L,"Communities","Community 2","","Non critical","Problematic"," "),
				new Respondente(282L,"Communities","Community 3","","Neutral","Problematic"," "),
				new Respondente(283L,"Communities","Community 3","","Critical","Problematic"," "),
				new Respondente(284L,"Communities","Community 3","","Critical","Problematic"," "),
				new Respondente(285L,"Communities","Community 3","","Neutral","Problematic"," "),
				new Respondente(286L,"Communities","Community 3","","Low criticality","Non Problematic"," "),
				new Respondente(287L,"Communities","Community 3","","Neutral","Non Problematic"," "),
				new Respondente(288L,"Communities","Community 3","","Neutral","Non Problematic"," "),
				new Respondente(289L,"Communities","Community 3","","Black out","Problematic"," "),
				new Respondente(290L,"Communities","Community 3","","Non critical","Non Problematic"," "),
				new Respondente(291L,"Communities","Community 3","","Non critical","Non Problematic"," "),
				new Respondente(292L,"Communities","Community 3","","Low criticality","Non Problematic"," "),
				new Respondente(293L,"Communities","Community 3","","Low criticality","Non Problematic"," "),
				new Respondente(294L,"Communities","Community 3","","Very critical","Non Problematic"," "),
				new Respondente(295L,"Communities","Community 3","","Low criticality","Problematic"," "),
				new Respondente(296L,"Communities","Community 3","","Low criticality","Non Problematic"," "),
				new Respondente(297L,"Communities","Community 3","","Critical","Problematic"," "),
				new Respondente(298L,"Communities","Community 3","","Neutral","Problematic"," "),
				new Respondente(299L,"Communities","Community 3","","Black out","Problematic"," "),
				new Respondente(300L,"Communities","Community 3","","Non critical","Non Problematic"," "),
				new Respondente(301L,"Communities","Community 3","","Black out","Non Problematic"," "),
				new Respondente(302L,"Communities","Community 3","","Low criticality","Problematic"," "),
				new Respondente(303L,"Communities","Community 3","","Black out","Problematic"," "),
				new Respondente(304L,"Communities","Community 3","","Very critical","Problematic"," "),
				new Respondente(305L,"Communities","Community 1","","Black out","Non Problematic"," "),
				new Respondente(306L,"Communities","Community 1","","Black out","Non Problematic"," "),
				new Respondente(307L,"Communities","Community 1","","Black out","Non Problematic"," "),
				new Respondente(308L,"Communities","Community 1","","Low criticality","Non Problematic"," "),
				new Respondente(309L,"Communities","Community 1","","Non critical","Non Problematic"," "),
				new Respondente(310L,"Citizens","Citizens","","Critical","Problematic"," "),
				new Respondente(311L,"Citizens","Citizens","","Low criticality","Problematic"," "),
				new Respondente(312L,"Citizens","Citizens","","Neutral","Problematic"," "),
				new Respondente(313L,"Citizens","Citizens","","Non critical","Problematic"," "),
				new Respondente(314L,"Citizens","Citizens","","Non critical","Problematic"," "),
				new Respondente(315L,"Citizens","Citizens","","Black out","Problematic"," "),
				new Respondente(316L,"Citizens","Citizens","","Very critical","Non Problematic"," "),
				new Respondente(317L,"Citizens","Citizens","","Critical","Non Problematic"," "),
				new Respondente(318L,"Citizens","Citizens","","Very critical","Non Problematic"," "),
				new Respondente(319L,"Citizens","Citizens","","Low criticality","Non Problematic"," "),
				new Respondente(320L,"Citizens","Citizens","","Neutral","Problematic"," "),
				new Respondente(321L,"Citizens","Citizens","","Neutral","Non Problematic"," "),
				new Respondente(322L,"Citizens","Citizens","","Black out","Problematic"," "),
				new Respondente(323L,"Citizens","Citizens","","Non critical","Non Problematic"," "),
				new Respondente(324L,"Citizens","Citizens","","Non critical","Problematic"," "),
				new Respondente(325L,"Citizens","Citizens","","Non critical","Problematic"," "),
				new Respondente(326L,"Citizens","Citizens","","Low criticality","Non Problematic"," "),
				new Respondente(327L,"Citizens","Citizens","","Low criticality","Problematic"," "),
				new Respondente(328L,"Citizens","Citizens","","Non critical","Non Problematic"," "),
				new Respondente(329L,"Citizens","Citizens","","Non critical","Non Problematic"," "),
				new Respondente(330L,"Authorities","Federal Government","Ministers","Neutral","Problematic"," "),
				new Respondente(331L,"Authorities","Federal Government","Presidency","Black out","Problematic"," "),
				new Respondente(332L,"Authorities","Federal Government","Subministers","Non critical","Problematic"," "),
				new Respondente(333L,"Authorities","Local Government","Mayors","Low criticality","Problematic"," "),
				new Respondente(334L,"Authorities","Local Government","Councilors","Low criticality","Non Problematic"," "),
				new Respondente(335L,"Authorities","Local Government","Neighborhood meetings","Very critical","Problematic"," "),
				new Respondente(336L,"Authorities","Local Government","Others in Local Government","Critical","Problematic"," "),
				new Respondente(337L,"Authorities","Regional Government","Regional Government Authorities","Very critical","Problematic"," "),
				new Respondente(338L,"Authorities","Regional Government","SEREMIS","Very critical","Non Problematic"," "),
				new Respondente(339L,"Politicians","Representative","","Non critical","Problematic"," "),
				new Respondente(340L,"Politicians","Political Parties Leaders","","Non critical","Non Problematic"," "),
				new Respondente(341L,"Politicians","Senators","","Black out","Problematic"," "),
				new Respondente(342L,"Employees","Managers","","Critical","Problematic"," "),
				new Respondente(343L,"Employees","Unions","","Non critical","Non Problematic"," "),
				new Respondente(344L,"Employees","Third party personnel","","Non critical","Problematic"," "),
				new Respondente(345L,"Employees","Managers","","Black out","Non Problematic"," "),
				new Respondente(346L,"Employees","Unions","","Low criticality","Non Problematic"," "),
				new Respondente(347L,"Employees","Third party personnel","","Very critical","Non Problematic"," "),
				new Respondente(348L,"Investors","Board Directors","","Very critical","Non Problematic"," "),
				new Respondente(349L,"Investors","Board Directors","","Black out","Problematic"," "),
				new Respondente(350L,"Investors","Board Directors","","Neutral","Non Problematic"," "),
				new Respondente(351L,"Investors","Board Directors","","Black out","Problematic"," "),
				new Respondente(352L,"Investors","Board Directors","","Black out","Problematic"," "),
				new Respondente(353L,"Investors","Shareholders","","Very critical","Non Problematic"," "),
				new Respondente(354L,"Investors","Shareholders","","Non critical","Problematic"," "),
				new Respondente(355L,"Investors","Shareholders","","Neutral","Non Problematic"," "),
				new Respondente(356L,"Investors","Shareholders","","Critical","Problematic"," "),
				new Respondente(357L,"Investors","Investors","","Neutral","Problematic"," "),
				new Respondente(358L,"Investors","Investors","","Black out","Non Problematic"," "),
				new Respondente(359L,"Research/Education","R&D centers","","Neutral","Non Problematic"," "),
				new Respondente(360L,"Research/Education","R&D centers","","Non critical","Non Problematic"," "),
				new Respondente(361L,"Research/Education","Institutes","","Critical","Non Problematic"," "),
				new Respondente(362L,"Research/Education","Institutes","","Neutral","Non Problematic"," "),
				new Respondente(363L,"Research/Education","Universities","","Black out","Problematic"," "),
				new Respondente(364L,"Research/Education","Universities","","Critical","Non Problematic"," "),
				new Respondente(365L,"Research/Education","Universities","","Very critical","Non Problematic"," "),
				new Respondente(366L,"Opinion Leaders","Opinion Leaders","","Non critical","Non Problematic"," "),
				new Respondente(367L,"Opinion Leaders","Opinion Leaders","","Non critical","Problematic"," "),
				new Respondente(368L,"Opinion Leaders","Opinion Leaders","","Black out","Non Problematic"," "),
				new Respondente(369L,"Opinion Leaders","Opinion Leaders","","Non critical","Problematic"," "),
				new Respondente(370L,"Opinion Leaders","Opinion Leaders","","Non critical","Problematic"," "),
				new Respondente(371L,"Opinion Leaders","Opinion Leaders","","Black out","Problematic"," "),
				new Respondente(372L,"Opinion Leaders","Opinion Leaders","","Critical","Non Problematic"," "),
				new Respondente(373L,"Asociations","Business Comunity Asociations","","Neutral","Problematic"," "),
				new Respondente(374L,"Asociations","Business Comunity Asociations","","Very critical","Problematic"," "),
				new Respondente(375L,"Asociations","Trade Unions","","Low criticality","Problematic"," "),
				new Respondente(376L,"Asociations","Trade Unions","","Critical","Problematic"," "),
				new Respondente(377L,"Asociations","Industrial Asociations","","Black out","Non Problematic"," "),
				new Respondente(378L,"Asociations","Industrial Asociations","","Neutral","Non Problematic"," "),
				new Respondente(379L,"Asociations","Professional Asociations","","Very critical","Non Problematic"," "),
				new Respondente(380L,"Asociations","Professional Asociations","","Non critical","Non Problematic"," "),
				new Respondente(381L,"Research/Education","Institutes","","Very critical","Problematic"," "),
				new Respondente(382L,"Research/Education","R&D centers","","Low criticality","Problematic"," "),
				new Respondente(383L,"Investors","Shareholders","","Neutral","Non Problematic"," "),
				new Respondente(384L,"Investors","Shareholders","","Critical","Non Problematic"," "),
				new Respondente(385L,"Investors","Investors","","Low criticality","Non Problematic"," "),
				new Respondente(386L,"Investors","Board Directors","","Very critical","Non Problematic"," "),
				new Respondente(387L,"NGO's","NGO's","","Very critical","Problematic"," "),
				new Respondente(388L,"NGO's","NGO's","","Critical","Non Problematic"," "),
				new Respondente(389L,"NGO's","NGO's","","Very critical","Non Problematic"," "),
				new Respondente(390L,"NGO's","NGO's","","Critical","Non Problematic"," "),
				new Respondente(391L,"NGO's","NGO's","","Low criticality","Problematic"," "),
				new Respondente(392L,"Suppliers","Critical suppliers","","Non critical","Non Problematic"," "),
				new Respondente(393L,"Suppliers","Critical suppliers","","Neutral","Non Problematic"," "),
				new Respondente(394L,"Suppliers","Critical suppliers","","Black out","Problematic"," "),
				new Respondente(395L,"Suppliers","Critical suppliers","","Black out","Problematic"," "),
				new Respondente(396L,"Suppliers","Critical suppliers","","Non critical","Problematic"," "),
				new Respondente(397L,"Suppliers","Non critical suppliers","","Black out","Problematic"," "),
				new Respondente(398L,"Suppliers","Non critical suppliers","","Very critical","Non Problematic"," "),
				new Respondente(399L,"Suppliers","Non critical suppliers","","Critical","Non Problematic"," "),
				new Respondente(400L,"Suppliers","Non critical suppliers","","Low criticality","Problematic"," "));
	}

	public static List<String> getStakeholderPorcentajeImportanciaString() {
		return Arrays.asList("4%","7%","2%","44%","6%","6%","14%","6%","4%","2%","2%","1%","2%");
	}
	public static List<Double> getStakeholderPorcentajeImportanciaDouble() {
		return Arrays.asList(4d,7d,2d,44d,6d,6d,14d,6d,4d,2d,2d,1d,2d);
	}

	public static List<String> getStakeholderPonderadoImportanciaString() {
		return Arrays.asList("9%","11%","10%","9%","11%","8%","4%","4%","5%","4%","5%","19%","1%");
	}
	
	public static List<Double> getStakeholderPonderadoImportanciaDouble() {
		return Arrays.asList(9d,11d,10d,9d,11d,8d,4d,4d,5d,4d,5d,19d,1d);
	}
	
}