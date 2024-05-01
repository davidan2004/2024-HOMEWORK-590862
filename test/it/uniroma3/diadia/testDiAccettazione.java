package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.*;

public class testDiAccettazione {
	private DiaDia diadiaSimulator;
	private IOSimulator io;

	@Before
	public void setUp() {
		io = new IOSimulator();
		diadiaSimulator = new DiaDia(io);
	}

	@Test
	public void testVaiNord() {
		String[] comandi = {"vai nord", "fine"};
		io.setInput(comandi);
		diadiaSimulator.gioca();
		assertTrue(io.getOutput()[1].contains("Biblioteca"));
	}

	@Test
	public void testDueComandiVai() {
		String[] comandi = {"vai sud", "vai nord", "fine"};
		io.setInput(comandi);
		diadiaSimulator.gioca();
		System.out.println(io.getOutput()[2]);
		assertTrue(io.getOutput()[2].contains("Atrio"));
	}
}