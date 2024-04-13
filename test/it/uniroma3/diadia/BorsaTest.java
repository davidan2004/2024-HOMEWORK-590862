package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	private Borsa borsaVuota;
	private Borsa borsaConAttrezzo;
	private Borsa borsaConDueAttrezzi;
	
	
	@Before
	public void setUp() {
		borsaVuota = new Borsa();
		
		borsaConAttrezzo = new Borsa();
		borsaConAttrezzo.addAttrezzo(new Attrezzo("Spada",3));
		
		borsaConDueAttrezzi = new Borsa();
		borsaConDueAttrezzi.addAttrezzo(new Attrezzo("Scudo",1));
		borsaConDueAttrezzi.addAttrezzo(new Attrezzo("Roccia",2));
		
	}

	@Test
	public void testBorsaVuota() {
		assertTrue(borsaVuota.isEmpty());
	}
	
	@Test
	public void testBorsaNonVuota() {
		assertFalse(borsaConAttrezzo.isEmpty());
	}
	
	@Test
	public void testRicercaAttrezzoGiusto() {
		assertNotNull(borsaConAttrezzo.getAttrezzo("Spada"));
	}
	
	@Test
	public void testRicercaAttrezzoSbagliato() {
		assertNull(borsaConAttrezzo.getAttrezzo("Roccia"));
	}
	
	@Test
	public void testRicercaAttrezzoInBorsaConDueAttrezzi() {
		assertNotNull(borsaConDueAttrezzi.getAttrezzo("Roccia"));
	}
     
	@Test
	public void testRicercaAttrezzoSbagliatoInBorsaConDueAttrezzi() {
		assertNull(borsaConDueAttrezzi.getAttrezzo("Spada"));
	}
	
	@Test
	public void testRimozioneAttrezzoBorsaVuota() {
		assertNull(borsaVuota.removeAttrezzo("Spada"));
	}
	
	@Test
	public void testRimozioneAttrezzoGiusto() {
		assertNotNull(borsaConAttrezzo.removeAttrezzo("Spada"));
	}
	
	@Test
	public void testRimozioneAttrezzoSbagliato() {
		assertNull(borsaConDueAttrezzi.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testSuperamentoLimitePeso() {
		assertFalse(borsaVuota.addAttrezzo(new Attrezzo("Martello",borsaVuota.getPesoMax()+1)));	
	}
}
