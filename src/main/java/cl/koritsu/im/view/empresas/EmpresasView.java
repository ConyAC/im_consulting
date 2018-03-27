package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;
import cl.koritsu.im.utils.Constants;

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

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = EmpresasView.NAME, cached = true)
public class EmpresasView extends CssLayout implements View {

	public static final String NAME = "empresas";
	
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
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Empresas");
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
	    rut.setCaption("RUT");
	    hl.addComponent(rut);
	    
	    ComboBox cbRazon = new ComboBox();
	    cbRazon.setCaption("Nombre o Razón Social");
	    cbRazon.addItems("Empresa SA", "Empresa1", "Empresa2", "Empresa3");
	    hl.addComponent(cbRazon);
	    
	    ComboBox cbIndustria = new ComboBox();
	    cbIndustria.setCaption("Industria");
	    cbIndustria.addItems("Automovilística", "Informática", "Farmaceutica", "Textil");
	    hl.addComponent(cbIndustria);
	    
	    ComboBox cbRegion = new ComboBox();
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
	    hl.addComponent(cbComuna);
	    
	    Button btnFiltrar = new Button("Buscar",FontAwesome.SEARCH);
	    btnFiltrar.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 3844920778615955739L;

			public void buttonClick(ClickEvent event) {
				Notification.show("click filtro");
			}
		});
		hl.addComponent(btnFiltrar);
		
		Button btnAregar = new Button("Agregar",FontAwesome.CHECK);
		btnAregar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click filtro");
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
    	tableEmpresas.setWidth("100%");

    	tableEmpresas.addContainerProperty("RUT", String.class, null);
    	tableEmpresas.addContainerProperty("Razón Social",  String.class, null);
    	tableEmpresas.addContainerProperty("Industria",  String.class, null);
    	tableEmpresas.addContainerProperty("C. Principal",  String.class, null);
    	tableEmpresas.addContainerProperty("Cargo",  String.class, null);
    	tableEmpresas.addContainerProperty("Correo Electrónico",  String.class, null);
    	tableEmpresas.addContainerProperty("Teléfono",  String.class, null);
    	tableEmpresas.addContainerProperty("Dirección",  String.class, null);
    	tableEmpresas.addContainerProperty("Acciones",  HorizontalLayout.class, null);
    	
    	HorizontalLayout hl = new HorizontalLayout();
    	hl.setSpacing(true);
    	Button btnEditar = new Button(null,FontAwesome.EDIT);
    	btnEditar.setDescription("Editar");
    	btnEditar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click editar");
			}
		});
    	hl.addComponent(btnEditar);
    	Button btnEstudios = new Button(null,FontAwesome.FILE);
    	btnEstudios.setDescription("Estudios");
    	btnEstudios.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(EncuestasEmpresaView.NAME);
			}
		});
    	hl.addComponent(btnEstudios);
    	Button btnUsuarios = new Button(null,FontAwesome.USER);
    	btnUsuarios.setDescription("Usuarios");
    	btnUsuarios.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click usuarios");
			}
		});
    	hl.addComponent(btnUsuarios);
    	Button btnDesactivar = new Button(null,FontAwesome.REMOVE);
    	btnDesactivar.setDescription("Desactivar");
    	btnDesactivar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click desactivar");
			}
		});
    	hl.addComponent(btnDesactivar);
    	
    	HorizontalLayout hl2 = new HorizontalLayout();
    	hl2.setSpacing(true);    	
    	Button btnEditar2 = new Button(null,FontAwesome.EDIT);
    	btnEditar2.setDescription("Editar");
    	btnEditar2.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click editar");
			}
		});
    	hl2.addComponent(btnEditar2);
    	Button btnEstudios2 = new Button(null,FontAwesome.FILE);
    	btnEstudios2.setDescription("Estudios");
    	btnEstudios2.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(EncuestasEmpresaView.NAME);
			}
		});
    	hl2.addComponent(btnEstudios2);
    	Button btnUsuarios2 = new Button(null,FontAwesome.USER);
    	btnUsuarios2.setDescription("Usuarios");
    	btnUsuarios2.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click usuarios");
			}
		});
    	hl2.addComponent(btnUsuarios2);
    	Button btnDesactivar2 = new Button(null,FontAwesome.REMOVE);
    	btnDesactivar2.setDescription("Desactivar");
    	btnDesactivar2.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click desactivar");
			}
		});
    	hl2.addComponent(btnDesactivar2);
    	
    	HorizontalLayout hl3 = new HorizontalLayout();
    	hl3.setSpacing(true);    	
    	Button btnEditar3 = new Button(null,FontAwesome.EDIT);
    	btnEditar3.setDescription("Editar");
    	btnEditar3.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click editar");
			}
		});
    	hl3.addComponent(btnEditar3);
    	Button btnEstudios3 = new Button(null,FontAwesome.FILE);
    	btnEstudios3.setDescription("Estudios");
    	btnEstudios3.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(EncuestasEmpresaView.NAME);
			}
		});
    	hl3.addComponent(btnEstudios3);
    	Button btnUsuarios3 = new Button(null,FontAwesome.USER);
    	btnUsuarios3.setDescription("Usuarios");
    	btnUsuarios3.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click usuarios");
			}
		});
    	hl3.addComponent(btnUsuarios3);
    	Button btnDesactivar3 = new Button(null,FontAwesome.REMOVE);
    	btnDesactivar3.setDescription("Desactivar");
    	btnDesactivar3.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click desactivar");
			}
		});
    	hl3.addComponent(btnDesactivar3);
    	// Add a few other rows using shorthand addItem()
    	tableEmpresas.addItem(new Object[]{"76.454.344-5", "Empresa SA", "Textil", "Juan Santander", "Gerente General", "jsantander@empresa.cl","234543123","Av. Apoquindo 233", hl}, 1);
    	tableEmpresas.addItem(new Object[]{"80.000.344-0", "Company SA", "Automotriz", "Paolo Medina", "Gerente Comercial", "pmedina@empresa.cl","34656544","Av. Alameda 783", hl2}, 2);
    	tableEmpresas.addItem(new Object[]{"76.574.874-3", "Jr SA", "Farmaceutica", "Sandra Fox", "Gerente General", "sfox@empresa.cl","234543123","Av. Las Condes 230", hl3}, 3);

    	// Show exactly the currently contained rows (items)
    	tableEmpresas.setPageLength(tableEmpresas.size());
    	
    	return tableEmpresas;
	}
        

    public void enter(final ViewChangeEvent event) {

    }
}
