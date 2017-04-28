package br.com.unipe.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.unipe.conexao.ConexaoException;
import br.com.unipe.conexao.ConexaoBD;
import br.com.unipe.entidade.Faturamento;

/**
 * 
 * @author Alysson Aves.
 * 
 * @version 1.0.
 *
 */
@ManagedBean(name="faturamentoDAO")
@ApplicationScoped
public class FaturamentoDAO implements Serializable {

	@PostConstruct
	private void init() {

	}

	public ArrayList<Faturamento> consulta_faturamento() {

		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement stmConsulta = null;

		ArrayList<Faturamento> lista_faturamento = new ArrayList<Faturamento>();
		
		Faturamento fat;

		try{

			conexao = ConexaoBD.getConexao();

			stmConsulta = conexao.prepareStatement("SELECT NM_CIDADE, DT_EMISSAO, SUM(VL_ITEM) AS VALOR "
													+ " FROM TB_DADOS "
													+ " WHERE NM_CIDADE IN ('JOAO PESSOA') "
													+ " group by NM_CIDADE, DT_EMISSAO "
													+ " ORDER BY DT_EMISSAO");
			
			rs = stmConsulta.executeQuery();

			lista_faturamento.clear();

			while(rs.next()){

				fat = new Faturamento();
				
				fat.setCidade(rs.getString("NM_CIDADE"));
				fat.setData(rs.getString("DT_EMISSAO"));
				fat.setValor(rs.getDouble("VALOR"));
				
				lista_faturamento.add(fat);
			}

			rs.close();
			stmConsulta.close();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException ex) {
			ex.printStackTrace();
		} finally{
			try {
				rs.close();
				stmConsulta.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return lista_faturamento;
	}

}
