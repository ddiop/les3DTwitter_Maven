package fmin362;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.transaction.UserTransaction;

import fmin362.model.Message;
import fmin362.model.UserAccount;

import static java.lang.System.out;

/**
 * ServletContextListener est appelé au démarrage et à l'arret de l'application.
 *
 * Si le jeu de données n'existe pas en base on l'y insère.
 *
 * commentaire djibi
 *  une implémentation de ServletContextListener permettant de
 *   démarrer une ressource externe lors du démarrage de l'application.

Lors d'un démarrage ,il démarre les applications une à une et passe bien dans
 la méthode contextInitialized de l'application visée.
 */
public class Lifecycle
    implements ServletContextListener
{

    @Resource
    private UserTransaction utx;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public void contextInitialized( ServletContextEvent sce )
    {
        out.println( "RestoFX Server STARTING" );

        if( emf == null )
        {
            throw new RuntimeException( "JPA Persistence Unit is not properly setup!" );
        }

        EntityManager em = emf.createEntityManager();

        try
        {
            utx.begin();
            em.joinTransaction();
            if( em.createQuery( "select u from UserAccount u" ).getResultList().isEmpty() )
            {
                List<UserAccount> userAccounts = createUserAccounts();
                for(UserAccount userAccount : userAccounts )
                {
                    em.persist( userAccount );
                }
                utx.commit();
            }
        }
        catch( Exception ex )
        {
            try
            {
                utx.setRollbackOnly();
            }
            catch( Exception rollbackEx )
            {
                // Impossible d'annuler les changements, vous devriez logguer une erreur,
                // voir envoyer un email à l'exploitant de l'application.
            }
            throw new RuntimeException( ex.getMessage(), ex );
        }
        finally
        {
            if( em != null )
            {
                em.close();
            }
        }

        out.println( "RestoFX Server STARTED" );
    }

    @Override
    public void contextDestroyed( ServletContextEvent sce )
    {//ServletContext context;
    	//context = contextEvent.getServletContext();
		System.out.println("Context Destroyed");
        out.println( "RestoFX Server STOPPED" );
    }

    private List<UserAccount> createUserAccounts()
    {
        // users
    	UserAccount djibi = new UserAccount( "djibi" ,"ddiopdjibi@gmail.com","ddiop");
    	UserAccount hug = new UserAccount( "hug","hug@gmail.com","hhug" );
    	
    	UserAccount dieye = new UserAccount( "dieye","dieye@gmail.com","ddieye" );
        // messages
        
        djibi.getMessages().add( new Message( djibi, "bonjour les gars "));
        djibi.getMessages().add( new Message( djibi, "il est grand temps qu on bosse le projet"));
        djibi.getMessages().add( new Message( djibi, "sinon on va morfler"));
     
        List<UserAccount>  userAccounts= new ArrayList<UserAccount>();
        userAccounts.add(djibi );
        userAccounts.add( hug);
        userAccounts.add( dieye);
        return userAccounts;
    }
    

}
