package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?";
	
	@Override
	public String esegui(Partita partita) {
		String msg;
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if(personaggio != null)
			msg = personaggio.agisci(partita);
		else
			msg = MESSAGGIO_CON_CHI;
		
		return msg;
	}

	@Override
	public String getNome() {
		return "ComandoInteragisci";
	}

}
