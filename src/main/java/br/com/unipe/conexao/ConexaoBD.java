package br.com.unipe.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Estaremos fazendo uso dessa classe para conseguir acesso à Base de Dados
 * Oracle, presente nos servidores da Operadora Unimed-NNE. Todas as informações
 * referentes a esse acesso, foram documentadas pelo autor da segunda versão do
 * sistema de Autorizações Intercâmbio.
 * 
 * @author Alysson Tiago S. Cordeiro (Analista de Sistemas)
 * @version 3
 * @since Versão 1 - 30/03/2005, por Francisco Pereira Neto (Analista de
 *        Sistemas) e Fábio Farias Fernandes (Gerente de Processos).
 * 
 */
public class ConexaoBD {
	/**
	 * Este método é responsável por retornar uma Conexão com o Banco de Dados.
	 * Retornará um OracleConnection, se for estabelecida uma conexão com
	 * sucesso, ou <b>null</b> se alguma Exception ocorrer.
	 * 
	 * Lembre-se: Fechar a conexão nas classes que usarão esse método.
	 * 
	 * @return Objeto do tipo OracleConnection
	 * @throws ConexaoException
	 * @throws ArquivoNaoEncontradoException
	 */
	public static Connection getConexao() throws ConexaoException {

		Connection sqlConexao = null;

		String url = "jdbc:mysql://localhost:3306/acwp";
		String usuario = "root";
		String senha = "";

		try {
			
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			
			sqlConexao = DriverManager.getConnection(url, usuario,senha);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return sqlConexao;

	}

	public static void main(String[] args) {

		Connection conexao = null;

		try {

			for (int i = 0; i < 5; i++) {
				conexao = new ConexaoBD().getConexao();
				System.out.println("Conexão: " + i + "  -  "
						+ conexao.toString());
				conexao.close();
			}

		} catch (ConexaoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
