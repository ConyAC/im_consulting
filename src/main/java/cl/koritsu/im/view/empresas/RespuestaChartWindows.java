package cl.koritsu.im.view.empresas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.koritsu.im.data.dummy.DummyDataGenerator;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BubbleChartConfig;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.BubbleDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import com.byteowls.vaadin.chartjs.options.zoom.XYMode;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import cl.koritsu.im.data.dummy.DummyDataGenerator;
import cl.koritsu.im.utils.Constants;

public class RespuestaChartWindows extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7102301543156005083L;

	VerticalLayout root = new VerticalLayout();

	public enum MODELO {
		REPUTACION, RIESGO, IMPORTANCIA, RESUMEN
	}
	
	public enum STAKEHOLDER {
		CITIZENS, PRIORITYCUSTOMER, OTHERS
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
		case RESUMEN:
			chart = buildChartResumen();
			break;
		}

		root.addComponent(chart);
		root.setComponentAlignment(chart, Alignment.TOP_CENTER);
	}

	private ChartJs buildChartResumen() {
		BubbleChartConfig config = new BubbleChartConfig();
		for (String string : DummyDataGenerator.getSegmentosResumenUS()) {
			config.data()
			.addDataset(new BubbleDataset().label(string))
			.and();
		}
		config.options().responsive(true).title().fontColor("#000000")
				.display(true).text("Affinity vs CRI Weighted")
				.and()
				.zoom().mode(XYMode.XY)
				.and()
				.scales().add(Axis.Y, 
						new DefaultScale()
								.display(true)
								.ticks()
									.display(true)
									.fontColor("#000000")
									.and()
								.scaleLabel()
									.display(true)
									.labelString("Affinity")
									.fontColor("#000000")
							.and())
				.add(Axis.X, 
						new DefaultScale()
								.display(true)
								.ticks()
									.display(true)
									.fontColor("#000000")
									.and()
								.scaleLabel()
									.display(true)
									.labelString("CRI Weighted")
									.fontColor("#000000")
							.and())
				.and()
				.pan().enabled(true)
				.and()
				.done();
		
		List<Double> emocional = Arrays.asList(
				77d,
				61d,
				75d,
				8d,
				37d,
				4d,
				12d,
				8d,
				92d,
				28d,
				39d,
				35d,
				69d,				
				5d,
				96d,
				85d,
				63d,
				9d,
				15d,
				34d,
				20d,
				7d,
				99d,
				26d,
				47d,
				78d,
				39d,
				10d,
				45d,
				57d,
				85d,
				85d,
				80d
				);
		List<Double> racional = Arrays.asList(
				83d,
				60d,
				83d,
				63d,
				54d,
				96d,
				43d,
				8d,
				93d,
				55d,
				60d,
				55d,
				60d,				
				9d,
				32d,
				63d,
				78d,
				10d,
				80d,
				91d,
				38d,
				45d,
				61d,
				20d,
				70d,
				65d,
				45d,
				16d,
				64d,
				51d,
				36d,
				78d,
				90d);

		int index = 0;
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			BubbleDataset lds = (BubbleDataset) ds;
			lds.backgroundColor(ColorUtils.randomColor(.7));
			lds.addData(emocional.get(index), racional.get(index), 8d);
			index++;
		}

		config.options().legend().position(Position.RIGHT).labels().fontColor("#000000");

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);

		chart.setWidth("80%");
		chart.setHeight("90%");
		return chart;
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
			.addDataset(new BubbleDataset().label(string));
		}
		
		config.options()
				.responsive(true)
				.title().fontColor(Constants.CHART_FONT_COLOR).display(true).text("Corporate Reputation Index (CRI) vs Risk Index (RI)")
				.and()
				.zoom().mode(XYMode.XY)
				.and()
				.scales().add(Axis.Y, 
						new DefaultScale()
								.display(true)
								.ticks()
									.display(true)
									.fontColor(Constants.CHART_FONT_COLOR)
									.and()
								.scaleLabel()
									.display(true)
									.labelString("Risk Index (RI)")
									.fontColor(Constants.CHART_FONT_COLOR)
							.and())
				.add(Axis.X, 
						new DefaultScale()
								.display(true)
								.ticks()
									.display(true)
									.fontColor(Constants.CHART_FONT_COLOR)
									.and()
								.scaleLabel()
									.display(true)
									.labelString("Corporate Reputation Index (CRI)")
									.fontColor(Constants.CHART_FONT_COLOR)
							.and())
				.and()
				.pan().enabled(true)
				.and()
				.done();
		
		List<Double> riesgos = Arrays.asList(		
		19d,
		21d,
		19d,
		19d,
		20d,
		19d,
		20d,
		19d,
		20d,
		24d,
		19d,
		28d,
		19d);

		List<Double> irc = Arrays.asList(
				80d,
				60d,
				79d,
				35d,
				45d,
				49d,
				27d,
				8d,
				93d,
				41d,
				49d,
				45d,
				64d);

		int index = 0;
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			BubbleDataset lds = (BubbleDataset) ds;
			lds.backgroundColor(ColorUtils.randomColor(.7));
			lds.addData(irc.get(index), riesgos.get(index), 8d);
			index++;
		}

		config.options().legend().position(Position.RIGHT).labels().fontColor(Constants.CHART_FONT_COLOR);

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);

		chart.setWidth("80%");
		chart.setHeight("90%");
		return chart;
	}

	private ChartJs buildChartReputacion() {
		BubbleChartConfig config = new BubbleChartConfig();
		for (String string : DummyDataGenerator.getStakeHolderUS()) {
			config.data()
			.addDataset(new BubbleDataset().label(string))
			.and();
		}
		config.options().responsive(true).title().fontColor(Constants.CHART_FONT_COLOR)
				.display(true).text("RCI: Emotional vs Rational Dimensions")
				.and()
				.zoom().mode(XYMode.XY)
				.and()
				.scales().add(Axis.Y, 
						new DefaultScale()
								.display(true)
								.ticks()
									.display(true)
									.fontColor(Constants.CHART_FONT_COLOR)
									.and()
								.scaleLabel()
									.display(true)
									.labelString("Rational Dimensions")
									.fontColor(Constants.CHART_FONT_COLOR)
							.and())
				.add(Axis.X, 
						new DefaultScale()
								.display(true)
								.ticks()
									.display(true)
									.fontColor(Constants.CHART_FONT_COLOR)
									.and()
								.scaleLabel()
									.display(true)
									.labelString("Emotional Dimensions")
									.fontColor(Constants.CHART_FONT_COLOR)
							.and())
				.and()
				.pan().enabled(true)
				.and()
				.done();
		
		List<Double> emocional = Arrays.asList(
				77d,
				61d,
				75d,
				8d,
				37d,
				4d,
				12d,
				8d,
				92d,
				28d,
				39d,
				35d,
				69d);
		List<Double> racional = Arrays.asList(
				83d,
				60d,
				83d,
				63d,
				54d,
				96d,
				43d,
				8d,
				93d,
				55d,
				60d,
				55d,
				60d);

		int index = 0;
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			BubbleDataset lds = (BubbleDataset) ds;
			lds.backgroundColor(ColorUtils.randomColor(.7));
			lds.addData(emocional.get(index), racional.get(index), 8d);
			index++;
		}

		config.options().legend().position(Position.RIGHT).labels().fontColor(Constants.CHART_FONT_COLOR);

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);

		chart.setWidth("80%");
		chart.setHeight("90%");
		return chart;
	}

	private ChartJs buildChartImportancia() {
		PieChartConfig config = new PieChartConfig();
		config.data()
				.labels(DummyDataGenerator.getStakeHolderUS()
						.toArray(new String[DummyDataGenerator.getStakeHolderUS().size()]))
				.addDataset(new PieDataset().label("Respondents weight")).and();

		config.options().responsive(true).title().display(true).text("Respondents weight").fontColor(Constants.CHART_FONT_COLOR).and()
				.animation()
				// .animateScale(true)
				.animateRotate(true).and().done();

		List<String> labels = config.data().getLabels();
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			PieDataset lds = (PieDataset) ds;
			List<Double> data = DummyDataGenerator.getStakeholderPonderadoImportanciaDouble();
			List<String> colors = new ArrayList<String>();
			for (int i = 0; i < labels.size(); i++) {
				colors.add(ColorUtils.randomColor(0.7));
			}
			lds.backgroundColor(colors.toArray(new String[colors.size()]));
			lds.dataAsList(data);
		}

		config.options().legend().position(Position.RIGHT).labels().fontColor(Constants.CHART_FONT_COLOR);

		ChartJs chart = new ChartJs(config);
		chart.setWidth("60%");
		chart.setJsLoggingEnabled(true);
		return chart;
	}

}
