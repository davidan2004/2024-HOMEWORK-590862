package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Mago;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private CaricatoreLabirinto caricatoreLabirinto;

	private Labirinto(String nomeFile) {
		try {
			this.caricatoreLabirinto = new CaricatoreLabirinto(nomeFile);
		}catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Il file " + nomeFile + "non esiste.");
		}
		try {
			this.caricatoreLabirinto.carica();
			this.stanzaIniziale = caricatoreLabirinto.getStanzaIniziale();
			this.stanzaVincente = caricatoreLabirinto.getStanzaVincente();
		} catch(FormatoFileNonValidoException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Il file " + nomeFile + "è nel formato sbagliato.");
		}
	}
	
	/*Costruttore usato per i test-case*/
	private Labirinto(Reader reader) {
		this.caricatoreLabirinto = new CaricatoreLabirinto(reader);
		try {
			caricatoreLabirinto.carica();
			this.stanzaIniziale = caricatoreLabirinto.getStanzaIniziale();
			this.stanzaVincente = caricatoreLabirinto.getStanzaVincente();
		} catch(FormatoFileNonValidoException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Reader non valido.");
		}
	}
	
	private Labirinto(Stanza stanzaIniziale, Stanza stanzaVincente) {
		this.stanzaIniziale = stanzaIniziale;
		this.stanzaVincente = stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder(String nomeFile) {
		return new LabirintoBuilder(new Labirinto(nomeFile));
	}
	
	public static LabirintoBuilder newBuilder(Reader reader) {
		return new LabirintoBuilder(new Labirinto(reader));
	}
	
	public static LabirintoBuilder newBuilder(Stanza iniziale, Stanza vincente) {
		return new LabirintoBuilder(new Labirinto(iniziale,vincente));
	}


		public void setStanzaVincente(Stanza stanza) {
			stanzaVincente = stanza;
		}

		public Stanza getStanzaVincente() {
			return this.stanzaVincente;
		}

		public void setStanzaIniziale(Stanza stanza) {
			stanzaIniziale = stanza;
		}

		public Stanza getStanzaIniziale() {
			return this.stanzaIniziale;
		}
		
public static class LabirintoBuilder {
	private String nomeStanzaIniziale;
	private String nomeStanzaVincente;
	private String nomeUltimaStanzaAcceduta;
	private Map<String,Stanza> stanzeLabirinto;
	private Labirinto output;
	
	public LabirintoBuilder(Labirinto labirinto) {
		if(labirinto != null) {
			nomeStanzaIniziale = labirinto.getStanzaIniziale().getNome();
			nomeStanzaVincente = labirinto.getStanzaVincente().getNome();
		}
		else {
			nomeStanzaIniziale = null;
			nomeStanzaVincente = null;
		}
		nomeUltimaStanzaAcceduta = null;
		output = labirinto;
		stanzeLabirinto = new HashMap<>();	
	}
	
	public LabirintoBuilder() {
		this(null);
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		nomeStanzaIniziale = nomeStanza;
		Stanza iniziale = new Stanza(nomeStanza);
		if(!stanzeLabirinto.containsKey(nomeStanza)) 
			stanzeLabirinto.put(nomeStanza, iniziale);
		
		if(output!=null)
			output.setStanzaIniziale(iniziale);
			
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		nomeStanzaVincente = nomeStanza;
		Stanza vincente = new Stanza(nomeStanza);
		
		if(!stanzeLabirinto.containsKey(nomeStanza)) 
			stanzeLabirinto.put(nomeStanza, vincente);
		
		if(output!=null)
			output.setStanzaVincente(vincente);
		
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
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzioneBloccata, String attrezzoSbloccante) {
		stanzeLabirinto.put(nomeStanza, new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante));
		
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String attrezzoSbloccante) {
		stanzeLabirinto.put(nomeStanza, new StanzaBloccata(nomeStanza, Direzione.valueOf(direzioneBloccata.toUpperCase()), attrezzoSbloccante));
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoLuminoso) {
		stanzeLabirinto.put(nomeStanza, new StanzaBuia(nomeStanza, attrezzoLuminoso));
		
		nomeUltimaStanzaAcceduta = nomeStanza;
		return this;
	}
	
	
	public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, Direzione direzione) {
		Stanza stanza1 = stanzeLabirinto.get(nomeStanza1);
		Stanza stanza2 = stanzeLabirinto.get(nomeStanza2);
		if(stanza1 == null) return this;
		
		stanza1.impostaStanzaAdiacente(direzione, stanza2);
		
		nomeUltimaStanzaAcceduta = nomeStanza1;
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, String direzione) {
		Stanza stanza1 = stanzeLabirinto.get(nomeStanza1);
		Stanza stanza2 = stanzeLabirinto.get(nomeStanza2);
		if(stanza1 == null) return this;
		
		stanza1.impostaStanzaAdiacente(Direzione.valueOf(direzione.toUpperCase()), stanza2);
		
		nomeUltimaStanzaAcceduta = nomeStanza1;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
		Stanza stanza = stanzeLabirinto.get(nomeUltimaStanzaAcceduta);
		if(stanza == null) return this;
		
		stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		return this;
	}
	
	public LabirintoBuilder addPersonaggio(String nomePersonaggio, String presentazione, String nomeStanza, Class<? extends AbstractPersonaggio> tipoPersonaggio) throws ClassNotFoundException {
		Stanza stanza = stanzeLabirinto.get(nomeStanza);
		if(stanza == null) return this;
		
		try {
			AbstractPersonaggio personaggio;
			if(tipoPersonaggio == Mago.class) {
				personaggio = new Mago(nomePersonaggio,presentazione,new Attrezzo("Bacchetta Magica",10));
			}
			else
				personaggio = tipoPersonaggio.getConstructor(String.class, String.class).newInstance(nomePersonaggio,presentazione);
			
			stanza.setPersonaggio(personaggio);
			return this;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ClassNotFoundException("Il tipo concreto di AbstractPersonaggio e' inesistente.");
		}
	}
	
	public Labirinto getLabirinto() {
		if(output != null)
			return output;
		
		Stanza iniziale = stanzeLabirinto.get(nomeStanzaIniziale);
		Stanza vincente = stanzeLabirinto.get(nomeStanzaVincente);
		
		return new Labirinto(iniziale,vincente);
	}

	public Map<String,Stanza> getMapStanze() {
		final Map<String,Stanza> out = new HashMap<>(stanzeLabirinto);
		return out;
	}
	
	public boolean setUltimaStanza(String nomeStanza) {
		if(this.stanzeLabirinto.containsKey(nomeStanza)) {
			this.nomeUltimaStanzaAcceduta = nomeStanza;
			return true;
		}
		return false;
	}
	
	public Stanza getStanzaIniziale() {
		return stanzeLabirinto.get(nomeStanzaIniziale);
	}
	
	public Stanza getStanzaVincente() {
		return stanzeLabirinto.get(nomeStanzaVincente);
	}
	

	}
}
