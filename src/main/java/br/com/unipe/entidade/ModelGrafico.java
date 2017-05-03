package br.com.unipe.entidade;

public class ModelGrafico {

	private String chave;
	private Object valor;

	public ModelGrafico(){}
	
	public ModelGrafico(String chave, Object valor){
		this.chave = chave;
		this.valor = valor;
	}
	
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	
	
}
