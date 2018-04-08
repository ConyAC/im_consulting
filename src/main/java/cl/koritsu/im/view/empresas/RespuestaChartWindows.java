package cl.koritsu.im.view.empresas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BubbleChartConfig;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.BubbleDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import cl.koritsu.im.data.dummy.DummyDataGenerator;

public class RespuestaChartWindows extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7102301543156005083L;

	VerticalLayout root = new VerticalLayout();

	public enum MODELO {
		REPUTACION, RIESGO, IMPORTANCIA
	}

	public RespuestaChartWindows(MODELO modelo) {
		super();

		setModal(true);
		center();
		setWidth("60%");
		setHeight("60%");

		setCaption("Graph");

		root.setSpacing(true);
		root.setMargin(true);

		setContent(new Panel(root) {
			{
				setSizeFull();
			}
		});

		ChartJs chart = null;

		switch (modelo) {
		case IMPORTANCIA:
			Table table = buildTableImportancia();
			root.addComponent(table);
			chart = buildChartImportancia();
			break;
		case REPUTACION:
			chart = buildChartReputacion();
			break;
		case RIESGO:
			chart = buildChartRiesgo();
			break;
		}

		root.addComponent(chart);
		root.setComponentAlignment(chart, Alignment.TOP_CENTER);
	}

	private Table buildTableImportancia() {
		Table table = new Table();
		table.setWidth("100%");

		table.addContainerProperty("Stakeholder", String.class, null);
		table.addContainerProperty("% Respondent", String.class, null);
		table.addContainerProperty("% Weighted", String.class, null);

		List<String> list1 = DummyDataGenerator.getStakeholderPorcentajeImportanciaString();
		List<String> list2 = DummyDataGenerator.getStakeholderPonderadoImportanciaString();
		int index = 0;
		for (String stakeholder : DummyDataGenerator.getStakeHolderUS()) {
			table.addItem(new Object[] { stakeholder, list1.get(index), list2.get(index) }, index);
			index++;
		}

		table.setPageLength(table.getItemIds().size());

		table.setFooterVisible(true);
		table.setColumnFooter("Stakeholder", "Total");
		table.setColumnFooter("% Respondent", "100%");
		table.setColumnFooter("% Weighted", "100%");

		return table;
	}

	private ChartJs buildChartRiesgo() {
		BubbleChartConfig config = new BubbleChartConfig();
		for (String string : DummyDataGenerator.getStakeHolderUS()) {
			config.data()
			.addDataset(new BubbleDataset().label(string))
			.and();
		}
		config.options().responsive(true).title()
				.display(true).text("G2 Stak").and().done();
		
		List<Double> riesgos = Arrays.asList(1d);
		List<Double> irc = Arrays.asList(1d);

		int index = 0;
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			BubbleDataset lds = (BubbleDataset) ds;
			lds.backgroundColor(ColorUtils.randomColor(.7));
				lds.addData(irc.get(index), riesgos.get(index), 1d);
			index++;
		}

		config.options().legend().labels().fontColor("#fff");

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);

		chart.setWidth("60%");
		return chart;
	}

	private ChartJs buildChartReputacion() {
		BubbleChartConfig config = new BubbleChartConfig();
		for (String string : DummyDataGenerator.getStakeHolderUS()) {
			config.data()
			.addDataset(new BubbleDataset().label(string))
			.and();
		}
		config.options().responsive(true).title()
				.display(true).text("G1 Stak").and().done();
		
		List<Double> riesgos = Arrays.asList(1d);
		List<Double> irc = Arrays.asList(1d);

		int index = 0;
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			BubbleDataset lds = (BubbleDataset) ds;
			lds.backgroundColor(ColorUtils.randomColor(.7));
				lds.addData(riesgos.get(index), irc.get(index), 1d);
			index++;
		}

		config.options().legend().labels().fontColor("#fff");

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);

		chart.setWidth("60%");
		return chart;
	}

	private ChartJs buildChartImportancia() {
		PieChartConfig config = new PieChartConfig();
		config.data()
				.labels(DummyDataGenerator.getStakeHolderUS()
						.toArray(new String[DummyDataGenerator.getStakeHolderUS().size()]))
				.addDataset(new PieDataset().label("Stakeholder Importance")).and();

		config.options().responsive(true).title().display(true).text("Stakeholder Importance").fontColor("#fff").and()
				.animation()
				// .animateScale(true)
				.animateRotate(true).and().done();

		List<String> labels = config.data().getLabels();
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			PieDataset lds = (PieDataset) ds;
			List<Double> data = DummyDataGenerator.getStakeholderPonderadoImportanciaDouble();
			List<String> colors = new ArrayList<>();
			for (int i = 0; i < labels.size(); i++) {
				colors.add(ColorUtils.randomColor(0.7));
			}
			lds.backgroundColor(colors.toArray(new String[colors.size()]));
			lds.dataAsList(data);
		}

		config.options().legend().labels().fontColor("#fff");

		ChartJs chart = new ChartJs(config);
		chart.setWidth("60%");
		chart.setJsLoggingEnabled(true);
		return chart;
	}

}
