package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_DELIVERY")
public class Delivery implements Serializable {
	
	public enum StateDelivery {
		IN_PROGRESS, 
		Approved;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DELIVERY_ID")
	private long  deliveryId;
	
	
	@Column(name = "DELIVERY_CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "DELIVERY_LOCATION")
	private String Lieu;
	
	
	@Column(name = "DELIVERY_DESCRIPTION")
	private String description;
	@Column(name = "DELIVERY_STATE")
	private StateDelivery stateDelivery;
	

	
	
	@ManyToOne
	Livreur livreur;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveries")
	private Set<Commande> commandes;
	

	public Delivery() {
		super();
	}


	public Delivery(long deliveryId, String customerName, String lieu, String description, StateDelivery stateDelivery,
			Livreur livreur, Set<Commande> commandes) {
		super();
		this.deliveryId = deliveryId;
		this.customerName = customerName;
		Lieu = lieu;
		this.description = description;
		this.stateDelivery = stateDelivery;
		this.livreur = livreur;
		this.commandes = commandes;
	}


	public Delivery(String customerName, String lieu, String description, StateDelivery stateDelivery, Livreur livreur,
			Set<Commande> commandes) {
		super();
		this.customerName = customerName;
		Lieu = lieu;
		this.description = description;
		this.stateDelivery = stateDelivery;
		this.livreur = livreur;
		this.commandes = commandes;
	}
	
	
	


	public Delivery(String customerName, String lieu, String description, StateDelivery stateDelivery,
			Livreur livreur) {
		super();
		this.customerName = customerName;
		Lieu = lieu;
		this.description = description;
		this.stateDelivery = stateDelivery;
		this.livreur = livreur;
	}

	
	
	

	public Delivery(String customerName, String lieu, String description, StateDelivery stateDelivery) {
		super();
		this.customerName = customerName;
		Lieu = lieu;
		this.description = description;
		this.stateDelivery = stateDelivery;
	}


	public long getDeliveryId() {
		return deliveryId;
	}


	public void setDeliveryId(long deliveryId) {
		this.deliveryId = deliveryId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getLieu() {
		return Lieu;
	}


	public void setLieu(String lieu) {
		Lieu = lieu;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public StateDelivery getStateDelivery() {
		return stateDelivery;
	}


	public void setStateDelivery(StateDelivery stateDelivery) {
		this.stateDelivery = stateDelivery;
	}


	public Livreur getLivreur() {
		return livreur;
	}


	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}


	public Set<Commande> getCommandes() {
		return commandes;
	}


	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
