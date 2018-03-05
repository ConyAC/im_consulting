package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = UsuariosEmpresaView.NAME, cached = true)
public class UsuariosEmpresaView extends CssLayout implements View {

	public static final String NAME = "encuestas";
	
    Table tbEmpresas;

    
//    @Autowired
//    ValuedService service;
//	@Autowired
//	UserService serviceUser;
    
    public UsuariosEmpresaView() {
	}

    @PostConstruct
    public void init() {
        setSizeFull();
        addStyleName("schedule");
//        ValuedEventBus.register(this);
        
        addComponent(buildToolbar());
        
     

    }
    
    private Component buildToolbar() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        header.setSpacing(true);
        Responsive.makeResponsive(header);

        Label title = new Label("Usuarios");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
        

    public void enter(final ViewChangeEvent event) {

    }
}
