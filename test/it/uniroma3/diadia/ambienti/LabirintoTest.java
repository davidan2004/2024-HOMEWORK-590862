package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.*;

public class LabirintoTest {	
	
	@Test
	public void testStanzaVincente() {
		assertNotNull(new Labirinto().getStanzaVincente());
	}
	
	@Test
	public void testStanzaIniziale() {
		assertNotNull(new Labirinto().getStanzaIniziale());
	}

}
