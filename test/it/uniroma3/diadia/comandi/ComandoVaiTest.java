package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	private Partita partita;
	private Stanza stanzaIniziale;
	private Comando comandoNullo;
	private Comando comandoSud;
	private Comando comandoNord;
	private Comando comandoEst;
	private Comando comandoOvest;

	@Before
	public void setUp() {
		partita = new Partita();
		stanzaIniziale = partita.getStanzaCorrente();
		
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
		assertEquals(stanzaIniziale.getStanzaAdiacente("nord"),partita.getStanzaCorrente());
	}

	@Test
	public void testEseguiDueDirezioniEsistenti() {
		comandoSud.esegui(partita);
		comandoNord.esegui(partita);
		assertEquals(stanzaIniziale.getStanzaAdiacente("sud").getStanzaAdiacente("nord"),partita.getStanzaCorrente());
	}
	
	@Test
	public void testEseguiDirezioniMultiple() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("inizio")
		.addStanza("stanza").addStanzaVincente("fine")
		.addAdiacenza("inizio", "stanza", "est")
		.addAdiacenza("stanza", "fine", "sud").getLabirinto();
		partita.setLabirinto(labirinto);
		
		comandoEst.esegui(partita);
		comandoSud.esegui(partita);
		assertEquals(labirinto.getStanzaVincente(),partita.getStanzaCorrente());
	}
	
	@Test
	public void testEseguiQuattroDirezioni() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("inizio")
		.addStanza("stanza").addStanza("stanzetta").addStanza("stanzina")
		.addStanzaVincente("fine").addAdiacenza("inizio", "stanza", "ovest")
		.addAdiacenza("stanza", "stanzetta", "nord").addAdiacenza("stanzetta", "stanzina", "sud")
		.addAdiacenza("stanzina","fine","est").getLabirinto();
		partita.setLabirinto(labirinto);
	
	comandoOvest.esegui(partita);
	comandoNord.esegui(partita);
	comandoSud.esegui(partita);
	comandoEst.esegui(partita);
	assertEquals(labirinto.getStanzaVincente(),partita.getStanzaCorrente());
	
	}
}
