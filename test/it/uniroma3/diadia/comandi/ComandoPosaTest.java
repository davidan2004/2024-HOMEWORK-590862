package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	private Partita partita;
	private AbstractComando comandoPosaNull;
	private AbstractComando comandoPosaAttrezzo;
	
	@Before
	public void setUp() {
		partita = new Partita();
		partita.getGiocatore().addAttrezzo(new Attrezzo("attrezzo"));
		
		comandoPosaNull = new ComandoPosa();
		comandoPosaNull.setParametro(null);
		
		comandoPosaAttrezzo = new ComandoPosa();
		comandoPosaAttrezzo.setParametro("attrezzo");
	}

	@Test
	public void testPosaAttrezzoNullo() {
		comandoPosaNull.esegui(partita);
		assertTrue(partita.getGiocatore().hasAttrezzo("attrezzo"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testPosaAttrezzoNonNullo() {
		comandoPosaAttrezzo.esegui(partita);
		assertFalse(partita.getGiocatore().hasAttrezzo("attrezzo"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
	
	

}
