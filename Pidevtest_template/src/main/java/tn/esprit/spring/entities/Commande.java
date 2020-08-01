package tn.esprit.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_COMMANDE")
public class Commande  implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	//@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private LocalDate date;
	private double montant;
	private String status;
	private String TypedePayment;
	private String Remise;
	private double PourcentageDeRemise;
	
	private double 	Total;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)// panierr par user 
	private LigneCommande ligneCommande;
	
	@OneToOne(mappedBy="commande")
	private Factures facture;
	@ManyToOne
	@JoinColumn(name="idUser")
	  private User idUser;
	
	@ManyToMany
	@JsonIgnore
	private List<Furniture> furnitures;
	
	@ManyToOne
	Delivery deliveries;
	
	public Commande() {
		super();
	}

	public Commande(Long id, LocalDate date, double montant, String status, String typedePayment, String remise,
			double pourcentageDeRemise, double total, LigneCommande ligneCommande, Factures facture, User idUser,
			List<Furniture> furnitures, Delivery deliveries) {
		super();
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.status = status;
		TypedePayment = typedePayment;
		Remise = remise;
		PourcentageDeRemise = pourcentageDeRemise;
		Total = total;
		this.ligneCommande = ligneCommande;
		this.facture = facture;
		this.idUser = idUser;
		this.furnitures = furnitures;
		this.deliveries = deliveries;
	}

	public Commande(LocalDate date, double montant, String status, String typedePayment, String remise,
			double pourcentageDeRemise, double total, LigneCommande ligneCommande, Factures facture, User idUser,
			List<Furniture> furnitures, Delivery deliveries) {
		super();
		this.date = date;
		this.montant = montant;
		this.status = status;
		TypedePayment = typedePayment;
		Remise = remise;
		PourcentageDeRemise = pourcentageDeRemise;
		Total = total;
		this.ligneCommande = ligneCommande;
		this.facture = facture;
		this.idUser = idUser;
		this.furnitures = furnitures;
		this.deliveries = deliveries;
	}
	
	

	public Commande(Long id, LocalDate date, double montant, String status, String typedePayment, String remise,
			double pourcentageDeRemise, double total, LigneCommande ligneCommande, Factures facture, User idUser,
			List<Furniture> furnitures) {
		super();
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.status = status;
		TypedePayment = typedePayment;
		Remise = remise;
		PourcentageDeRemise = pourcentageDeRemise;
		Total = total;
		this.ligneCommande = ligneCommande;
		this.facture = facture;
		this.idUser = idUser;
		this.furnitures = furnitures;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypedePayment() {
		return TypedePayment;
	}

	public void setTypedePayment(String typedePayment) {
		TypedePayment = typedePayment;
	}

	public String getRemise() {
		return Remise;
	}

	public void setRemise(String remise) {
		Remise = remise;
	}

	public double getPourcentageDeRemise() {
		return PourcentageDeRemise;
	}

	public void setPourcentageDeRemise(double pourcentageDeRemise) {
		PourcentageDeRemise = pourcentageDeRemise;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Factures getFacture() {
		return facture;
	}

	public void setFacture(Factures facture) {
		this.facture = facture;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public List<Furniture> getFurnitures() {
		return furnitures;
	}

	public void setFurnitures(List<Furniture> furnitures) {
		this.furnitures = furnitures;
	}

	public Delivery getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Delivery deliveries) {
		this.deliveries = deliveries;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}







}
