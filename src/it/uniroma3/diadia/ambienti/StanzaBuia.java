package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzoLuminoso;
	
	public StanzaBuia(String nomeStanza, String nomeAttrezzo) {
		super(nomeStanza);
		this.nomeAttrezzoLuminoso = nomeAttrezzo;
		
	}
	
	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(nomeAttrezzoLuminoso))
			return "qui c'è buio pesto";
		else
			return super.getDescrizione();
	}

}
