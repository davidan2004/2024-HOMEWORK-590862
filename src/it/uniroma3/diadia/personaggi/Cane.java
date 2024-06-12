package it.uniroma3.diadia.personaggi;

import java.util.Random;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private final static String ABBAIA = "Woof!Woof!";
	
	public Cane(String nome, String presentazione) {
		super(nome,presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		Random random = new Random();
		int cfu = partita.getGiocatore().getCfu();
		int rimossi = random.nextInt(5) + 1;
		
		partita.getGiocatore().setCfu(cfu - rimossi); //rimuovo tra 1 e 5 cfu
		
		return ABBAIA + "\n"+super.getNome()+" ti ha morso togliendoti " + rimossi + " cfu.";
	}

	@Override
	public String saluta() {
		return null;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return ABBAIA + "\n" + super.getNome() + " ha lasciato cadere l'attrezzo nella stanza";
	}
}
