package cl.koritsu.im.view.empresas;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;
import cl.koritsu.im.data.dummy.DummyDataGenerator;
import cl.koritsu.im.utils.Constants;
import cl.koritsu.im.view.empresas.RespuestaChartWindows.MODELO;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

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
     	
     	GridLayout glRoot = new GridLayout(7,10);
     	glRoot.setSpacing(true);
		glRoot.setMargin(true);
		glRoot.setSizeFull();
		
		/*
		 * se comenta para la presentación
		 */
		//ComboBox cbEstudios = new ComboBox("Surveys");
		//cbEstudios.addItem("Reputation");
		//cbEstudios.addItem("Risk");
		//cbEstudios.addItem("Importance");
		//glRoot.addComponents(cbEstudios);
		
		ComboBox cbStakeholder = new ComboBox("Stakeholder");
		cbStakeholder.addItems(DummyDataGenerator.getStakeHolderUS());
		cbStakeholder.addItems("Total");
		cbStakeholder.select("Total");
		glRoot.addComponents(cbStakeholder);
		
		ComboBox cbSegmento = new ComboBox("Segment");
		cbSegmento.addItems(DummyDataGenerator.getSegmentosUS());
		cbSegmento.addItems("Total");
		cbSegmento.select("Total");
		glRoot.addComponents(cbSegmento);
		
		ComboBox cbSubsegmento = new ComboBox("Subsegment");
		cbSubsegmento.addItems(DummyDataGenerator.getSubsegmentosUS());
		cbSubsegmento.addItems("Total");
		cbSubsegmento.select("Total");
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
		
		glRoot.addComponents(new Button("Calculate",FontAwesome.CALCULATOR) {
			{
				addClickListener(new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						buildCalcular();
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
    	},"Affinity / Weighing Model");
    	
    	VerticalLayout brand = buildBrand();
    	tab.addTab(new Panel(brand) {
    		{
    			setSizeFull();
    		}
    	}, "Brand Asset & Loyalty Model");
    	
		return tab;
	}
    
	private VerticalLayout buildAfinidad() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	
    	ttable.setStyleName("treetable-resultado-afinidad");
    	
    	ttable.setColumnCollapsingAllowed(true);
    	
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
//    	ttable.addContainerProperty("Chart", CheckBox.class, null);
    	ttable.addContainerProperty("2018 Severiy/Weight (Probability)", ProgressBar.class, null);
    	ttable.addContainerProperty("2017 Severiy/Weight (Probability)", String.class, null);
    	ttable.addContainerProperty("2016 Severiy/Weight (Probability)", String.class, null);

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
    	
    	ttable.addItem(new Object[]{"Weighing Score (WS)", null, "", ""}, 5);
    	ttable.addItem(new Object[]{"Scenario 1", getProgressBar(0.45f), "", ""}, 6);
    	ttable.addItem(new Object[]{"Scenario 2", getProgressBar(0.41f), "", ""}, 7);
    	ttable.addItem(new Object[]{"Scenario 3", getProgressBar(0.46f), "", ""}, 8);
    	ttable.addItem(new Object[]{"Average Scenario", getProgressBar(0.49f), "", ""}, 9);
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
		respondenteTable.addContainerProperty("Net Awareness", String.class, null);
		respondenteTable.addContainerProperty("Net Relevance", String.class, null);
		respondenteTable.addContainerProperty("Net Personal Regard", String.class, null);
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
	
	private VerticalLayout buildBrand() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	Table table = new Table();
    	
    	table.setStyleName("treetable-resultado");
    	
    	table.setColumnCollapsingAllowed(true);
    	
    	table.setSizeFull();
    	table.addContainerProperty("Index/Question", String.class, null);
    	table.addContainerProperty("2018 Performance / Important", ProgressBar.class, null);
    	table.addContainerProperty("2017 Performance / Important", String.class, null);
    	table.addContainerProperty("2016 Performance / Important", String.class, null);
    	table.addContainerProperty("Simulate", TextField.class, null);
    	
    	Map<String, ProgressBar> items = new LinkedHashMap<String, ProgressBar>();
    	items.put("Net Awareness", getProgressBar(0.91f));
    	items.put("Net Relevance", getProgressBar(0.83f));
    	items.put("Net Personal Regard", getProgressBar(0.38f));
    	items.put("Net Promoter Score (NPS)", getProgressBar(0.18f));
    	items.put("Net Effort Score (NES)", getProgressBar(0.06f));
    	items.put("Net Propensity to Repurchase", getProgressBar(0.09f));
    	items.put("Differentiation", null);
    	
    	int i = 1;
    	for (Map.Entry<String, ProgressBar> item : items.entrySet())
            table.addItem(new Object[]{item.getKey(), item.getValue(), "","", new TextField()}, i++);
    	
    	vl.addComponents(table);
		return vl;
	}
	
	private ProgressBar getProgressBar(float percent) {
		ProgressBar progressBar = new ProgressBar(percent);
		
		if(percent < Constants.FIRST_RANGE_TOP)
			progressBar.setStyleName("first-range");
		else if(percent >= Constants.FIRST_RANGE_TOP && percent <= Constants.SECOND_RANGE_TOP)
			progressBar.setStyleName("second-range");
		else
			progressBar.setStyleName("third-range");
		
		progressBar.setSizeFull();
		progressBar.setImmediate(true);
		progressBar.setVisible(true);
		
		return progressBar;
	}

	private VerticalLayout buildRiesgos() {
		VerticalLayout vl = new VerticalLayout();
    	vl.setSizeFull();
    	
    	TreeTable ttable = new TreeTable();
    	
    	ttable.setStyleName("treetable-resultado-riesgos");
    	
    	ttable.setColumnCollapsingAllowed(true);
    	
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
//    	ttable.addContainerProperty("Chart", CheckBox.class, null);
    	ttable.addContainerProperty("2018 Severiy", ProgressBar.class, null);
    	ttable.addContainerProperty("2018 Weight (Probability)", ProgressBar.class, null);
    	ttable.addContainerProperty("2017 Severiy", String.class, null);
    	ttable.addContainerProperty("2017 Weight (Probability)", String.class, null);
    	ttable.addContainerProperty("2016 Severiy / Weight (Probability)", String.class, null);
    	ttable.addContainerProperty("2016 Weight (Probability)", String.class, null);
    	ttable.addContainerProperty("Simulate", TextField.class, null);
 //   	ttable.addContainerProperty("Simulated Result", String.class, null);

    	// Create the tree nodes and set the hierarchy
    	ttable.addItem(new Object[]{"Risk Model", null, null, "", "", "", "", new TextField()}, 0);
    	ttable.addItem(new Object[]{"Risk 1", getProgressBar(0.32f), getProgressBar(0.4f), "", "", "", "", new TextField()}, 1);
    	ttable.addItem(new Object[]{"Risk 2", getProgressBar(0.07f), getProgressBar(0.15f), "", "", "", "", new TextField()}, 2);
    	ttable.addItem(new Object[]{"Risk 3", getProgressBar(0.03f), getProgressBar(0.05f), "", "", "", "", new TextField()}, 3);
    	ttable.addItem(new Object[]{"Risk 4", getProgressBar(0.18f), getProgressBar(0.35f), "", "", "", "", new TextField()}, 4);
    	ttable.addItem(new Object[]{"Risk 5", getProgressBar(0.58f), getProgressBar(0.75f), "", "", "", "", new TextField()}, 5);
    	ttable.addItem(new Object[]{"Risk 6", getProgressBar(0.08f), getProgressBar(0.15f), "", "", "", "", new TextField()}, 6);
    	ttable.addItem(new Object[]{"Risk 7", getProgressBar(0.02f), getProgressBar(0.03f), "", "", "", "", new TextField()}, 7);
    	ttable.addItem(new Object[]{"Risk 8", getProgressBar(0.19f), getProgressBar(0.35f), "", "", "", "", new TextField()}, 8);
    	ttable.addItem(new Object[]{"Risk 9", getProgressBar(0.52f), getProgressBar(0.65f), "", "", "", "", new TextField()}, 9);
    	ttable.addItem(new Object[]{"Risk 10", getProgressBar(0.36f), getProgressBar(0.7f), "", "", "", "", new TextField()}, 10);
    	ttable.addItem(new Object[]{"Risk 11", getProgressBar(0.02f), getProgressBar(0.03f), "", "", "", "", new TextField()},11);
    	ttable.addItem(new Object[]{"Risk 12", getProgressBar(0.35f), getProgressBar(0.7f), "", "", "", "", new TextField()}, 12);
    	ttable.addItem(new Object[]{"Risk 13", getProgressBar(0.08f), getProgressBar(0.15f), "", "", "", "", new TextField()},13);
    	ttable.addItem(new Object[]{"Risk 14", getProgressBar(0.05f), getProgressBar(0.1f), "", "", "", "", new TextField()}, 14);
    	ttable.addItem(new Object[]{"Risk 15", getProgressBar(0.13f), getProgressBar(0.25f), "", "", "", "", new TextField()}, 15);
    	
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
		respondenteTable.addContainerProperty("Net Awareness", String.class, null);
		respondenteTable.addContainerProperty("Net Relevance", String.class, null);
		respondenteTable.addContainerProperty("Net Personal Regard", String.class, null);
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
    	
    	ttable.setStyleName("treetable-resultado");
    	
    	ttable.setColumnCollapsingAllowed(true);
		
//    	ttable.setWidth("100%");
    	ttable.setSizeFull();
    	ttable.addContainerProperty("Index/Question", String.class, null);
//    	ttable.addContainerProperty("Chart", CheckBox.class, null);
    	ttable.addContainerProperty("2018 Performance", ProgressBar.class, null);
    	ttable.addContainerProperty("2018 Important", ProgressBar.class, null);
    	ttable.addContainerProperty("2017 Performance", String.class, null);
    	ttable.addContainerProperty("2017 Important", String.class, null);
    	ttable.addContainerProperty("2016 Performance", String.class, null);
    	ttable.addContainerProperty("2016 Important", String.class, null);
    	ttable.addContainerProperty("Simulate", TextField.class, null);
 //   	ttable.addContainerProperty("Simulated Result", String.class, null);
    	
    	ttable.addItem(new Object[]{"Corporate Reputation Index (CRI)",  null, null, "", "", "", "", new TextField()}, 4);
    	
    	ttable.addItem(new Object[]{"Emotional Dimensions", getProgressBar(0.16f), getProgressBar(0.5f), "", "", "", "", new TextField()}, 5);
    	ttable.setParent(5, 4);
    	ttable.addItem(new Object[]{"Esteem",  getProgressBar(0.28f), getProgressBar(0.2f), "","","","", new TextField()}, 6);
    	ttable.addItem(new Object[]{"Trust",  getProgressBar(0.21f), getProgressBar(0.2f), "","","","", new TextField()}, 7);
    	ttable.addItem(new Object[]{"Admiration",  getProgressBar(0.22f), getProgressBar(0.2f), "","","","", new TextField()}, 8);
    	ttable.addItem(new Object[]{"Identification",  getProgressBar(0.24f), getProgressBar(0.2f), "","","","", new TextField()}, 13);
    	ttable.addItem(new Object[]{"Empathy", getProgressBar(0.25f), getProgressBar(0.2f), "","","","", new TextField()}, 14);

    	ttable.setParent(6, 5);
    	ttable.setParent(7, 5);
    	ttable.setParent(8, 5);
    	ttable.setParent(13, 5);
    	ttable.setParent(14, 5);
    	
    	ttable.addItem(new Object[]{"Rational Dimensions", getProgressBar(0.61f), getProgressBar(0.17f), "","","","", new TextField()}, 9);    	
    	ttable.setParent(9, 4);
     	ttable.addItem(new Object[]{"Economic Dimensions",  getProgressBar(0.92f), getProgressBar(0.1f), "","","","", new TextField()}, 16);
     	ttable.setParent(16, 9);   
    	ttable.addItem(new Object[]{"Profitability",  getProgressBar(0.91f), getProgressBar(0.25f), "","","","", new TextField()}, 17);
    	ttable.addItem(new Object[]{"Solvency",  getProgressBar(0.91f), getProgressBar(0.25f), "","","","", new TextField()}, 18);
    	ttable.addItem(new Object[]{"Growth",  getProgressBar(0.94f), getProgressBar(0.25f), "","","","", new TextField()}, 19);
    	ttable.addItem(new Object[]{"Market Cap", getProgressBar(0.93f), getProgressBar(0.26f), "","","","", new TextField()}, 20);
    	
       	ttable.setParent(17, 16);
    	ttable.setParent(18, 16);
    	ttable.setParent(19, 16);
    	ttable.setParent(20, 16);    	
    	
    	ttable.addItem(new Object[]{"Service Dimensions", getProgressBar(0.29f), getProgressBar(0.18f), "","","","", new TextField()}, 21);
    	ttable.setParent(21, 9); 
    	ttable.addItem(new Object[]{"Quality", getProgressBar(0.29f), getProgressBar(0.2f), "","","","", new TextField()}, 22);
    	ttable.addItem(new Object[]{"Price",  getProgressBar(0.36f), getProgressBar(0.2f), "","","","", new TextField()}, 23);
    	ttable.addItem(new Object[]{"Customer Service", getProgressBar(0.3f), getProgressBar(0.2f), "","","","", new TextField()}, 24);
    	ttable.addItem(new Object[]{"Custormer Satisfaction", getProgressBar(0.32f), getProgressBar(0.2f), "","","","", new TextField()}, 25);
    	ttable.addItem(new Object[]{"Innovation", getProgressBar(0.24f), getProgressBar(0.2f), "","","","", new TextField()}, 26);
    	
    	ttable.setParent(22, 21);
    	ttable.setParent(23, 21);
    	ttable.setParent(24, 21);
    	ttable.setParent(25, 21);
    	ttable.setParent(26, 21);
    	
    	
    	ttable.addItem(new Object[]{"People Dimensions", getProgressBar(0.58f), getProgressBar(0.17f), "","","","", new TextField()}, 27);
    	ttable.setParent(27, 9); 
    	ttable.addItem(new Object[]{"Talent", getProgressBar(0.56f), getProgressBar(0.17f), "","","","", new TextField()}, 28);
    	ttable.addItem(new Object[]{"Job stability", getProgressBar(0.56f), getProgressBar(0.17f), "","","","", new TextField()}, 29);
    	ttable.addItem(new Object[]{"Wellness", getProgressBar(0.61f), getProgressBar(0.17f), "","","","", new TextField()}, 30);
    	ttable.addItem(new Object[]{"Personal incentives and compensation", getProgressBar(0.58f), getProgressBar(0.17f), "","","","", new TextField()}, 31);
    	ttable.addItem(new Object[]{"Equality", getProgressBar(0.64f), getProgressBar(0.17f), "","","","", new TextField()}, 32);
    	ttable.addItem(new Object[]{"Meritocracy", getProgressBar(0.59f), getProgressBar(0.17f), "","","","", new TextField()}, 33);
    	
    	ttable.setParent(28, 27);
    	ttable.setParent(29, 27);
    	ttable.setParent(30, 27);
    	ttable.setParent(31, 27);
    	ttable.setParent(32, 27);
    	ttable.setParent(33, 27);
    	
    	ttable.addItem(new Object[]{"Gobernance Dimensions", getProgressBar(0.84f), getProgressBar(0.19f), "","","","", new TextField()}, 34);
    	ttable.setParent(34, 9);
    	ttable.addItem(new Object[]{"Supplier Quality", getProgressBar(0.9f), getProgressBar(0.18f), "","","","", new TextField()}, 35);
    	ttable.addItem(new Object[]{"Transparency", getProgressBar(0.76f), getProgressBar(0.2f), "","","","", new TextField()}, 36);
    	ttable.addItem(new Object[]{"Ethics", getProgressBar(0.84f), getProgressBar(0.2f), "","","","", new TextField()}, 37);
    	ttable.addItem(new Object[]{"Antibribery", getProgressBar(0.8f), getProgressBar(0.2f), "","","","", new TextField()}, 38);
    	ttable.addItem(new Object[]{"Respect", getProgressBar(0.8f), getProgressBar(0.21f), "","","","", new TextField()}, 39);
    	
    	ttable.setParent(35, 34);
    	ttable.setParent(36, 34);
    	ttable.setParent(37, 34);
    	ttable.setParent(38, 34);
    	ttable.setParent(39, 34);
    	
    	ttable.addItem(new Object[]{"Leadership Dimension", getProgressBar(0.79f), getProgressBar(0.18f), "","","","", new TextField()}, 40);
    	ttable.setParent(40, 9);
    	ttable.addItem(new Object[]{"Leadership", getProgressBar(0.8f), getProgressBar(0.2f), "","","","", new TextField()},41);
    	ttable.addItem(new Object[]{"Faireness", getProgressBar(0.79f), getProgressBar(0.2f), "","","","", new TextField()}, 42);
    	ttable.addItem(new Object[]{"Management", getProgressBar(0.71f), getProgressBar(0.2f), "","","","", new TextField()}, 43);
    	ttable.addItem(new Object[]{"Vision", getProgressBar(0.78f), getProgressBar(0.2f), "","","","", new TextField()}, 44);
    	ttable.addItem(new Object[]{"Comunications", getProgressBar(0.77f), getProgressBar(0.21f), "","","","", new TextField()}, 45);
    	
    	ttable.setParent(41, 40);
    	ttable.setParent(42, 40);
    	ttable.setParent(43, 40);
    	ttable.setParent(44, 40);
    	ttable.setParent(45, 40);
    	
    	ttable.addItem(new Object[]{"Social Responsability Dimensions",  getProgressBar(0.22f), getProgressBar(0.18f), "","","","", new TextField()}, 46);
    	ttable.setParent(46, 9);
    	ttable.addItem(new Object[]{"Environment Friendly", getProgressBar(0.33f), getProgressBar(0.17f), "","","","", new TextField()}, 47);
    	ttable.addItem(new Object[]{"Energy Savings", getProgressBar(0.26f), getProgressBar(0.17f), "","","","", new TextField()}, 48);
    	ttable.addItem(new Object[]{"Comunity Engagement", getProgressBar(0.35f), getProgressBar(0.17f), "","","","", new TextField()}, 49);
    	ttable.addItem(new Object[]{"Country Engagement", getProgressBar(0.33f), getProgressBar(0.17f), "","","","", new TextField()}, 50);
    	ttable.addItem(new Object[]{"Inclusion", getProgressBar(0.36f), getProgressBar(0.17f), "","","","", new TextField()}, 51);
    	ttable.addItem(new Object[]{"Good Causes", getProgressBar(0.3f), getProgressBar(0.17f), "","","","", new TextField()}, 52);
    	
    	ttable.setParent(47, 46);
    	ttable.setParent(48, 46);
    	ttable.setParent(49, 46);
    	ttable.setParent(50, 46);
    	ttable.setParent(51, 46);
    	ttable.setParent(52, 46);
    	
		Table respondenteTable = new Table();
		respondenteTable.setWidth("100%");
		respondenteTable.addContainerProperty("Name", String.class, null);
		respondenteTable.addContainerProperty("Net Awareness", String.class, null);
		respondenteTable.addContainerProperty("Net Relevance", String.class, null);
		respondenteTable.addContainerProperty("Net Personal Regard", String.class, null);
		respondenteTable.addContainerProperty("Diferentiation", String.class, null);
		respondenteTable.addContainerProperty("Net Promoter Score (NPS)", String.class, null);
		respondenteTable.addContainerProperty("Net Effort Score (NES)", String.class, null);
		respondenteTable.setSizeFull();
		
//		respondenteTable.setPageLength(respondenteTable.getItemIds().size());
		
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
        
        Label title = new Label("COEVOLUTION IM CONSULTING > Results / Simulation");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        return header;
    }
    
	private Window buildCalcular(){    
    	final Window window = new Window("Simulated Result");
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		vl.setSpacing(true);
		
		window.setModal(true);
		window.setResizable(false);
		window.setWidth("450px");
		window.setHeight("500px");
		window.center();
		
		Table table = new Table();
		table.setWidth("100%");
		table.addContainerProperty("Citizens",  String.class, null);
		table.addContainerProperty("Actual", String.class, null);
		table.addContainerProperty("Simulated", String.class, null);
    	
    	table.addItem(new Object[]{"IRE", "8%", "16%"}, 1);
    	table.addItem(new Object[]{"IRR", "8%", "9%"}, 2);
    	table.addItem(new Object[]{"CRI", "8%", "13%"}, 3);
    	table.setPageLength(table.size());
    	
    	Table table2 = new Table();
    	table2.setWidth("100%");
		table2.addContainerProperty("General/Total",  String.class, null);
		table2.addContainerProperty("Actual", String.class, null);
		table2.addContainerProperty("Simulated", String.class, null);
    	
    	table2.addItem(new Object[]{"IRE", "24%","34%"}, 1);
    	table2.addItem(new Object[]{"IRR", "60%","61%"}, 2);
    	table2.addItem(new Object[]{"CRI", "42%","45%"}, 3);
    	table2.setPageLength(table2.size());

		vl.addComponent(table);
		vl.addComponent(table2);
		
		Button btnCerrar = new Button("Close",FontAwesome.CLOSE);
		btnCerrar.setDescription("Close");
		btnCerrar.addClickListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().removeWindow(window);
			}
		});
		
		vl.addComponent(btnCerrar);
		vl.setComponentAlignment(btnCerrar, Alignment.BOTTOM_RIGHT);
		
		window.setContent(vl);
		
		UI.getCurrent().addWindow(window);
		
		return window;
    }
	
	public void enter(final ViewChangeEvent event) {

    }
}
