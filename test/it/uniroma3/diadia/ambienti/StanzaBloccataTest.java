package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private Stanza bloccataDaChiaveSud;
	private Stanza bloccataDaLucchettoNord;
	
	@Before
	public void setUp() {
		bloccataDaChiaveSud = new StanzaBloccata("Stanza 1", Direzione.SUD, "chiave");
		bloccataDaLucchettoNord = new StanzaBloccata("Stanza 2", Direzione.NORD, "lucchetto");
		
		bloccataDaChiaveSud.impostaStanzaAdiacente(Direzione.SUD, bloccataDaLucchettoNord);
		bloccataDaLucchettoNord.impostaStanzaAdiacente(Direzione.NORD, bloccataDaChiaveSud);
	}

	@Test
	public void testDirezioneNonBloccata() {
		assertNotEquals(bloccataDaChiaveSud.getStanzaAdiacente(Direzione.EST),bloccataDaChiaveSud);
	}

	@Test
	public void testDirezioneBloccata() {
		assertEquals(bloccataDaChiaveSud.getStanzaAdiacente(Direzione.SUD),bloccataDaChiaveSud);
	}
	
	@Test
	public void testAttrezzoSbloccanteSingolo() {
		bloccataDaChiaveSud.addAttrezzo(new Attrezzo("chiave"));
		assertEquals(bloccataDaChiaveSud.getStanzaAdiacente(Direzione.SUD),bloccataDaLucchettoNord);
		assertEquals(bloccataDaLucchettoNord.getStanzaAdiacente(Direzione.NORD),bloccataDaLucchettoNord);
	}

	@Test
	public void testAttrezzoSbloccanteDoppio() {
		bloccataDaChiaveSud.addAttrezzo(new Attrezzo("chiave"));
		bloccataDaLucchettoNord.addAttrezzo(new Attrezzo("lucchetto"));
		assertEquals(bloccataDaChiaveSud.getStanzaAdiacente(Direzione.SUD),bloccataDaLucchettoNord);
		assertEquals(bloccataDaLucchettoNord.getStanzaAdiacente(Direzione.NORD), bloccataDaChiaveSud);
	}
}
