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
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

public class BenutzerDAOImplTest {


		private static Logger logger = LogManager.getLogger(BenutzerDAOImplTest.class);

		private static List<Person> personen;
		
		@BeforeClass
		public static void setUpBeforeClass() throws Exception{
			personen = Util.createPersonListe();
		}
		
		@AfterClass
		public static void tearDownAfterClass() throws Exception {
			DbHelper.deletePerson();
		}
		@Before
		public void setUp() throws Exception {
		}

		@After
		public void tearDown() throws Exception {
			
		}
		
		@Test
		public void testsave() {

			EntityManager em = JPAUtil.createEntityManager();
			em.getTransaction().begin();

			try {
				for (Person p : personen) {
					//em.persist(p.getAdresse());
					//em.persist(p.getKontakt());
					em.persist(p);
				}

				em.getTransaction().commit();

			} catch (Exception e) {
				logger.error("Person konnte nicht gespeichert werden", e);

				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} finally {
				if (em.isOpen()) {
					em.close();
				}
			}

			em = JPAUtil.createEntityManager();
			List<Person> pListe = em.createQuery("SELECT a FROM Person a ORDER BY a.nachname", Person.class).getResultList();
			assertTrue(pListe.size() == personen.size());
			

			em.close();
		}
		
		
		
//		@Test
//		public void testUpdaten() {
//			// Eine Person erstellen ...
//			Person person = new Person("Meier", "Peter", new Adresse("Eggstrase 5", 7364, "Sempach"), new Kontakt("simi.adlkfjslkd", "3048029384034"));
//			
//			
//
//			// Adresse speichern ...
//			
//			EntityManager em = JPAUtil.createEntityManager();
//			em.getTransaction().begin();
//			em.persist(person.getAdresse());
//			em.persist(person.getKontakt());
//			em.persist(person);
//			em.getTransaction().commit();
//			em.clear();
//			
//
//			// ID festhalten
//			int id = person.getId();
//
//			// Adresse aus der DB holen
//			person = null;
//			Person persFromDb = em.find(Person.class, id);
//			
//			assertNotNull("Sollte nicht null sein!", persFromDb);
//
//			// Person aendern ...
//			String nameNeu = "Achermann";
//			persFromDb.setName(nameNeu);
//			// Updaten ...
//			em.getTransaction().begin();
//			em.merge(persFromDb);
//			em.getTransaction().commit();
//
//			// Adresse nach dem Update holen
//			persFromDb = null;
//			Person persNachUpdate = em.find(Person.class, id);
//
//			// Kontrollieren, ob die Strasse korrekt aktualisiert wurde
//			
//			assertEquals("Sollten gleich sein",nameNeu, persNachUpdate.getName());
//		}
	}

	
	

