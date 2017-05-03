package br.com.unipe.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.unipe.conexao.ConexaoBD;
import br.com.unipe.conexao.ConexaoException;
import br.com.unipe.entidade.ModelGrafico;
import br.com.unipe.util.Constantes;

@ManagedBean(name="graficosDAO")
@ApplicationScoped
public class GraficosDAO implements Serializable{

	private static final long serialVersionUID = -5311143961982944900L;
	
	@PostConstruct
	private void init() {
	}

	public List<ModelGrafico> vendasDosEstados(){
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement stmConsulta = null;

		List<ModelGrafico> retorno = new ArrayList<ModelGrafico>();
		
		try {
			conexao = ConexaoBD.getConexao();
			stmConsulta = conexao.prepareStatement(Constantes.SQL_VENDAS_DOS_ESTADOS);

			rs = stmConsulta.executeQuery();
		
			while(rs.next()){
				retorno.add( new ModelGrafico(rs.getString("UF_CLIENTE"), rs.getDouble("VL_TOT_NOTA")) );
			}
		} catch (SQLException e) {
			System.out.println(Constantes.ERRO_REALIZAR_CONSULTA);			
		} catch (ConexaoException e) {
			System.out.println(Constantes.ERRO_CONEXAO_BD);	
		}
		
		
		return retorno;
	}

	public List<ModelGrafico> produtosMaisVendidos(){
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement stmConsulta = null;
		List<ModelGrafico> retorno = new ArrayList<ModelGrafico>();
		try {
			conexao = ConexaoBD.getConexao();
			stmConsulta = conexao.prepareStatement(Constantes.SQL_PRODUTOS_MAIS_VENDIDOS);
			rs = stmConsulta.executeQuery();
			while(rs.next()){
				retorno.add( new ModelGrafico(rs.getString("NM_PRODUTO"), rs.getDouble("cd_produto")) );
			}
		} catch (SQLException e) {
			System.out.println(Constantes.ERRO_REALIZAR_CONSULTA);			
		} catch (ConexaoException e) {
			System.out.println(Constantes.ERRO_CONEXAO_BD);	
		}
		return retorno;
	}

	public List<ModelGrafico> diasComMaioresVendas(){
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement stmConsulta = null;
		List<ModelGrafico> retorno = new ArrayList<ModelGrafico>();
		try {
			conexao = ConexaoBD.getConexao();
			stmConsulta = conexao.prepareStatement(Constantes.SQL_DIAS_COM_MAIORES_VENDAS);
			rs = stmConsulta.executeQuery();
			while(rs.next()){
				retorno.add( new ModelGrafico(rs.getString("dt_emissao"), rs.getDouble("total")) );
			}
		} catch (SQLException e) {
			System.out.println(Constantes.ERRO_REALIZAR_CONSULTA);			
		} catch (ConexaoException e) {
			System.out.println(Constantes.ERRO_CONEXAO_BD);	
		}
		return retorno;
	}
	public List<ModelGrafico> clienteQueMaisCompraram(){
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement stmConsulta = null;
		List<ModelGrafico> retorno = new ArrayList<ModelGrafico>();
		try {
			conexao = ConexaoBD.getConexao();
			stmConsulta = conexao.prepareStatement(Constantes.SQL_CLIENTES_MAIS_COMPRARAM_QTDE);
			rs = stmConsulta.executeQuery();
			while(rs.next()){
				retorno.add( new ModelGrafico(rs.getString("nm_cliente"), rs.getDouble("quantidade")) );
			}
		} catch (SQLException e) {
			System.out.println(Constantes.ERRO_REALIZAR_CONSULTA);			
		} catch (ConexaoException e) {
			System.out.println(Constantes.ERRO_CONEXAO_BD);	
		}
		return retorno;
	}
	public List<ModelGrafico> clienteQueMaisCompraramReais(){
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement stmConsulta = null;
		List<ModelGrafico> retorno = new ArrayList<ModelGrafico>();
		try {
			conexao = ConexaoBD.getConexao();
			stmConsulta = conexao.prepareStatement(Constantes.SQL_CLIENTES_MAIS_COMPRARAM_VALOR);
			rs = stmConsulta.executeQuery();
			while(rs.next()){
				retorno.add( new ModelGrafico(rs.getString("nm_cliente"), rs.getDouble("total")) );
			}
		} catch (SQLException e) {
			System.out.println(Constantes.ERRO_REALIZAR_CONSULTA);			
		} catch (ConexaoException e) {
			System.out.println(Constantes.ERRO_CONEXAO_BD);	
		}
		return retorno;
	}
	
	public List<ModelGrafico> vendaDasCidadesPorEstado(String estado){

		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement stmConsulta = null;

		List<ModelGrafico> retorno = new ArrayList<ModelGrafico>();
		try {
			conexao = ConexaoBD.getConexao();
			stmConsulta = conexao.prepareStatement(Constantes.SQL_VENDAS_DAS_CIDADES_POR_ESTADOS);
			stmConsulta.setString(1, estado);
			rs = stmConsulta.executeQuery();
			
			while(rs.next()){
				retorno.add( new ModelGrafico(rs.getString("NM_CIDADE"), rs.getDouble("VL_TOT_NOTA")) );
			}
		} catch (SQLException e) {
			System.out.println(Constantes.ERRO_REALIZAR_CONSULTA);			
		} catch (ConexaoException e) {
			System.out.println(Constantes.ERRO_CONEXAO_BD);	
		}
		return retorno;
	}
}
