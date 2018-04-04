package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;
import cl.koritsu.im.data.dummy.DummyDataGenerator;
import cl.koritsu.im.domain.Segmento;
import cl.koritsu.im.domain.Stakeholder;
import cl.koritsu.im.domain.SubSegmento;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = FichaRespondenteEncuestaView.NAME, cached = true)
public class FichaRespondenteEncuestaView extends CssLayout implements View {

	public static final String NAME = "ficha";
	
    public FichaRespondenteEncuestaView() {
	}

    @PostConstruct
    public void init() {
    	
        setSizeFull();
        addStyleName("schedule");
        
        addComponent(buildToolbar());
     	
     	GridLayout glRoot = new GridLayout(2,10);
     	glRoot.setSpacing(true);
		glRoot.setMargin(true);
		glRoot.setWidth("100%");
		
		ComboBox cbRespondenteId = new ComboBox("Respondete");
		cbRespondenteId.addItem("1");
		cbRespondenteId.select("1");
		cbRespondenteId.addItem("2");
		cbRespondenteId.addItem("3");
		cbRespondenteId.addItem("4");
		glRoot.addComponent(cbRespondenteId,0,0);
		
		TextField tfStakeholder = new TextField("Stakeholder");
		for(Stakeholder sh : DummyDataGenerator.getStakeHolder()) {
			tfStakeholder.setValue(sh.getNombre());
			break;
		}
		tfStakeholder.setReadOnly(true);
			
		glRoot.addComponent(tfStakeholder,0,1);
		
		TextField tfSegmento = new TextField("Segmento");
		for(Segmento sh : DummyDataGenerator.getSegmentos()) {
			tfSegmento.setValue(sh.getNombre()); 
			break;
		}
		tfSegmento.setReadOnly(true);
		glRoot.addComponent(tfSegmento,0,2);
		
		TextField tfSubsegmento = new TextField("Sub-segmento");
		for(SubSegmento sh : DummyDataGenerator.getSubsegmentos()) {
			tfSubsegmento.setValue(sh.getNombre());
			break;
		}

		tfSubsegmento.setReadOnly(true);
		glRoot.addComponent(tfSubsegmento,0,3);
		
		ComboBox cbCriticidad = new ComboBox("Criticidad");
		for(String sh : DummyDataGenerator.getCriticidades())
			cbCriticidad.addItem(sh);
		cbCriticidad.setValue(DummyDataGenerator.getCriticidades().get(1));
		glRoot.addComponent(cbCriticidad,0,4);
		
		ComboBox cbActitud = new ComboBox("Actitud");
		for(String sh : DummyDataGenerator.getActitudes())
			cbActitud.addItem(sh);
		cbActitud.setValue(DummyDataGenerator.getActitudes().get(1));
		glRoot.addComponent(cbActitud,0,5);
		
		TextArea taObservacion = new TextArea("Observacion");
		taObservacion.setValue("Respondente ingresado para Encuesta inicio de Enero, área patrocinadora Gerencia Estudios.");
		glRoot.addComponent(taObservacion,0,6);
		
		
		//tab con los resultado
		TabSheet tab = buildTab();
		glRoot.addComponent(tab,1,0,1,6);
		glRoot.setColumnExpandRatio(1, 0.8f);
		
		addComponent(new Panel(glRoot) {
			{
				setSizeFull();
			}
		});
		
		Button agregarContacto = new Button("Agregar Contacto");
		agregarContacto.setIcon(FontAwesome.PLUS);
		glRoot.addComponent(agregarContacto,0,7,1,7);
		glRoot.setComponentAlignment(agregarContacto, Alignment.MIDDLE_RIGHT);
		
		Table table = new Table();
		table.setSizeFull();
		table.addContainerProperty("Nombre", String.class, null);
		table.addContainerProperty("RUT", String.class, null);
		table.addContainerProperty("Cargo", String.class, null);
		table.addContainerProperty("Correo", String.class, null);
		table.addContainerProperty("Teléfono", String.class, null);
		table.addContainerProperty("Rol", String.class, null);
		table.addContainerProperty("Adaptabilidad", String.class, null);
		table.addContainerProperty("Actitud", String.class, null);
		table.addContainerProperty("Frecuencia", String.class, null);
		table.addContainerProperty("Acción", Button.class, null);
		
		
		table.addItem(new Object[]{"Respondente 1", "1-4","Gerente Comercial", "respondente@a.com","", "","", "","",new Button(FontAwesome.EDIT)}, 0);
		table.addItem(new Object[]{"Contacto 1", "1-9","Gerente General", "a@a.com","667884344", "Aprobador","Innovador", "Neutral","En profundidad",new Button(FontAwesome.EDIT)}, 1);
		table.addItem(new Object[]{"Contacto 2", "1-8","Gerente Comercial", "a@a.com","33423232", "Aprobador","Innovador", "Neutral","En profundidad",new Button(FontAwesome.EDIT)}, 2);
		table.addItem(new Object[]{"Contacto 3", "1-7","Gerente RRHH", "a@a.com","22323555", "Aprobador","Innovador", "Neutral","En profundidad",new Button(FontAwesome.EDIT)}, 3);
		
		glRoot.addComponent(table,0,8,1,8);
		glRoot.setRowExpandRatio(8, 1.0f);

    }
    
    private TabSheet buildTab() {
    	TabSheet tab = new TabSheet();
    	tab.setSizeFull();
    	
    	VerticalLayout reputacion = buildReputacionTable();
    	tab.addTab(new Panel(reputacion) {
    		{
    			setSizeFull();
    		}
    	},"Modelo Reputación");
    	
    	VerticalLayout riesgos = buildRiesgos();
    	
    	tab.addTab(new Panel(riesgos) {
    		{
    			setSizeFull();
    		}
    	},"Modelo Riesgo");
    	
    	VerticalLayout afinidad = buildAfinidad();
    	
    	tab.addTab(new Panel(afinidad) {
    		{
    			setSizeFull();
    		}
    	},"Modelo Afinidad - Importancia");
    	
		return tab;
	}
    
	private VerticalLayout buildAfinidad() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Indice/Pregunta", String.class, null);
    	ttable.addContainerProperty("Importancia", String.class, null);

    	// Create the tree nodes and set the hierarchy   	
    	ttable.addItem(new Object[]{"Modelo Importancia(II)",  ""}, 0);
    	ttable.addItem(new Object[]{"Escenario 1",  "43%"}, 1);
    	ttable.addItem(new Object[]{"Escenario 2", "39%"}, 2);
    	ttable.addItem(new Object[]{"Escenario 3",  "52%"}, 3);
    	ttable.addItem(new Object[]{"Escenario Promedio", "49%"}, 4);
    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(4, 0);
    	
//    	ttable.setPageLength(ttable.getItemIds().size());
    	
		vl.addComponents(ttable);
		vl.setExpandRatio(ttable, 1.0f);
		return vl;
	}

	private VerticalLayout buildRiesgos() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Indice/Pregunta", String.class, null);
    	ttable.addContainerProperty("Importancia", String.class, null);
    	
    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Modelo Riesgos",  "19%"}, 0);
    	ttable.addItem(new Object[]{"R1",  "5"}, 1);
    	ttable.addItem(new Object[]{"R2",  "2"}, 2);
    	ttable.addItem(new Object[]{"R3",  "3"}, 3);
    	ttable.addItem(new Object[]{"R4",  "3"}, 4);
    	ttable.addItem(new Object[]{"R5",  "4"}, 5);
    	ttable.addItem(new Object[]{"R6",  "3"}, 6);
    	ttable.addItem(new Object[]{"R7",  "2"}, 7);
    	ttable.addItem(new Object[]{"R8",  "1"}, 8);
    	ttable.addItem(new Object[]{"R9",  "5"}, 9);
    	ttable.addItem(new Object[]{"R10",  "1"}, 10);
    	ttable.addItem(new Object[]{"R11",  "2"}, 11);
    	ttable.addItem(new Object[]{"R12",  "2"}, 12);
    	ttable.addItem(new Object[]{"R13",  "4"}, 13);
    	ttable.addItem(new Object[]{"R14",  "4"}, 14);
    	ttable.addItem(new Object[]{"R15",  "1"}, 15);
    	
    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(4, 0);
    	ttable.setParent(5, 0);
    	ttable.setParent(6, 0);
    	ttable.setParent(7, 0);
    	ttable.setParent(8, 0);
    	ttable.setParent(9, 0);
    	ttable.setParent(10, 0);
    	ttable.setParent(11, 0);
    	ttable.setParent(12, 0);
    	ttable.setParent(13, 0);
    	ttable.setParent(14, 0);
    	ttable.setParent(15, 0);
		
		vl.addComponents(ttable);
		return vl;
	}

	private VerticalLayout buildReputacionTable() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
//    	ttable.setWidth("100%");
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Indice/Pregunta", String.class, null);
    	ttable.addContainerProperty("Desempeño", String.class, null);
    	ttable.addContainerProperty("Importancia", String.class, null);

    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Modelo Reputación",  "", ""}, 0);
    	ttable.addItem(new Object[]{"Conocimiento",  "7", ""}, 1);
    	ttable.addItem(new Object[]{"Relevancia",  "6", ""}, 2);
    	ttable.addItem(new Object[]{"Actitud",  "5", ""}, 3);
    	ttable.addItem(new Object[]{"Recomendación",  "7", ""}, 10);
    	ttable.addItem(new Object[]{"Esfuerzo",  "6", ""},11);
    	ttable.addItem(new Object[]{"Renovación/Recompra",  "5", ""}, 12);

    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(10, 0);
    	ttable.setParent(11, 0);
    	ttable.setParent(12, 0);
    	
    	ttable.addItem(new Object[]{"Indice de Reputación (IRC)",  "5,48", ""}, 4);
    	ttable.addItem(new Object[]{"Atributos Emocionales",  "", ""}, 5);
    	ttable.setParent(5, 4);
    	ttable.addItem(new Object[]{"D. Emocional General",  "6", "4"}, 15);
    	ttable.setParent(15, 5);
    	ttable.addItem(new Object[]{"Estima",  "5", "3"}, 6);
    	ttable.addItem(new Object[]{"Confianza",  "4", "5"}, 7);
    	ttable.addItem(new Object[]{"Admiración",  "5", "5"}, 8);
    	ttable.addItem(new Object[]{"Identificación",  "4", "3"}, 13);
    	ttable.addItem(new Object[]{"Empatía",  "6", "3"}, 14);

    	ttable.setParent(6, 15);
    	ttable.setParent(7, 15);
    	ttable.setParent(8, 15);
    	ttable.setParent(13, 15);
    	ttable.setParent(14, 15);
    	
    	ttable.addItem(new Object[]{"Atributos Racionales",  "", ""}, 9);    	
    	ttable.setParent(9, 4);
    	ttable.addItem(new Object[]{"D. Económico General",  "7", "1"}, 16);
    	ttable.setParent(16, 9);    	
    	ttable.addItem(new Object[]{"Rentabilidad",  "6", "3"}, 17);
    	ttable.addItem(new Object[]{"Solvencia",  "7", "3"}, 18);
    	ttable.addItem(new Object[]{"Crecimiento",  "6", "2"}, 19);
    	ttable.addItem(new Object[]{"Valor de mercado",  "6", "3"}, 20);
    	
    	ttable.setParent(17, 16);
    	ttable.setParent(18, 16);
    	ttable.setParent(19, 16);
    	ttable.setParent(20, 16);
    	
    	ttable.addItem(new Object[]{"D. Servicio General",  "4", "4"}, 21);
    	ttable.setParent(21, 9);    	
    	ttable.addItem(new Object[]{"Calidad",  "3", "5"}, 22);
    	ttable.addItem(new Object[]{"Precio",  "3", "4"}, 23);
    	ttable.addItem(new Object[]{"Atención al cliente",  "3", "4"}, 24);
    	ttable.addItem(new Object[]{"Satisfacción",  "3", "5"}, 25);
    	ttable.addItem(new Object[]{"Innovación",  "3", "4"}, 26);
    	
    	ttable.setParent(22, 21);
    	ttable.setParent(23, 21);
    	ttable.setParent(24, 21);
    	ttable.setParent(25, 21);
    	ttable.setParent(26, 21);
    	
    	ttable.addItem(new Object[]{"D. Colaboradores General",  "5", "4"}, 27);
    	ttable.setParent(27, 9);    	
    	ttable.addItem(new Object[]{"Talento",  "6", "4"}, 28);
    	ttable.addItem(new Object[]{"Seguridad laboral",  "5", "4"}, 29);
    	ttable.addItem(new Object[]{"Bienestar",  "6", "3"}, 30);
    	ttable.addItem(new Object[]{"Compensaciones",  "5", "4"}, 31);
    	ttable.addItem(new Object[]{"Igualdad",  "6", "3"}, 32);
    	ttable.addItem(new Object[]{"Meritocracia",  "6", "3"}, 33);
    	
    	ttable.setParent(28, 27);
    	ttable.setParent(29, 27);
    	ttable.setParent(30, 27);
    	ttable.setParent(31, 27);
    	ttable.setParent(32, 27);
    	ttable.setParent(33, 27);
    	
    	ttable.addItem(new Object[]{"D. Gobernabilidad General",  "6", "4"}, 34);
    	ttable.setParent(34, 9);    	
    	ttable.addItem(new Object[]{"Proveedores",  "7", "4"},35);
    	ttable.addItem(new Object[]{"Transparencia",  "6", "4"}, 36);
    	ttable.addItem(new Object[]{"Ética",  "6", "4"},37);
    	ttable.addItem(new Object[]{"Anticorrupción",  "6", "5"},38);
    	ttable.addItem(new Object[]{"Respeto",  "6", "4"}, 39);
    	
    	ttable.setParent(35, 34);
    	ttable.setParent(36, 34);
    	ttable.setParent(37, 34);
    	ttable.setParent(38, 34);
    	ttable.setParent(39, 34);
    	
    	ttable.addItem(new Object[]{"D. Liderazgo General",  "6", "4"}, 40);
    	ttable.setParent(40, 9);    	
    	ttable.addItem(new Object[]{"Líderes",  "7", "4"},41);
    	ttable.addItem(new Object[]{"Liderazgo justo",  "6", "4"}, 42);
    	ttable.addItem(new Object[]{"Gestión",  "7", "4"}, 43);
    	ttable.addItem(new Object[]{"Visión",  "7", "5"}, 44);
    	ttable.addItem(new Object[]{"Comunicación",  "6", "5"}, 45);
    	
    	ttable.setParent(41, 40);
    	ttable.setParent(42, 40);
    	ttable.setParent(43, 40);
    	ttable.setParent(44, 40);
    	ttable.setParent(45, 40);
    	
    	ttable.addItem(new Object[]{"D. RSE General",  "5", "5"}, 46);
    	ttable.setParent(46, 9);    	
    	ttable.addItem(new Object[]{"Responsable Medio Ambiente",  "7", "5"}, 47);
    	ttable.addItem(new Object[]{"Ahorro energético",  "7", "5"}, 48);
    	ttable.addItem(new Object[]{"Compromiso comunidad",  "7", "5"}, 49);
    	ttable.addItem(new Object[]{"Compromiso Chile",  "5", "4"}, 50);
    	ttable.addItem(new Object[]{"Inclusión",  "7", "4"}, 51);
    	ttable.addItem(new Object[]{"Buenas causas",  "4", "5"}, 52);
    	
    	ttable.setParent(47, 46);
    	ttable.setParent(48, 46);
    	ttable.setParent(49, 46);
    	ttable.setParent(50, 46);
    	ttable.setParent(51, 46);
    	ttable.setParent(52, 46);
		
		vl.addComponents(ttable);
		return vl;
	}

	private Component buildToolbar() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        header.setSpacing(true);
        Responsive.makeResponsive(header);
        
        Image logo = new Image();
        logo.setSource(new ThemeResource("img/logo_im_gris.png"));
        logo.setHeight("76px");
        logo.setWidth("70px");
        header.addComponent(logo);   
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Ficha Respondente");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
    
    public void enter(final ViewChangeEvent event) {

    }
}
