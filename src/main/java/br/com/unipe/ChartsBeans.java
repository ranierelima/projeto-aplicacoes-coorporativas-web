package br.com.unipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

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
import br.com.unipe.dao.GraficosDAO;
import br.com.unipe.entidade.Faturamento;
import br.com.unipe.entidade.ModelGrafico;

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
	private BarChartModel graficoBalanca;
	private PieChartModel graficoPizza;
	private PieChartModel graficoVendasDosEstados;

	private BarChartModel graficoLinhaFat;

	@ManagedProperty(value = "#{faturamentoDAO}")
	private FaturamentoDAO faturamentoDAO;

	@ManagedProperty(value = "#{graficosDAO}")
	private GraficosDAO graficosDAODB;

	@PostConstruct
	public void init() {

		// criarGraficoLinha();
		criarGraficoVendasDosEstados();
		criarGraficoBarra();
		criarGraficoBarraHorizontal();
		criarGraficoMedia();
		createMeterGaugeModels();
		criarGraficoPizza();
		createGraficoFat();

	}

	private void createGraficoFat() {

		

//		List<ModelGrafico> diasComMaioresVendas = graficosDAODB.diasComMaioresVendas();
//		BarChartModel model = new BarChartModel();
//
//		for (ModelGrafico mg : diasComMaioresVendas) {
//
//			ChartSeries dias = new ChartSeries();
//			dias.setLabel(mg.getChave());
//			dias.set(mg.getChave(), (Number) mg.getValor());
//			model.addSeries(dias);
//		}
//
//		return model;

		graficoLinhaFat = montaGraficoFat();
		graficoLinhaFat.setTitle("Clientes que mais compraram (Em Reais)");
		graficoLinhaFat.setLegendPosition("ne");
		graficoLinhaFat.setAnimate(true);
		graficoLinhaFat.setShowPointLabels(false);
		

		Axis xAxis = graficoLinhaFat.getAxis(AxisType.X);
		xAxis.setLabel("Ano");

		Axis yAxis = graficoLinhaFat.getAxis(AxisType.Y);
		yAxis.setLabel("Valor");
		yAxis.setTickFormat("R$ %'.2f");
		
//		graficoLinhaFat.getAxes().put(AxisType.X, new CategoryAxis("Ano"));
//		graficoLinhaFat.setExtender("customExtender");
//		Axis yAxis = graficoLinhaFat.getAxis(AxisType.Y);
//		yAxis.setLabel("Valor");
	}

	private BarChartModel montaGraficoFat() {
		List<ModelGrafico> clientes = graficosDAODB.clienteQueMaisCompraramReais();

		BarChartModel model = new BarChartModel();


		for (ModelGrafico c : clientes) {
			ChartSeries faturamento = new ChartSeries();
			faturamento.setLabel(c.getChave());
			faturamento.set(c.getChave(), (Number) c.getValor());
			model.addSeries(faturamento);
		}
		return model;
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
	}

	private LineChartModel montaGraficoLinha() {

		List<ModelGrafico> vendasDosEstados = graficosDAODB.vendasDosEstados();

		LineChartModel model = new LineChartModel();

		ChartSeries cs = null;
		for (ModelGrafico mg : vendasDosEstados) {
			cs = new ChartSeries();
			cs.set(mg.getChave(), (Number) mg.getValor());
			model.addSeries(cs);
		}

		return model;
	}

	private void criarGraficoBarra() {

		graficoBarra = montaGraficoBarra();

		graficoBarra.setTitle("Grafico dias com mais vendas");
		graficoBarra.setLegendPosition("ne");

		Axis xAxis = graficoBarra.getAxis(AxisType.X);
		xAxis.setLabel("Dias");

		Axis yAxis = graficoBarra.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setTickFormat("R$ %'.2f");
		yAxis.setMin(0);
	}

	private BarChartModel montaGraficoBarra() {

		List<ModelGrafico> diasComMaioresVendas = graficosDAODB.diasComMaioresVendas();
		BarChartModel model = new BarChartModel();

		for (ModelGrafico mg : diasComMaioresVendas) {

			ChartSeries dias = new ChartSeries();
			dias.setLabel(mg.getChave());
			dias.set(mg.getChave(), (Number) mg.getValor());
			model.addSeries(dias);
		}

		return model;
	}

	private BarChartModel montaGraficoBarraProdutos() {

		List<ModelGrafico> diasComMaioresVendas = graficosDAODB.produtosMaisVendidos();
		BarChartModel model = new BarChartModel();


		for (ModelGrafico mg : diasComMaioresVendas) {

			BarChartSeries produtos = new BarChartSeries();
			produtos.setLabel(mg.getChave());
			produtos.set(mg.getChave(), (Number) mg.getValor());
			model.addSeries(produtos);
		}

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

		graficoCartesiano = montaGraficoBarraProdutos();

		graficoCartesiano.setTitle("Produtos que mais venderam");
		graficoCartesiano.setLegendPosition("ne");
		graficoCartesiano.setMouseoverHighlight(false);
		graficoCartesiano.setShowDatatip(false);
		graficoCartesiano.setAnimate(true);

		Axis yAxis = graficoCartesiano.getAxis(AxisType.Y);
		yAxis.setMin(0);
	}

	private void createMeterGaugeModels() {
		graficoBalanca = initMeterGaugeModel();
		graficoBalanca.setTitle("Quantidade de clientes que mais compraram");
		graficoBalanca.setLegendPosition("ne");
		// meterGaugeModel1.setSeriesColors("66cc66,93b75f,E7E658,cc6666");

	}

	private BarChartModel initMeterGaugeModel() {

		List<ModelGrafico> diasComMaioresVendas = graficosDAODB.clienteQueMaisCompraram();
		BarChartModel model = new BarChartModel();

		for (ModelGrafico mg : diasComMaioresVendas) {
			BarChartSeries produtos = new BarChartSeries();
			produtos.setLabel(mg.getChave());
			produtos.set(mg.getChave(), (Number) mg.getValor());
			model.addSeries(produtos);
		}

		return model;
	}

	private void criarGraficoVendasDosEstados() {
		graficoVendasDosEstados = new PieChartModel();
		List<ModelGrafico> vendasDosEstados = graficosDAODB.vendasDosEstados();

		for (ModelGrafico mg : vendasDosEstados) {
			graficoVendasDosEstados.set(mg.getChave(), (Number) mg.getValor());
		}

		graficoVendasDosEstados.setShowDataLabels(true); // apresenta o percentual
		graficoVendasDosEstados.setTitle("Grafico estados que mais venderam");
		graficoVendasDosEstados.setLegendPosition("w");
	}

	private void criarGraficoPizza() {
		
		graficoPizza = new PieChartModel();
		List<ModelGrafico> vendaDasCidadesPorEstado = graficosDAODB.vendaDasCidadesPorEstado("PB");

		for(ModelGrafico mg : vendaDasCidadesPorEstado){
			graficoPizza.set(mg.getChave(), (Number) mg.getValor());
		}
		
		// graficoPizza.setFill(false); //tira o contorno
		graficoPizza.setShowDataLabels(true); // apresenta o percentual
		graficoPizza.setTitle("Vendas por Estado: Paraiba");
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

	public void setGraficoBarraHorizontal(HorizontalBarChartModel graficoBarraHorizontal) {
		this.graficoBarraHorizontal = graficoBarraHorizontal;
	}

	public CartesianChartModel getGraficoCartesiano() {
		return graficoCartesiano;
	}

	public void setGraficoCartesiano(CartesianChartModel graficoCartesiano) {
		this.graficoCartesiano = graficoCartesiano;
	}

	public BarChartModel getGraficoBalanca() {
		return graficoBalanca;
	}

	public void setGraficoBalanca(BarChartModel graficoBalanca) {
		this.graficoBalanca = graficoBalanca;
	}

	public PieChartModel getGraficoPizza() {
		return graficoPizza;
	}

	public void setGraficoPizza(PieChartModel graficoPizza) {
		this.graficoPizza = graficoPizza;
	}

	public BarChartModel getGraficoLinhaFat() {
		return graficoLinhaFat;
	}

	public PieChartModel getGraficoVendasDosEstados() {
		return graficoVendasDosEstados;
	}

	public void setGraficoVendasDosEstados(PieChartModel graficoVendasDosEstados) {
		this.graficoVendasDosEstados = graficoVendasDosEstados;
	}

	public void setGraficoLinhaFat(BarChartModel graficoLinhaFat) {
		this.graficoLinhaFat = graficoLinhaFat;
	}

	public FaturamentoDAO getFaturamentoDAO() {
		return faturamentoDAO;
	}

	public void setFaturamentoDAO(FaturamentoDAO faturamentoDAO) {
		this.faturamentoDAO = faturamentoDAO;
	}

	public GraficosDAO getGraficosDAODB() {
		return graficosDAODB;
	}

	public void setGraficosDAODB(GraficosDAO graficosDAODB) {
		this.graficosDAODB = graficosDAODB;
	}

}
