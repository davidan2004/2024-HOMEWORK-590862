package it.uniroma3.diadia;

public class IOSimulator implements IO {
	
	private String[] righeInput;
	private String[] righeOutput;
	private int numeroRigheInput;
	private int numeroRigheOutput;
	
	public IOSimulator(){
		righeInput = new String[50];
		righeOutput = new String[50];
		numeroRigheInput = 0;
		numeroRigheOutput = 0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		if(numeroRigheOutput >= righeOutput.length) return;
		
		righeOutput[numeroRigheOutput++] = messaggio;
	}
	
	@Override
	public String leggiRiga() {
		if(numeroRigheInput >= righeInput.length) return null;
		
		return righeInput[numeroRigheInput++];
	}
	
	public void setInput(String[] input) {
		righeInput = input;
	}
	
	public String[] getOutput() {
		return righeOutput;
	}
	
	
	
}
