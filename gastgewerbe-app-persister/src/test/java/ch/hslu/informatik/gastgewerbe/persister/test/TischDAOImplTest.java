package ch.hslu.informatik.gastgewerbe.persister.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hslu.informatik.gastgewerbe.model.Tisch;
import ch.hslu.informatik.gastgewerbe.persister.TischDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.TischDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.InitHelper;

public class TischDAOImplTest {
	
	private static TischDAO pTisch = new TischDAOImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InitHelper.resetDb();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitHelper.deleteAllTisch();
	}

	@Before
	public void setUp() throws Exception {
		InitHelper.deleteAllTisch();
	}

	@After
	public void tearDown() throws Exception {
	}

	private void init() throws Exception {
		InitHelper.initTisch();
	}

	@Test
	public final void testSave() throws Exception {
		init();
		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH);
	}

	@Test
	public final void testUpdate() throws Exception {

		init();
		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH);

		Tisch ersterTisch = pTisch.findAll().get(0);

		int tischNr = ersterTisch.getTischNr();

		ersterTisch.setTischNr(tischNr);

		pTisch.update(ersterTisch);

		Tisch tischFromDb = pTisch.findByTischNr(tischNr);

		assertTrue(tischNr == tischFromDb.getTischNr());
	}

	@Test
	public final void testDelete() throws Exception {

		init();
		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH);

		Tisch ersterTisch = pTisch.findAll().get(0);
		pTisch.delete(ersterTisch);

		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH - 1);
	}

	@Test
	public final void testDeleteById() throws Exception {

		init();
		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH);

		Tisch ersteCredentials = pTisch.findAll().get(0);
		pTisch.deleteById(ersteCredentials.getId());

		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH - 1);
	}

	@Test
	public final void testFindById() throws Exception {

		init();
		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH);

		Tisch ersterTisch = pTisch.findAll().get(0);

		long id = ersterTisch.getId();

		assertTrue(ersterTisch.equals(pTisch.findById(id)));
	}

	@Test
	public final void testFindByNummer() throws Exception {

		init();
		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH);

		Tisch ersterTisch = pTisch.findAll().get(0);

		int tischNr = ersterTisch.getTischNr();

		assertTrue(ersterTisch.equals(pTisch.findByTischNr(tischNr)));
	}

	@Test
	public final void testFindAll() throws Exception {
		init();
		assertTrue(pTisch.findAll().size() == InitHelper.INIT_SIZE_TISCH);
	}

}


