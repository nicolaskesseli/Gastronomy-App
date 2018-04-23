package ch.hslu.informatik.gastgewerbe.persister.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ch.hslu.informatik.gastgewerbe.model.Adresse;
import ch.hslu.informatik.gastgewerbe.model.Kontakt;
import ch.hslu.informatik.gastgewerbe.model.Person;
import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;


public class BenutzerDAOImplTest {
	
	private static BenutzerDAO pBenutzer = new BenutzerDAOImpl();

	private static Logger logger = LogManager.getLogger(BenutzerDAOImplTest.class);

	private static List<Person> personen;

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
}
