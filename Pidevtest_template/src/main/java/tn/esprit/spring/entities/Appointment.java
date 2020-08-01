package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Appointment")
//@Inheritance(strategy = InheritanceType.JOINED)

public class Appointment  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idAppointement; 
	@Temporal(TemporalType.DATE)
	private Date dateAppointement;
	private boolean Visibility;
	private String state;
	private int Heure;
	private String Attendance;
	private boolean purchased;
	private String justification;
	private Date DatePurchasing;

	private String files;
	
	@ManyToOne
	Client client;
	@ManyToOne
	Ad ad;
	
	
	
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDatePurchasing() {
		return DatePurchasing;
	}
	public void setDatePurchasing(Date datePurchasing) {
		DatePurchasing = datePurchasing;
	}
	public Ad getAd() {
		return ad;
	}
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public boolean isPurchased() {
		return purchased;
	}
	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Long getIdAppointement() {
		return idAppointement;
	}
	public void setIdAppointement(Long idAppointement) {
		idAppointement = idAppointement;
	}
	public Date getDateAppointement() {
		return dateAppointement;
	}
	public void setDateAppointement(Date dateAppointement) {
		this.dateAppointement = dateAppointement;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	public Client getClient() {
		return client;
	}
	
	
	
	
	
	public boolean isVisibility() {
		return Visibility;
	}
	public void setVisibility(boolean visibility) {
		Visibility = visibility;
	}
	public int getHeure() {
		return Heure;
	}
	public void setHeure(int heure) {
		Heure = heure;
		
	}
	public String getAttendance() {
		return Attendance;
	}
	public void setAttendance(String attendance) {
		Attendance = attendance;
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Appointment [IdAppointement=" + idAppointement + ", dateAppointement=" + dateAppointement
				+ ", Visibility=" + Visibility + ", state=" + state + ", Heure=" + Heure + ", Attendance=" + Attendance
				+ ", purchased=" + purchased + ", justification=" + justification + ", DatePurchasing=" + DatePurchasing
				+ "]";
	}
	public Appointment(Long appointmentIdToBeUpdated, boolean visibility2, String state2, boolean purchased2, String attendance2, String justification2) {
		super();
	}
	public Appointment(Long idAppointement, Date dateAppointement, boolean visibility, String state, int heure,
			String attendance, boolean purchased, String justification, Date datePurchasing, Client client) {
		super();
		idAppointement = idAppointement;
		this.dateAppointement = dateAppointement;
		Visibility = visibility;
		this.state = state;
		Heure = heure;
		Attendance = attendance;
		this.purchased = purchased;
		this.justification = justification;
		DatePurchasing = datePurchasing;
		this.client = client;
	}
	public Appointment(Date dateAppointement, int heure) {
		super();
		this.dateAppointement = dateAppointement;
		Heure = heure;
	}
	
	//loua
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Appointment(Long idAppointement, Date dateAppointement, boolean visibility, String state, int heure,
			String attendance, boolean purchased, String justification, Date datePurchasing, Client client,Ad ad,String files) {
		super();
		this.idAppointement = idAppointement;
		this.dateAppointement = dateAppointement;
		Visibility = visibility;
		this.state = state;
		Heure = heure;
		Attendance = attendance;
		this.purchased = purchased;
		this.justification = justification;
		this.DatePurchasing = datePurchasing;
		this.client = client;
		this.ad=ad;
		this.files=files;
	}
	public Appointment(Long idAppointement, Date dateAppointement, boolean visibility, String state, int heure,
			String attendance, boolean purchased, String justification, Date datePurchasing, String file) {
		super();
		this.idAppointement = idAppointement;
		this.dateAppointement = dateAppointement;
		Visibility = visibility;
		this.state = state;
		Heure = heure;
		Attendance = attendance;
		this.purchased = purchased;
		this.justification = justification;
		this.DatePurchasing = datePurchasing;
		this.files=file;
		//this.client = client;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}