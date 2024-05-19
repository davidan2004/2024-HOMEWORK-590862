package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class DiaDiaSimulatorTest {
	private DiaDia diadiaSimulator;
	private IOSimulator io;
	private List<String> comandi;

	@Before
	public void setUp() {
		comandi = new LinkedList<>();
		io = new IOSimulator();
		diadiaSimulator = new DiaDia(io);
	}

	@Test
	public void testVaiNord() {
		comandi.add("vai nord");
		comandi.add("fine");
		io.setInput(comandi);
		diadiaSimulator.gioca();
		assertTrue(io.getInputOutput().get("vai nord").get(0).contains("Biblioteca"));
	}

	@Test
	public void testDueComandiVai() {
		comandi.add("vai sud"); 
		comandi.add("vai nord");
		comandi.add("fine");
		io.setInput(comandi);
		diadiaSimulator.gioca();
		assertTrue(io.getInputOutput().get("vai nord").get(0).contains("Atrio"));
	}
	
	@Test
	public void testComandoRipetuto() {
		Labirinto labirinto = new LabirintoBuilder()
		.addStanzaIniziale("camera")
		.addStanza("salone")
		.addStanzaVincente("cucina")
		.addAdiacenza("camera", "salone", "nord")
		.addAdiacenza("salone", "cucina", "nord")
		.getLabirinto();
		diadiaSimulator = new DiaDia(labirinto, io);
		
		comandi.add("vai nord");
		comandi.add("vai nord");
		comandi.add("fine");
		io.setInput(comandi);
		diadiaSimulator.gioca();
		assertTrue(io.getInputOutput().get("vai nord").get(0).contains("salone"));
		assertTrue(io.getInputOutput().get("vai nord").get(1).contains("cucina"));
	}
}