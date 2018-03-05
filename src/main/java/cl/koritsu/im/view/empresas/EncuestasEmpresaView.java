package cl.koritsu.im.view.empresas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = EncuestasEmpresaView.NAME, cached = true)
public class EncuestasEmpresaView extends CssLayout implements View {

	public static final String NAME = "encuestas";
	
    Table tbEncuestas;
    String fullpath;

    
//    @Autowired
//    ValuedService service;
//	@Autowired
//	UserService serviceUser;
    
    public EncuestasEmpresaView() {
	}

    @PostConstruct
    public void init() {
    	fullpath = "uploaded_files_path";
        setSizeFull();
        addStyleName("schedule");
//        ValuedEventBus.register(this);
        
        addComponent(buildToolbar());
        
    	HorizontalLayout hl = new HorizontalLayout();
     	addComponent(hl);
     	hl.setSizeFull();
     	hl.addComponent(buildEncuestas());

    }
    
    private Component buildToolbar() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        header.setSpacing(true);
        Responsive.makeResponsive(header);

        Label title = new Label("Encuestas");
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
	    
	    TextField tfNombre = new TextField();
	    tfNombre.setCaption("Nombre");
	    hl.addComponent(tfNombre);
	    
	    ComboBox cbTipoEstudio = new ComboBox();
	    cbTipoEstudio.setCaption("Tipo Estudio");
	    cbTipoEstudio.addItems("Gerencia Marketing", "Gerencia Estudios o Investigación de Mercados", "Gerencia de Relaciones Corporativas", "Gerencia de Riesgos");
	    hl.addComponent(cbTipoEstudio);
	    
	    ComboBox cbEstado = new ComboBox();
	    cbEstado.setCaption("Estado");
	    cbEstado.addItems("Activa", "Inactiva");
	    hl.addComponent(cbEstado);
	    
	    DateField dtDesde = new DateField();
	    dtDesde.setCaption("Trabajo de campo desde");
	    hl.addComponent(dtDesde);
	    
	    DateField dtHasta = new DateField();
	    dtHasta.setCaption("Trabajo de campo hasta");
	    hl.addComponent(dtHasta);
	    
	    Button btnFiltrar = new Button("Buscar",FontAwesome.SEARCH);
	    btnFiltrar.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 3844920778615955739L;

			public void buttonClick(ClickEvent event) {
				Notification.show("click Buscar");
			}
		});
		hl.addComponent(btnFiltrar);
		
		Button btnAregar = new Button("Agregar",FontAwesome.CHECK);
		btnAregar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Agregar");
			}
		});
		
		hl.addComponent(btnAregar);
		
    	return hl;
    }
    
    private VerticalLayout buildEncuestas() {		
    	VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		
		vl.addComponent(buildFiltro());
		
		tbEncuestas =  drawTableEncuestas();
		vl.addComponent(tbEncuestas);
		vl.setExpandRatio(tbEncuestas, 1.0f);
		
		return vl;
	}

    private Table drawTableEncuestas() {
    	Table tableEncuestas = new Table();

    	tableEncuestas.addContainerProperty("Nombre", String.class, null);
    	tableEncuestas.addContainerProperty("Área Patrocinadora",  String.class, null);
    	tableEncuestas.addContainerProperty("Fecha inicio \ntrabajo de campo",  String.class, null);
    	tableEncuestas.addContainerProperty("Fecha cierre \ntrabajo de campo",  String.class, null);
    	tableEncuestas.addContainerProperty("Estado",  String.class, null);
    	tableEncuestas.addContainerProperty("N° Respondentes",  String.class, null);
    	tableEncuestas.addContainerProperty("Acciones",  HorizontalLayout.class, null);
    	
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
    	Button btnFichas = new Button(null,FontAwesome.FILE);
    	btnFichas.setDescription("Fichas");
    	btnFichas.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(FichasEncuestaEmpresaView.NAME);
			}
		});
    	hl.addComponent(btnFichas);
    	Button btnCargar = new Button(null,FontAwesome.UPLOAD);
    	btnCargar.setDescription("Cargar");
    	btnCargar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {				
				buildCarga();
			}
		});
    	hl.addComponent(btnCargar);
    	Button btnResultado = new Button(null,FontAwesome.SIMPLYBUILT);
    	btnResultado.setDescription("Resultado/Simulación");
    	btnResultado.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Resultado");
			}
		});
    	hl.addComponent(btnResultado);
    	Button btnAfinidad = new Button(null,FontAwesome.SHARE_ALT);
    	btnAfinidad.setDescription("Afinidad");
    	btnAfinidad.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click Afinidad");
			}
		});
    	hl.addComponent(btnAfinidad);
    	Button btnImportancia = new Button(null,FontAwesome.TACHOMETER);
    	btnImportancia.setDescription("Importancia S.");
    	btnImportancia.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				buildImportancia();
			}
		});
    	hl.addComponent(btnImportancia);
    	// Add a few other rows using shorthand addItem()
    	tableEncuestas.addItem(new Object[]{"Encuesta inicio de Enero", "Gerencia Estudios", "02/01/2018", "18/02/2018", "Activa", "23", hl}, 1);

    	// Show exactly the currently contained rows (items)
    	tableEncuestas.setPageLength(tableEncuestas.size());
    	
    	return tableEncuestas;
	}
    
    private Window buildCarga(){    
    	final Window window = new Window("Cargar Resultados Estudios");
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
		FormLayout fl = new FormLayout();
		fl.setMargin(true);
		vl.addComponent(fl);
		
		window.setModal(true);
		window.setResizable(false);
		window.center();
		
		Accordion accor = new Accordion();
		accor.addStyleName(ValoTheme.ACCORDION_BORDERLESS);
//		accor.setHeight(100.0f, Unit.PERCENTAGE);
//		accor.setWidth(60.0f, Unit.PERCENTAGE);
		
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        CssLayout group = new CssLayout();
        group.addStyleName("v-component-group");
         
        group.addComponent(uploadFile());
        layout.addComponent(group);
        
        VerticalLayout layout2 = new VerticalLayout();
        layout2.setMargin(true);
        CssLayout group2 = new CssLayout();
        group2.addStyleName("v-component-group");
         
        group2.addComponent(uploadFile());
        layout2.addComponent(group2);
        
        VerticalLayout layout3 = new VerticalLayout();
        layout3.setMargin(true);
        CssLayout group3 = new CssLayout();
        group3.addStyleName("v-component-group");
         
        group3.addComponent(uploadFile());
        layout3.addComponent(group3);
        
        VerticalLayout layout4 = new VerticalLayout();
        layout3.setMargin(true);
        CssLayout group4 = new CssLayout();
        group4.addStyleName("v-component-group");
         
        group4.addComponent(uploadFile());
        layout4.addComponent(group4);
		
		accor.addTab(layout, "Modelo Reputacional", null, 0);
		accor.addTab(layout2, "Modelo de Riesgos",null,1);
		accor.addTab(layout3, "Modelo de Afinidad",null,2);
		accor.addTab(layout4, "Preguntas",null,3);
		fl.addComponent(accor);
		
		window.setContent(vl);
		
		UI.getCurrent().addWindow(window);
		
		return window;
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
		            file = new File(fullpath+filename);
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

    private Window buildImportancia(){    
    	final Window window = new Window("Importancia Stakeholder");
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
		FormLayout fl = new FormLayout();
		fl.setMargin(true);
		vl.addComponent(fl);
		
		window.setModal(true);
		window.setResizable(false);
		window.center();
		
		HorizontalLayout hl = new HorizontalLayout();
		fl.addComponent(hl);
    	hl.setSpacing(true);
    	hl.setMargin(true);
	    
    	ComboBox cbStakeholder = new ComboBox();
    	cbStakeholder.setCaption("Stakeholder");
    	cbStakeholder.addItems("Cliente", "Colaborador", "Inversionista", "Proveedor");
 	    hl.addComponent(cbStakeholder);   
	    
	    ComboBox cbSegmento = new ComboBox();
	    cbSegmento.setCaption("Segmento");
	    cbSegmento.addItems("Cliente Medio", "Cliente Secundario", "Cliente Prioritario");
	    hl.addComponent(cbSegmento);
	    
	    ComboBox cbSub = new ComboBox();
	    cbSub.setCaption("Sub-Segmento");
	    hl.addComponent(cbSub);	   
		
	    Button btnFiltrar = new Button("Buscar",FontAwesome.SEARCH);
	    btnFiltrar.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 3844920778615955739L;

			public void buttonClick(ClickEvent event) {
				Notification.show("click Buscar");
			}
		});
		hl.addComponent(btnFiltrar);
		
		Table tableFichas = new Table();
		tableFichas.addContainerProperty("ID Respondente", String.class, null);
		tableFichas.addContainerProperty("Stakeholder",  String.class, null);
		tableFichas.addContainerProperty("Segmento",  String.class, null);
		tableFichas.addContainerProperty("Sub-Segmento",  String.class, null);
		tableFichas.addContainerProperty("Criticidad",  String.class, null);
		tableFichas.addContainerProperty("Peso Relativo",  String.class, null);
		tableFichas.addContainerProperty("Acciones",  HorizontalLayout.class, null);    	
    	
    	HorizontalLayout hl2 = new HorizontalLayout();
    	hl2.setSpacing(true);
    	
    	Button btnEditar = new Button(null,FontAwesome.EDIT);
    	btnEditar.setDescription("Editar");
    	btnEditar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				Notification.show("click editar");
			}
		});
    	hl2.addComponent(btnEditar);
    	
    	tableFichas.addItem(new Object[]{"1", "Cliente", "Cliente prioritario", "C1", "Critico", "xx", hl2}, 1);
    	tableFichas.setPageLength(tableFichas.size());

		fl.addComponent(tableFichas);
		
		window.setContent(vl);
		
		UI.getCurrent().addWindow(window);
		
		return window;
    }
    
    public void enter(final ViewChangeEvent event) {

    }
}
