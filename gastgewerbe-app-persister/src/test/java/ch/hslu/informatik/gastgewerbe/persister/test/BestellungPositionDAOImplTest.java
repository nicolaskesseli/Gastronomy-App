package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.persister.BestellungPositionDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungPositionDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.InitHelper;
import org.junit.*;

import static junit.framework.TestCase.assertTrue;

public class BestellungPositionDAOImplTest {

    private static BestellungPositionDAO pBestPosDAO = new BestellungPositionDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        InitHelper.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        InitHelper.deleteAllBestellungPosition();
        InitHelper.deleteAllProdukt();
    }

    @Before
    public void setUp() throws Exception {
        InitHelper.deleteAllBestellungPosition();
        InitHelper.deleteAllProdukt();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        InitHelper.initProdukt();
        InitHelper.initBestellungPosition();
    }

    @Test
    public void testSave() throws Exception {

        init();
        assertTrue(pBestPosDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN);
    }

    @Test
    public void testUpdate() throws Exception{
        init();
        assertTrue(pBestPosDAO.findAll().size()==InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN);

        BestellungPosition p = pBestPosDAO.findAll().get(0);

        p.setAnzahl(1);

        /* Updaten */
        pBestPosDAO.update(p);

        /* Kontrollieren */
        assertTrue(pBestPosDAO.findById(p.getId()).getAnzahl() == 1);
    }

    @Test
    public void testDelete() throws Exception {

        init();
        assertTrue(pBestPosDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN);

        BestellungPosition p = pBestPosDAO.findAll().get(0);

        pBestPosDAO.delete(p);
        assertTrue(pBestPosDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN - 1);
    }

    @Test
    public void testFindById() throws Exception {

        init();
        assertTrue(pBestPosDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN);

        BestellungPosition p = pBestPosDAO.findAll().get(0);

        long id = p.getId();

        assertTrue(p.equals(pBestPosDAO.findById(id)));
    }

    @Test
    public void testFindAll() throws Exception {

        init();
        assertTrue(pBestPosDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN);
    }

    @Test
    public void testfindByBereit() throws Exception{

        init();
        assertTrue(pBestPosDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN);

        BestellungPosition p = pBestPosDAO.findAll().get(0);

        boolean bereit = p.isBestellungBereit();

        p.setBestellungBereit(true);
        pBestPosDAO.update(p);

        assertTrue(pBestPosDAO.findByBereit(true).get(0).isBestellungBereit()!=bereit);
    }

    @Test
    public void testfindByAusgeliefert() throws Exception{

        init();
        assertTrue(pBestPosDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNGPOSITIONEN);

        BestellungPosition p = pBestPosDAO.findAll().get(0);

        boolean ausgeliefert = p.isBestellungAusgeliefert();

        p.setBestellungAusgeliefert(true);
        pBestPosDAO.update(p);

        assertTrue(pBestPosDAO.findByAusgeliefert(true).get(0).isBestellungAusgeliefert()!=ausgeliefert);
    }
}
