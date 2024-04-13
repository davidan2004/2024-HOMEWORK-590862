package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public boolean addAttrezzo(Attrezzo a) {
		return this.borsa.addAttrezzo(a);
	}
	public Attrezzo removeAttrezzo(String stringAttrezzo) {
		return this.borsa.removeAttrezzo(stringAttrezzo);
	}
	
	public Attrezzo getAttrezzo(String stringAttrezzo) {
		return this.borsa.getAttrezzo(stringAttrezzo);
	}
	
	public boolean hasAttrezzo(String stringAttrezzo) {
		return this.borsa.hasAttrezzo(stringAttrezzo);
	}
}

