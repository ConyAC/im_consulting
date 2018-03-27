package cl.koritsu.im.data.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.koritsu.im.domain.Pregunta;
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
    	
    	SubSegmento subsegmento = new SubSegmento(); subsegmento.setNombre("A. Empresariales");subsegmento.setId(1L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("A. Gremiales");subsegmento.setId(2L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("A. Industriales");subsegmento.setId(3L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("A. Profesionales");subsegmento.setId(4L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("A. Gobierno central");subsegmento.setId(5L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("A. Locales");subsegmento.setId(6L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("A. Regionales");subsegmento.setId(7L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("CP. Diputados");subsegmento.setId(8L);
    	subsegmentos.add(subsegmento);
    	subsegmento = new SubSegmento(); subsegmento.setNombre("CP. Líderes de partidos políticos");subsegmento.setId(9L);
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
}