package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	private Partita partita;
	private Stanza stanzaIniziale;
	private Comando comandoNullo;
	private Comando comandoSud;
	private Comando comandoNord;

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
}
