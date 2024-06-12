package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private Direzione direzioneBloccata;
	private String nomeAttrezzoSbloccante;
	
	public StanzaBloccata(String nome,Direzione direzione,String nomeAttrezzo) {
		super(nome);
		direzioneBloccata = direzione;
		nomeAttrezzoSbloccante = nomeAttrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(!super.hasAttrezzo(nomeAttrezzoSbloccante))
			if(direzioneBloccata.equals(direzione))
				return this;
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		StringBuilder risultato = new StringBuilder("");
		
		risultato.append("Direzione bloccata: " + direzioneBloccata+"\n");
		risultato.append("Attrezzo sbloccante "+ nomeAttrezzoSbloccante);
		if(super.hasAttrezzo(nomeAttrezzoSbloccante))
			risultato.append(" presente nella stanza\n");
		else
			risultato.append(" non presente nella stanza\n" );
		
		return super.getDescrizione() + risultato.toString(); 
	}
	
	@Override
	public boolean isBloccata() {
		return true;
	}
}
