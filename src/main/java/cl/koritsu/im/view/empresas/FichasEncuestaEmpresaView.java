package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;
import cl.koritsu.im.data.dummy.DummyDataGenerator;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = FichasEncuestaEmpresaView.NAME, cached = true)
public class FichasEncuestaEmpresaView extends CssLayout implements View {

	public static final String NAME = "fichas";
	
    Table tbFichas;

    
//    @Autowired
//    ValuedService service;
//	@Autowired
//	UserService serviceUser;
    
    public FichasEncuestaEmpresaView() {
	}

    @PostConstruct
    public void init() {
        setSizeFull();
        addStyleName("schedule");
//        ValuedEventBus.register(this);
        
        addComponent(buildToolbar());
        
    	HorizontalLayout hl = new HorizontalLayout();
     	addComponent(hl);
     	hl.setSizeFull();
     	hl.addComponent(buildFichas());

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
        
        Label title = new Label("COEVOLUTION IM CONSULTING Fichas > Respondent Form");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
    
    private HorizontalLayout buildFiltro() {	
    	HorizontalLayout hl = new HorizontalLayout();
    	hl.setSpacing(true);
    	hl.setMargin(true);
	    
    	ComboBox cbStakeholder = new ComboBox();
    	cbStakeholder.setCaption("Stakeholder");
    	cbStakeholder.addItems(DummyDataGenerator.getStakeHolderUS());
 	    hl.addComponent(cbStakeholder);   
	    
 	    ComboBox cbSegmento = new ComboBox("Segment");
 	   cbSegmento.addItems(DummyDataGenerator.getSegmentosUS());
		hl.addComponents(cbSegmento);
	    
	    ComboBox cbSub = new ComboBox();
	    cbSub.setCaption("Subsegment");
	    cbSub.addItems(DummyDataGenerator.getSubsegmentosUS());
	    hl.addComponent(cbSub);
	    
	    ComboBox cbCriticidad = new ComboBox();
	    cbCriticidad.setCaption("Criticality");
	    cbCriticidad.addItems(DummyDataGenerator.getCriticidadUS());
	    hl.addComponent(cbCriticidad);
	    
	    ComboBox cbActitud = new ComboBox();
	    cbActitud.setCaption("Personal contact attitude");
	    cbActitud.addItems(DummyDataGenerator.getActitudesUS());
	    hl.addComponent(cbActitud);	  
	    
	    Button btnFiltrar = new Button("Search",FontAwesome.SEARCH);
	    btnFiltrar.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 3844920778615955739L;

			public void buttonClick(ClickEvent event) {
				Notification.show("click Search");
			}
		});
		hl.addComponent(btnFiltrar);
		
    	return hl;
    }
    
    private VerticalLayout buildFichas() {		
    	VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		
		vl.addComponent(buildFiltro());
		
		tbFichas =  drawTableFichas();
		vl.addComponent(tbFichas);
		vl.setExpandRatio(tbFichas, 1.0f);
		
		return vl;
	}

    private Table drawTableFichas() {
    	Table tableFichas = new Table();
    	tableFichas.setWidth("100%");

    	tableFichas.addContainerProperty("ID Respondent", String.class, null);
    	tableFichas.addContainerProperty("Stakeholder",  String.class, null);
    	tableFichas.addContainerProperty("Segment",  String.class, null);
    	tableFichas.addContainerProperty("Subsegment",  String.class, null);
    	tableFichas.addContainerProperty("Criticality",  String.class, null);
    	tableFichas.addContainerProperty("Personal contact attitude",  String.class, null);
    	tableFichas.addContainerProperty("Observation",  String.class, null);
    	tableFichas.addContainerProperty("Actions",  HorizontalLayout.class, null);
    	
    	HorizontalLayout hl = new HorizontalLayout();
    	hl.setSpacing(true);
    	
    	Button btnEditar = new Button(null,FontAwesome.BOOK);
    	btnEditar.setDescription("See file");
    	btnEditar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(FichaRespondenteEncuestaView.NAME);
			}
		});
    	hl.addComponent(btnEditar);
    	
    	// Add a few other rows using shorthand addItem()
    	tableFichas.addItem(new Object[]{"1", "Customer", "Priority Customer", "Councilors", "Critical", "Non Problematic","Respondent entered for...", hl}, 1);

    	// Show exactly the currently contained rows (items)
    	tableFichas.setPageLength(tableFichas.size());
    	
    	return tableFichas;
	}

    public void enter(final ViewChangeEvent event) {

    }
}
