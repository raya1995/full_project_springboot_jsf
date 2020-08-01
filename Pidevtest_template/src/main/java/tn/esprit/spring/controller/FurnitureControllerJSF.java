package tn.esprit.spring.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.entities.FurnitureState;
import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.FurnitureRepository;
import tn.esprit.spring.repository.LigneCommandeRepository;
import tn.esprit.spring.services.CommandeServiceImpl;
import tn.esprit.spring.services.ICommandeService;
import tn.esprit.spring.services.IFurnitureService;
import tn.esprit.spring.services.ILigneCommandeService;
@Async
@Scope(value = "session")
@Controller(value ="furnitureController")
@ELBeanName(value = "furnitureController")
//@Join(path= "/", to = "/pages/furniture.jsf")
public class FurnitureControllerJSF {

	@Autowired
	IFurnitureService iFurnitureService;
	
	@Autowired
	ICommandeService iCommandeService;
	
	@Autowired
	FurnitureRepository furnitureRepository;
	
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	
	@Autowired
	ILigneCommandeService iLigneCommande;
	
	@Autowired
	UserRestController urc;
	
	
	  private Long idPanier;
	private int id;

	private String login;
	private String password;
	private User user;
	private Boolean loggedIn;
	 private User authenticatedUser;
	private static Furniture f2;
	private String name;
	private String description;
	private float price;	
	private int quantity;
	private Long Barcode;	
	private Date publishedDate;
	private String Image;
	private Furniture fur;
	private Furniture furniture;
	private FurnitureState state;
	public UploadedFiles getFiles() {
		return files;
	}
	public void setFiles(UploadedFiles files) {
		this.files = files;
	}

	private UploadedFiles files;

	

	private List<Furniture> furnitures;

	 
	 public String ajouterPicture() throws FileNotFoundException{
		 FacesMessage facesMessage = new FacesMessage("oussssssssssssssssssssssssss");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			 Furniture f1= new Furniture(name,description,price,quantity);
			 iFurnitureService.ajouterMeuble(f1);
			f2=f1;
			return "/uploadview1.xhtml?faces-redirect=true";
			
		
		}
	 
	 
	 
	 
		public List<Furniture> displayFurniture() {
			furnitures= (List<Furniture>) furnitureRepository.findAll();
			return furnitures;
			
		
		}
		
		
		public String produitDansPanier(Furniture furni){
			furniture=furni;
			Client c= urc.getClientConnecte();
			 
			iLigneCommande.saveLigneCommande(c.getId());
			LigneCommande ligne=ligneCommandeRepository.getLigneCommandByUserId1(c.getId());
			idPanier=ligne.getId();
			return "/templateyoussef/cart.xhtml?faces-redirect=true";
		}
		
		
		

	 
		public String addMeubleDansPanier12(int furnitureId,int quantity, long panierId) {
			
			Client c= urc.getClientConnecte();
			System.out.println("bbbbbbbbbbbbb"+c);
			LigneCommande l=ligneCommandeRepository.getLigneCommandByUserId1(c.getId());
			System.out.println("bbbbbbbbbbbbb"+l);
			
		 
			
			iFurnitureService.addMeubleDansPanier12(furnitureId, quantity, panierId)	;	
			iCommandeService.saveCommande2(c.getId(), l.getId(), "EN_LIGNE");
			return "/templateyoussef/checkout.xhtml?faces-redirect=true";
		}
		
		
		
		
		////////////////////////////////////// getter and setter

	public IFurnitureService getiFurnitureService() {
		return iFurnitureService;
	}

	public void setiFurnitureService(IFurnitureService iFurnitureService) {
		this.iFurnitureService = iFurnitureService;
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
	public List<Furniture> getFurnitures() {
		furnitures=iFurnitureService.getAllFurniture();
		return furnitures;
	}

	public void setFurnitures(List<Furniture> furnitures) {
		this.furnitures = furnitures;
	}

	public Furniture getFur() {
		return fur;
	}

	public void setFur(Furniture fur) {
		this.fur = fur;
	}



	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public FurnitureState getState() {
		return state;
	}

	public void setState(FurnitureState state) {
		this.state = state;
	}
	 

	public static Furniture getF2() {
		return f2;
	}
	public static void setF2(Furniture f2) {
		FurnitureControllerJSF.f2 = f2;
	}
	public Furniture getFurniture() {
		return furniture;
	}
	public void setFurniture(Furniture furniture) {
		this.furniture = furniture;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getIdPanier() {
		return idPanier;
	}
	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}
	
}
