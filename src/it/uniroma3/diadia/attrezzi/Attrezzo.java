package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo {

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}
	
	public Attrezzo(String nome) {
		this(nome,1);
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}

	@Override
	public boolean equals(Object o) {
		Attrezzo a = (Attrezzo) o;
		
		if(this.nome.equals(a.getNome()))
			if(this.peso == a.getPeso())
				return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.peso;
	}
}