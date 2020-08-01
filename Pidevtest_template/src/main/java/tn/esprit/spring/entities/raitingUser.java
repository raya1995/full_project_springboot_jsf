package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class raitingUser implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long id;  
	
	private int nbretoile;
	
	@ManyToOne(cascade = CascadeType.ALL) 
	User UserConnecte;
	@ManyToOne(cascade = CascadeType.ALL) 
	User UserRate;
	
	public int getNbretoile() {
		return nbretoile;
	}
	public void setNbretoile(int nbretoile) {
		this.nbretoile = nbretoile;
	}
	public User getUserConnecte() {
		return UserConnecte;
	}
	public void setUserConnecte(User userConnecte) {
		UserConnecte = userConnecte;
	}
	public User getUserRate() {
		return UserRate;
	}
	public void setUserRate(User userRate) {
		UserRate = userRate;
	}
	
	public raitingUser(Long id, int nbretoile, User userConnecte, User userRate) {
		super();
		this.id = id;
		this.nbretoile = nbretoile;
		UserConnecte = userConnecte;
		UserRate = userRate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public raitingUser( int nbretoile, User userConnecte, User userRate) {
		super();
		
		this.nbretoile = nbretoile;
		UserConnecte = userConnecte;
		UserRate = userRate;
	}
	public raitingUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		raitingUser other = (raitingUser) obj;
		if (UserConnecte == null) {
			if (other.UserConnecte != null)
				return false;
		} else if (!UserConnecte.equals(other.UserConnecte))
			return false;
		if (UserRate == null) {
			if (other.UserRate != null)
				return false;
		} else if (!UserRate.equals(other.UserRate))
			return false;
		if (id != other.id)
			return false;
		if (nbretoile != other.nbretoile)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "raitingUser [id=" + id + ", nbretoile=" + nbretoile + ", UserConnecte=" + UserConnecte + ", UserRate="
				+ UserRate + "]";
	}
	
	
	
	

}
