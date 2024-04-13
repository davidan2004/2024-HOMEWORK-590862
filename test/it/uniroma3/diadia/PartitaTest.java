package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.*;

public class PartitaTest {
	private Partita partitaIniziale;
	private Partita partitaZeroCfu;
	private Partita partitaFinita;
	
	@Before
	public void setUp() {
		partitaIniziale = new Partita();
		
		partitaZeroCfu = new Partita();
		partitaZeroCfu.getGiocatore().setCfu(0);
		
		partitaFinita = new Partita();
		partitaFinita.setFinita();
	}
	
	
	@Test
	public void testPartitaNelloStatoIniziale() {
		assertFalse(partitaIniziale.isFinita());
	}
	
	@Test
	public void testPartitaConZeroCFU() {
		assertTrue(partitaZeroCfu.isFinita());
	}
	
	@Test
	public void testPartitaFinita() {
		assertTrue(partitaFinita.isFinita());
	}

}
