package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandi fabbrica = new FabbricaDiComandiFisarmonica();

	@Test
	public void testComandoNullo() {
		AbstractComando nullo = fabbrica.costruisciComando("");
		assertEquals("ComandoNonValido",nullo.getNome());
	}

	@Test
	public void testComandoSenzaParametro() {
		AbstractComando aiuto = fabbrica.costruisciComando("aiuto");
		assertEquals("ComandoAiuto",aiuto.getNome());
	}
	
	@Test
	public void testComandoConParametroNullo() {
		AbstractComando vai = fabbrica.costruisciComando("vai");
		assertEquals("ComandoVai",vai.getNome());
		assertNull(vai.getParametro());
	}

	@Test
	public void testComandoConParametroNonNullo() {
		AbstractComando vai = fabbrica.costruisciComando("vai nord");
		assertEquals("nord",vai.getParametro());
	}
	
	@Test
	public void testComandoConDueParametri() {
		AbstractComando prendi = fabbrica.costruisciComando("prendi attrezzo1 attrezzo2");
		assertEquals("attrezzo1",prendi.getParametro());
	}
}
