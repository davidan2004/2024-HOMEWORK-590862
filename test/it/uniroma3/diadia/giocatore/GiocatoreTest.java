package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {
	private Giocatore giocatoreCon10Cfu;
	private Giocatore giocatoreConAttrezzo;
	private Attrezzo spada;
	
	@Before
	public void setUp() {
		giocatoreCon10Cfu = new Giocatore();
		giocatoreCon10Cfu.setCfu(10);
		
		spada = new Attrezzo("Spada",1);
		giocatoreConAttrezzo = new Giocatore();
		giocatoreConAttrezzo.addAttrezzo(spada);
	
	}

	@Test
	public void testGetCfu() {
		assertEquals(10,giocatoreCon10Cfu.getCfu());
	}
	
	@Test
	public void testGetAttrezzo() {
		assertNotNull(giocatoreConAttrezzo.getAttrezzo("Spada"));
	}
	
	@Test
	public void testRimuoviAttrezzo() {
		assertEquals(spada,giocatoreConAttrezzo.removeAttrezzo("Spada"));
		
	}

}
