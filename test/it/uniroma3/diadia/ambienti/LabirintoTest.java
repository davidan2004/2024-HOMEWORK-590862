package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.*;

public class LabirintoTest {	
	
	@Test
	public void testStanzaVincente() {
		assertNotNull(Labirinto.newBuilder(new Stanza("1"),new Stanza("2")).getLabirinto().getStanzaVincente());
	}
	
	@Test
	public void testStanzaIniziale() {
		assertNotNull(Labirinto.newBuilder(new Stanza("1"),new Stanza("2")).getLabirinto().getStanzaIniziale());
	}

}
