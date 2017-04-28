package br.com.unipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.com.unipe.dao.FaturamentoDAO;
import br.com.unipe.entidade.Faturamento;

import com.mysql.fabric.xmlrpc.base.Data;
/**
 * 
 * @author Alysson Alves.
 * 
 * @version 1.0.
 *
 */

@ManagedBean(name = "chartsBeans")
@ViewScoped
public class ChartsBeans implements Serializable {

	private LineChartModel graficoLinha;
	private BarChartModel graficoBarra;
	private HorizontalBarChartModel graficoBarraHorizontal;
	private CartesianChartModel graficoCartesiano;
	private MeterGaugeChartModel graficoBalanca;
	private PieChartModel graficoPizza;
	
	private LineChartModel graficoLinhaFat;
	
	@ManagedProperty(value = "#{faturamentoDAO}")
	private FaturamentoDAO faturamentoDAO;

	@PostConstruct
	public void init() {

		criarGraficoLinha();
		criarGraficoBarra();
		criarGraficoBarraHorizontal();
		criarGraficoMedia();
		createMeterGaugeModels();
		graficoPizza();
		
		createGraficoFat();

	}
	
	private void createGraficoFat() {

		ArrayList<Faturamento> lista_faturamento = faturamentoDAO.consulta_faturamento();
		
		LineChartModel model = new LineChartModel();

		ChartSeries faturamento = new ChartSeries();
		faturamento.setLabel("Faturamento");
		
		String data;
		
		for(Faturamento fat : lista_faturamento){
			data = fat.getData();
			faturamento.set(data, fat.getValor());
		}

		model.addSeries(faturamento);

		graficoLinhaFat = model;
		graficoLinhaFat.setTitle("Evolução do Faturamento");
		graficoLinhaFat.setLegendPosition("ne");
		graficoLinhaFat.setAnimate(true);
		graficoLinhaFat.setShowPointLabels(false);
		graficoLinhaFat.getAxes().put(AxisType.X, new CategoryAxis("Ano"));
		graficoLinhaFat.setExtender("customExtender");
		Axis yAxis = graficoLinhaFat.getAxis(AxisType.Y);
		yAxis.setLabel("Valor");
		yAxis.setTickFormat("R$ %'.2f");
	}

	private void criarGraficoLinha() {

		graficoLinha = montaGraficoLinha();
		graficoLinha.setTitle("Homens X Mulheres");
		graficoLinha.setLegendPosition("e");
		graficoLinha.setShowPointLabels(true);
		graficoLinha.getAxes().put(AxisType.X, new CategoryAxis("Anos"));
		Axis yAxis = graficoLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private LineChartModel montaGraficoLinha() {

		LineChartModel model = new LineChartModel();

		ChartSeries homens = new ChartSeries();
		homens.setLabel("Homens");
		homens.set("1970", 45);
		homens.set("1975", 30);
		homens.set("1980", 130);
		homens.set("1985", 90);
		homens.set("1990", 150);

		ChartSeries mulheres = new ChartSeries();
		mulheres.setLabel("Mulheres");
		mulheres.set("1970", 52);
		mulheres.set("1975", 92);
		mulheres.set("1980", 150);
		mulheres.set("1985", 120);
		mulheres.set("1990", 95);

		model.addSeries(homens);
		model.addSeries(mulheres);

		return model;
	}

	private void criarGraficoBarra() {

		graficoBarra = montaGraficoBarra();

		graficoBarra.setTitle("Grafico de Barra");
		graficoBarra.setLegendPosition("ne");

		Axis xAxis = graficoBarra.getAxis(AxisType.X);
		xAxis.setLabel("Anos");

		Axis yAxis = graficoBarra.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private BarChartModel montaGraficoBarra() {

		BarChartModel model = new BarChartModel();

		ChartSeries homens = new ChartSeries();
		homens.setLabel("Homens");
		homens.set("1970", 45);
		homens.set("1975", 30);
		homens.set("1980", 130);
		homens.set("1985", 90);
		homens.set("1990", 150);

		ChartSeries mulheres = new ChartSeries();
		mulheres.setLabel("Mulheres");
		mulheres.set("1970", 52);
		mulheres.set("1975", 92);
		mulheres.set("1980", 150);
		mulheres.set("1985", 120);
		mulheres.set("1990", 95);

		model.addSeries(homens);
		model.addSeries(mulheres);

		return model;
	}

	private void criarGraficoBarraHorizontal() {

		graficoBarraHorizontal = new HorizontalBarChartModel();

		ChartSeries homens = new ChartSeries();
		homens.setLabel("Homens");
		homens.set("1970", 45);
		homens.set("1975", 30);
		homens.set("1980", 130);
		homens.set("1985", 90);
		homens.set("1990", 150);

		ChartSeries mulheres = new ChartSeries();
		mulheres.setLabel("Mulheres");
		mulheres.set("1970", 52);
		mulheres.set("1975", 92);
		mulheres.set("1980", 150);
		mulheres.set("1985", 120);
		mulheres.set("1990", 95);

		graficoBarraHorizontal.addSeries(homens);
		graficoBarraHorizontal.addSeries(mulheres);

		graficoBarraHorizontal.setTitle("Grafico Barra na Horizontal");
		graficoBarraHorizontal.setLegendPosition("e");
		graficoBarraHorizontal.setStacked(true);

		Axis xAxis = graficoBarraHorizontal.getAxis(AxisType.X);
		xAxis.setLabel("Quantidade");
		xAxis.setMin(0);
		xAxis.setMax(300);

		Axis yAxis = graficoBarraHorizontal.getAxis(AxisType.Y);
		yAxis.setLabel("Anos");        
	}

	private void criarGraficoMedia() {
		graficoCartesiano = new BarChartModel();

		BarChartSeries homens = new BarChartSeries();
		homens.setLabel("Boys");

		homens.set("1970", 45);
		homens.set("1975", 30);
		homens.set("1980", 130);
		homens.set("1985", 90);
		homens.set("1990", 150);

		LineChartSeries mulheres = new LineChartSeries();
		mulheres.setLabel("Girls");

		mulheres.set("1970", 52);
		mulheres.set("1975", 92);
		mulheres.set("1980", 150);
		mulheres.set("1985", 120);
		mulheres.set("1990", 95);

		graficoCartesiano.addSeries(homens);
		graficoCartesiano.addSeries(mulheres);

		graficoCartesiano.setTitle("Barra e Linha");
		graficoCartesiano.setLegendPosition("ne");
		graficoCartesiano.setMouseoverHighlight(false);
		graficoCartesiano.setShowDatatip(false);
		graficoCartesiano.setShowPointLabels(true);
		graficoCartesiano.setAnimate(true);
		Axis yAxis = graficoCartesiano.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private void createMeterGaugeModels() {
		graficoBalanca = initMeterGaugeModel();
		graficoBalanca.setTitle("Grafico Balança");
		graficoBalanca.setGaugeLabel("Kg");
		//meterGaugeModel1.setSeriesColors("66cc66,93b75f,E7E658,cc6666");

	}

	private MeterGaugeChartModel initMeterGaugeModel() {
		List<Number> intervals = new ArrayList<Number>(){{
			add(1000);
			add(2000);
			add(3000);
			add(4000);
			add(5000);
		}};

		return new MeterGaugeChartModel(2400, intervals);
	}

	private void graficoPizza() {
		graficoPizza = new PieChartModel();

		graficoPizza.set("João Pessoa", 540);
		graficoPizza.set("Recife", 325);
		graficoPizza.set("Natal", 702);
		graficoPizza.set("Fortaleza", 421);
		
		//graficoPizza.setFill(false); //tira o contorno 
		//graficoPizza.setShowDataLabels(true); // apresenta o percentual
		graficoPizza.setTitle("Grafico Pizza");
		graficoPizza.setLegendPosition("w");
	}


	public LineChartModel getGraficoLinha() {
		return graficoLinha;
	}

	public void setGraficoLinha(LineChartModel graficoLinha) {
		this.graficoLinha = graficoLinha;
	}

	public BarChartModel getGraficoBarra() {
		return graficoBarra;
	}

	public void setGraficoBarra(BarChartModel graficoBarra) {
		this.graficoBarra = graficoBarra;
	}

	public HorizontalBarChartModel getGraficoBarraHorizontal() {
		return graficoBarraHorizontal;
	}

	public void setGraficoBarraHorizontal(
			HorizontalBarChartModel graficoBarraHorizontal) {
		this.graficoBarraHorizontal = graficoBarraHorizontal;
	}

	public CartesianChartModel getGraficoCartesiano() {
		return graficoCartesiano;
	}

	public void setGraficoCartesiano(CartesianChartModel graficoCartesiano) {
		this.graficoCartesiano = graficoCartesiano;
	}

	public MeterGaugeChartModel getGraficoBalanca() {
		return graficoBalanca;
	}

	public void setGraficoBalanca(MeterGaugeChartModel graficoBalanca) {
		this.graficoBalanca = graficoBalanca;
	}

	public PieChartModel getGraficoPizza() {
		return graficoPizza;
	}

	public void setGraficoPizza(PieChartModel graficoPizza) {
		this.graficoPizza = graficoPizza;
	}

	public LineChartModel getGraficoLinhaFat() {
		return graficoLinhaFat;
	}

	public void setGraficoLinhaFat(LineChartModel graficoLinhaFat) {
		this.graficoLinhaFat = graficoLinhaFat;
	}

	public FaturamentoDAO getFaturamentoDAO() {
		return faturamentoDAO;
	}

	public void setFaturamentoDAO(FaturamentoDAO faturamentoDAO) {
		this.faturamentoDAO = faturamentoDAO;
	}

	
	

}
