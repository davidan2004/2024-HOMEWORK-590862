package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private Stanza bloccataDaChiaveSud;
	private Stanza bloccataDaLucchettoNord;
	
	@Before
	public void setUp() {
		bloccataDaChiaveSud = new StanzaBloccata("Stanza 1", "sud", "chiave");
		bloccataDaLucchettoNord = new StanzaBloccata("Stanza 2", "nord", "lucchetto");
		
		bloccataDaChiaveSud.impostaStanzaAdiacente("sud", bloccataDaLucchettoNord);
		bloccataDaLucchettoNord.impostaStanzaAdiacente("nord", bloccataDaChiaveSud);
	}

	@Test
	public void testDirezioneNonBloccata() {
		assertNotEquals(bloccataDaChiaveSud.getStanzaAdiacente("est"),bloccataDaChiaveSud);
	}

	@Test
	public void testDirezioneBloccata() {
		assertEquals(bloccataDaChiaveSud.getStanzaAdiacente("sud"),bloccataDaChiaveSud);
	}
	
	@Test
	public void testAttrezzoSbloccanteSingolo() {
		bloccataDaChiaveSud.addAttrezzo(new Attrezzo("chiave"));
		assertEquals(bloccataDaChiaveSud.getStanzaAdiacente("sud"),bloccataDaLucchettoNord);
		assertEquals(bloccataDaLucchettoNord.getStanzaAdiacente("nord"),bloccataDaLucchettoNord);
	}

	@Test
	public void testAttrezzoSbloccanteDoppio() {
		bloccataDaChiaveSud.addAttrezzo(new Attrezzo("chiave"));
		bloccataDaLucchettoNord.addAttrezzo(new Attrezzo("lucchetto"));
		assertEquals(bloccataDaChiaveSud.getStanzaAdiacente("sud"),bloccataDaLucchettoNord);
		assertEquals(bloccataDaLucchettoNord.getStanzaAdiacente("nord"), bloccataDaChiaveSud);
	}
}
