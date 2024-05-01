package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	@Override
	public String esegui(Partita partita) {
		StringBuilder output = new StringBuilder();
		output.append(partita.getStanzaCorrente().getDescrizione() + "\n");
		output.append(partita.getGiocatore().listaAttrezzi() + "\n");
		output.append("CFU rimanenti: " + partita.getGiocatore().getCfu() + "\n");;
		
		return output.toString();
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getNome() {
		return "ComandoGuarda";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
