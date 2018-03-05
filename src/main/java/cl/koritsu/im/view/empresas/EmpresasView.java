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
    	// Add a few other rows using shorthand addItem()
    	tableEmpresas.addItem(new Object[]{"76.454.344-5", "Empresa SA", "Textil", "Juan Santander", "Gerente General", "jsantander@empresa.cl","234543123","Av. Apoquindo 233", hl}, 1);

    	// Show exactly the currently contained rows (items)
    	tableEmpresas.setPageLength(tableEmpresas.size());
    	
    	return tableEmpresas;
	}
        

    public void enter(final ViewChangeEvent event) {

    }
}
