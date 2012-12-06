package fmin362.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Message {
	 @Id
	    @GeneratedValue( strategy = GenerationType.SEQUENCE )
	    private Long id;
	    private String texte;
	    private Date date ;
	    @ManyToOne
	    private User user;
		
		
		public String getTexte() {
			return texte;
		}
		
		public Date getDate() {
			return date;
		}
		
		public User getUser() {
			return user;
		}
		
		public Message() {
			super();
		}
		public Message( User user,Date date ,String texte ) {
			super();
			this.texte = texte;
			this.date = date;
			this.user = user;
		}
	    
}
