package ch.hslu.informatik.gastgewerbe.persister.test;


import static org.junit.Assert.assertTrue;


import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.RolleTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.test.InitHelper;

import java.util.List;


public class BenutzerDAOImplTest {
	
	private static BenutzerDAO pBenutzer = new BenutzerDAOImpl();

	private static Logger logger = LogManager.getLogger(BenutzerDAOImplTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InitHelper.resetDb();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitHelper.deleteAllBenutzer();
	}

	@Before
	public void setUp() throws Exception {
		InitHelper.deleteAllBenutzer();
	}

	@After
	public void tearDown() throws Exception {
	}

	private void init() throws Exception {
		InitHelper.initBenutzer();
	}

	@Test
	public void testSave() throws Exception {
		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);
	}

	@Test
	public void testUpdate() throws Exception{
		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);

		Benutzer b = pBenutzer.findAll().get(0);
		long id = b.getId();

		b.setRolle(RolleTyp.KELLNER);

		pBenutzer.update(b);

		b=pBenutzer.findById(id);

		assertTrue(b.getRolle().equals(RolleTyp.KELLNER));
	}

	@Test
	public void testDelete() throws Exception{
		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);

		Benutzer b = pBenutzer.findAll().get(0);

		pBenutzer.delete(b);
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER - 1);
	}

	@Test
	public void testDeleteById() throws Exception {

		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);

		/* Den ersten Benutzer holen */
		Benutzer b = pBenutzer.findAll().get(0);

		/* Den ersten Benutzer löschen und kontrollieren */
		pBenutzer.deleteById(b.getId());

		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER - 1);
	}

	@Test
	public void testFindByBenutzername() throws Exception {

		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);

		Benutzer b = pBenutzer.findAll().get(0);

		String benutzername = b.getCredentials().getBenutzername();
		Benutzer bNachBenutzername = pBenutzer.findByBenutzername(benutzername);
		assertTrue(b.equals(bNachBenutzername));
	}

	@Test
	public void testFindByRolleTyp() throws Exception {

		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);

		assertTrue(pBenutzer.findByRolleTyp(RolleTyp.BAR_MITARBEITER).size() == 2);
		assertTrue(pBenutzer.findByRolleTyp(RolleTyp.KELLNER).size() == 2);

	}

	@Test
	public void testFindById() throws Exception {

		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);

		/* Den ersten Benutzer holen */
		Benutzer b = pBenutzer.findAll().get(0);

		long id = b.getId();
		Benutzer bNachId = pBenutzer.findById(id);
		assertTrue(b.equals(bNachId));
	}

	@Test
	public void testfindByNachnameUndVorname() throws Exception{
		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);

		Benutzer b = pBenutzer.findAll().get(1);

		long id = b.getId();

		String nachname = b.getNachname();
		String vorname = b.getVorname();



		List<Benutzer> bNachVorNach = pBenutzer.findByNachnameUndVorname(nachname, vorname);
		assertTrue(bNachVorNach.contains(pBenutzer.findById(id)));
	}

	@Test
	public void testFindAll() throws Exception {
		init();
		assertTrue(pBenutzer.findAll().size() == InitHelper.INIT_SIZE_BENUTZER);
	}

}
