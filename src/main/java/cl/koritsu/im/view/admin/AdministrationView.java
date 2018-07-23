package cl.koritsu.im.view.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.tepi.filtertable.FilterTable;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.fieldgroup.FieldGroup.FieldGroupInvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.domain.Rol;
import cl.koritsu.im.domain.Usuario;
import cl.koritsu.im.domain.enums.EstadoUsuario;
import cl.koritsu.im.domain.enums.Permiso;
import cl.koritsu.im.utils.Utils;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = AdministrationView.NAME, cached = true)
public class AdministrationView extends CssLayout implements View {

	public static final String NAME = "administration";
	
    BeanFieldGroup<Usuario> fieldGroup = new BeanFieldGroup<Usuario>(Usuario.class);
	BeanFieldGroup<Rol> rolFieldGroup = new BeanFieldGroup<Rol>(Rol.class);
    BeanItemContainer<Usuario> userContainer = new BeanItemContainer<Usuario>(Usuario.class);
    BeanItemContainer<Rol> rolContainer = new BeanItemContainer<Rol>(Rol.class);
    FilterTable usersTable;

	FilterTable rolesTable;
    FormLayout detailLayout,rolDetailLayout;
    TwinColSelect tcsPermissions;
    
//    @Autowired
//    ValuedService service;
//	@Autowired
//	UserService serviceUser;
    
    public AdministrationView() {
	}

    @PostConstruct
    public void init() {
        setSizeFull();
        addStyleName("schedule");
//        ValuedEventBus.register(this);
		
        TabSheet tabs = new TabSheet();
        tabs.setSizeFull();
        tabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        addComponent(tabs);
        
     	HorizontalSplitPanel hsp = new HorizontalSplitPanel();
     	hsp.setSizeFull();
     	tabs.addTab(hsp,"Users");
     		
     	VerticalLayout usersListLayout = drawUsers();
     	hsp.addComponent(usersListLayout);
     	
     	VerticalLayout usersDetailLayout = drawUserDetail();	
		hsp.addComponent(usersDetailLayout);

		hsp = new HorizontalSplitPanel();
		hsp.setSizeFull();
		tabs.addTab(hsp,"Profiles");
		
		VerticalLayout roleListLayout = drawRoles();
		hsp.addComponent(roleListLayout);
		
		VerticalLayout roleDetailLayout = drawRoleDetail();	
		hsp.addComponent(roleDetailLayout);
    }
    
    private VerticalLayout drawUsers() {		
		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		vl.setSizeFull();
		
		//botones agrega y eliminar
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		vl.addComponent(hl);
		vl.setComponentAlignment(hl, Alignment.BOTTOM_LEFT);
				
		Button agregaUsuario = new Button("Add User",FontAwesome.PLUS);
		agregaUsuario.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 3844920778615955739L;

			public void buttonClick(ClickEvent event) {
				
				detailLayout.setEnabled(true);
				Usuario user = new Usuario();
				user.setNombres("New User");
				user.setApellidoPaterno("");
				user.setEmail("");
				user.setEstadoUsuario(EstadoUsuario.HABILITADO);
				user.setTasador(Boolean.FALSE);
				
		        fieldGroup.setItemDataSource(new BeanItem<Usuario>(user));				
			}
		});
		hl.addComponent(agregaUsuario);
		
		Button borrarUsuario = new Button(null,FontAwesome.TRASH_O);
		borrarUsuario.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				//recupera el elemento seleccionado
				final Usuario user = (Usuario) usersTable.getValue();
				if(user == null){
					Notification.show("Debe seleccionar un usuario para eliminarlo");
					return;
				}
				ConfirmDialog.show(UI.getCurrent(), "Confirm Action:", "You are sure to delete the selected user?",
						"Delete", "Cancel", new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							if(user.getId() != null ) {
//								service.deshabilitarUsuario(user);
								userContainer.removeItem(user);				
								setUser(userContainer.getItem( userContainer.firstItemId() ));
							}
						}
					}
				});	
				
			}
		});
		
		hl.addComponent(borrarUsuario);
		
		usersTable =  drawTableUsers();
		vl.addComponent(usersTable);
		vl.setExpandRatio(usersTable, 1.0f);
		
		return vl;
	}

    private FilterTable drawTableUsers() {
		FilterTable usersTable =  new FilterTable();
		userContainer.addNestedContainerProperty("rol.nombre");		
		usersTable.setContainerDataSource(userContainer);
		usersTable.setSizeFull();
		usersTable.setFilterBarVisible(true);
		usersTable.setVisibleColumns("rut","apellidoPaterno","email","rol.nombre","estadoUsuario");
		usersTable.setColumnHeaders("RUT","Name","Email","Profile","Status");
		usersTable.setSelectable(true);
		
		usersTable.addItemClickListener(new ItemClickListener() {
			
			public void itemClick(ItemClickEvent event) {
				setUser((BeanItem<Usuario>)event.getItem());
			}
		});
		
		return usersTable;
	}
    
    private VerticalLayout drawUserDetail() {		
		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		vl.setSizeFull();
		
        Button btnSave = new Button("Save",new Button.ClickListener() {

        	public void buttonClick(ClickEvent event) {
        		try {
        			fieldGroup.commit();
        			Usuario user = fieldGroup.getItemDataSource().getBean();
        			boolean isNew = user.getId() == null;
//        			serviceUser.saveUser(user);
        			
        			if(isNew){
	        			BeanItem<Usuario> userItem = userContainer.addBean(user);
	        			setUser(userItem);
        			}
        			
        			fieldGroup.getField("contrasena").setValue(null);
        			fieldGroup.getField("contrasena2").setValue(null);
        			Notification.show("Usuario guardado correctamente",Type.TRAY_NOTIFICATION);
        		} catch (CommitException e) {
        			Utils.validateEditor("usuario detail",e);
        		}

        	}
        }){{
        	setIcon(FontAwesome.SAVE);
        }};
        
        vl.addComponent(btnSave);
        vl.setComponentAlignment(btnSave, Alignment.TOP_LEFT);
		
		detailLayout = new FormLayout();
		detailLayout.setEnabled(false);
		detailLayout.setMargin(true);
		detailLayout.setSpacing(true);
		
		Panel p = new Panel(detailLayout);
		p.setSizeFull();
		vl.addComponent(p);
		vl.setExpandRatio(p, 1.0f);
        
        // Loop through the properties, build fields for them and add the fields
        // to this UI
        for (Object propertyId : new String[]{"rut","nombres","rol","email","contrasena","contrasena2","telefonoFijo"}) {
        	if(propertyId.equals("male"))
        		;
        	else if(propertyId.equals("nombres")){
	    		TextField pf2 = new TextField("Name");
	    		pf2.setNullRepresentation("");
	    		pf2.setWidth("100%");
	    		detailLayout.addComponent(pf2);
	    		fieldGroup.bind(pf2, propertyId);
	    		pf2.setValue(null);
        	}else if(propertyId.equals("rut")){
        		TextField pf2 = new TextField("RUT");
	    		pf2.setNullRepresentation("");
	    		pf2.setWidth("100%");
	    		detailLayout.addComponent(pf2);
	    		fieldGroup.bind(pf2, propertyId);
	    		pf2.setValue(null);
        	}else if(propertyId.equals("rol")){
        		ComboBox cb = new ComboBox("Profile",rolContainer);
        		cb.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        		cb.setItemCaptionPropertyId("nombre");
        		cb.setWidth("100%");
        		fieldGroup.bind(cb, propertyId);
				cb.setContainerDataSource(rolContainer);				
        		detailLayout.addComponent(cb);
        	}else if(propertyId.equals("contrasena")){
        		PasswordField pf = new PasswordField("Password");
        		pf.setNullRepresentation("");
        		detailLayout.addComponent(pf);
        		fieldGroup.bind(pf, propertyId);
        		pf.setWidth("100%");
        		pf.setValue(null);
        	}else if(propertyId.equals("contrasena2")){
        		PasswordField pf2 = new PasswordField("Confirm Password");
        		pf2.setNullRepresentation("");
        		pf2.setWidth("100%");
        		detailLayout.addComponent(pf2);
        		fieldGroup.bind(pf2, propertyId);
        		pf2.setValue(null);
        	}else if(propertyId.equals("telefonoFijo")){
        		TextField pf2 = new TextField("Phone");
	    		pf2.setNullRepresentation("");
	    		pf2.setWidth("100%");
	    		detailLayout.addComponent(pf2);
	    		fieldGroup.bind(pf2, propertyId);
	    		pf2.setValue(null);
        	}else{
        		Field<?> field = fieldGroup.buildAndBind(propertyId);
        		field.setWidth("100%");
        		detailLayout.addComponent(field);
        	}
        }
        		
		fieldGroup.addCommitHandler(new CommitHandler() {
			
			public void preCommit(CommitEvent commitEvent) throws CommitException {
				
			}
			
			public void postCommit(CommitEvent commitEvent) throws CommitException {
				
				Long id = fieldGroup.getItemDataSource().getBean().getId();
				//si es un nuevo usuario, valida que no exista un usuario con el mismo email
				if( id == null ){
					Field<?> email = fieldGroup.getField("email");					
//					Usuario user = service.findUsuarioByUsername((String)email.getValue());
//					if(user != null ){
//						Map<Field<?>,InvalidValueException> map = new HashMap<Field<?>,InvalidValueException>();
//						map.put(email, new InvalidValueException("Ya existe un usuario con el mismo email."));
//						throw new FieldGroupInvalidValueException(map);
//					}
				}
				
				//antes de comitear revisa que los passwords sean iguales si alguno es distinto de null
				Field<?> pf = fieldGroup.getField("contrasena");
				Field<?> pf2 = fieldGroup.getField("contrasena2");
				
				//	creacion debe validar que venga seteado el password y además coincida, en edicion solo deben coincidir
				if(  id == null && pf.getValue() == null ){
					Map<Field<?>,InvalidValueException> map = new HashMap<Field<?>,InvalidValueException>();
					map.put(pf, new InvalidValueException("El password es requerido para crear el usuario."));
					throw new FieldGroupInvalidValueException(map);
				}
				if( pf.getValue() != null || pf2.getValue() != null ){
					if(!pf.getValue().equals(pf2.getValue())){
						Map<Field<?>,InvalidValueException> map = new HashMap<Field<?>,InvalidValueException>();
						map.put(pf, new InvalidValueException("Los passwords deben coincidir"));
						throw new FieldGroupInvalidValueException(map);
					}
				}
			}
		});
				
		return vl;
	}
    
	private void setUser(BeanItem<Usuario> userItem){		
		//obtiene el vertical Layout
		if(userItem == null){
			detailLayout.setEnabled(false);
			return;
		}
		
        usersTable.select(userItem.getBean());	
		detailLayout.setEnabled(true);		
		userItem.getBean().setContrasena(null);
		userItem.getBean().setContrasena2(null);
		// We need an item data source before we create the fields to be able to
        // find the properties, otherwise we have to specify them by hand
        fieldGroup.setItemDataSource(userItem);
		
	}

    public void enter(final ViewChangeEvent event) {
//    	List<Rol> roles = service.getRoles();
//    	rolContainer.removeAllItems();
//  		rolContainer.addAll(roles);
  		
//  		List<Usuario> usuarios = service.getUsuarios();
//  		userContainer.removeAllItems();
//  		userContainer.addAll(usuarios);
    }
    
    private VerticalLayout drawRoles() {
		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		vl.setSizeFull();
		
		//botones agrega y eliminar
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		vl.addComponent(hl);
		vl.setComponentAlignment(hl, Alignment.BOTTOM_LEFT);
				
		Button btnAddRole = new Button("Add Profile",FontAwesome.PLUS);
		btnAddRole.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 3844920778615955739L;

			public void buttonClick(ClickEvent event) {
				rolDetailLayout.setEnabled(true);
				Rol rol = new Rol();
				rol.setNombre("New Profile");				
		        rolFieldGroup.setItemDataSource(new BeanItem<Rol>(rol));				
			}
		});
		hl.addComponent(btnAddRole);
		Button btnDeleteRole = new Button(null,FontAwesome.TRASH_O);
		btnDeleteRole.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				//recupera el elemento seleccionado
				final Rol rol = (Rol) rolesTable.getValue();
				if(rol == null){
					Notification.show("Debe seleccionar un perfil para eliminarlo");
					return;
				}
				ConfirmDialog.show(UI.getCurrent(), "Confirmar Acción:", "¿Está seguro de eliminar el perfil seleccionado?",
						"Eliminar", "Cancelar", new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							if(rol.getId() != null ) {
//								if(!serviceUser.findUsersByRole(rol))
//									Notification.show("El role esta siendo ocupado por usuarios habilitados.");
//								else {
//									service.deleteRol(rol.getId());
//									rolContainer.removeItem(rol);				
//									setRole( rolContainer.getItem(rolContainer.firstItemId() ));
//								}
									
							}
						}
					}
				});	
				
			}
		});
		hl.addComponent(btnDeleteRole);
		
		rolesTable =  drawTableRoles();
		vl.addComponent(rolesTable);
		vl.setExpandRatio(rolesTable, 1.0f);
		
		return vl;
	}


	private FilterTable drawTableRoles() {
		FilterTable rolesTable =  new FilterTable();
		rolesTable.setContainerDataSource(rolContainer);
		rolesTable.setSizeFull();
		rolesTable.setFilterBarVisible(true);
		rolesTable.setVisibleColumns("nombre","descripcion");
		rolesTable.setColumnHeaders("Nombre","Descripción");
		rolesTable.setSelectable(true);
		
		rolesTable.addItemClickListener(new ItemClickListener() {
			
			public void itemClick(ItemClickEvent event) {
				setRole((BeanItem<Rol>)event.getItem());
			}
		});
		
		return rolesTable;
	}
	
	private void setRole(BeanItem<Rol> roleItem){
		
		//obtiene el vertical Layout
		if(roleItem == null){
			rolDetailLayout.setEnabled(false);
			return;
		}
		
        rolesTable.select(roleItem.getBean());		
        rolDetailLayout.setEnabled(true);
		
		// We need an item data source before we create the fields to be able to
        // find the properties, otherwise we have to specify them by hand
        rolFieldGroup.setItemDataSource(roleItem);
		
	}
	
	private VerticalLayout drawRoleDetail() {
		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		vl.setSizeFull();
		
        //agrega un boton que hace el commit
        Button btnSave = new Button("Guardar",new Button.ClickListener() {

        	public void buttonClick(ClickEvent event) {
        		try {
        			rolFieldGroup.commit();
        			Rol role = rolFieldGroup.getItemDataSource().getBean();
        			boolean isNew = role.getId() == null;
//        			service.saveRol(role);
        			
        			if(isNew){
	        			BeanItem<Rol> userItem = rolContainer.addBean(role);
	        			setRole(userItem);
        			}
        			
        			Notification.show("Role guardado correctamente",Type.TRAY_NOTIFICATION);
        		} catch (CommitException e) {        			
        			Utils.validateEditor("rol detail",e);       			
        		}

        	}
        }){{
        	setIcon(FontAwesome.SAVE);
        }};
        
        vl.addComponent(btnSave);
        vl.setComponentAlignment(btnSave, Alignment.TOP_LEFT);
		
		rolDetailLayout = new FormLayout();
		rolDetailLayout.setEnabled(false);
		rolDetailLayout.setMargin(true);
		rolDetailLayout.setSpacing(true);
		
		Panel p = new Panel(rolDetailLayout);
		p.setSizeFull();
		vl.addComponent(p);
		vl.setExpandRatio(p, 1.0f);
        
        // Loop through the properties, build fields for them and add the fields
        // to this UI
        for (Object propertyId : new String[]{"nombre","descripcion"}) {
        	if(propertyId.equals("id")){
        		;
        	} else if(propertyId.equals("descripcion")){
        		TextArea txArea = new TextArea("Description");
        		txArea.setWidth("100%");
        		txArea.setNullRepresentation("");
        		rolFieldGroup.bind(txArea, propertyId);
        		rolDetailLayout.addComponent(txArea);
        	}else{
        		Field<?> field = rolFieldGroup.buildAndBind(propertyId);
        		field.setWidth("100%");
        		if(field instanceof AbstractTextField)
        			((AbstractTextField) field).setNullRepresentation("");
        		rolDetailLayout.addComponent(field);
        	}
        }
        
        tcsPermissions = new TwinColSelect("Assign Permissions");
        for(Permiso per : Permiso.values())
        	tcsPermissions.addItem(per);
        tcsPermissions.setNullSelectionAllowed(true);
        tcsPermissions.setImmediate(true);
		rolFieldGroup.bind(tcsPermissions, "permisos");
		
		rolDetailLayout.addComponent(tcsPermissions);
		
		return vl;
	}
}
