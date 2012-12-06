package fmin362;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.transaction.UserTransaction;

import fmin362.model.Message;

import fmin362.model.User;

import static java.lang.System.out;

public class CycleDeVie implements ServletContextListener {

	@Resource
	private UserTransaction utx;
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		out.println("RestoFX Server STARTING");

		if (emf == null) {
			throw new RuntimeException(
					"JPA Persistence Unit is not properly setup!");
		}

		EntityManager em = emf.createEntityManager();

		try {
			utx.begin();
			em.joinTransaction();
			if (em.createQuery("select u from User u").getResultList()
					.isEmpty()) {
				List<User> users = createUsers();
				for (User user : users) {
					em.persist(user);
				}
				utx.commit();
			}
		} catch (Exception ex) {
			try {
				utx.setRollbackOnly();
			} catch (Exception rollbackEx) {
				// Impossible d'annuler les changements, vous devriez logguer
				// une erreur,
				// voir envoyer un email à l'exploitant de l'application.
			}
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			if (em != null) {
				em.close();
			}
		}

		out.println("RestoFX Server STARTED");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {// ServletContext
															// context;
															// context =
															// contextEvent.getServletContext();
		System.out.println("Context Destroyed");
		out.println("RestoFX Server STOPPED");
	}

	private List<User> createUsers() {
		// Users
		User djibi = new User("djibi");
		
		//Messages
		
		
		djibi.getMessages().add(new Message(djibi, new Date(), "bonjour"));
		System.out.println("========\n===============");
		 System.out.println("========\n"+djibi.getMessages()+"\n===============");
		 System.out.println("========\n===============");
		djibi.getMessages().add(
				new Message(djibi, new Date(), "je viens de me connecter"));
		List<User> users = new ArrayList<User>();
		users.add(djibi);
		return users;
	}
	


}