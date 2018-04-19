package ch.hslu.informatik.gastgewerbe.persister.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.hslu.informatik.gastgewerbe.model.Adresse;
import ch.hslu.informatik.gastgewerbe.model.Person;


public class DbHelper {
	

		public static void deleteAdresse() {

			EntityManager em = null;
			TypedQuery<Adresse> tQuery = null;
			List<Adresse> liste;

			// TODO - vervollstaendigen ...

		}
		
		public static void deletePerson() {
			EntityManager em = null;
			TypedQuery<Person> tQuery = null;
			List<Person> liste;
		}
	}


