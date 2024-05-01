package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	@Override
	public String esegui(Partita partita) {
		StringBuilder output = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++)
			output.append(elencoComandi[i]+" ");
		output.append("");
		return output.toString();
	}

	@Override
	public void setParametro(String parametro) {
		return;
	}
	
	@Override
	public String getNome() {
		return "ComandoAiuto";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
