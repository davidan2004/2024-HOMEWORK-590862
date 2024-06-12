package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */
public class ComandoFine extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		StringBuilder output = new StringBuilder();
		output.append("Grazie di aver giocato!");
		partita.setFinita();
		
		return output.toString();
	}

	@Override
	public String getNome() {
		return "ComandoFine";
	}
}
