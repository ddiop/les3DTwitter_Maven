package fmin362.resources;


import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fmin362.model.Message;
import fmin362.model.UserAccount;
import fmin362.values.Tweet;

@Path( "/tweet" )
public class TweetResource
{
		
//Récupération d'informations - Utilisation de la méthode GET du service web REST pour un user.
    @GET
    @Produces( MediaType.APPLICATION_JSON + ";charset=utf-8" )
    public Tweet tweet()
        throws Exception
    {
        EntityManager em = (EntityManager) new InitialContext().lookup( "java:comp/env/persistence/EntityManager" );
        List<UserAccount> userAccounts = em.createQuery( "select u from UserAccount u" ).getResultList();
       Tweet tweet = new Tweet( userAccounts );
        return tweet;
    }
    
    //Enregistrement de nouvelles informations - Utilisation de la méthode POST du service web REST  pour un nouveau user
    @POST
    @Path("newUser")
    @Consumes("application/json")
    public void nouveau(UserAccount userAccount) throws Exception {
    	 EntityManager em = (EntityManager) new InitialContext().lookup( "java:comp/env/persistence/EntityManager" );	
       em.persist(userAccount);
    }
    //suppression d un user
    @DELETE
    @Path("supUser")
    @Consumes("application/json")
    public void supprimer(UserAccount userAccount ) throws Exception {
    	EntityManager em = (EntityManager) new InitialContext().lookup( "java:comp/env/persistence/EntityManager" );	
    	UserAccount  recherche = em.find(UserAccount .class, userAccount.getId());
      em.remove(recherche);
      
    }   
  //suppression d un user
    @DELETE
    @Path("supMessage")
    @Consumes("application/json")
    public void supprimer(Message message ) throws Exception {
    	EntityManager em = (EntityManager) new InitialContext().lookup( "java:comp/env/persistence/EntityManager" );	
    	Message  recherche = em.find(Message .class, message.getId());
      em.remove(recherche);
      
    }   
}