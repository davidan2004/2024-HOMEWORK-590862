package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando {
	private String direzione;
	
	@Override
	public String esegui(Partita partita) { 
		Stanza stanzaCorrrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione == null)
			return "Devi specificare una direzione";
				
		prossimaStanza = stanzaCorrrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
				return "Direzione inesistente";

		partita.setStanzaCorrente(prossimaStanza);
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(--cfu);

		return prossimaStanza.getDescrizione();
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return "ComandoVai";
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}

}
