package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */
public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	public Comando costruisciComando(String istruzione) {
			Scanner scannerDiParole = new Scanner(istruzione);
			String nomeComando = null;
			String parametro = null;
			Comando comando = null;

			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next(); // prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
			
			if (nomeComando == null)
				comando = new ComandoNonValido();
			else if (nomeComando.equals("vai"))
				comando = new ComandoVai();
			else if (nomeComando.equals("prendi"))
				comando = new ComandoPrendi();
			else if (nomeComando.equals("posa"))
				comando = new ComandoPosa();
			else if (nomeComando.equals("aiuto"))
				comando = new ComandoAiuto();
			else if (nomeComando.equals("fine"))
				comando = new ComandoFine();
			else if (nomeComando.equals("guarda"))
				comando = new ComandoGuarda();
			else comando = new ComandoNonValido();
			comando.setParametro(parametro);
			return comando;
	}
}
