package it.uniroma3.diadia.giocatore;

import java.io.IOException;
import java.util.Properties;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	
	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		Properties properties = new Properties();
		try{
			properties.load(this.getClass().getClassLoader().getResourceAsStream("diadia.properties"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		this.borsa = new Borsa();
		this.cfu = Integer.valueOf(properties.getProperty("cfu_iniziali"));
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
	
	public String listaAttrezzi() {
		return this.borsa.toString();
	}
}

