package fmin362.resources;

import fmin362.model.Message;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/messages" )
public class MessagesResource
{

    
    //recuperation d information
	//Le première permet de restituer l'ensemble des users au format JSON
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public List<Message> messages()
            throws Exception
    {
        UserTransaction utx = null;
        try {

            // Lookup
            InitialContext ic = new InitialContext();
            utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
            EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );

            // Transaciton begin
            utx.begin();
            em.joinTransaction();

            Message msg = new Message( "bienvenue!", new Date() );
            em.persist( msg );

            List<Message> messages = em.createQuery( "select m from Message m" ).getResultList();
            utx.commit();

            return messages;

        } catch ( Exception ex ) {

            try {
                if ( utx != null ) {
                    utx.setRollbackOnly();
                }
            } catch ( Exception rollbackEx ) {
                // Impossible d'annuler les changements, vous devriez logguer une erreur,
                // voir envoyer un email à l'exploitant de l'application.
            }
            throw new Exception( ex );

        }

    }
    
    
    @GET
    @Produces("application/json")
    @Path("/{id}")
    //permet de retourner un user également au format JSON à partir de son identifiant :
    public Message rechercheJSON(@PathParam("id") long id) throws Exception {
    	 UserTransaction utx;
    	InitialContext ic = new InitialContext();
        utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
        EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );

    	
       return em.find(Message.class, id);
    }
    //générer un nouveau user directement à partir d'un document JSON
    @POST
    @Path("json")
    @Consumes("application/json")
    public void nouveau(Message message) throws NamingException { 
    	UserTransaction utx;
	InitialContext ic = new InitialContext();
    utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
    EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );
    	
       em.persist(message);
    }
    @DELETE
    @Path("json")
    @Consumes("application/json")
    public void supprimer(Message message) throws NamingException {
    	UserTransaction utx;
    	InitialContext ic = new InitialContext();
        utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
        EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );
     Message recherche = em.find(Message.class, message.getId());
     em.remove(recherche);
    }   
    @PUT
    @Path("json")
    @Consumes("application/json")
    public void modifier(Message message) throws NamingException {
    	UserTransaction utx;
    	InitialContext ic = new InitialContext();
        utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
        EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );
      em.merge(message);
    }
}
