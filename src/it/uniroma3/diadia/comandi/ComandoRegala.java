package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		if(super.getParametro() == null)
			return "Uso corretto: regala <attrezzo>";
		
		Attrezzo attrezzo = partita.getGiocatore().removeAttrezzo(super.getParametro());
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(attrezzo == null)
			return "Attrezzo non presente nella borsa del giocatore!";
		if(personaggio == null)
			return "Non c'è nessuno a cui regalare l'attrezzo " + attrezzo.getNome();
		
		return personaggio.riceviRegalo(attrezzo, partita);
	}

	@Override
	public String getNome() {
		return "ComandoRegala";
	}
}
