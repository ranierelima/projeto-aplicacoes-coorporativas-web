package br.com.unipe.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Estaremos fazendo uso dessa classe para conseguir acesso � Base de Dados
 * Oracle, presente nos servidores da Operadora Unimed-NNE. Todas as informa��es
 * referentes a esse acesso, foram documentadas pelo autor da segunda vers�o do
 * sistema de Autoriza��es Interc�mbio.
 * 
 * @author Alysson Tiago S. Cordeiro (Analista de Sistemas)
 * @version 3
 * @since Vers�o 1 - 30/03/2005, por Francisco Pereira Neto (Analista de
 *        Sistemas) e F�bio Farias Fernandes (Gerente de Processos).
 * 
 */
public class ConexaoBD {
	/**
	 * Este m�todo � respons�vel por retornar uma Conex�o com o Banco de Dados.
	 * Retornar� um OracleConnection, se for estabelecida uma conex�o com
	 * sucesso, ou <b>null</b> se alguma Exception ocorrer.
	 * 
	 * Lembre-se: Fechar a conex�o nas classes que usar�o esse m�todo.
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
				System.out.println("Conex�o: " + i + "  -  "
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
