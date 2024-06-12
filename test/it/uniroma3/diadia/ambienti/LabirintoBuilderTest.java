package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class LabirintoBuilderTest {
	private Labirinto vuoto;
	private Labirinto monolocale;
	private Labirinto bilocale;
	private Labirinto trilocale;
	
	@Before
	public void setUp() {
		vuoto = new LabirintoBuilder()
		.getLabirinto();
		
		monolocale = new LabirintoBuilder()
		.addStanzaIniziale("salotto")
		.addStanzaVincente("salotto")
		.getLabirinto();
		
		bilocale = new LabirintoBuilder()
		.addStanzaIniziale("salotto")
		.addStanzaVincente("camera")
		.addAttrezzo("letto",10)
		.addAdiacenza("salotto", "camera", Direzione.NORD)
		.getLabirinto();
		
		trilocale = new LabirintoBuilder()
		.addStanzaIniziale("salotto")
		.addStanza("cucina")
		.addAttrezzo("pentola",1)
		.addStanzaVincente("camera")
		.addAdiacenza("salotto", "cucina", Direzione.NORD)
		.addAdiacenza("cucina", "camera", Direzione.EST)
		.getLabirinto();
		
	}

	@Test
	public void testLabirintoVuoto() {
		assertNull(vuoto.getStanzaIniziale());
		assertNull(vuoto.getStanzaVincente());
	}
	
	@Test
	public void testLabirintoMonolocale() {
		Stanza salotto = new Stanza("salotto");
		assertEquals(salotto,monolocale.getStanzaIniziale());
		assertEquals(salotto,monolocale.getStanzaVincente());
	}
	
	@Test
	public void testLabirintoBilocaleAttrezzo() {
		assertEquals(new Attrezzo("letto",10),
		bilocale.getStanzaVincente().getAttrezzo("letto"));
	}
	
	@Test
	public void testLabirintoBilocaleAdiacenza() {
		assertEquals("camera",
		bilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
	}

	@Test
	public void testLabirintoTrilocaleAdiacenze() {
		Stanza cucina = trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD);
		assertEquals("cucina",cucina.getNome());
		assertEquals("camera",cucina.getStanzaAdiacente(Direzione.EST).getNome());
	}
	
	@Test
	public void testLabirintoTrilocaleAttrezzo() {
		Stanza cucina = trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD);
		assertEquals(new Attrezzo("pentola",1),cucina.getAttrezzo("pentola"));
	}
}
