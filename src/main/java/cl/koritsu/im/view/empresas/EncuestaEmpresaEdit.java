package cl.koritsu.im.view.empresas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.vaadin.dialogs.ConfirmDialog;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = EncuestaEmpresaEdit.NAME, cached = true)
public class EncuestaEmpresaEdit extends CssLayout implements View {

	public static final String NAME = "editarencuestas";
	
    Table tbFichas;
    VerticalLayout vlayout;

    
//    @Autowired
//    ValuedService service;
//	@Autowired
//	UserService serviceUser;
    
    public EncuestaEmpresaEdit() {
	}

    @PostConstruct
    public void init() {
    	
    	vlayout = new VerticalLayout();
        setSizeFull();
        addStyleName("schedule");
//        ValuedEventBus.register(this);
        
        addComponent(buildToolbar());
     	
     	GridLayout glRoot = new GridLayout(3,10);
     	addComponent(glRoot);
     	glRoot.setSpacing(true);
		glRoot.setMargin(true);
		glRoot.setWidth("100%");
		
		glRoot.addComponents(new Label("Nombre"));
		glRoot.addComponent(new HorizontalLayout(){
			{
				setSpacing(true);
				TextField tf = new TextField();
				addComponents(tf);
			}
		});
		glRoot.addComponent(new Label(""));
		
		glRoot.addComponents(new Label("Modelos"));
		glRoot.addComponent(new HorizontalLayout(){
			{
				setSpacing(true);
				OptionGroup og = new OptionGroup();
				og.addItem("Reputación");
				og.addItem("Riesgos");
				og.addItem("Afinidad");
				og.addItem("Preguntas");
				addComponent(og);
				
				og.addValueChangeListener(new ValueChangeListener() {
					
					@Override
					public void valueChange(ValueChangeEvent event) {
						String opcion= (String)event.getProperty().getValue();
						System.out.println("opcion: "+opcion);
					}
				});
			}
		});
		glRoot.addComponent(new Label(""));
		
		glRoot.addComponents(new Label("Periodo desde"));
		glRoot.addComponent(new HorizontalLayout(){
			{
				setSpacing(true);
				DateField tf = new DateField();
				tf.setDateFormat("dd/MM/yyyy");
				tf.setValue(new Date());
				addComponents(tf);
			}
		});
		Label lb1 = new Label();
		glRoot.addComponent(lb1);
			
		glRoot.addComponents(new Label("Periodo hasta"));
		glRoot.addComponent(new HorizontalLayout(){
			{
				setSpacing(true);
				DateField tf = new DateField();
				tf.setDateFormat("dd/MM/yyyy");				
				tf.setValue(new Date());
				addComponents(tf);
			}
		});
		Label lb2 = new Label();
		glRoot.addComponent(lb2);
		
		glRoot.addComponents(new Label("Estado"));
		glRoot.addComponent(new HorizontalLayout(){
			{
				setSpacing(true);
				ComboBox cbEstado = new ComboBox();
				cbEstado.addItems("Activa", "Inactiva");
			    addComponent(cbEstado);
			}
		});
		glRoot.addComponent(new Label(""));
		
		glRoot.addComponents(new Label("Área Patrocinadora"));
		glRoot.addComponent(new HorizontalLayout(){
			{
				setSpacing(true);
				ComboBox cbPatrocinador = new ComboBox();
			    cbPatrocinador.addItems("Gerencia Marketing", "Gerencia Estudios o Investigación de Mercados", "Gerencia de Relaciones Corporativas", "Gerencia de Riesgos");
			    addComponent(cbPatrocinador);
			}
		});
		glRoot.addComponent(new Label(""));
		
        HorizontalLayout footer = new HorizontalLayout();
        glRoot.addComponent(footer);
        
        Button btnGuardar = new Button("Guardar");
        btnGuardar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Guardar");
			}
		});
        footer.addComponent(btnGuardar);
        
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(EncuestasEmpresaView.NAME);
			}
		});
        footer.addComponent(btnCancelar);       

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
    
    private VerticalLayout buildCarga() {		
    	VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		
		vl.addComponent(uploadFile());
		
		Button btnAdm = new Button("Administrar Riesgos");
		btnAdm.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click administrar");
			}
		});
		vl.addComponent(btnAdm);
		
		Table tbCarga = new Table();
		tbCarga.addContainerProperty("Riesgo", String.class, null);
		tbCarga.addContainerProperty("Factor",  String.class, null);
		tbCarga.addContainerProperty("Acciones",  HorizontalLayout.class, null);    	
    	
    	HorizontalLayout hl2 = new HorizontalLayout();
    	hl2.setSpacing(true);
    	
    	Button btnEliminar = new Button(null,FontAwesome.TRASH);
    	btnEliminar.setDescription("Eliminar");
    	btnEliminar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				ConfirmDialog.show(UI.getCurrent(), "Confirmar Acción:", "¿Está seguro de eliminar el riesgo seleccionado?",
						"Eliminar", "Cancelar", new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							Notification.show("Pregunta sobre riesgo eliminada");
						}
					}
				});	
			}
		});
    	hl2.addComponent(btnEliminar);
    	
    	tbCarga.addItem(new Object[]{"1", "Cliente", "Cliente prioritario", "C1", "Critico", "xx", hl2}, 1);
    	tbCarga.setPageLength(tbCarga.size());
    	vl.addComponent(tbCarga);
		
		return vl;
    }

    private Upload uploadFile(){
    	Upload upload = new Upload("Seleccionar Archivo", null);
		upload.setButtonCaption("Iniciar Carga");
		
		class fileUploader implements Receiver, SucceededListener {
		    /**
			 * 
			 */
			private static final long serialVersionUID = -2957126296893401853L;
			public File file;
		    
		    public OutputStream receiveUpload(String filename, String mimeType) {
		        // Create upload stream
		        FileOutputStream fos = null; // Output stream to write to
		        try {
		            // Open the file for writing.
		            file = new File(""+filename);
		            fos = new FileOutputStream(file);
		            
		        } catch (final java.io.FileNotFoundException e) {
		        	new Notification("No es posible acceder al archivo", e.getMessage());
		            return null;
		        }catch (Exception e) {
		        	new Notification("No es posible acceder al archivo", e.getMessage());
		            return null;
		        }
		        return fos; // Return the output stream to write to
		    }

			@Override
			public void uploadSucceeded(SucceededEvent event) {
		    
				Notification.show("click Iniciar Carga...");					
			}
		};
		
		final fileUploader uploader = new fileUploader(); 
		upload.setReceiver(uploader);
		upload.addListener(uploader);
		
		return upload;
    }
    
    public void enter(final ViewChangeEvent event) {

    }
}
