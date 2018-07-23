package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.vaadin.annotations.JavaScript;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.data.dummy.DummyDataGenerator;
import cl.koritsu.im.utils.Constants;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = AfinidadEncuestaEmpresaView.NAME, cached = true)
@JavaScript({"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js","https://cdnjs.cloudflare.com/ajax/libs/vis/4.21.0/vis.min.js", "vaadin://themes/dashboard/js/im-consulting.js" })
public class AfinidadEncuestaEmpresaView extends CssLayout implements View {

public static final String NAME = "afinidad";
	
    public AfinidadEncuestaEmpresaView() {
	}

    @PostConstruct
    public void init() {
    	
        setSizeFull();
        addStyleName("schedule");
        
        addComponent(buildToolbar());
     	
     	GridLayout glRoot = new GridLayout(2,10);
     	glRoot.setSpacing(true);
		glRoot.setMargin(true);
		glRoot.setSizeFull();
		
		ComboBox cbRespondenteId = new ComboBox("1. Risk Scenario");
		cbRespondenteId.addItem("Average Scenario");
		cbRespondenteId.select("Average Scenario");
		cbRespondenteId.addItem("Scenario 1");
		cbRespondenteId.addItem("Scenario 2");
		cbRespondenteId.addItem("Scenario 3");
		glRoot.addComponent(cbRespondenteId,0,0);
		
		ComboBox cbStakeholder = new ComboBox("2. Agents");
		cbStakeholder.addItem("Stakeholder");
		cbStakeholder.addItem("Media");
		cbStakeholder.select("Stakeholder");
			
		glRoot.addComponent(cbStakeholder,0,1);
		
		ComboBox cbSegmento = new ComboBox("3. Hub to highlight and center");
		for(String sh : DummyDataGenerator.getSegmentosUS())
			cbSegmento.addItem(sh);
		glRoot.addComponent(cbSegmento,0,2);
				
		//tab con los grafos
		VerticalLayout tab = buildGrafo();
		glRoot.addComponent(tab,1,0,1,6);
		glRoot.setColumnExpandRatio(1, 0.8f);
		
		addComponent(new Panel(glRoot) {
			{
				setSizeFull();
			}
		});
		com.vaadin.ui.JavaScript.getCurrent().execute("setTimeout(drawNetwork,1000);");
    }
    
    private VerticalLayout buildGrafo() {
		return new VerticalLayout() {
			{
				setId("mynetwork");
				setSizeFull();
			}
		};
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
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Affinity Model");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
    
    public void enter(final ViewChangeEvent event) {

    }
}
