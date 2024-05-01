package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private Stanza stanzaBuiaSenzaAttrezzi;
	private Stanza stanzaBuiaConAttrezzoSbagliato;
	private Stanza stanzaBuiaConAttrezzoGiusto;
	
	@Before
	public void setUp() {
		stanzaBuiaSenzaAttrezzi = new StanzaBuia("Stanza 1", "attrezzo");
		
		stanzaBuiaConAttrezzoSbagliato = new StanzaBuia("Stanza 2", "torcia");
		stanzaBuiaConAttrezzoSbagliato.addAttrezzo(new Attrezzo("lanterna"));
		
		stanzaBuiaConAttrezzoGiusto = new StanzaBuia("Stanza 3", "lampada");
		stanzaBuiaConAttrezzoGiusto.addAttrezzo(new Attrezzo("lampada"));
	}

	@Test
	public void testStanzaBuiaSenzaAttrezzi() {
		assertEquals("qui c'è buio pesto", stanzaBuiaSenzaAttrezzi.getDescrizione());
	}
	
	@Test
	public void testStanzaBuiaConAttrezzoSbagliato() {
		assertEquals("qui c'è buio pesto", stanzaBuiaConAttrezzoSbagliato.getDescrizione());
	}
	
	@Test
	public void testStanzaBuiaConAttrezzoGiusto() {
		assertNotEquals("qui c'è buio pesto", stanzaBuiaConAttrezzoGiusto.getDescrizione());
	}

}
