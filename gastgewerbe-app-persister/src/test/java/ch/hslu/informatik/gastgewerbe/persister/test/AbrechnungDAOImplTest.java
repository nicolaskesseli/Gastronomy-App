package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.persister.AbrechnungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.AbrechnungDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.InitHelper;
import org.junit.*;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class AbrechnungDAOImplTest {

    private static AbrechnungDAO pAbrechnung = new AbrechnungDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        InitHelper.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        InitHelper.deleteAllAbrechnung();
        InitHelper.deleteAllBenutzer();
        InitHelper.deleteAllBestellung();
        InitHelper.deleteAllProdukt();
        InitHelper.deleteAllTisch();
    }

    @Before
    public void setUp() throws Exception {
        InitHelper.deleteAllAbrechnung();
        InitHelper.deleteAllBestellung();
        InitHelper.deleteAllBenutzer();
        InitHelper.deleteAllProdukt();
        InitHelper.deleteAllTisch();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        InitHelper.initTisch();
        InitHelper.initProdukt();
        InitHelper.initBenutzer();
        InitHelper.initBestellung();
        InitHelper.initAbrechnung();
    }

    @Test
    public void testSave() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);
    }

    @Test
    public void testUpdate() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

        Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);
        double betrag = erste.getGesamtBetrag();

        /* Ein produkt entfernen geht nur über Bestellung/BestellungPos!!!!! weil ManyToOne */

        // Abrechnungsbetrag ändern
        double neuerBetrag = 50.66;
        erste.setBetrag(neuerBetrag);
        pAbrechnung.update(erste);

        Abrechnung abrechFromDB = pAbrechnung.findById(erste.getId());

        assertTrue(abrechFromDB.getBetrag()==neuerBetrag);
    }

    @Test
    public void testDelete() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

        Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);
        pAbrechnung.delete(erste);
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG - 1);
    }

    @Test
    public void testDeleteById() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

        Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);
        pAbrechnung.deleteById(erste.getId());
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG - 1);
    }

    @Test
    public void testFindById() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

        Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);
        assertTrue(pAbrechnung.findById(erste.getId()).equals(erste));
    }

    @Test
    public void testFindByDatum() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

         Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);
        Abrechnung rechnungFromDb = pAbrechnung.findByDatum(erste.getZeit()).get(0);
        assertTrue(rechnungFromDb.equals(erste));
    }

    @Test
    public void testFindAll() throws Exception {
        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);
    }

    @Test
    public void testFindByBenutzer() throws Exception{
        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

        Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);

        Abrechnung rechnungFromDb = pAbrechnung.findByBenutzerUndDatum(erste.getBenutzer(),erste.getZeit()).get(0);
        assertTrue(rechnungFromDb.equals(erste));

    }

    @Test
    public void testFindByBenutzerUndDatum() throws Exception{
        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

        Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);

        Abrechnung rechnungFromDb = pAbrechnung.findByBenutzer(erste.getBenutzer()).get(0);
        assertTrue(rechnungFromDb.equals(erste));

    }

}
