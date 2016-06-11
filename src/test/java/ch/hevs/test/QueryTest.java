package ch.hevs.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.eclipse.persistence.annotations.Properties;
import org.junit.Test;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Brand;
import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.Network;
import ch.hevs.businessobject.OperatingSystem;
import ch.hevs.businessobject.User;


public class QueryTest {

	public static EntityManager em;
	@Test
	public void test() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String cmd;
			while (true) {
				System.out
						.println("Write a query [or 'populate' or 'quit']: ");
				cmd = reader.readLine();

				if ("populate".equals(cmd)) {
					populate();
				} else if ("quit".equals(cmd)) {
					System.out.println("The End");
					break;
				} else {
					executeRequest(cmd);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void executeRequest(String cmd) {
		List result = null;
		EntityTransaction tx = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			result = em.createQuery(cmd).getResultList();
			Iterator it = result.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			tx.commit();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} 
		}
	}

	public static void populate() {
		EntityTransaction tx = null;
		try {
			
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("test");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Networks
			Network wifi = new Network();
			wifi.setDescription("Public Wifi Network");
			wifi.setName("Wifi Hevs Public");
			em.persist(wifi);
			
			Network lan = new Network();
			lan.setDescription("Lan Network");
			lan.setName("Lan Hevs");
			em.persist(lan);
			
			// Users
			
			User benjamin = new User();
			benjamin.setFirstname("Benjamin");
			benjamin.setLastname("Mathieu");
			benjamin.setEmail("mathieu.b93@gmail.com");
			em.persist(benjamin);
			
			User nico = new User();
			nico.setFirstname("Nico");
			nico.setLastname("Tscharner");
			nico.setEmail("nico@gmail.com");
			em.persist(nico);
			 
			// Brands 
			Brand lenovo = new Brand();
			lenovo.setBrand_description("electronics manufacturing company");
			lenovo.setBrand_name("Lenovo");
			lenovo.setBrand_headquarters("Chicago");

			Brand apple = new Brand();
			apple.setBrand_description("electronics manufacturing company");
			apple.setBrand_name("Apple");
			apple.setBrand_headquarters("Cupertino, California");

			Brand samsung = new Brand();
			samsung.setBrand_description("electronics manufacturing company");
			samsung.setBrand_name("Samsung");
			samsung.setBrand_headquarters("Seoul");

			Brand microsoft = new Brand();
			microsoft.setBrand_description("software company");
			microsoft.setBrand_name("Microsoft");
			microsoft.setBrand_headquarters("Redmond");
			
			Brand google = new Brand();
			google.setBrand_description("software company /search engine");
			google.setBrand_name("Google");
			google.setBrand_headquarters("Mountain View, California");
			
			// OperatingSystems
			OperatingSystem windows10 = new OperatingSystem();
			windows10.setBrand(microsoft);
			windows10.setName("Windows 10");
			em.persist(windows10);

			OperatingSystem windows7 = new OperatingSystem();
			windows7.setBrand(microsoft);
			windows7.setName("Windows 7");
			em.persist(windows7);

			OperatingSystem osx = new OperatingSystem();
			osx.setBrand(apple);
			osx.setName("OS X Leopard");
			em.persist(osx);
			
			OperatingSystem iOS = new OperatingSystem();
			iOS.setBrand(apple);
			iOS.setName("iOS");
			em.persist(iOS);
			
			OperatingSystem android = new OperatingSystem();
			android.setBrand(google);
			android.setName("Android 6.0");
			em.persist(android);
			
			// Devices
			Device laptop = new Device();
			laptop.setBrand(lenovo);
			laptop.setDescription("library laptop");
			laptop.setName("School Lapotop");
			laptop.setOs(windows10);
			laptop.addNetwork(lan);
			laptop.addNetwork(wifi);
			laptop.addOwner(nico);
			laptop.addOwner(benjamin);
			em.persist(laptop);
			
			Device phone = new Device();
			phone.setBrand(google);
			phone.setDescription("Bens personal phone");
			phone.setName("Personal Phone");
			phone.setOs(android);
			phone.addNetwork(wifi);
			phone.addOwner(benjamin);
			em.persist(phone);
			
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
