package ch.hslu.informatik.gastgewerbe.persister;


        import java.util.List;

        import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
        import ch.hslu.informatik.gastgewerbe.model.Produkt;

/**
 * Interface für Persistierung von ProduktTyp-Entities.
 *
 * @version 1.0
 * @author jsucur
 *
 */
public interface ProduktDAO extends GenericPersisterDAO<Produkt> {

    /**
     * Liefert alle ProduktTypen für den übergebenen Namen zurück.
     *
     * @param name
     * @return
     * @throws Exception
     */
    List<Produkt> findByName(String name) throws Exception;

    /**
     * Liefert das Produkt mit der übergebenen produktCode zurück.
     *
     * @param produktCode
     * @return
     * @throws Exception
     */
    Produkt findByProduktCode(String produktCode) throws Exception;

    /**
     * Liefert alle Produkte mit der erwähnten Kategorie zurück
     *
     * @param kategorie
     * @return
     * @throws Exception
     */
    List<Produkt> findByKategorie(KategorieTyp kategorie) throws Exception;

}
