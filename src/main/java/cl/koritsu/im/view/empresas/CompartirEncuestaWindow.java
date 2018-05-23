package cl.koritsu.im.view.empresas;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import cl.koritsu.im.domain.Rol;
import cl.koritsu.im.domain.Usuario;

public class CompartirEncuestaWindow extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8588667188363307877L;
	//raiz del sitio
	VerticalLayout root = new VerticalLayout();
	BeanItemContainer<Usuario> ds = new BeanItemContainer<Usuario>(Usuario.class);

	public CompartirEncuestaWindow() {
		setCaption("Share to Users");
		setModal(true);
		center();
		
		ds.addBean(new Usuario() {
			{
				setRut("1-9");
				setNombres("Peter Perre");
				setRol(new Rol() {
					{
						setNombre("Presidente");
					}
				});
				setEmail("a@a.com");
			}
		});
		
		setWidth("50%");
		
		ds.addNestedContainerBean("rol");
		
		root.addComponent(buildFilters());
		Component tabla = buildTable();
		root.addComponent(tabla);
		root.setExpandRatio(tabla, 1);
		root.addComponent(buildFooter());
		
		setContent(root);
		
	}

	private Component buildFooter() {
		HorizontalLayout footer = new HorizontalLayout();
        
        Button btnGuardar = new Button("Save");
        btnGuardar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Save");
				close();
			}
		});
        footer.addComponent(btnGuardar);
        
        Button btnCancelar = new Button("Cancel");
        btnCancelar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
        footer.addComponent(btnCancelar); 
        return footer;
	}

	private Component buildTable() {
		Table tableUsuarios = new Table();
		tableUsuarios.setSizeFull();
		tableUsuarios.setContainerDataSource(ds);
		tableUsuarios.addGeneratedColumn("check", new Table.ColumnGenerator() {
			
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				return new CheckBox();
			}
		});
		tableUsuarios.setVisibleColumns("check","rut","nombres","rol.nombre","email");
		tableUsuarios.setColumnHeaders("","Rut","Nombre","Cargo","Email");
		
		return tableUsuarios;
	}

	private Component buildFilters() {
		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");
		hl.setSpacing(true);
		hl.setMargin(true);
		
		//rut
		TextField rutField = new TextField("Rut");
		hl.addComponent(rutField);
		
		//nombre
		TextField nombreField = new TextField("Nombre");
		hl.addComponent(nombreField);
		
		//Cargo
		TextField cargoField = new TextField("Cargo");
		hl.addComponent(cargoField);
		
		//boton
		Button btnBuscar = new Button("Buscar",FontAwesome.SEARCH);
		hl.addComponent(btnBuscar);
		hl.setComponentAlignment(btnBuscar, Alignment.BOTTOM_LEFT);
		btnBuscar.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("Buscar");
			}
		});
		
		return hl;
	}

}
