package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.data.dummy.DummyDataGenerator;
import cl.koritsu.im.utils.Constants;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = FichaRespondenteEncuestaView.NAME, cached = true)
public class FichaRespondenteEncuestaView extends CssLayout implements View {

	public static final String NAME = "ficha";
	
    public FichaRespondenteEncuestaView() {
	}

    @PostConstruct
    public void init() {
    	
        setSizeFull();
        addStyleName("schedule");
        
        addComponent(buildToolbar());
     	
     	GridLayout glRoot = new GridLayout(2,10);
     	glRoot.setSpacing(true);
		glRoot.setMargin(true);
		glRoot.setWidth("100%");
		
		ComboBox cbRespondenteId = new ComboBox("Respondent");
		cbRespondenteId.addItem("1");
		cbRespondenteId.select("1");
		cbRespondenteId.addItem("2");
		cbRespondenteId.addItem("3");
		cbRespondenteId.addItem("4");
		glRoot.addComponent(cbRespondenteId,0,0);
		
		TextField tfStakeholder = new TextField("Stakeholder");
		for(String sh : DummyDataGenerator.getStakeHolderUS()){
			tfStakeholder.setValue(sh);
    		break;
		}
		tfStakeholder.setReadOnly(true);
			
		glRoot.addComponent(tfStakeholder,0,1);
		
		TextField tfSegmento = new TextField("Segment");
		tfSegmento.setValue(DummyDataGenerator.getSegmentosUS().get(1));
		tfSegmento.setReadOnly(true);
		glRoot.addComponent(tfSegmento,0,2);
		
		TextField tfSubsegmento = new TextField("Subsegment");
		 for(String sh : DummyDataGenerator.getSubsegmentosUS()){
			 tfSubsegmento.setValue(sh);
			 break;
		 }

		tfSubsegmento.setReadOnly(true);
		glRoot.addComponent(tfSubsegmento,0,3);
		
		ComboBox cbCriticidad = new ComboBox("Criticity");
		for(String sh : DummyDataGenerator.getCriticidades())
			cbCriticidad.addItem(sh);
		cbCriticidad.setValue(DummyDataGenerator.getCriticidades().get(1));
		glRoot.addComponent(cbCriticidad,0,4);
		
		ComboBox cbActitud = new ComboBox("Actitude");
		for(String sh : DummyDataGenerator.getActitudesUS())
			cbActitud.addItem(sh);
		cbActitud.setValue(DummyDataGenerator.getActitudesUS().get(1));
		glRoot.addComponent(cbActitud,0,5);
		
		ComboBox cbTipo = new ComboBox("Type");
		for(String sh : DummyDataGenerator.getTypesUS())
			cbTipo.addItem(sh);
		cbTipo.setValue(DummyDataGenerator.getTypesUS().get(1));
		glRoot.addComponent(cbTipo,0,6);
		
		TextArea taObservacion = new TextArea("Observation");
		taObservacion.setValue("Respondent admitted for the beginning of January survey, sponsoring area Management Studies.");
		glRoot.addComponent(taObservacion,0,7);
		
		
		//tab con los resultado
		TabSheet tab = buildTab();
		glRoot.addComponent(tab,1,0,1,6);
		glRoot.setColumnExpandRatio(1, 0.8f);
		
		addComponent(new Panel(glRoot) {
			{
				setSizeFull();
			}
		});
		
		Button agregarContacto = new Button("Add Contact");
		agregarContacto.setIcon(FontAwesome.PLUS);
		glRoot.addComponent(agregarContacto,0,8,1,8);
		glRoot.setComponentAlignment(agregarContacto, Alignment.MIDDLE_RIGHT);
		
		Table table = new Table();
		table.setSizeFull();
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Social number", String.class, null);
		table.addContainerProperty("Position", String.class, null);
		table.addContainerProperty("Email", String.class, null);
		table.addContainerProperty("Phone", String.class, null);
		table.addContainerProperty("Role", String.class, null);
		table.addContainerProperty("Adaptability", String.class, null);
		table.addContainerProperty("Attitude", String.class, null);
		table.addContainerProperty("Frequency", String.class, null);
		table.addContainerProperty("Actions", Button.class, null);
		
		
		table.addItem(new Object[]{"Respondent 1", "1-4","Commercial Manager", "respondent@a.com","", "","", "","",new Button(FontAwesome.EDIT)}, 0);
		table.addItem(new Object[]{"Contact 1", "1-9","General Manager", "a@a.com","667884344", "Approver","Innovative", "Neutral","In deep",new Button(FontAwesome.EDIT)}, 1);
		table.addItem(new Object[]{"Contact 2", "1-8","Commercial Manager", "a@a.com","33423232", "Approver","Innovative", "Neutral","In deep",new Button(FontAwesome.EDIT)}, 2);
		table.addItem(new Object[]{"Contact 3", "1-7","General Manager", "a@a.com","22323555", "Approver","Innovative", "Neutral","In deep",new Button(FontAwesome.EDIT)}, 3);
		
		glRoot.addComponent(table,0,9,1,9);
		glRoot.setRowExpandRatio(8, 1.0f);

    }
    
    private TabSheet buildTab() {
    	TabSheet tab = new TabSheet();
    	tab.setSizeFull();
    	
    	VerticalLayout reputacion = buildReputacionTable();
    	tab.addTab(new Panel(reputacion) {
    		{
    			setSizeFull();
    		}
    	},"Reputation Model");
    	
    	VerticalLayout riesgos = buildRiesgos();
    	
    	tab.addTab(new Panel(riesgos) {
    		{
    			setSizeFull();
    		}
    	},"Risk Model/Index");
    	
    	VerticalLayout afinidad = buildAfinidad();
    	
    	tab.addTab(new Panel(afinidad) {
    		{
    			setSizeFull();
    		}
    	},"Affinity / Weighing Model");
    	
		return tab;
	}
    
	private VerticalLayout buildAfinidad() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
    	ttable.addContainerProperty("Important", String.class, null);

    	// Create the tree nodes and set the hierarchy   	
    	ttable.addItem(new Object[]{"Weighing Score (WS)",  ""}, 0);
    	ttable.addItem(new Object[]{"Scenario 1",  "43%"}, 1);
    	ttable.addItem(new Object[]{"Scenario 2", "39%"}, 2);
    	ttable.addItem(new Object[]{"Scenario 3",  "52%"}, 3);
    	ttable.addItem(new Object[]{"Average Scenario", "49%"}, 4);
    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(4, 0);
    	
//    	ttable.setPageLength(ttable.getItemIds().size());
    	
		vl.addComponents(ttable);
		vl.setExpandRatio(ttable, 1.0f);
		return vl;
	}

	private VerticalLayout buildRiesgos() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
    	ttable.addContainerProperty("Important", String.class, null);
    	
    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Risk Model/Index",  "19%"}, 0);
    	ttable.addItem(new Object[]{"Risk 1",  "5"}, 1);
    	ttable.addItem(new Object[]{"Risk 2",  "2"}, 2);
    	ttable.addItem(new Object[]{"Risk 3",  "3"}, 3);
    	ttable.addItem(new Object[]{"Risk 4",  "3"}, 4);
    	ttable.addItem(new Object[]{"Risk 5",  "4"}, 5);
    	ttable.addItem(new Object[]{"Risk 6",  "3"}, 6);
    	ttable.addItem(new Object[]{"Risk 7",  "2"}, 7);
    	ttable.addItem(new Object[]{"Risk 8",  "1"}, 8);
    	ttable.addItem(new Object[]{"Risk 9",  "5"}, 9);
    	ttable.addItem(new Object[]{"Risk 10",  "1"}, 10);
    	ttable.addItem(new Object[]{"Risk 11",  "2"}, 11);
    	ttable.addItem(new Object[]{"Risk 12",  "2"}, 12);
    	ttable.addItem(new Object[]{"Risk 13",  "4"}, 13);
    	ttable.addItem(new Object[]{"Risk 14",  "4"}, 14);
    	ttable.addItem(new Object[]{"Risk 15",  "1"}, 15);
    	
    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(4, 0);
    	ttable.setParent(5, 0);
    	ttable.setParent(6, 0);
    	ttable.setParent(7, 0);
    	ttable.setParent(8, 0);
    	ttable.setParent(9, 0);
    	ttable.setParent(10, 0);
    	ttable.setParent(11, 0);
    	ttable.setParent(12, 0);
    	ttable.setParent(13, 0);
    	ttable.setParent(14, 0);
    	ttable.setParent(15, 0);
		
		vl.addComponents(ttable);
		return vl;
	}

	private VerticalLayout buildReputacionTable() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
//    	ttable.setWidth("100%");
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
    	ttable.addContainerProperty("Result", String.class, null);
    	ttable.addContainerProperty("Important", String.class, null);

    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Brand Asset & Loyalty Model",  "", ""}, 0);
    	ttable.addItem(new Object[]{"Net Awareness",  "7", ""}, 1);
    	ttable.addItem(new Object[]{"Net Relevance",  "6", ""}, 2);
    	ttable.addItem(new Object[]{"Net Personal Regard",  "5", ""}, 3);
    	ttable.addItem(new Object[]{"Net Promoter Score (NPS)",  "7", ""}, 10);
    	ttable.addItem(new Object[]{"Net Effort Score (NES)",  "6", ""},11);
    	ttable.addItem(new Object[]{"Net Propensity to Repurchase",  "5", ""}, 12);
    	ttable.addItem(new Object[]{"Differentiation",  "4", ""}, 53);

    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(10, 0);
    	ttable.setParent(11, 0);
    	ttable.setParent(12, 0);
    	ttable.setParent(53, 0);
    	
    	ttable.addItem(new Object[]{"Corporate Reputation Index (CRI)",  "6", ""}, 4);
    	ttable.addItem(new Object[]{"Emotional Dimensions",  "6", "4"}, 15);
    	ttable.setParent(15, 4);
    	ttable.addItem(new Object[]{"Esteem",  "5", "3"}, 6);
    	ttable.addItem(new Object[]{"Trust",  "4", "5"}, 7);
    	ttable.addItem(new Object[]{"Admiration",  "5", "5"}, 8);
    	ttable.addItem(new Object[]{"Identification",  "4", "3"}, 13);
    	ttable.addItem(new Object[]{"Empathy",  "6", "3"}, 14);

    	ttable.setParent(6, 15);
    	ttable.setParent(7, 15);
    	ttable.setParent(8, 15);
    	ttable.setParent(13, 15);
    	ttable.setParent(14, 15);
    	
    	ttable.addItem(new Object[]{"Rational Dimensions",  "6", "4"}, 9);    	
    	ttable.setParent(9, 4);
    	ttable.addItem(new Object[]{"Economic Dimensions",  "7", "1"}, 16);
    	ttable.setParent(16, 9);    	
    	ttable.addItem(new Object[]{"Profitability",  "6", "3"}, 17);
    	ttable.addItem(new Object[]{"Solvency",  "7", "3"}, 18);
    	ttable.addItem(new Object[]{"Growth",  "6", "2"}, 19);
    	ttable.addItem(new Object[]{"Market Cap",  "6", "3"}, 20);
    	
    	ttable.setParent(17, 16);
    	ttable.setParent(18, 16);
    	ttable.setParent(19, 16);
    	ttable.setParent(20, 16);
    	
    	ttable.addItem(new Object[]{"Service Dimensions",  "4", "4"}, 21);
    	ttable.setParent(21, 9);    	
    	ttable.addItem(new Object[]{"Quality",  "3", "5"}, 22);
    	ttable.addItem(new Object[]{"Price",  "3", "4"}, 23);
    	ttable.addItem(new Object[]{"Customer Service",  "3", "4"}, 24);
    	ttable.addItem(new Object[]{"Custormer Satisfaction",  "3", "5"}, 25);
    	ttable.addItem(new Object[]{"Innovation",  "3", "4"}, 26);
    	
    	ttable.setParent(22, 21);
    	ttable.setParent(23, 21);
    	ttable.setParent(24, 21);
    	ttable.setParent(25, 21);
    	ttable.setParent(26, 21);
    	
    	ttable.addItem(new Object[]{"People Dimensions",  "5", "4"}, 27);
    	ttable.setParent(27, 9);    	
    	ttable.addItem(new Object[]{"Talent",  "6", "4"}, 28);
    	ttable.addItem(new Object[]{"Job stability",  "5", "4"}, 29);
    	ttable.addItem(new Object[]{"Wellness",  "6", "3"}, 30);
    	ttable.addItem(new Object[]{"Personal incentives and compensation",  "5", "4"}, 31);
    	ttable.addItem(new Object[]{"Equality",  "6", "3"}, 32);
    	ttable.addItem(new Object[]{"Meritocracy",  "6", "3"}, 33);
    	
    	ttable.setParent(28, 27);
    	ttable.setParent(29, 27);
    	ttable.setParent(30, 27);
    	ttable.setParent(31, 27);
    	ttable.setParent(32, 27);
    	ttable.setParent(33, 27);
    	
    	ttable.addItem(new Object[]{"Gobernance Dimensions",  "6", "4"}, 34);
    	ttable.setParent(34, 9);    	
    	ttable.addItem(new Object[]{"Supplier Quality",  "7", "4"},35);
    	ttable.addItem(new Object[]{"Transparency",  "6", "4"}, 36);
    	ttable.addItem(new Object[]{"Ethics",  "6", "4"},37);
    	ttable.addItem(new Object[]{"Antibribery",  "6", "5"},38);
    	ttable.addItem(new Object[]{"Respect",  "6", "4"}, 39);
    	
    	ttable.setParent(35, 34);
    	ttable.setParent(36, 34);
    	ttable.setParent(37, 34);
    	ttable.setParent(38, 34);
    	ttable.setParent(39, 34);
    	
    	ttable.addItem(new Object[]{"Leadership Dimension",  "6", "4"}, 40);
    	ttable.setParent(40, 9);    	
    	ttable.addItem(new Object[]{"Leadership",  "7", "4"},41);
    	ttable.addItem(new Object[]{"Faireness",  "6", "4"}, 42);
    	ttable.addItem(new Object[]{"Management",  "7", "4"}, 43);
    	ttable.addItem(new Object[]{"Vision",  "7", "5"}, 44);
    	ttable.addItem(new Object[]{"Comunications",  "6", "5"}, 45);
    	
    	ttable.setParent(41, 40);
    	ttable.setParent(42, 40);
    	ttable.setParent(43, 40);
    	ttable.setParent(44, 40);
    	ttable.setParent(45, 40);
    	
    	ttable.addItem(new Object[]{"Social Responsability Dimensions",  "5", "5"}, 46);
    	ttable.setParent(46, 9);    	
    	ttable.addItem(new Object[]{"Environment Friendly",  "7", "5"}, 47);
    	ttable.addItem(new Object[]{"Energy Savings",  "7", "5"}, 48);
    	ttable.addItem(new Object[]{"Comunity Engagement",  "7", "5"}, 49);
    	ttable.addItem(new Object[]{"Country Engagement",  "5", "4"}, 50);
    	ttable.addItem(new Object[]{"Inclusion",  "7", "4"}, 51);
    	ttable.addItem(new Object[]{"Good Causes",  "4", "5"}, 52);
    	
    	ttable.setParent(47, 46);
    	ttable.setParent(48, 46);
    	ttable.setParent(49, 46);
    	ttable.setParent(50, 46);
    	ttable.setParent(51, 46);
    	ttable.setParent(52, 46);
		
		vl.addComponents(ttable);
		return vl;
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
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Respondent Form");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
    
    public void enter(final ViewChangeEvent event) {

    }
}
