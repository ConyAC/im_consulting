package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.utils.Constants;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = EmpresaEdit.NAME, cached = true)
public class EmpresaEdit extends CssLayout implements View {

	public static final String NAME = "editarempresa";

	 @PostConstruct
	 public void init() {
		 
		 setSizeFull();
		 
		 addStyleName("companies");
		 
		 addComponent(buildToolbar());
		 
		 addComponent(buildDatosEmpresa());
		 
		 addComponent(buildContactoEmpresa());
		 
		 Component component = buildAceptaCancelar();
		 addComponent(component);
		 
	 }
	 
	 private Component buildAceptaCancelar() {
		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");
		hl.setMargin(true);
		
		final Button btn1 = new Button("Aceptar");
		btn1.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(EmpresasView.NAME);
			}
		});
		
		final Button btn2 = new Button("Cancelar");
		btn2.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(EmpresasView.NAME);
			}
		});
		
		HorizontalLayout hl2 = new HorizontalLayout() {
			{
				setSpacing(true);
				addComponent(btn1);
				addComponent(btn2);
			}
		};
		hl.addComponent(hl2);
		hl.setComponentAlignment(hl2, Alignment.MIDDLE_RIGHT);
		
		return hl;
	}

	private Component buildToolbar() {
	        HorizontalLayout header = new HorizontalLayout();
	        header.addStyleName("viewheader");
	        header.setSpacing(true);
	        Responsive.makeResponsive(header);

	        Image logo = new Image();
	        logo.setSource(new ThemeResource(Constants.LOGO_URL));
	        logo.setHeight("76px");
	        logo.setWidth("70px");
	        header.addComponent(logo);
	        
	        Label title = new Label("COEVOLUTION IM CONSULTING > Company");
	        title.setSizeUndefined();
	        title.addStyleName(ValoTheme.LABEL_H1);
	        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
	        header.addComponent(title);
	        
	        return header;
	    }
	 
	 
    private Component buildContactoEmpresa() {
    	final GridLayout glLayout = new GridLayout(4,2);
		glLayout.setWidth("100%");
		glLayout.setMargin(true);
		glLayout.setSpacing(true);
    	
    	//Nombre 1
    	TextField nombre1Field = new TextField("Nombre Contacto Principal");
    	nombre1Field.setWidth("100%");
    	glLayout.addComponent(nombre1Field,0,0,0,0);
    	
    	//Cargo
    	ComboBox cargo1Field = new ComboBox("Cargo");
    	cargo1Field.setWidth("100%");
    	glLayout.addComponent(cargo1Field,1,0,1,0);
    	
    	//correo   	
    	TextField correoField = new TextField("Correo electrónico");
    	correoField.setWidth("100%");
    	glLayout.addComponent(correoField,2,0,2,0);
    	
    	//telefono celular
    	TextField telefonoField = new TextField("Telefono / Celular");
    	telefonoField.setWidth("100%");
    	glLayout.addComponent(telefonoField,3,0,3,0);
    	
    	
    	//Nombre 1
    	TextField nombre2Field = new TextField("Nombre Contacto Principal");
    	nombre2Field.setWidth("100%");
    	glLayout.addComponent(nombre2Field,0,1,0,1);
    	
    	//Cargo
    	ComboBox cargo2Field = new ComboBox("Cargo");
    	cargo2Field.setWidth("100%");
    	glLayout.addComponent(cargo2Field,1,1,1,1);
    	
    	//correo   	
    	TextField correo2Field = new TextField("Correo electrónico");
    	correo2Field.setWidth("100%");
    	glLayout.addComponent(correo2Field,2,1,2,1);
    	
    	//telefono celular
    	TextField telefono2Field = new TextField("Telefono / Celular");
    	telefono2Field.setWidth("100%");
    	glLayout.addComponent(telefono2Field,3,1,3,1);
    	
		return new Panel() {
			{
				setCaption("Contacto Empresa");
				setWidth("100%");
				setContent(glLayout);
			}
		};
	}


	private Component buildDatosEmpresa() {
		final GridLayout glLayout = new GridLayout(4,3);
		
		glLayout.setWidth("100%");
		glLayout.setMargin(true);
		glLayout.setSpacing(true);
    	
    	//logo
    	TextField logoField = new TextField("Logo");
    	logoField.setWidth("100%");
    	glLayout.addComponent(logoField,0,0,1,0);
    	
    	//boton examinar
    	Button btnExaminar = new Button("Examinar");
    	glLayout.addComponent(btnExaminar,2,0,2,0);
    	glLayout.setComponentAlignment(btnExaminar, Alignment.BOTTOM_LEFT);
    	
    	//RUT
    	TextField rutField = new TextField("RUT");
    	rutField.setWidth("100%");
    	glLayout.addComponent(rutField,0,1,0,1);
    	
    	//razon social
    	TextField razonSocialField = new TextField("Razón Social");
    	razonSocialField.setWidth("100%");
    	glLayout.addComponent(razonSocialField,1,1,1,1);
    	
    	//industria
    	ComboBox industriaField = new ComboBox("Industria");
    	industriaField.setWidth("100%");
    	glLayout.addComponent(industriaField,2,1,2,1);
    	
    	//Region
    	ComboBox regionField = new ComboBox("Región");
    	regionField.setWidth("100%");
    	glLayout.addComponent(regionField,0,2,0,2);
    	
    	//Comuna
    	ComboBox comunaField = new ComboBox("Comuna");
    	comunaField.setWidth("100%");
    	glLayout.addComponent(comunaField,1,2,1,2);
    	
    	//dirección
    	TextField direccionField = new TextField("Dirección");
    	direccionField.setWidth("100%");
    	glLayout.addComponent(direccionField,2,2,2,2);
    	
    	//logo subido
    	Label label = new Label("<span style='color:red;font-size:8em' >" + FontAwesome.HOME.getHtml() + "</span>",
			    ContentMode.HTML);
    	glLayout.addComponent(label,3,0,3,2);
    	glLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    	
		return new Panel() {
			{
				setCaption("Datos Empresa");
				setWidth("100%");
				setContent(glLayout);
			}
		};
	}


	public void enter(final ViewChangeEvent event) {

    }
}
