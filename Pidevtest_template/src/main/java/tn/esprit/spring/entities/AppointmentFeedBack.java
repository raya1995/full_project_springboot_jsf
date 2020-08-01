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
@Table(name = "AppointmentFeedBack")
public class AppointmentFeedBack implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long IdAppFeedBack;
	private String DescriptionFeedBack;

	private boolean Likes;

	
	@ManyToOne
	Client client;
	
	public Long getIdAppFeedBack() {
		return IdAppFeedBack;
	}
	public void setIdAppFeedBack(Long idAppFeedBack) {
		IdAppFeedBack = idAppFeedBack;
	}
	public String getDescriptionFeedBack() {
		return DescriptionFeedBack;
	}
	public void setDescriptionFeedBack(String descriptionFeedBack) {
		DescriptionFeedBack = descriptionFeedBack;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	@Override
	public String toString() {
		return "AppointmentFeedBack [IdAppFeedBack=" + IdAppFeedBack + ", DescriptionFeedBack=" + DescriptionFeedBack
				+ "]";
	}
	public AppointmentFeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public AppointmentFeedBack(Long idAppFeedBack, String descriptionFeedBack) {
		super();
		IdAppFeedBack = idAppFeedBack;
		DescriptionFeedBack = descriptionFeedBack;
	}
	public boolean isLikes() {
		return Likes;
	}
	public void setLikes(boolean likes) {
		Likes = likes;
	}
	public AppointmentFeedBack(Long idAppFeedBack, String descriptionFeedBack, boolean likes, Client client) {
		super();
		IdAppFeedBack = idAppFeedBack;
		DescriptionFeedBack = descriptionFeedBack;
		Likes = likes;
		//this.client = client;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	
	
	
	

}
