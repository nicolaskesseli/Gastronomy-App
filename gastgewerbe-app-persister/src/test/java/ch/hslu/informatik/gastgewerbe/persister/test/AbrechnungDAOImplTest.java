package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.persister.AbrechnungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.AbrechnungDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.InitHelper;
import org.junit.*;

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
        InitHelper.deleteAllBestellung();
        InitHelper.deleteAllBenutzer();
        InitHelper.deleteAllAbrechnung();

    }

    @Before
    public void setUp() throws Exception {
        InitHelper.deleteAllBestellung();
        InitHelper.deleteAllBenutzer();
        InitHelper.deleteAllAbrechnung();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        InitHelper.initAbrechnung();
    }

    @Test
    public final void testSave() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);
    }

    @Test
    public final void testUpdate() throws Exception {

        init();
        assertTrue(pAbrechnung.findAll().size() == InitHelper.INIT_SIZE_ABRECHNUNG);

        Abrechnung erste = pAbrechnung.findAll().get(0);
        assertNotNull(erste);
        int anzahlProdukeInRechnung = erste.getBestellung().getBestellungPositionListe().size();

        /* Ein produkt entfernen  */
        erste.getBestellung().getBestellungPositionListe().remove(2);

        pAbrechnung.update(erste);

        Abrechnung abrechnungFromDb = pAbrechnung.findById(erste.getId());

        assertTrue(abrechnungFromDb.getBestellung().getBestellungPositionListe().size() == anzahlProdukeInRechnung - 1);
    }






}
