package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Comando "Posa".
 */
public class ComandoPosa implements Comando {
	private String stringAttrezzo;

	@Override
	public String esegui(Partita partita) {
		if(this.stringAttrezzo == null)
			return "Uso corretto: posa <attrezzo>";
		
		StringBuilder output = new StringBuilder();
		Giocatore giocatore = partita.getGiocatore();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		
		if(giocatore.hasAttrezzo(this.stringAttrezzo)) {
			Attrezzo a = giocatore.removeAttrezzo(stringAttrezzo);
			stanzaCorrente.addAttrezzo(a);
			output.append("Attrezzo " + stringAttrezzo + " posato in " + stanzaCorrente.getNome());
		}
		else
			output.append("Attrezzo " + stringAttrezzo + " non presente nella borsa del giocatore");
		
		return output.toString();
	}

	@Override
	public void setParametro(String parametro) {
		this.stringAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return "ComandoPosa";
	}
	
	@Override
	public String getParametro() {
		return this.stringAttrezzo;
	}

}
