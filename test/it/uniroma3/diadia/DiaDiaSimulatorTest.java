package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class DiaDiaSimulatorTest {
	private DiaDia diadiaSimulator;
	private IOSimulator io;
	private List<String> comandi;
	private final String testoLabirinto = "Stanze: Atrio, Biblioteca\n"
	+ "Inizio: Atrio\n" + "Vincente: Biblioteca\n" + "Attrezzi: spada 5 Atrio\n" +
	"Uscite: Atrio nord Biblioteca, Biblioteca sud Atrio"; 

	@Before
	public void setUp() {
		comandi = new LinkedList<>();
		io = new IOSimulator();
		StringReader reader = new StringReader(testoLabirinto);
		diadiaSimulator = new DiaDia(Labirinto.newBuilder(reader).getLabirinto(), io);
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
		comandi.add("vai nord"); 
		comandi.add("vai sud");
		comandi.add("fine");
		io.setInput(comandi);
		diadiaSimulator.gioca();
		assertTrue(io.getInputOutput().get("vai nord").get(0).contains("Biblioteca"));
	}
	
	@Test
	public void testComandoRipetuto() {
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
		.addStanzaIniziale("camera")
		.addStanza("salone")
		.addStanzaVincente("cucina")
		.addAdiacenza("camera", "salone", Direzione.NORD)
		.addAdiacenza("salone", "cucina", Direzione.NORD)
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