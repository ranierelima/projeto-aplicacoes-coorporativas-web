package br.com.unipe.util;

public class Constantes {
//	MENSAGENS DE ERRO
	public static final String ERRO_CONEXAO_BD = " Não conseguiu conectar ao banco de dados ";
	public static final String ERRO_REALIZAR_CONSULTA = " Não foi possível realizar a consulta "; 
	
//	SCRIPT SQL PARA CONSULTAS
	public static final String SQL_VENDAS_DOS_ESTADOS = "select UF_CLIENTE, VL_TOT_NOTA from ( SELECT UF_CLIENTE, sum(VL_TOT_NOTA) as VL_TOT_NOTA FROM tb_dados group by UF_CLIENTE )  as td_dados  order by VL_TOT_NOTA desc limit 6";
	public static final String SQL_VENDAS_DAS_CIDADES_POR_ESTADOS = " select NM_CIDADE, VL_TOT_NOTA from (  SELECT NM_CIDADE, sum(VL_TOT_NOTA) as VL_TOT_NOTA  FROM tb_dados WHERE UF_CLIENTE= ? group by NM_CIDADE )   as td_dados  order by VL_TOT_NOTA desc limit 6";
	public static final String SQL_PRODUTOS_MAIS_VENDIDOS = "select NM_PRODUTO, cd_produto from ( select NM_PRODUTO, count(cd_produto) as cd_produto from tb_dados group by cd_produto ) as td_dados order by td_dados.cd_produto desc limit 6";
	public static final String SQL_DIAS_COM_MAIORES_VENDAS = "select dt_emissao, sum(VL_TOT_NOTA) as 'total' from tb_dados group by dt_emissao order by sum(VL_TOT_NOTA) desc limit 6";
	public static final String SQL_CLIENTES_MAIS_COMPRARAM_QTDE = "select nm_cliente, count(nm_cliente) as 'quantidade' from tb_dados group by nm_cliente order by count(nm_cliente) desc limit 6";
	public static final String SQL_CLIENTES_MAIS_COMPRARAM_VALOR = "select nm_cliente, sum(VL_TOT_NOTA) as 'total' from tb_dados group by nm_cliente order by sum(VL_TOT_NOTA) desc limit 6";
	public static final String SQL_SELECT_TODAS_UFS = "select UF_CLIENTE from tb_dados group by UF_CLIENTE";

}
