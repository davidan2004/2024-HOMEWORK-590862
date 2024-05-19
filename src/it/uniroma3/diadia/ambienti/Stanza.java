package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;	
	private String nome;
    private Map<String,Attrezzo> attrezzi;
    private Set<String> direzioni;
    private Map<String,Stanza> stanzeAdiacenti;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.direzioni = new HashSet<>(NUMERO_MASSIMO_DIREZIONI);
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
       if (!this.direzioni.contains(direzione) 
       && this.direzioni.size()==NUMERO_MASSIMO_DIREZIONI) return;
       
       this.direzioni.add(direzione);
       this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce il nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return new ArrayList<Attrezzo>(this.attrezzi.values());
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.attrezzi.containsKey(attrezzo.getNome())) return false;
    	
    	this.attrezzi.put(attrezzo.getNome(),attrezzo);
    	return true;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo a : this.attrezzi.values()) {
    		risultato.append(a.toString()+" ");
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		Attrezzo rimosso = this.attrezzi.remove(attrezzo.getNome());
		
		if(rimosso == null) return false;
		return true;
	}


	public Set<String> getDirezioni() {
		return this.direzioni;
    }
	
	@Override
	public boolean equals(Object o) {
		Stanza s = (Stanza) o;
		if(this.nome.equals(s.nome))
			if(this.attrezzi.equals(s.attrezzi))
				if(this.direzioni.equals(s.direzioni))
					if(this.stanzeAdiacenti.equals(s.stanzeAdiacenti))
						return true;
		return false;
	}

	public Map<String,Stanza> getMapStanzeAdiacenti() {
		final Map<String,Stanza> out = new HashMap<>(stanzeAdiacenti);
		return out;
	}
	
	public boolean isMagica() {
		return false;
	}

}