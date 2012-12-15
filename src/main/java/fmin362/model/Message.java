package fmin362.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message
    implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private Long id;
    private String texte;
    
    @ManyToOne
    private UserAccount userAccount;

    
	public Message() {
		
	}

    public Message(UserAccount userAccount,String texte) {
    	this();
    	this.userAccount = userAccount;
		this.texte = texte;
		
	}

	public Long getId() {
		return id;
	}

	public String getTexte() {
		return texte;
	}

	


	

}