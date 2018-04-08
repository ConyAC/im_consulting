package cl.koritsu.im.view.empresas;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.data.dummy.DummyDataGenerator;
import cl.koritsu.im.view.empresas.RespuestaChartWindows.MODELO;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(value = RespuestaEncuestaView.NAME, cached = true)
public class RespuestaEncuestaView extends CssLayout implements View {

	public static final String NAME = "resultado";
	
    Table tbFichas;
    
    public RespuestaEncuestaView() {
	}

    @PostConstruct
    public void init() {
    	
        setSizeFull();
        addStyleName("schedule");
        
        addComponent(buildToolbar());
     	
     	GridLayout glRoot = new GridLayout(6,10);
     	glRoot.setSpacing(true);
		glRoot.setMargin(true);
		glRoot.setSizeFull();
		
		
		ComboBox cbEstudios = new ComboBox("Surveys");
		cbEstudios.addItem("Reputation");
		cbEstudios.addItem("Risk");
		cbEstudios.addItem("Importance");
		glRoot.addComponents(cbEstudios);
		
		ComboBox cbStakeholder = new ComboBox("Stakeholder");
		cbStakeholder.addItems(DummyDataGenerator.getStakeHolderUS());
		glRoot.addComponents(cbStakeholder);
		
		ComboBox cbSegmento = new ComboBox("Segment");
		cbSegmento.addItems(DummyDataGenerator.getSegmentosUS());
		glRoot.addComponents(cbSegmento);
		
		ComboBox cbSubsegmento = new ComboBox("Subsegment");
		cbSubsegmento.addItems(DummyDataGenerator.getSubsegmentosUS());
		glRoot.addComponents(cbSubsegmento);
		

		final TabSheet tab = buildTab();
		
		glRoot.addComponents(new Button("Search",FontAwesome.SEARCH));
		glRoot.addComponents(new Button("Chart",FontAwesome.AREA_CHART) {
			{
				addClickListener(new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						//dependiendo de la pestaña activa, pide el grafico de un modelo u otro
						MODELO modelo = MODELO.REPUTACION;
						int position = tab.getTabPosition( tab.getTab( tab.getSelectedTab() ) );
						if( position == 0 ) {
							modelo = MODELO.REPUTACION;
						}else if (position == 1 ) {
							modelo = MODELO.RIESGO;
						}else {
							modelo = MODELO.IMPORTANCIA;
						}
							
						RespuestaChartWindows resp = new RespuestaChartWindows(modelo);
						UI.getCurrent().addWindow(resp);
					}
				});
			}
		});
		
		
		glRoot.addComponent(tab,0,1,5,1);
		glRoot.setRowExpandRatio(1, 1.0f);
		
		addComponent(glRoot);

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
    	},"Affinity / Importance Model");
    	
		return tab;
	}
    
	private VerticalLayout buildAfinidad() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
//    	ttable.addContainerProperty("Chart", CheckBox.class, null);
    	ttable.addContainerProperty("2018 Value", String.class, null);
    	ttable.addContainerProperty("2017 Value", String.class, null);
    	ttable.addContainerProperty("2016 Value", String.class, null);

    	// Create the tree nodes and set the hierarchy
    	/*ttable.addItem(new Object[]{"Affinity Index (AI)", new CheckBox(), "", "",""}, 0);
    	ttable.addItem(new Object[]{"Escenario 1", new CheckBox(), "73%", "76%","77%"}, 1);
    	ttable.addItem(new Object[]{"Escenario 2", new CheckBox(), "84%", "86%","87%"}, 2);
    	ttable.addItem(new Object[]{"Escenario 3", new CheckBox(), "94%", "96%","97%"}, 3);
    	ttable.addItem(new Object[]{"Escenario Promedio", new CheckBox(), "74%", "86%","97%"}, 4);
    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(4, 0);*/
    	
    	ttable.addItem(new Object[]{"Weighing Score (WS)",  "", "",""}, 5);
    	ttable.addItem(new Object[]{"Scenario 1",  "45%", "",""}, 6);
    	ttable.addItem(new Object[]{"Scenario 2",  "41%", "",""}, 7);
    	ttable.addItem(new Object[]{"Scenario 3",  "46%", "",""}, 8);
    	ttable.addItem(new Object[]{"Average Scenario",  "49%", "",""}, 9);
    	ttable.setParent(6, 5);
    	ttable.setParent(7, 5);
    	ttable.setParent(8, 5);
    	ttable.setParent(9, 5);
    	
    	
    	/*ComboBox categoria = new ComboBox();
    	categoria.setNullSelectionAllowed(false);
    	categoria.addItem("Stakeholder");
    	categoria.addItem("Medio");    	categoria.select("Stakeholder");*/
    	
		Table respondenteTable = new Table();
		respondenteTable.setWidth("100%");
		respondenteTable.addContainerProperty("Name", String.class, null);
		respondenteTable.addContainerProperty("Knowledge", String.class, null);
		respondenteTable.addContainerProperty("Relevance", String.class, null);
		respondenteTable.addContainerProperty("Personal Regard", String.class, null);
		respondenteTable.addContainerProperty("Diferentiation", String.class, null);
		respondenteTable.addContainerProperty("Net Promoter Score (NPS)", String.class, null);
		respondenteTable.addContainerProperty("Net Effort Score (NES)", String.class, null);
		respondenteTable.setSizeFull();
		
//		respondenteTable.setPageLength(respondenteTable.getItemIds().size());
		
		vl.addComponents(ttable/*,categoria*/,respondenteTable);
		vl.setExpandRatio(ttable, 1.0f);
		vl.setExpandRatio(respondenteTable, 1.0f);
		return vl;
	}

	private VerticalLayout buildRiesgos() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
//    	ttable.addContainerProperty("Chart", CheckBox.class, null);
    	ttable.addContainerProperty("2018 Value", String.class, null);
    	ttable.addContainerProperty("2017 Value", String.class, null);
    	ttable.addContainerProperty("2016 Value", String.class, null);
    	ttable.addContainerProperty("Simulate", TextField.class, null);
    	ttable.addContainerProperty("Simulated Result", String.class, null);

    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Risk Model/Index",  "", "","", new TextField(),""}, 0);
    	ttable.addItem(new Object[]{"Risk 1",  "32%", "","", new TextField(),""}, 1);
    	ttable.addItem(new Object[]{"Risk 2",  "7%", "","", new TextField(),""}, 2);
    	ttable.addItem(new Object[]{"Risk 3",  "3%", "","", new TextField(),""}, 3);
    	ttable.addItem(new Object[]{"Risk 4",  "18%", "","", new TextField(),""}, 4);
    	ttable.addItem(new Object[]{"Risk 5",  "58%", "","", new TextField(),""}, 5);
    	ttable.addItem(new Object[]{"Risk 6",  "8%", "","", new TextField(),""}, 6);
    	ttable.addItem(new Object[]{"Risk 7",  "2%", "","", new TextField(),""}, 7);
    	ttable.addItem(new Object[]{"Risk 8",  "19%", "","", new TextField(),""}, 8);
    	ttable.addItem(new Object[]{"Risk 9",  "52%", "","", new TextField(),""}, 9);
    	ttable.addItem(new Object[]{"Risk 10",  "36%", "","", new TextField(),""}, 10);
    	ttable.addItem(new Object[]{"Risk 11",  "2%", "","", new TextField(),""},11);
    	ttable.addItem(new Object[]{"Risk 12",  "35%", "","", new TextField(),""}, 12);
    	ttable.addItem(new Object[]{"Risk 13",  "8%", "","", new TextField(),""},13);
    	ttable.addItem(new Object[]{"Risk 14",  "5%", "","", new TextField(),""}, 14);
    	ttable.addItem(new Object[]{"Risk 15",  "13%", "","", new TextField(),""}, 15);
    	
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
    	
//    	ttable.setPageLength(ttable.getItemIds().size());
    	
		Table respondenteTable = new Table();
		respondenteTable.setWidth("100%");
		respondenteTable.addContainerProperty("Name", String.class, null);
		respondenteTable.addContainerProperty("Knowledge", String.class, null);
		respondenteTable.addContainerProperty("Relevance", String.class, null);
		respondenteTable.addContainerProperty("Personal Regard", String.class, null);
		respondenteTable.addContainerProperty("Diferentiation", String.class, null);
		respondenteTable.addContainerProperty("Net Promoter Score (NPS)", String.class, null);
		respondenteTable.addContainerProperty("Net Effort Score (NES)", String.class, null);
		respondenteTable.setSizeFull();
		
//		respondenteTable.setPageLength(respondenteTable.getItemIds().size());
		
		vl.addComponents(ttable,respondenteTable);
		return vl;
	}

	private VerticalLayout buildReputacionTable() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
//    	ttable.setWidth("100%");
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
//    	ttable.addContainerProperty("Chart", CheckBox.class, null);
    	ttable.addContainerProperty("2018 Value \nResult/Important", String.class, null);
    	ttable.addContainerProperty("2017 Value \nResult/Important", String.class, null);
    	ttable.addContainerProperty("2016 Value \nResult/Important", String.class, null);
    	ttable.addContainerProperty("Simulate", TextField.class, null);
    	ttable.addContainerProperty("Simulated Result", String.class, null);

    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Brand Asset & Loyalty Model",  "", "","", new TextField(),""}, 0);
    	ttable.addItem(new Object[]{"Knowledge",  "91%", "","", new TextField(),""}, 1);
    	ttable.addItem(new Object[]{"Relevance",  "83%", "","%", new TextField(),""}, 2);
    	ttable.addItem(new Object[]{"Personal Regard",  "38%", "","", new TextField(),""}, 3);
    	ttable.addItem(new Object[]{"Net Promoter Score (NPS)",  "18%", "","", new TextField(),""}, 10);
    	ttable.addItem(new Object[]{"Net Effort Score (NES)",  "6%", "","", new TextField(),""},11);
    	ttable.addItem(new Object[]{"Renewal/Repurchase",  "9%", "","", new TextField(),""}, 12);
    	ttable.addItem(new Object[]{"Differentiation",  "", "","", new TextField(),""}, 53);

    	ttable.setParent(1, 0);
    	ttable.setParent(2, 0);
    	ttable.setParent(3, 0);
    	ttable.setParent(10, 0);
    	ttable.setParent(11, 0);
    	ttable.setParent(12, 0);
    	ttable.setParent(53, 0);
    	
    	ttable.addItem(new Object[]{"Corporate Reputation Index (IRC)",  "", "","", new TextField(),""}, 4);
    	ttable.addItem(new Object[]{"Emotional Dimensions",  "", "","", new TextField(),""}, 5);
    	ttable.setParent(5, 4);
    	ttable.addItem(new Object[]{"Esteem",  "26%/81%", "","", new TextField(),""}, 6);
    	ttable.addItem(new Object[]{"Trust",  "21%/78%", "","", new TextField(),""}, 7);
    	ttable.addItem(new Object[]{"Admiration",  "21%/79%", "","", new TextField(),""}, 8);
    	ttable.addItem(new Object[]{"Identification",  "25%/78%", "","", new TextField(),""}, 13);
    	ttable.addItem(new Object[]{"Empathy",  "24%/79%", "","", new TextField(),""}, 14);

    	ttable.setParent(6, 5);
    	ttable.setParent(7, 5);
    	ttable.setParent(8, 5);
    	ttable.setParent(13, 5);
    	ttable.setParent(14, 5);
    	
    	ttable.addItem(new Object[]{"Rational Dimensions",  "", "","", new TextField(),""}, 9);    	
    	ttable.setParent(9, 4);
     	ttable.addItem(new Object[]{"Economic dimension",  "91%/49%", "","", new TextField(),""}, 16);
     	ttable.setParent(16, 9);   
    	ttable.addItem(new Object[]{"Profitability",  "90%/49%", "","", new TextField(),""}, 17);
    	ttable.addItem(new Object[]{"Solvency",  "90%/49%", "","", new TextField(),""}, 18);
    	ttable.addItem(new Object[]{"Growth",  "94%/48%", "","", new TextField(),""}, 19);
    	ttable.addItem(new Object[]{"Market Cap",  "94%/50%", "","", new TextField(),""}, 20);
    	
       	ttable.setParent(17, 16);
    	ttable.setParent(18, 16);
    	ttable.setParent(19, 16);
    	ttable.setParent(20, 16);    	
    	
    	ttable.addItem(new Object[]{"Service Dimensions",  "26%/84%", "","", new TextField(),""}, 21);
    	ttable.setParent(21, 9); 
    	ttable.addItem(new Object[]{"Quality",  "27%/84%", "","", new TextField(),""}, 22);
    	ttable.addItem(new Object[]{"Price",  "34%/83%", "","", new TextField(),""}, 23);
    	ttable.addItem(new Object[]{"Customer Service",  "29%/83%", "","", new TextField(),""}, 24);
    	ttable.addItem(new Object[]{"Custormer Satisfaction",  "30%/83%", "","", new TextField(),""}, 25);
    	ttable.addItem(new Object[]{"Innovation",  "22%/83%", "","", new TextField(),""}, 26);
    	
    	ttable.setParent(22, 21);
    	ttable.setParent(23, 21);
    	ttable.setParent(24, 21);
    	ttable.setParent(25, 21);
    	ttable.setParent(26, 21);
    	
    	
    	ttable.addItem(new Object[]{"People Dimensions",  "61%/78%", "","", new TextField(),""}, 27);
    	ttable.setParent(27, 9); 
    	ttable.addItem(new Object[]{"Talent",  "60%/78%", "","", new TextField(),""}, 28);
    	ttable.addItem(new Object[]{"Job stability",  "60%/78%", "","", new TextField(),""}, 29);
    	ttable.addItem(new Object[]{"Wellness",  "63%/77%", "","", new TextField(),""}, 30);
    	ttable.addItem(new Object[]{"Personal incentives and compensation",  "61%/77%", "","", new TextField(),""}, 31);
    	ttable.addItem(new Object[]{"Equality",  "66%/78%", "","", new TextField(),""}, 32);
    	ttable.addItem(new Object[]{"Meritocracy",  "61%/78%", "","", new TextField(),""}, 33);
    	
    	ttable.setParent(28, 27);
    	ttable.setParent(29, 27);
    	ttable.setParent(30, 27);
    	ttable.setParent(31, 27);
    	ttable.setParent(32, 27);
    	ttable.setParent(33, 27);
    	
    	ttable.addItem(new Object[]{"Gobernability Dimensions",  "84%/90%", "","", new TextField(),""}, 34);
    	ttable.setParent(34, 9);
    	ttable.addItem(new Object[]{"Supplier Quality",  "93%/78%", "","", new TextField(),""}, 35);
    	ttable.addItem(new Object[]{"Transparency",  "77%/89%", "","", new TextField(),""}, 36);
    	ttable.addItem(new Object[]{"Ethics",  "84%/89%", "","", new TextField(),""}, 37);
    	ttable.addItem(new Object[]{"Antibribery",  "82%/89%", "","", new TextField(),""}, 38);
    	ttable.addItem(new Object[]{"Respect",  "81%/90%", "","", new TextField(),""}, 39);
    	
    	ttable.setParent(35, 34);
    	ttable.setParent(36, 34);
    	ttable.setParent(37, 34);
    	ttable.setParent(38, 34);
    	ttable.setParent(39, 34);
    	
    	ttable.addItem(new Object[]{"Leadership Dimension",  "77%/85%", "","", new TextField(),""}, 40);
    	ttable.setParent(40, 9);
    	ttable.addItem(new Object[]{"Leadership",  "79%/83%", "","", new TextField(),""},41);
    	ttable.addItem(new Object[]{"Faireness",  "81%/84%", "","", new TextField(),""}, 42);
    	ttable.addItem(new Object[]{"Management",  "72%/84%", "","", new TextField(),""}, 43);
    	ttable.addItem(new Object[]{"Vision",  "80%/86%", "","", new TextField(),""}, 44);
    	ttable.addItem(new Object[]{"Comunications",  "76%/90%", "","", new TextField(),""}, 45);
    	
    	ttable.setParent(41, 40);
    	ttable.setParent(42, 40);
    	ttable.setParent(43, 40);
    	ttable.setParent(44, 40);
    	ttable.setParent(45, 40);
    	
    	ttable.addItem(new Object[]{"Socially Responsible Dimensions",  "24%/83%", "","", new TextField(),""}, 46);
    	ttable.setParent(46, 9);
    	ttable.addItem(new Object[]{"Environment Friendly",  "34%/82%", "","", new TextField(),""}, 47);
    	ttable.addItem(new Object[]{"Energy Savings",  "24%/83%", "","", new TextField(),""}, 48);
    	ttable.addItem(new Object[]{"Comunity Engagement",  "36%/82%", "","", new TextField(),""}, 49);
    	ttable.addItem(new Object[]{"Country Engagement",  "34%/82%", "","", new TextField(),""}, 50);
    	ttable.addItem(new Object[]{"Inclusion",  "36%/83%", "","", new TextField(),""}, 51);
    	ttable.addItem(new Object[]{"Good Causes",  "31%/82%", "","", new TextField(),""}, 52);
    	
    	ttable.setParent(47, 46);
    	ttable.setParent(48, 46);
    	ttable.setParent(49, 46);
    	ttable.setParent(50, 46);
    	ttable.setParent(51, 46);
    	ttable.setParent(52, 46);
    	
		Table respondenteTable = new Table();
		respondenteTable.setWidth("100%");
		respondenteTable.addContainerProperty("Name", String.class, null);
		respondenteTable.addContainerProperty("Knowledge", String.class, null);
		respondenteTable.addContainerProperty("Relevance", String.class, null);
		respondenteTable.addContainerProperty("Personal Regard", String.class, null);
		respondenteTable.addContainerProperty("Diferentiation", String.class, null);
		respondenteTable.addContainerProperty("Net Promoter Score (NPS)", String.class, null);
		respondenteTable.addContainerProperty("Net Effort Score (NES)", String.class, null);
		respondenteTable.setSizeFull();
		
//		respondenteTable.setPageLength(respondenteTable.getItemIds().size());
		
		vl.addComponents(ttable,respondenteTable);
		return vl;
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
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Results / Simulation");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
    
    public void enter(final ViewChangeEvent event) {

    }
}
