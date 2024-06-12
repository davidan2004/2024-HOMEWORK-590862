package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.personaggi.Mago;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";


	static private Partita partita;
	static private IO io;

	public DiaDia(Labirinto labirinto,IO inputOutput) {
		partita = new Partita(labirinto);
		io = inputOutput;
	}
	
	public DiaDia(IO inputOutput) {
		this(null,inputOutput);
	}
	
	


	public void gioca(Scanner scannerDiLinee) {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga(scannerDiLinee);
		while (!processaIstruzione(istruzione));
	}

	/*metodo usato per i test*/
	public void gioca() {
		this.gioca(null);
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		io.mostraMessaggio(comandoDaEseguire.esegui(partita));
		if (partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (partita.getGiocatore().getCfu() == 0)
			io.mostraMessaggio("Hai esaurito i CFU...");
		return partita.isFinita();
	}

	public static void main (String[] argc) throws ClassNotFoundException {
		IO io = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne", "Biblioteca", Direzione.OVEST)
				.addPersonaggio("Stregone", "Sono un mago.", "LabCampusOne", Mago.class)
				.getLabirinto();

		DiaDia gioco = new DiaDia(labirinto,io);
		try(Scanner scannerDiLinee = new Scanner(System.in)){
			gioco.gioca(scannerDiLinee);
		}
	}
}
