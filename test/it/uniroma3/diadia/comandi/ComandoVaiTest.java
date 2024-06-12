package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	private Partita partita;
	private Stanza stanzaIniziale;
	private AbstractComando comandoNullo;
	private AbstractComando comandoSud;
	private AbstractComando comandoNord;
	private AbstractComando comandoEst;
	private AbstractComando comandoOvest;

	@Before
	public void setUp() {
		Stanza stanzaANord = new Stanza("Stanza a nord");
		Stanza stanzaASud = new Stanza("Stanza a sud");
		partita = new Partita();
		stanzaIniziale = partita.getStanzaCorrente();
		stanzaIniziale.impostaStanzaAdiacente(Direzione.NORD, stanzaANord);
		stanzaANord.impostaStanzaAdiacente(Direzione.SUD, stanzaIniziale);
		stanzaIniziale.impostaStanzaAdiacente(Direzione.SUD, stanzaASud);
		stanzaASud.impostaStanzaAdiacente(Direzione.NORD, stanzaIniziale);
		
		comandoNullo = new ComandoVai();
		comandoNullo.setParametro(null);
		
		comandoSud = new ComandoVai();
		comandoSud.setParametro("sud");
		
		comandoNord = new ComandoVai();
		comandoNord.setParametro("nord");
		
		comandoEst = new ComandoVai();
		comandoEst.setParametro("est");
		
		comandoOvest = new ComandoVai();
		comandoOvest.setParametro("ovest");
	}

	@Test
	public void testEseguiDirezioneNulla() {
		comandoNullo.esegui(partita);
		assertEquals(stanzaIniziale,partita.getStanzaCorrente());
	}

	@Test
	public void testEseguiDirezioneEsistente() {
		comandoNord.esegui(partita);
		assertEquals(stanzaIniziale.getStanzaAdiacente(Direzione.NORD),partita.getStanzaCorrente());
	}

	@Test
	public void testEseguiDueDirezioniEsistenti() {
		comandoSud.esegui(partita);
		comandoNord.esegui(partita);
		assertEquals(stanzaIniziale.getStanzaAdiacente(Direzione.SUD).getStanzaAdiacente(Direzione.NORD),partita.getStanzaCorrente());
	}
	
	@Test
	public void testEseguiDirezioniMultiple() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("inizio")
		.addStanza("stanza").addStanzaVincente("fine")
		.addAdiacenza("inizio", "stanza", Direzione.EST)
		.addAdiacenza("stanza", "fine", Direzione.SUD).getLabirinto();
		partita.setLabirinto(labirinto);
		
		comandoEst.esegui(partita);
		comandoSud.esegui(partita);
		assertEquals(labirinto.getStanzaVincente(),partita.getStanzaCorrente());
	}
	
	@Test
	public void testEseguiQuattroDirezioni() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("inizio")
		.addStanza("stanza").addStanza("stanzetta").addStanza("stanzina")
		.addStanzaVincente("fine").addAdiacenza("inizio", "stanza", Direzione.OVEST)
		.addAdiacenza("stanza", "stanzetta", Direzione.NORD).addAdiacenza("stanzetta", "stanzina", Direzione.SUD)
		.addAdiacenza("stanzina","fine",Direzione.EST).getLabirinto();
		partita.setLabirinto(labirinto);
	
	comandoOvest.esegui(partita);
	comandoNord.esegui(partita);
	comandoSud.esegui(partita);
	comandoEst.esegui(partita);
	assertEquals(labirinto.getStanzaVincente(),partita.getStanzaCorrente());
	
	}
}
