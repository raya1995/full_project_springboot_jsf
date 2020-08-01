package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ConnectedUser implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private Date Today;
	private int nbrConnect;
	
	
	
	public ConnectedUser() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ConnectedUser(Long id, Date today, int nbrConnect) {
		super();
		Id = id;
		Today = today;
		this.nbrConnect = nbrConnect;
	}



	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public Date getToday() {
		return Today;
	}



	public void setToday(Date today) {
		Today = today;
	}



	public int getNbrConnect() {
		return nbrConnect;
	}



	public void setNbrConnect(int nbrConnect) {
		this.nbrConnect = nbrConnect;
	}



	@Override
	public String toString() {
		return "ConnectedUser [Id=" + Id + ", Today=" + Today + ", nbrConnect=" + nbrConnect + "]";
	}



	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectedUser other = (ConnectedUser) obj;
		if (Id != other.Id)
			return false;
		if (Today == null) {
			if (other.Today != null)
				return false;
		} else if (!Today.equals(other.Today))
			return false;
		if (nbrConnect != other.nbrConnect)
			return false;
		return true;
	}
	

}
