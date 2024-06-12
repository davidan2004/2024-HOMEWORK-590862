package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class TestCaricatoreLabirinto {
	private Labirinto labirinto;
	private StringReader lettore;

	@Test
	public void testLabirintoMonolocale() {
		String specificaLabirinto = "Stanze: N10\n" + "Inizio: N10\n" + "Vincente: N10\n" +
				"Attrezzi: martello 10 N10\n";
		lettore = new StringReader(specificaLabirinto);
		labirinto = Labirinto.newBuilder(lettore).getLabirinto();
		
		assertEquals(labirinto.getStanzaIniziale().getNome(),"N10");
		assertEquals(labirinto.getStanzaIniziale().getAttrezzo("martello").getPeso(),10);
	}
	
	@Test
	public void testLabirintoBilocale() {
		String specificaLabirinto = "Stanze: N10, N11\n" + "Inizio: N10\n" + "Vincente: N11\n" +
				"Attrezzi: spada 20 N11, martello 5 N10\n" + "Uscite: N10 sud N11, N11 nord N10\n" +
				"Personaggi: nomeMago ciao Mago N10\n";
		lettore = new StringReader(specificaLabirinto);
		labirinto = Labirinto.newBuilder(lettore).getLabirinto();
		
		assertEquals("nomeMago",labirinto.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals(labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.SUD).getNome(), 
				"N11");
		assertEquals(labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.SUD)
				.getStanzaAdiacente(Direzione.NORD).getNome(),"N10");
	}

	@Test
	public void testLabirintoTriLocale() {
		String specificaLabirinto = "Stanze: N10, N11 Magica 15, N12 Bloccata nord lucchetto\n" + "Inizio: N11\n" + "Vincente: N12\n" +
				"Attrezzi: martello 15 N12\n" + "Uscite: N11 est N12, N12 ovest N11, N10 nord N11, N11 sud N10\n";
		lettore = new StringReader(specificaLabirinto);
		labirinto = Labirinto.newBuilder(lettore).getLabirinto();
		
		assertTrue(labirinto.getStanzaIniziale().isMagica());
		assertTrue(labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.EST).isBloccata());
	}

}
