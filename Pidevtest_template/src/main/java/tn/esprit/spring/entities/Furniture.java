package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_FURNITURE")
public class Furniture implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "FURNITURE_ID")
	private int id;

	@Column(name = "FURNITURE_NAME")
	private String name;
	
	@Column(name = "FURNITURE_DESCRIPTION")
	private String description;

	@Column(name = "FURNITURE_PRICE")
	private float price;
	
	@Column(name = "FURNITURE_QUANTITY")
	private int quantity;
	@Column(name = "FURNITURE_BARCODE")
	private Long Barcode;
	@Column(name = "FURNITURE_PUBLISHED_DATE")
	@CreationTimestamp
	private Date publishedDate;
	
	private String image;
 
	
	@Enumerated(EnumType.STRING)
	private FurnitureState state;




	
	
	
	//@ManyToMany
//	@JsonIgnore
//	private Set<LigneCommande> ligneCommande;
	
//	@JSONIGNORE
//	@ONETOMANY(MAPPEDBY="IDFURNITURE",CASCADE=CASCADETYPE.ALL)
//	PRIVATE SET<IMAGEPRODUIT> IMAGES;








	public Furniture() {
		super();
	}




	public Furniture(String name, String description, float price, int quantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}




	public Furniture(int id, String name, String description, float price, int quantity, Long barcode,
			Date publishedDate, String image, FurnitureState state) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		Barcode = barcode;
		this.publishedDate = publishedDate;
		this.image = image;
		this.state = state;
	}




	public Furniture(String name, String description, float price, int quantity, Long barcode, Date publishedDate,
			String image, FurnitureState state) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		Barcode = barcode;
		this.publishedDate = publishedDate;
		this.image = image;
		this.state = state;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public float getPrice() {
		return price;
	}




	public void setPrice(float price) {
		this.price = price;
	}




	public int getQuantity() {
		return quantity;
	}




	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public Long getBarcode() {
		return Barcode;
	}




	public void setBarcode(Long barcode) {
		Barcode = barcode;
	}




	public Date getPublishedDate() {
		return publishedDate;
	}




	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public FurnitureState getState() {
		return state;
	}




	public void setState(FurnitureState state) {
		this.state = state;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	



	
}
