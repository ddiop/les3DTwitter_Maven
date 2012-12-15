package fmin362.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class UserAccount
    implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private Long id;
    
    @Column(unique=true)
	private String nickname;

	private String mail;

 
 @Column(unique=true)
	private String password;
	
    @OneToMany( cascade = CascadeType.PERSIST )
    private List<Message> messages = new ArrayList<Message>();

    
    public UserAccount() {
		
	}

	public UserAccount(String nickname) {
		super();
		this.nickname = nickname;
	}

	
	public UserAccount(String nickname, String mail) {
		super();
		this.nickname = nickname;
		this.mail = mail;
	}

	public UserAccount(String nickname, String mail, String password) {
		super();
		this.nickname = nickname;
		this.mail = mail;
		this.password = password;
	}

	public Long getId()
    {
        return id;
    }

    
    public String getMail() {
		return mail;
	}

	
	public String getNickname() {
		return nickname;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Message> getMessages()
    {
        return messages;
    }

}
