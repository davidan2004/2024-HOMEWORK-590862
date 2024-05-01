package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando "Non valido".
 */
public class ComandoNonValido implements Comando {
	
	@Override
	public String esegui(Partita partita) {
		return "Comando non valido";
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getNome() {
		return "ComandoNonValido";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
