package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.ambienti.Labirinto;

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
