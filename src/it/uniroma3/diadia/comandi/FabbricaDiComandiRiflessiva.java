package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@Override
	public AbstractComando costruisciComando(String istruzione) {

		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;

		try(Scanner scannerDiParole = new Scanner(istruzione)) {
			if(scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();
			if(scannerDiParole.hasNext())
				parametro = scannerDiParole.next();
		} catch(Exception e) {
			throw e;
		}
		
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);

			comando = (AbstractComando)Class.forName(nomeClasse).getDeclaredConstructor().newInstance();
			comando.setParametro(parametro);
		} catch(Exception e) {
			comando = new ComandoNonValido();
		}

		return comando;
	}

}
