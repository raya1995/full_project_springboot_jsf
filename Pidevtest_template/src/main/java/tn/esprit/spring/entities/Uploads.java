package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Uploads")
public class Uploads implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	private String uploads;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUploads() {
		return uploads;
	}

	public void setUploads(String uploads) {
		this.uploads = uploads;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	

	public Uploads(int id,String uploads, Client client) {
		super();
		this.id=id;
		this.uploads = uploads;
		this.client = client;
	}



	@Override
	public String toString() {
		return "uploads [uploads=" + uploads + "]";
	}



	@ManyToOne
	Client client;
	
	public Uploads() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
