package tn.esprit.spring.controller;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.entities.FurnitureState;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IFurnitureService;




@Scope(value = "session")
@Controller(value ="ttttt")
@ELBeanName(value = "ttttt")
@Join(path= "/furniture", to = "/login.jsf")
public class IControllerFurnitureImpl {
	
	@Autowired
	IFurnitureService iFurnitureService;
	
	
	private String login;
	private String password;
	private User user;
	private Boolean loggedIn;
	 private User authenticatedUser;
	
	private String name;
	private String description;
	private float price;	
	private int quantity;
	private Long Barcode;	
	private Date publishedDate;
	private String destination = "C:\\Users\\youss\\git\\dari-spring-boot\\dari-spring-boot\\uploads";
	
	 private List<Furniture> furnitures;
	
	@Enumerated(EnumType.STRING)
	private FurnitureState state;

	
	public String ajouterMeuble(){
		String navigateTo ="null";	
	//	iFurnitureService.ajouterMeuble(new Furniture(name,description,price,quantity,Barcode) );
		return navigateTo ;
		//return furniture.getId();
	}
	
	
	

	
	public String displayFurniture(Furniture fur) {
		
		this.setName(fur.getName());
		this.setDescription(fur.getDescription());
		this.setPrice(fur.getPrice());
		this.setQuantity(fur.getQuantity());
		return"good";
	}





	public List<Furniture> getAllFurniture(){
		
		return iFurnitureService.getAllFurniture();
	}
	
	public List<String> getAllFurnitureNamesJPQL() {
		
		return  iFurnitureService.getAllFurnitureNamesJPQL();
	}

	
	public void deleteFurnitureById(int furnitureId){
		
		iFurnitureService.deleteFurnitureById(furnitureId);
	}
	
	
	
	public int getNombreFurnitureJPQL(){
		return 	iFurnitureService.getNombreFurnitureJPQL();
	
	}


	public IFurnitureService getiFurnitureService() {
		return iFurnitureService;
	}


	public void setiFurnitureService(IFurnitureService iFurnitureService) {
		this.iFurnitureService = iFurnitureService;
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


	public FurnitureState getState() {
		return state;
	}


	public void setState(FurnitureState state) {
		this.state = state;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Boolean getLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	public User getAuthenticatedUser() {
		return authenticatedUser;
	}


	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}


	public List<Furniture> getFurnitures() {
		return furnitures;
	}


	public void setFurnitures(List<Furniture> furnitures) {
		this.furnitures = furnitures;
	}





	public String getDestination() {
		return destination;
	}





	public void setDestination(String destination) {
		this.destination = destination;
	}

}
