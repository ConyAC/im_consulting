package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

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
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.data.dummy.DummyDataGenerator;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = EmpresasView.NAME, cached = true)
public class EmpresasView extends CssLayout implements View {

	public static final String NAME = "companies";
	
    Table tbEmpresas;

    
//    @Autowired
//    ValuedService service;
//	@Autowired
//	UserService serviceUser;
    
    public EmpresasView() {
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
     	hl.addComponent(buildEmpresas());

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
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Companies");
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
	    
	    TextField rut = new TextField();
	    rut.setCaption("Social Security Number");
	    hl.addComponent(rut);
	    
	    ComboBox cbRazon = new ComboBox();
	    cbRazon.setCaption("Name");
	    cbRazon.addItems("Company SA", "Company1", "Company2", "Company3");
	    hl.addComponent(cbRazon);
	    
	    ComboBox cbIndustria = new ComboBox("Industry");
		for(String sh : DummyDataGenerator.getIndustrias())
			cbIndustria.addItem(sh);
		hl.addComponent(cbIndustria);	  
	    
	    /*
	     * Se comenta por motivo de presentación
	     */
	    /*ComboBox cbRegion = new ComboBox();
	    cbRegion.setCaption("Región");
	    for(String b : Constants.REGIONES) {
	    	cbRegion.addItem(b);
		}
	    hl.addComponent(cbRegion);
	    
	    ComboBox cbComuna = new ComboBox();
	    cbComuna.setCaption("Comuna");
	    for(String b : Constants.COMUNAS) {
	    	cbComuna.addItem(b);
		}
	    hl.addComponent(cbComuna);*/
	    
	    Button btnFiltrar = new Button("Search",FontAwesome.SEARCH);
	    btnFiltrar.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 3844920778615955739L;

			public void buttonClick(ClickEvent event) {
				Notification.show("click Search");
			}
		});
		hl.addComponent(btnFiltrar);
		
		Button btnAregar = new Button("Add",FontAwesome.CHECK);
		btnAregar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				;Notification.show("click Add");
			}
		});
		
		hl.addComponent(btnAregar);
		
    	return hl;
    }
    
    private VerticalLayout buildEmpresas() {		
    	VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		
		vl.addComponent(buildFiltro());
		
		tbEmpresas =  drawTableEmpresas();
		vl.addComponent(tbEmpresas);
		vl.setExpandRatio(tbEmpresas, 1.0f);
		
		return vl;
	}

    private Table drawTableEmpresas() {
    	Table tableEmpresas = new Table();

    	tableEmpresas.addContainerProperty("Social Security Number", String.class, null);
    	tableEmpresas.addContainerProperty("Name",  String.class, null);
    	tableEmpresas.addContainerProperty("Industry",  String.class, null);
    	tableEmpresas.addContainerProperty("Principal",  String.class, null);
    	tableEmpresas.addContainerProperty("Position",  String.class, null);
    	tableEmpresas.addContainerProperty("Email",  String.class, null);
    	tableEmpresas.addContainerProperty("Phone",  String.class, null);
    	tableEmpresas.addContainerProperty("Address",  String.class, null);
    	tableEmpresas.addContainerProperty("Actions",  HorizontalLayout.class, null);
    	
    	HorizontalLayout hl = new HorizontalLayout();
    	hl.setSpacing(true);
    	Button btnEditar = new Button(null,FontAwesome.EDIT);
    	btnEditar.setDescription("Edit");
    	btnEditar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Edit");
			}
		});
    	hl.addComponent(btnEditar);
    	Button btnEstudios = new Button(null,FontAwesome.FILE);
    	btnEstudios.setDescription("Studies");
    	btnEstudios.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(EncuestasEmpresaView.NAME);
			}
		});
    	hl.addComponent(btnEstudios);
    	Button btnUsuarios = new Button(null,FontAwesome.USER);
    	btnUsuarios.setDescription("Users");
    	btnUsuarios.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Users");
			}
		});
    	hl.addComponent(btnUsuarios);
    	Button btnDesactivar = new Button(null,FontAwesome.REMOVE);
    	btnDesactivar.setDescription("Deactivate");
    	btnDesactivar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Deactivate");
			}
		});
    	hl.addComponent(btnDesactivar);
    	
    	// Add a few other rows using shorthand addItem()
    	tableEmpresas.addItem(new Object[]{"76.454.344-5", "Shell Global", "Reseach Area", "Ben van Beurden", "Chief Executive Officer (CEO)", ""," +31 70 377 9111","Carel van Bylandtlaan 16, 2596 HR The Hague, The Netherlands", hl}, 1);

    	// Show exactly the currently contained rows (items)
    	tableEmpresas.setPageLength(tableEmpresas.size());
    	
    	tableEmpresas.setWidth("100%");
    	return tableEmpresas;
	}
        

    public void enter(final ViewChangeEvent event) {

    }
}
