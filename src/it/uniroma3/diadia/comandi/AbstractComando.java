package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	private String parametro;
	
	/**
	* esecuzione del comando
	*/
	public abstract String esegui(Partita partita);
	
	/**
	* set parametro del comando
	*/
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	/**
	 * get nome del comando
	 */
	public abstract String getNome();
	
	/**
	 * get parametro del comando
	 */
	public String getParametro() {
		return this.parametro;
	}

}
