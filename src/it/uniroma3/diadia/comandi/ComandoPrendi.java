package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Comando "Prendi".
 */	
public class ComandoPrendi extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		if(super.getParametro() == null)
			return "Uso corretto: prendi <attrezzo>";
		
		StringBuilder output = new StringBuilder();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(stanzaCorrente.hasAttrezzo(super.getParametro())) {
			Attrezzo a = stanzaCorrente.getAttrezzo(super.getParametro());
			stanzaCorrente.removeAttrezzo(a);
			
			partita.getGiocatore().addAttrezzo(a);
			output.append("Attrezzo " + super.getParametro() + " messo nella borsa del giocatore");
		}
		else
			output.append("Attrezzo " + super.getParametro() + " non presente in " + stanzaCorrente.getNome());;
		
		return output.toString();
	}

	@Override
	public String getNome() {
		return "ComandoPrendi";
	}
}
