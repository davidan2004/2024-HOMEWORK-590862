package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class StanzaProtected {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;	
	private String nome;
    protected Map<String,Attrezzo> attrezzi;
    private Set<String> direzioni;
    private Map<String,Stanza> stanzeAdiacenti;
    
    public StanzaProtected(String nome) {
        this.nome = nome;
        this.direzioni = new HashSet<>(NUMERO_MASSIMO_DIREZIONI);
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
    }

    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
       if (!this.direzioni.contains(direzione) 
       && this.direzioni.size()==NUMERO_MASSIMO_DIREZIONI) return;
       
       this.direzioni.add(direzione);
       this.stanzeAdiacenti.put(direzione, stanza);
    }

	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.toString();
    }

    public List<Attrezzo> getAttrezzi() {
        return new ArrayList<Attrezzo>(this.attrezzi.values());
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.attrezzi.containsKey(attrezzo.getNome())) return false;
    	
    	this.attrezzi.put(attrezzo.getNome(),attrezzo);
    	return true;
    }

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

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		Attrezzo rimosso = this.attrezzi.remove(attrezzo.getNome());
		
		if(rimosso == null) return false;
		return true;
	}


	public Set<String> getDirezioni() {
		return this.direzioni;
    }
}
