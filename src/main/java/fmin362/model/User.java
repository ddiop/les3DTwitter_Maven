package fmin362.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class User {

	 @Id
	    @GeneratedValue( strategy = GenerationType.SEQUENCE )
	    private Long id;
	    private String name;
	    @OneToMany( cascade = CascadeType.PERSIST )
	    private List<Message> messages = new ArrayList<Message>();
		public User() {
		
			// TODO Auto-generated constructor stub
		}
		public User(String name) {
			super();
			this.name = name;
		}
		public Long getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public List<Message> getMessages() {
			return messages;
		}
		

	   

	}