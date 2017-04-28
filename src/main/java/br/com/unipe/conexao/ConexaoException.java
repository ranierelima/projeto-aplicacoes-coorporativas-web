package br.com.unipe.conexao;

/**
 *
 *	Estaremos fazendo uso dessa classe, extensora da ExceptionAutorizador, para registro de erros que poderiam acontecer  
 *	em rela��o � conex�o com o Banco de Dados.
 * 	
 *	@author George Fernandes Maia (T�cnico em Inform�tica)
 *	@version 2
 *	@since Vers�o 1 - 30/03/2005, por Francisco Pereira Neto (Analista de Sistemas) e F�bio Farias Fernandes (Gerente de Processos).
 *
 */
public class ConexaoException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param mensagemConexaoException Essa mensagem ser� usada para avisar sobre o erro que aconteceu.
	 */
	public ConexaoException(String mensagemConexaoException)
	{
		super(mensagemConexaoException);
		
	}


}
