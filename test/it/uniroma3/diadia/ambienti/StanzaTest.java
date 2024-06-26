package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private Stanza stanzaIsolata;
	private Stanza stanzaAdiacenteASud;
	private Stanza stanzaAdiacenteANord;
	
	private Stanza stanzaSenzaAttrezzi;
	private Stanza stanzaConUnAttrezzo;
	private Stanza stanzaConDueAttrezzi;
	
	
	@Before
	public void setUp() {
		/*Test adiacenza stanze*/
		stanzaIsolata = new Stanza("Stanza 1");
		
		stanzaAdiacenteASud = new Stanza("Stanza 2");
		stanzaAdiacenteANord = new Stanza("Stanza 3");
		stanzaAdiacenteASud.impostaStanzaAdiacente(Direzione.SUD,stanzaAdiacenteANord);
		stanzaAdiacenteANord.impostaStanzaAdiacente(Direzione.NORD,stanzaAdiacenteASud);
		
		/*Test attrezzi stanze*/
		stanzaSenzaAttrezzi = new Stanza("Stanza 4");
		
		stanzaConUnAttrezzo = new Stanza("Stanza 5");
		stanzaConUnAttrezzo.addAttrezzo(new Attrezzo("Spada",0));
		
		stanzaConDueAttrezzi = new Stanza("Stanza 6");
		stanzaConDueAttrezzi.addAttrezzo(new Attrezzo("Roccia",0));
		stanzaConDueAttrezzi.addAttrezzo(new Attrezzo("Scudo",0));
	}
	/*Test adiacenza stanze*/
		
	@Test
	public void testStanzaIsolata() {
		assertNull(stanzaIsolata.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testStanzaAdiacenteNellaGiustaDirezione() {
		assertEquals(stanzaAdiacenteASud,stanzaAdiacenteANord.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testStanzaAdiacenteInDirezioneSbagliata() {
		assertNull(stanzaAdiacenteASud.getStanzaAdiacente(Direzione.NORD));
	}
	
	/*Test attrezzi stanze*/
	
	@Test
	public void testStanzaSenzaAttrezzi() {
		assertNull(stanzaSenzaAttrezzi.getAttrezzo("Spada"));
	}
	
	@Test
	public void testStanzaConAttrezzo() {
		assertNotNull(stanzaConUnAttrezzo.getAttrezzo("Spada"));
	}
	
	@Test
	public void testStanzaConDueAttrezzi() {
		assertNotNull(stanzaConDueAttrezzi.getAttrezzo("Roccia"));
		assertNotNull(stanzaConDueAttrezzi.getAttrezzo("Scudo"));
	}
	@Test
	public void testRimozioneAttrezzoDallaStanza() {
		stanzaConDueAttrezzi.removeAttrezzo(stanzaConDueAttrezzi.getAttrezzo("Roccia"));
		assertNull(stanzaConDueAttrezzi.getAttrezzo("Roccia"));
		assertNotNull(stanzaConDueAttrezzi.getAttrezzo("Scudo"));
	}

}
