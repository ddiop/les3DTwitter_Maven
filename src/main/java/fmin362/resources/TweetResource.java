package fmin362.resources;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import java.util.List;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import fmin362.model.User;
import fmin362.values.Tweet;

@Path("/tweet")
public class TweetResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Tweet Tweet() throws Exception {
		EntityManager em = (EntityManager) new InitialContext()
				.lookup("java:comp/env/persistence/EntityManager");
		List<User> users = em.createQuery("select c from User c")
				.getResultList();
		Tweet tweet = new Tweet(users);
		return tweet;
	}
}