package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */
public class ComandoFine implements Comando {

	@Override
	public String esegui(Partita partita) {
		StringBuilder output = new StringBuilder();
		output.append("Grazie di aver giocato!");
		partita.setFinita();
		
		return output.toString();
	}

	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getNome() {
		return "ComandoFine";
	}
	
	@Override
	public String getParametro() {
		return null;
	}

}
