package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando "Non valido".
 */
public class ComandoNonValido extends AbstractComando {
	
	@Override
	public String esegui(Partita partita) {
		return "Comando non valido";
	}

	@Override
	public String getNome() {
		return "ComandoNonValido";
	}
}
