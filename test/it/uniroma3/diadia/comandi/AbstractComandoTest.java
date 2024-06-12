package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class AbstractComandoTest {

	@Test
	public void testAbstractComando() {
		AbstractComando comando = new AbstractComando() {
			
			@Override
			public String getNome() {
				return "nome";
			}
			
			@Override
			public String esegui(Partita partita) {
				return "esegui";
			}
		};
		
		assertEquals(comando.esegui(new Partita()),"esegui");
		assertEquals(comando.getNome(),"nome");
		assertEquals(comando.getParametro(),null);
	}

}
