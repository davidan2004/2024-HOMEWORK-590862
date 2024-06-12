package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	private static final String PERSONAGGIO_ASSENTE = "Qui non c'è nessuno da salutare.";

	@Override
	public String esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null)
			return PERSONAGGIO_ASSENTE;
		else
			return personaggio.saluta();
	}

	@Override
	public String getNome() {
		return "ComandoSaluta";
	}

}
