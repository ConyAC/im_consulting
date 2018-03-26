package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.data.dummy.DummyDataGenerator;
import cl.koritsu.im.domain.Segmento;
import cl.koritsu.im.domain.Stakeholder;
import cl.koritsu.im.domain.SubSegmento;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = RespuestaEncuesta.NAME, cached = true)
public class RespuestaEncuesta extends CssLayout implements View {

	public static final String NAME = "resultado";
	
    Table tbFichas;
    Panel vlayout;
    
//    @Autowired
//    ValuedService service;
//	@Autowired
//	UserService serviceUser;
    
    public RespuestaEncuesta() {
	}

    @PostConstruct
    public void init() {
    	
    	vlayout = new Panel();
        setSizeFull();
        addStyleName("schedule");
//        ValuedEventBus.register(this);
        
        addComponent(buildToolbar());
     	
     	GridLayout glRoot = new GridLayout(6,10);
     	addComponent(vlayout);
     	glRoot.setSpacing(true);
		glRoot.setMargin(true);
		glRoot.setWidth("100%");
		
		
		ComboBox cbEstudios = new ComboBox("Estudios");
		cbEstudios.addItem("Reputación");
		cbEstudios.addItem("Riesgos");
		cbEstudios.addItem("Importancia");
		glRoot.addComponents(cbEstudios);
		
		ComboBox cbStakeholder = new ComboBox("Stakeholder");
		for(Stakeholder sh : DummyDataGenerator.getStakeHolder())
			cbStakeholder.addItem(sh.getNombre());
		glRoot.addComponents(cbStakeholder);
		
		ComboBox cbSegmento = new ComboBox("Segmento");
		for(Segmento sh : DummyDataGenerator.getSegmentos())
			cbSegmento.addItem(sh.getNombre());
		glRoot.addComponents(cbSegmento);
		
		ComboBox cbSubsegmento = new ComboBox("Sub-segmento");
		for(SubSegmento sh : DummyDataGenerator.getSubsegmentos())
			cbSubsegmento.addItem(sh.getNombre());
		glRoot.addComponents(cbSubsegmento);
		
		glRoot.addComponents(new Button("Buscar",FontAwesome.SEARCH));
		glRoot.addComponents(new Button("Graficar",FontAwesome.AREA_CHART));
		
		TabSheet tab = buildTab();
		
		glRoot.addComponent(tab,0,1,5,1);
		
		vlayout.setContent(glRoot);
		

    }
    
    private TabSheet buildTab() {
    	TabSheet tab = new TabSheet();
    	tab.setWidth("100%");
    	
    	VerticalLayout vl = new VerticalLayout();
    	vl.setWidth("100%");
    	
    	TreeTable ttable = new TreeTable();
    	ttable.setWidth("100%");ttable.setHeightUndefined();
    	ttable.addContainerProperty("Indice/Pregunta", String.class, null);
    	ttable.addContainerProperty("Graficar", CheckBox.class, null);
    	ttable.addContainerProperty("Valor 2018", String.class, null);
    	ttable.addContainerProperty("Valor 2017", String.class, null);
    	ttable.addContainerProperty("Valor 2016", String.class, null);
    	ttable.addContainerProperty("Simulador", TextField.class, null);
    	ttable.addContainerProperty("Resultado", String.class, null);

    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Modelo Reputación", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 0);
    	ttable.addItem(new Object[]{"Conocimiento", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 1);
    	ttable.addItem(new Object[]{"Relevancia", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 2);
    	ttable.addItem(new Object[]{"Actitud", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 3);
    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	
    	ttable.addItem(new Object[]{"Indice de Reputación (IRC)", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 4);
    	ttable.addItem(new Object[]{"Atributos Emocionales", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 5);
    	ttable.setParent(5, 4);
    	ttable.addItem(new Object[]{"Estima", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 6);
    	ttable.addItem(new Object[]{"Confianza", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 7);
    	ttable.addItem(new Object[]{"Admiración", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 8);
    	ttable.setParent(6, 5);
    	ttable.setParent(7, 5);
    	ttable.setParent(8, 5);
    	ttable.addItem(new Object[]{"Atributos Racionales", new CheckBox(), "4%", "6%","7%", new TextField(),""}, 9);
    	ttable.setParent(9, 4);
    	
		Table respondenteTable = new Table();
		respondenteTable.setWidth("100%");
		respondenteTable.addContainerProperty("Nombres", String.class, null);
		respondenteTable.addContainerProperty("Conocimiento", String.class, null);
		respondenteTable.addContainerProperty("Relevancia", String.class, null);
		respondenteTable.addContainerProperty("Actitud", String.class, null);
		respondenteTable.addContainerProperty("Estima", String.class, null);
		respondenteTable.addContainerProperty("Confianza", String.class, null);
		respondenteTable.addContainerProperty("Admiración", String.class, null);
    	
		vl.addComponents(ttable,respondenteTable);
    	
    	tab.addTab(new Panel(vl) {
    		{
    			setSizeFull();
    		}
    	},"Modelo Reputación");
    	tab.addTab(new Table(),"Modelo Riesgo");
    	tab.addTab(new Table(),"Modelo Afinidad - Importancia");
    	
		return tab;
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
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Editar Encuesta");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
    
    public void enter(final ViewChangeEvent event) {

    }
}
