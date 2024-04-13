package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	static private IOConsole scannerDiLinee;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		scannerDiLinee.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = scannerDiLinee.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isEmpty()) return false;
		
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi")) {
			if(comandoDaEseguire.getParametro() == null)
				scannerDiLinee.mostraMessaggio("Uso corretto: prendi <oggetto>");
			else
				this.prendi(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("posa")) {
			if(comandoDaEseguire.getParametro() == null) 
				scannerDiLinee.mostraMessaggio("Uso corretto: posa <oggetto>");
			else
				this.posa(comandoDaEseguire.getParametro());
		}
		else
			scannerDiLinee.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			scannerDiLinee.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++)
			scannerDiLinee.mostraMessaggio(elencoComandi[i]+" ");
		scannerDiLinee.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione == null) {
			scannerDiLinee.mostraMessaggio("Dove vuoi andare ?");
			direzione = scannerDiLinee.leggiRiga();
		}
			
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			scannerDiLinee.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		scannerDiLinee.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());

	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		scannerDiLinee.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}
	
	/**
	 * Comando "Prendi".
	 */	
	private void prendi(String stringAttrezzo) {
		if(this.partita.getStanzaCorrente().hasAttrezzo(stringAttrezzo)) {
			Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo(stringAttrezzo);
			this.partita.getStanzaCorrente().removeAttrezzo(a);
			
			this.partita.getGiocatore().addAttrezzo(a);
			scannerDiLinee.mostraMessaggio("Attrezzo " + stringAttrezzo + " messo nella borsa del giocatore");
		}
		else
			scannerDiLinee.mostraMessaggio("Attrezzo " + stringAttrezzo + " non presente in " + this.partita.getStanzaCorrente().getNome());
		
	}
	/**
	 * Comando "Posa".
	 */
	private void posa(String stringAttrezzo) {
		if(this.partita.getGiocatore().hasAttrezzo(stringAttrezzo)) {
			Attrezzo a = this.partita.getGiocatore().removeAttrezzo(stringAttrezzo);
			this.partita.getStanzaCorrente().addAttrezzo(a);
			scannerDiLinee.mostraMessaggio("Attrezzo " + stringAttrezzo + " posato in " + this.partita.getStanzaCorrente().getNome());
		}
		else
			scannerDiLinee.mostraMessaggio("Attrezzo " + stringAttrezzo + " non presente nella borsa del giocatore");
		
	}

	public static void main(String[] argc) {
		scannerDiLinee = new IOConsole();
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}