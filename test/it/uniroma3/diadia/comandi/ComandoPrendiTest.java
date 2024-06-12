package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {
	private Partita partita;
	private AbstractComando comandoPrendiNull;
	private AbstractComando comandoPrendiAttrezzo;
	
	@Before
	public void setUp() {
		partita = new Partita();
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("attrezzo"));
		
		comandoPrendiNull = new ComandoPrendi();
		comandoPrendiNull.setParametro(null);
		
		comandoPrendiAttrezzo = new ComandoPrendi();
		comandoPrendiAttrezzo.setParametro("attrezzo");
	}

	@Test
	public void testPrendiAttrezzoNullo() {
		comandoPrendiNull.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		assertFalse(partita.getGiocatore().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testPrendiAttrezzoNonNullo() {
		comandoPrendiAttrezzo.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		assertTrue(partita.getGiocatore().hasAttrezzo("attrezzo"));
	}
}
