package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.ProduktDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.ProduktDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.InitHelper;
import org.junit.*;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class ProduktDAOImplTest {

    private static ProduktDAO pProdukt = new ProduktDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        InitHelper.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        InitHelper.deleteAllProdukt();

    }

    @Before
    public void setUp() throws Exception {
        InitHelper.deleteAllProdukt();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        InitHelper.initProdukt();
    }

    @Test
    public void testSave() throws Exception{
        init();
        assertTrue(pProdukt.findAll().size()==InitHelper.INIT_SIZE_PRODUKT);
    }

    @Test
    public void testUpdate() throws Exception{
        init();
        assertTrue(pProdukt.findAll().size()==InitHelper.INIT_SIZE_PRODUKT);

        int size = pProdukt.findAll().size();
        Produkt lastProdukt = pProdukt.findAll().get(size-1);

        String produktCode = lastProdukt.getProduktCode();

        String produktCodeNeu = "TestErfolgreich";

        lastProdukt.setProduktCode(produktCodeNeu);

        pProdukt.update(lastProdukt);

        Produkt produktFromDb = pProdukt.findAll().get(size - 1);

        assertFalse(produktFromDb.getProduktCode() == produktCode);
        assertTrue(produktFromDb.getProduktCode() == produktCodeNeu);
    }

    @Test
    public void testDelete() throws Exception{
        init();
        assertTrue(pProdukt.findAll().size()==InitHelper.INIT_SIZE_PRODUKT);

        int size = pProdukt.findAll().size();

        /* Das letze Produkt löschen */
        Produkt lastProdukt = pProdukt.findAll().get(size - 1);
        pProdukt.delete(lastProdukt);

        assertTrue(pProdukt.findAll().size() == size - 1);

    }

    @Test
    public void testDeleteById() throws Exception {

        init();
        assertTrue(pProdukt.findAll().size() == InitHelper.INIT_SIZE_PRODUKT);

        int size = pProdukt.findAll().size();

        /* Das letze Produkt löschen */
        Produkt lastProdukt = pProdukt.findAll().get(size - 1);
        pProdukt.deleteById(lastProdukt.getId());

        assertTrue(pProdukt.findAll().size() == size - 1);

    }

    @Test
    public void testFindByName() throws Exception {

        init();
        assertTrue(pProdukt.findAll().size() == InitHelper.INIT_SIZE_PRODUKT);

        /* Alle Produkte mit Name Coca Cola  holen */
        Produkt cola = new ProduktDAOImpl().findByName("Coca Cola").get(0);
        assertNotNull(cola);
        List<Produkt> gRivella = pProdukt.findByName("Rivella Rot");

        assertTrue(gRivella.size() == 1);
    }

    @Test
    public void testFindByProduktCode() throws Exception{
        init();
        assertTrue(pProdukt.findAll().size() == InitHelper.INIT_SIZE_PRODUKT);

        int size = pProdukt.findAll().size();
        Produkt lastProdukt = pProdukt.findAll().get(size - 1);

        String code = lastProdukt.getProduktCode();

        Produkt produktNachCode = pProdukt.findByProduktCode(code);

        assertNotNull(produktNachCode);
        assertTrue(produktNachCode.equals(lastProdukt));
    }

    @Test
   public void  testFindByKategorie() throws Exception{
        init();
        assertTrue(pProdukt.findAll().size() == InitHelper.INIT_SIZE_PRODUKT);

        // Alle Getränke holen sollten 2 sein... Gleiches mit Snacks
        List<Produkt> getranke = pProdukt.findByKategorie(KategorieTyp.GETRANK);

        assertTrue(getranke.size()==2);
        assertTrue(pProdukt.findByKategorie(KategorieTyp.SNACK).size()==2);
    }

    @Test
    public void testFindById() throws Exception {

        init();
        assertTrue(pProdukt.findAll().size() == InitHelper.INIT_SIZE_PRODUKT);

        int size = pProdukt.findAll().size();
        Produkt lastProdukt = pProdukt.findAll().get(size - 1);

        long id = lastProdukt.getId();

        Produkt produktNachId = pProdukt.findById(id);

        assertNotNull(produktNachId);
        assertTrue(produktNachId.equals(lastProdukt));
    }

    @Test
    public final void testFindAll() throws Exception {

        init();
        assertTrue(pProdukt.findAll().size() == InitHelper.INIT_SIZE_PRODUKT);
    }

}

