package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;
import org.junit.*;

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
    }

    @Before
    public void setUp() throws Exception {
        InitHelper.deleteAllBestellung();
        InitHelper.deleteAllProdukt();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        InitHelper.initProdukt();
        InitHelper.initBestellung();
    }

    @Test
    public final void testSave() throws Exception {

        init();
        assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);
    }



}
