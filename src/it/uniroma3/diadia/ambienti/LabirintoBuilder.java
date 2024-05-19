package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private String nomeStanzaIniziale;
	private String nomeStanzaVincente;
	private String nomeUltimaStanzaAcceduta;
	private Map<String,Stanza> stanzeLabirinto;
	
	public LabirintoBuilder() {
		nomeStanzaIniziale = null;
		nomeStanzaVincente = null;
		nomeUltimaStanzaAcceduta = null;
		stanzeLabirinto = new HashMap<>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		nomeStanzaIniziale = nomeStanza;
		if(!stanzeLabirinto.containsKey(nomeStanza))
			stanzeLabirinto.put(nomeStanza, new Stanza(nomeStanza));

		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		nomeStanzaVincente = nomeStanza;
		if(!stanzeLabirinto.containsKey(nomeStanza))
			stanzeLabirinto.put(nomeStanza, new Stanza(nomeStanza));
		
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		stanzeLabirinto.put(nomeStanza, new Stanza(nomeStanza));
		
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		stanzeLabirinto.put(nomeStanza, new StanzaMagica(nomeStanza, sogliaMagica));
		
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String attrezzoSbloccante) {
		stanzeLabirinto.put(nomeStanza, new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante));
		
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoLuminoso) {
		stanzeLabirinto.put(nomeStanza, new StanzaBuia(nomeStanza, attrezzoLuminoso));
		
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	
	public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, String direzione) {
		Stanza stanza1 = stanzeLabirinto.get(nomeStanza1);
		Stanza stanza2 = stanzeLabirinto.get(nomeStanza2);
		if(stanza1 == null) return this;
		
		stanza1.impostaStanzaAdiacente(direzione, stanza2);
		
		nomeUltimaStanzaAcceduta = nomeStanza1;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
		Stanza stanza = stanzeLabirinto.get(nomeUltimaStanzaAcceduta);
		if(stanza == null) return this;
		
		stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		return this;
	}
	
	public Labirinto getLabirinto() {
		Stanza iniziale = stanzeLabirinto.get(nomeStanzaIniziale);
		Stanza vincente = stanzeLabirinto.get(nomeStanzaVincente);
		
		return new Labirinto(iniziale,vincente);
	}

	public Map<String,Stanza> getListaStanze() {
		final Map<String,Stanza> out = new HashMap<>(stanzeLabirinto);
		return out;
	}
	

}
