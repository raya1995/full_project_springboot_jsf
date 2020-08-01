package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "T_FACTURE")
public class Factures  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id_facture")
	private long id;
	private double montant;
	
	@CreationTimestamp
	private Date date_de_depart;
	
	private String type;
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Commande commande;
	
	public Factures(){
		super();
	}

	public Factures(long id, double montant, Date date_de_depart, String type, Commande commande) {
		super();
		this.id = id;
		this.montant = montant;
		this.date_de_depart = date_de_depart;
		this.type = type;
		this.commande = commande;
	}

	public Factures(double montant, Date date_de_depart, String type, Commande commande) {
		super();
		this.montant = montant;
		this.date_de_depart = date_de_depart;
		this.type = type;
		this.commande = commande;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDate_de_depart() {
		return date_de_depart;
	}

	public void setDate_de_depart(Date date_de_depart) {
		this.date_de_depart = date_de_depart;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
		

}
