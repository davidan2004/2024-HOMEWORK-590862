package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsaVuota;
	private Borsa borsaAttrezzo;
	private Borsa borsaDueAttrezzi;
	private Borsa borsaOrdinamentoPeso;
	private Borsa borsaOrdinamentoNome;
	private Borsa borsaStessoPeso;
	
	@Before
	public void setUp() {
		borsaVuota = new Borsa();
		
		borsaAttrezzo = new Borsa();
		borsaAttrezzo.addAttrezzo(new Attrezzo("Spada",3));
		
		borsaDueAttrezzi = new Borsa();
		borsaDueAttrezzi.addAttrezzo(new Attrezzo("Scudo",1));
		borsaDueAttrezzi.addAttrezzo(new Attrezzo("Roccia",2));
		
		borsaOrdinamentoPeso = new Borsa(50);
		borsaOrdinamentoPeso.addAttrezzo(new Attrezzo("piombo",10));
		borsaOrdinamentoPeso.addAttrezzo(new Attrezzo("ps",5));
		borsaOrdinamentoPeso.addAttrezzo(new Attrezzo("piuma",1));
		borsaOrdinamentoPeso.addAttrezzo(new Attrezzo("libro",5));
		
		borsaOrdinamentoNome = new Borsa(50);
		borsaOrdinamentoNome.addAttrezzo(new Attrezzo("piombo",10));
		borsaOrdinamentoNome.addAttrezzo(new Attrezzo("piombo",7));
		borsaOrdinamentoNome.addAttrezzo(new Attrezzo("scudo",1));
		borsaOrdinamentoNome.addAttrezzo(new Attrezzo("libro",5));
		
		borsaStessoPeso = new Borsa(50);
		borsaStessoPeso.addAttrezzo(new Attrezzo("1peso1",1));
		borsaStessoPeso.addAttrezzo(new Attrezzo("2peso1",1));
		borsaStessoPeso.addAttrezzo(new Attrezzo("1peso5",5));
		borsaStessoPeso.addAttrezzo(new Attrezzo("2peso5",5));
		borsaStessoPeso.addAttrezzo(new Attrezzo("3peso5",5));
		borsaStessoPeso.addAttrezzo(new Attrezzo("1peso10",10));
	}

	@Test
	public void testBorsaVuota() {
		assertTrue(borsaVuota.isEmpty());
	}
	
	@Test
	public void testBorsaNonVuota() {
		assertFalse(borsaAttrezzo.isEmpty());
	}
	
	@Test
	public void testRicercaAttrezzoGiusto() {
		assertNotNull(borsaAttrezzo.getAttrezzo("Spada"));
	}
	
	@Test
	public void testRicercaAttrezzoSbagliato() {
		assertNull(borsaAttrezzo.getAttrezzo("Roccia"));
	}
	
	@Test
	public void testRicercaAttrezzoInBorsaConDueAttrezzi() {
		assertNotNull(borsaDueAttrezzi.getAttrezzo("Roccia"));
	}
     
	@Test
	public void testRicercaAttrezzoSbagliatoInBorsaConDueAttrezzi() {
		assertNull(borsaDueAttrezzi.getAttrezzo("Spada"));
	}
	
	@Test
	public void testRimozioneAttrezzoBorsaVuota() {
		assertNull(borsaVuota.removeAttrezzo("Spada"));
	}
	
	@Test
	public void testRimozioneAttrezzoGiusto() {
		assertNotNull(borsaAttrezzo.removeAttrezzo("Spada"));
	}
	
	@Test
	public void testRimozioneAttrezzoSbagliato() {
		assertNull(borsaDueAttrezzi.removeAttrezzo("Martello"));
	}	
	@Test
	public void testSuperamentoLimitePeso() {
		assertFalse(borsaVuota.addAttrezzo(new Attrezzo("Martello",borsaVuota.getPesoMax()+1)));	
	}

	@Test
	public void testOrdinamentoPerPesoBorsaVuota() {
		assertTrue(borsaVuota.getContenutoOrdinatoPerPeso().isEmpty());
	}

	@Test
	public void testOrdinamentoPerPesoBorsaConDueAttrezzi() {
		Iterator<Attrezzo> i = borsaDueAttrezzi.getContenutoOrdinatoPerPeso().iterator();
		assertEquals(new Attrezzo("Scudo",1),i.next());
		assertEquals(new Attrezzo("Roccia",2),i.next());
	}
	
	@Test
	public void testOrdinamentoPerPesoBorsaConAttrezziDelloStessoPeso() {
		Iterator<Attrezzo> i = borsaOrdinamentoPeso.getContenutoOrdinatoPerPeso().iterator();
		assertEquals(new Attrezzo("piuma",1),i.next());
		assertEquals(new Attrezzo("libro",5),i.next());
		assertEquals(new Attrezzo("ps",5),i.next());
		assertEquals(new Attrezzo("piombo",10),i.next());
	}
	
	@Test
	public void testOrdinamentoPerNomeBorsaVuota() {
		assertTrue(borsaVuota.getContenutoOrdinatoPerNome().isEmpty());
	}

	@Test
	public void testOrdinamentoPerNomeBorsaConDueAttrezzi() {
		Iterator<Attrezzo> i = borsaDueAttrezzi.getContenutoOrdinatoPerNome().iterator();
		assertEquals(new Attrezzo("Roccia",2),i.next());
		assertEquals(new Attrezzo("Scudo",1),i.next());
	}
	
	@Test
	public void testOrdinamentoPerNomeBorsaConAttrezziDelloStessoNome() {
		Iterator<Attrezzo> i = borsaOrdinamentoNome.getContenutoOrdinatoPerNome().iterator();
		assertEquals(new Attrezzo("libro",5),i.next());
		assertEquals(new Attrezzo("piombo",7),i.next());
		assertEquals(new Attrezzo("piombo",10),i.next());
		assertEquals(new Attrezzo("scudo",1),i.next());
	}
	
	@Test
	public void testContenutoRaggruppatoPerPesoBorsaVuota() {
		assertTrue(borsaVuota.getContenutoRaggruppatoPerPeso().isEmpty());
	}

	@Test
	public void testContenutoRaggruppatoPerPesoBorsaPesiDiversi() {
		Iterator<Attrezzo> peso1 = borsaDueAttrezzi.getContenutoRaggruppatoPerPeso().get(1).iterator();
		Iterator<Attrezzo> peso2 = borsaDueAttrezzi.getContenutoRaggruppatoPerPeso().get(2).iterator();
		assertEquals(new Attrezzo("Scudo",1),peso1.next());
		assertEquals(new Attrezzo("Roccia",2),peso2.next());
	}

	@Test
	public void testContenutoRaggruppatoPerPesoBorsaPesiUguali() {
		Iterator<Attrezzo> peso1 = borsaStessoPeso.getContenutoRaggruppatoPerPeso().get(1).iterator();
		Iterator<Attrezzo> peso5 = borsaStessoPeso.getContenutoRaggruppatoPerPeso().get(5).iterator();
		Iterator<Attrezzo> peso10 = borsaStessoPeso.getContenutoRaggruppatoPerPeso().get(10).iterator();		
		assertEquals(1,peso1.next().getPeso());
		assertEquals(1,peso1.next().getPeso());
		assertEquals(5,peso5.next().getPeso());
		assertEquals(5,peso5.next().getPeso());
		assertEquals(5,peso5.next().getPeso());
		assertEquals(10,peso10.next().getPeso());
	}

	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		Iterator<Attrezzo> i = borsaOrdinamentoPeso.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals(new Attrezzo("piuma",1),i.next());
		assertEquals(new Attrezzo("libro",5),i.next());
		assertEquals(new Attrezzo("ps",5),i.next());
		assertEquals(new Attrezzo("piombo",10),i.next());
	}
	
}