package ch.hslu.informatik.gastgewerbe.persister.test;

import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({BenutzerDAOImplTest.class, BestellungDAOImplTest.class,CredentialsDAOImplTest.class, PersonDAOImplTest.class, ProduktDAOImplTest.class, TischDAOImplTest.class})
public class AllTests {

}
