package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_LIGNE_COMMANDE")
public class LigneCommande  implements Serializable {

	

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	





	@ElementCollection
		//@ManyToMany(mappedBy="ligneCommande",fetch=FetchType.EAGER )
		private Map<Furniture,Long> furnitures;
	
	
	
	
	 @OneToMany( mappedBy="ligneCommande", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 private List<Commande> commande ;

	 
	 @OneToOne
		private User user;

	 
	 
	 
	public LigneCommande() {
		super();
	}




	public LigneCommande(Long id, Map<Furniture, Long> furnitures, List<Commande> commande, User user) {
		super();
		this.id = id;
		this.furnitures = furnitures;
		this.commande = commande;
		this.user = user;
	}




	public LigneCommande(Map<Furniture, Long> furnitures, List<Commande> commande, User user) {
		super();
		this.furnitures = furnitures;
		this.commande = commande;
		this.user = user;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Map<Furniture, Long> getFurnitures() {
		return furnitures;
	}




	public void setFurnitures(Map<Furniture, Long> furnitures) {
		this.furnitures = furnitures;
	}




	public List<Commande> getCommande() {
		return commande;
	}




	public void setCommande(List<Commande> commande) {
		this.commande = commande;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	
	

	
}
