package it.uniroma3.diadia.personaggi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_POSITIVO = "Grazie per avermi salutato, " +
			"ora ti porto nella stanza con piu attrezzi.";
	private static final String MESSAGGIO_NEGATIVO = "Dato che non mi hai salutato, " +
			"ora ti porto nella stanza con meno attrezzi.";
	private static final String MESSAGGIO_REGALO = "Grazie per il tuo regalo, hahahaha.";
	
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
	}
	
	

	@Override
	public String agisci(Partita partita) {
		Map<Integer,Stanza> mapStanzePerNumeroAttrezzi = new HashMap<>();
		for(Stanza s : partita.getStanzaCorrente().getMapStanzeAdiacenti().values())
			mapStanzePerNumeroAttrezzi.put(s.getAttrezzi().size(),s);
		
		String msg;
		
		if(super.haSalutato()) {
			msg = MESSAGGIO_POSITIVO;
			partita.setStanzaCorrente(mapStanzePerNumeroAttrezzi.get(
					Collections.max(mapStanzePerNumeroAttrezzi.keySet())));
		}
		else {
			msg = MESSAGGIO_NEGATIVO;
			partita.setStanzaCorrente(mapStanzePerNumeroAttrezzi.get(
					Collections.min(mapStanzePerNumeroAttrezzi.keySet())));	
		}
		
		return msg;
	}



	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_REGALO;
	}

}
