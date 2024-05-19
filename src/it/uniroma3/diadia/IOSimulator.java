package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {
	
	private Map<Integer,String> righeInput;
	private Map<Integer,String> righeOutput;
	private int numeroRigheInput;
	private int numeroRigheOutput;
	
	public IOSimulator(){
		righeInput = new HashMap<>();
		righeOutput = new HashMap<>();
		numeroRigheInput = 0;
		numeroRigheOutput = 0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		righeOutput.put(++numeroRigheOutput, messaggio);
	}
	
	@Override
	public String leggiRiga() {
		return righeInput.get(++numeroRigheInput);
	}
	
	public void setInput(List<String> input) {
		int i = 1;
		for(String s : input) {
			righeInput.put(i, s);
			i++;
		}
	}
	
	public List<String> getOutput() {
		return new ArrayList<>(righeOutput.values());
	}
	
	public Map<String,List<String>> getInputOutput(){
		final Map<String,List<String>> out = new HashMap<>();
		int i = 1;
		
		while(true) {
			if(righeInput.get(i) == null || righeOutput.get(i) == null) break;
			
			List<String> app = out.get(righeInput.get(i));
			if(app == null) app = new LinkedList<>();
			app.add(righeOutput.get(i+1)); //la prima riga contiene l'introduzione
			
			out.put(righeInput.get(i), app);
			i++;
		}
		return out;
	}
	
	
	
	
}
