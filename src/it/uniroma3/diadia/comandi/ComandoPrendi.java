package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Comando "Prendi".
 */	
public class ComandoPrendi implements Comando {
	private String stringAttrezzo;

	@Override
	public String esegui(Partita partita) {
		if(this.stringAttrezzo == null)
			return "Uso corretto: prendi <attrezzo>";
		
		StringBuilder output = new StringBuilder();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(stanzaCorrente.hasAttrezzo(this.stringAttrezzo)) {
			Attrezzo a = stanzaCorrente.getAttrezzo(this.stringAttrezzo);
			stanzaCorrente.removeAttrezzo(a);
			
			partita.getGiocatore().addAttrezzo(a);
			output.append("Attrezzo " + this.stringAttrezzo + " messo nella borsa del giocatore");
		}
		else
			output.append("Attrezzo " + this.stringAttrezzo + " non presente in " + stanzaCorrente.getNome());;
		
		return output.toString();
	}

	@Override
	public void setParametro(String parametro) {
		this.stringAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return "ComandoPrendi";
	}
	
	@Override
	public String getParametro() {
		return this.stringAttrezzo;
	}
}
