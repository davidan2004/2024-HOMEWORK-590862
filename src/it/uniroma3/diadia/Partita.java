package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Giocatore giocatore;
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private boolean finita;
	
	public Partita(Labirinto labirinto){
		this.giocatore = new Giocatore();
		this.labirinto = labirinto;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.finita = false;
	}
	
	/*Costruttore usato per test-case*/
	public Partita(){
		this(Labirinto.newBuilder(new Stanza("iniziale"), new Stanza("finale")).getLabirinto());
	}
	
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || this.giocatore.getCfu() == 0;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public void setStanzaCorrente(Stanza nuovaStanzaCorrente) {
		this.stanzaCorrente = nuovaStanzaCorrente;
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

}
