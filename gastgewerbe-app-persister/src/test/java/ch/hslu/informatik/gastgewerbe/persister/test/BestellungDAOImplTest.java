package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.InitHelper;
import org.junit.*;

import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class BestellungDAOImplTest {

	private static BestellungDAO pBestellungDAO = new BestellungDAOImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InitHelper.resetDb();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitHelper.deleteAllBestellung();
		InitHelper.deleteAllProdukt();
		InitHelper.deleteAllTisch();
	}

	@Before
	public void setUp() throws Exception {
		InitHelper.deleteAllBestellung();
		InitHelper.deleteAllProdukt();
		InitHelper.deleteAllTisch();
	}

	@After
	public void tearDown() throws Exception {
	}

	private void init() throws Exception {
		InitHelper.initTisch();
		InitHelper.initProdukt();
		InitHelper.initBestellung();
	}

	@Test
	public void testSave() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);
	}

	@Test
	public void testUpdate() throws Exception {
		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);
		assertTrue(b.getBestellungPositionListe().size() == 3);

		/* Die erste BestellungPosition wird gelöscht */
		b.getBestellungPositionListe().remove(0);

		/* Updaten */
		pBestellungDAO.update(b);

		/* Kontrollieren */
		assertTrue(pBestellungDAO.findAll().get(0).getBestellungPositionListe().size() == 2);
	}

	@Test
	public void testDelete() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		pBestellungDAO.delete(b);
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG - 1);
	}

	@Test
	public void testFindByZeit() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		LocalDateTime zeit = b.getZeit();

		assertTrue(b.equals(pBestellungDAO.findByZeit(zeit).get(0)));

	}

	@Test
	public void testFindById() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		long id = b.getId();

		assertTrue(b.equals(pBestellungDAO.findById(id)));
	}

	@Test
	public void testFindAll() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);
	}

	@Test
	public void testFindByTischNr() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		int TischNr = b.getTisch().getTischNr();

		assertTrue(b.equals(pBestellungDAO.findByTischNr(TischNr).get(0)));

	}

	@Test
	public void testfindByRechBezahlt() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		boolean bezahlt = b.isRechnungBezahlt();

		b.setRechnungBezahlt(true);
		pBestellungDAO.update(b);

		assertTrue(pBestellungDAO.findByRechBezahlt(true).get(0).isRechnungBezahlt() != bezahlt);

	}

	@Test
	public void testfindByBereit() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		boolean bereit = b.getBestellungPositionListe().get(0).isBestellungBereit();

		b.getBestellungPositionListe().get(0).setBestellungBereit(true);
		pBestellungDAO.update(b);

		assertTrue(pBestellungDAO.findByBereit(true).get(0).getBestellungPositionListe().get(0)
				.isBestellungBereit() != bereit);

	}

	@Test
	public void testfindByAusgeliefert() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		boolean ausgeliefert = b.getBestellungPositionListe().get(0).isBestellungAusgeliefert();

		b.getBestellungPositionListe().get(0).setBestellungAusgeliefert(true);
		pBestellungDAO.update(b);

		assertTrue(pBestellungDAO.findByAusgeliefert(true).get(0).getBestellungPositionListe().get(0)
				.isBestellungAusgeliefert() != ausgeliefert);

	}

	@Test
	public void testFindByRechBezahltTisch() throws Exception {

		init();
		assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

		Bestellung b = pBestellungDAO.findAll().get(0);

		boolean abgerechnet = b.isRechnungBezahlt();
		int TischNr = b.getTisch().getTischNr();

		List<Bestellung> list = pBestellungDAO.findByRechBezahltTisch(TischNr, abgerechnet);

		assertTrue(list.get(0).getTisch().getTischNr() == TischNr && list.get(0).isRechnungBezahlt() == abgerechnet);

	}

}
