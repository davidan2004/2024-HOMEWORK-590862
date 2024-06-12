package it.uniroma3.diadia.comandi;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto extends AbstractComando {
	static final private Set<String> elencoComandi = new HashSet<>();
	
	private void trovaComandi() {
		List<String> nomiClassi = getClassiInComandi();
		for(String nomeClasse : nomiClassi) {
			if(nomeClasse.contains("$") || nomeClasse.contains("Test") || nomeClasse.equals("ComandoNonValido"))
				continue;
			if(nomeClasse.startsWith("Comando")) {
				elencoComandi.add(Character.toLowerCase(nomeClasse.charAt(7))  + nomeClasse.substring(8));
			}
		}
	}
	
	@Override
	public String esegui(Partita partita) {
		trovaComandi();
		StringBuilder output = new StringBuilder();
		for(String comando : elencoComandi)
			output.append(comando + " ");
		output.append("");
		return output.toString();
	}
	
	@Override
	public String getNome() {
		return "ComandoAiuto";
	}
	

    private List<String> getClassiInComandi() {
        String percorso = "it/uniroma3/diadia/comandi";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
        File directory;
		try {
			directory = new File(classLoader.getResource(percorso).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new InvalidPathException(percorso, percorso + "non e' un percorso valido.");
		}
        List<String> nomiClassi = new ArrayList<>();

        if (directory.exists()) {
            File[] files = directory.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".class");
				}
			});
            for (File file : files) {
                String nomeClasse = file.getName();
                nomeClasse = nomeClasse.substring(0, nomeClasse.length() - 6); // rimuovere ".class"
                nomiClassi.add(nomeClasse);
            }
        }
        
        return nomiClassi;
    }
}
