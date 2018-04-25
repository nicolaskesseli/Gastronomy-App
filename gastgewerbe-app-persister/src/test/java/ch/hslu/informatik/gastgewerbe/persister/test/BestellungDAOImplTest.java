package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.InitHelper;
import org.junit.*;

import java.time.LocalDate;

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
    public void testSave() throws Exception {

         init();
        assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);
    }

    @Test
    public void testUpdate() throws Exception{
        init();
        assertTrue(pBestellungDAO.findAll().size()==InitHelper.INIT_SIZE_BESTELLUNG);

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

        LocalDate zeit = b.getZeit();

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
    public void testFindByTischNr() throws Exception{

        init();
        assertTrue(pBestellungDAO.findAll().size() == InitHelper.INIT_SIZE_BESTELLUNG);

        Bestellung b = pBestellungDAO.findAll().get(0);

        int TischNr = b.getTisch().getTischNr();

        assertTrue(b.equals(pBestellungDAO.findByTischNr(TischNr)));


    }


}
