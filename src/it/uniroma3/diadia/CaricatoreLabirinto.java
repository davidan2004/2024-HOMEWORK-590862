package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze  da collocare nel formato <nomeStanza> <tipoStanza>
	 <parametro1> <parametro2>*/
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/* prefisso della riga contenente le specifiche dei personaggi nel formato <nomePersonaggio> <presentazione> <tipo> <nomeStanza> */
	private static final String PERSONAGGI_MARKER = "Personaggi:";
	
	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	private LabirintoBuilder costruttoreLabirinto;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.costruttoreLabirinto = new LabirintoBuilder();
	}

	public CaricatoreLabirinto(Reader reader) {
		this.reader = new LineNumberReader(reader);
		this.costruttoreLabirinto = new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiEAggiungiPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			if(riga == null)
				return null;

			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length() + 1);
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String stringheStanza = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		Map<String,List<String>> stanza2parametri = this.separaStringhePerStanze(stringheStanza);
		for(String nomeStanza : stanza2parametri.keySet()) {
					List<String> parametri = stanza2parametri.get(nomeStanza);
				if(parametri.size() == 0)
					this.costruttoreLabirinto.addStanza(nomeStanza);
				else if(parametri.size() == 2 && parametri.get(0).equals("Magica")) {
					int sogliaMagica = Integer.valueOf(parametri.get(1).toUpperCase());
					this.costruttoreLabirinto.addStanzaMagica(nomeStanza, sogliaMagica);
				}else if(parametri.size() == 3 && parametri.get(0).equals("Bloccata")) 
					this.costruttoreLabirinto.addStanzaBloccata(nomeStanza, Direzione.valueOf(parametri.get(1).toUpperCase()), parametri.get(2));
				else
					throw new FormatoFileNonValidoException("Il tipo di stanza specificato e' inesistente.");
			}
		}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new ArrayList<>();
		try(Scanner scannerDiParole = new Scanner(string)){
			scannerDiParole.useDelimiter(", ");
			while(scannerDiParole.hasNext()) 
					result.add(scannerDiParole.next());
			}
		return result;
	}
	
	private Map<String,List<String>> separaStringhePerStanze(String string) {
		Map<String,List<String>> result = new HashMap<>();
		try(Scanner scannerDiStringhe = new Scanner(string)){
			scannerDiStringhe.useDelimiter(", ");
			while(scannerDiStringhe.hasNext()) {
				try(Scanner scannerDiParole = new Scanner(scannerDiStringhe.next())){
					String nomeStanza = scannerDiParole.next(); 
					List<String> listaParametri = new ArrayList<>();
					if(scannerDiParole.hasNext())
						listaParametri.add(scannerDiParole.next()); //tipo Stanza
					if(scannerDiParole.hasNext())
						listaParametri.add(scannerDiParole.next()); //parametro1
					if(scannerDiParole.hasNext())
						listaParametri.add(scannerDiParole.next());

					result.put(nomeStanza, listaParametri);
				}
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		
		this.costruttoreLabirinto.addStanzaIniziale(nomeStanzaIniziale);
		this.costruttoreLabirinto.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		if(specificheAttrezzi == null) return;

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.costruttoreLabirinto.setUltimaStanza(nomeStanza);
			this.costruttoreLabirinto.addAttrezzo(nomeAttrezzo, peso);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.costruttoreLabirinto.getMapStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		if(specificheUscite == null) return;

		Iterator<String> it = separaStringheAlleVirgole(specificheUscite).iterator();
		while(it.hasNext()) {
			try (Scanner scannerDiLinea = new Scanner(it.next())){			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					Direzione dir = Direzione.valueOf(scannerDiLinea.next().toUpperCase());
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.costruttoreLabirinto.addAdiacenza(stanzaDa, nomeA, dir);
	}
	
	private void leggiEAggiungiPersonaggi() throws FormatoFileNonValidoException{
		String specificaPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		if(specificaPersonaggi == null) return;
		
		Iterator<String> it = separaStringheAlleVirgole(specificaPersonaggi).iterator();
		while(it.hasNext()) {
			try(Scanner scannerDiLinea = new Scanner(it.next())){
				
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio."));
					String nomePersonaggio = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la presentazione del personaggio."));
					String presentazionePersonaggio = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il tipo del personaggio."));
					String tipoPersonaggio = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui mettere il personaggio."));
					String nomeStanza = scannerDiLinea.next();
					
					impostaPersonaggio(nomePersonaggio, presentazionePersonaggio, tipoPersonaggio, nomeStanza);
				}
				
			}
		}
	}


	private void impostaPersonaggio(String nomePersonaggio, String presentazione, String tipoPersonaggio, String nomeStanza) throws FormatoFileNonValidoException {
		Class<? extends AbstractPersonaggio> tipo = getTipoPersonaggio(tipoPersonaggio);
		check(isStanzaValida(nomeStanza),msgTerminazionePrecoce("stanza in cui mettere il personaggio."));
		check(tipo!=null,msgTerminazionePrecoce("tipo del personaggio."));
		
		try {
			this.costruttoreLabirinto.addPersonaggio(nomePersonaggio, presentazione, nomeStanza, tipo);
		} catch(ClassNotFoundException e) {
			throw new FormatoFileNonValidoException("Il tipo " + tipoPersonaggio + " e' inesistente.");
		}
	}

	private Class<? extends AbstractPersonaggio> getTipoPersonaggio(String tipoPersonaggio) {
		String nomeClasse = "it.uniroma3.diadia.personaggi.";
		nomeClasse += tipoPersonaggio;
		try {
			@SuppressWarnings("unchecked")
			Class<? extends AbstractPersonaggio> tipo = (Class<? extends AbstractPersonaggio>) Class.forName(nomeClasse);
			return tipo;
		} catch(Exception e) {
			return null;
		}
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.costruttoreLabirinto.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.costruttoreLabirinto.getStanzaVincente();
	}
	
	
}
