package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Comando "Posa".
 */
public class ComandoPosa extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		if(super.getParametro() == null)
			return "Uso corretto: posa <attrezzo>";
		
		StringBuilder output = new StringBuilder();
		Giocatore giocatore = partita.getGiocatore();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		
		if(giocatore.hasAttrezzo(super.getParametro())) {
			Attrezzo a = giocatore.removeAttrezzo(super.getParametro());
			stanzaCorrente.addAttrezzo(a);
			output.append("Attrezzo " + super.getParametro() + " posato in " + stanzaCorrente.getNome());
		}
		else
			output.append("Attrezzo " + super.getParametro() + " non presente nella borsa del giocatore");
		
		return output.toString();
	}

	@Override
	public String getNome() {
		return "ComandoPosa";
	}
}
