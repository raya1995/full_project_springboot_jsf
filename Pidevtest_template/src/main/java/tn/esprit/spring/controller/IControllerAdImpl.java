package tn.esprit.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Etat;
import tn.esprit.spring.entities.KindOfGood;
import tn.esprit.spring.entities.Notification;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.RentPeriod;
import tn.esprit.spring.entities.RentingType;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.services.AchatLocationService;
import tn.esprit.spring.services.FavoriteAdService;
import tn.esprit.spring.services.IAdService;
import tn.esprit.spring.services.MailService;
import tn.esprit.spring.services.NotificationService;
import tn.esprit.spring.services.RatingView;
import tn.esprit.spring.services.ReclamationService;
import tn.esprit.spring.services.WishListService;


@Scope(value = "session")
@ELBeanName(value = "adController")
//@Join(path = "/", to = "/gererAd/basic.jsf")
@Controller(value = "adController")
public class IControllerAdImpl {


	public static final Logger l = LogManager.getLogger(IControllerAdImpl.class);
	@Autowired
	UserRestController urc;
	@Autowired
	IAdService iadService;
	@Autowired
	IAdService adService;
	@Autowired
	RatingView ratingview;
	@Autowired
	MailService mails;
	@Autowired
	NotificationService NotificationService;
	@Autowired
	ReclamationService ReclamationService;
	@Autowired
	WishListService wlService;

	private int IdAd;
	private Ad ad;
	private Comment c;
	private List<Ad> ads;
	private List<Ad> filterprix;
	private List<Comment> com;
	private String Description;
	private String Location;
	private int Area;
	private int nbBaths;
	private String Image;
	private KindOfGood kindofgood;
	private Date AdDate;
	private Integer adIdToBeUpdated;
	private int ViewsNumber;
	private Boolean Success;
	private int Score;
	private Date StartDate;
	private Date EndDate;
	private RentPeriod rentperiod;
	private RentingType rentingtype;
	private float Price;
	private UploadedFile file;
	private String DescriptionComment;
	private int NumberLikes;
	private User user;
	private Client client;
	private int IdComment;
	private String descCom;
	private int idPub;
	private Ad aadDialog;
	private int a;
	private int nbRooms;
	private int nbGarage;
	private Integer CommetIdToBeUpdated;
	private String destination = "C:\\work\\Pidevtest_template\\src\\main\\webapp\\resources\\";
	private Ad Ads;
	private String updatedComment;
	private DonutChartModel donutModel;
	private DonutChartModel donutModel2;
	private int nbDisLikes;
	private Etat etat;
	private Boolean Furnished;
	private Boolean Terrace;
	private Boolean Garden;
	private Boolean SwimmingPool;
	private Boolean SousSol;
	private Boolean Garage;
	private Boolean AirConditioning;
	private Boolean Ascenseur;
	private Boolean Heater;
	private String newComment;
	private List<Reclamation> reclamations;
	private int IdRec;
	private int rating1;


	////////////////////////////////////Loua////////////////////////////////////////////////////////////
	@Autowired
	AchatLocationService achatService;
	@Autowired
	FavoriteAdService favService;

	private float areamin;
	private float areamax;
	private float pricemin;
	private float pricemax;
	private List<Ad> lastSold;	
	private List<Ad> favoris;
	private List<Ad> historique;
	private List<Ad> vente;
	private List<Ad> Hrent;
	private List<Ad> Trent;
	private List<Ad> rent;
	private List<Ad> searchp;
	private List<Ad> search;

	///oussama
	private int nbreannonce;
	private int nbrannonceuser;
	List<Ad> userannonces;




	/*public List<Ad> getAnnonces() {
	Client ClientConnecte=userRestController.getClientConnecte();
	return adService.afficherAnnonceFollow(ClientConnecte.getId());
}
	 */

	public WishListService getWlService() {
		return wlService;
	}
	public void setWlService(WishListService wlService) {
		this.wlService = wlService;
	}
	public int getIdRec() {
		return IdRec;
	}
	public void setIdRec(int idRec) {
		IdRec = idRec;
	}
	public AchatLocationService getAchatService() {
		return achatService;
	}
	public void setAchatService(AchatLocationService achatService) {
		this.achatService = achatService;
	}
	public FavoriteAdService getFavService() {
		return favService;
	}
	public void setFavService(FavoriteAdService favService) {
		this.favService = favService;
	}
	public void setLastSold(List<Ad> lastSold) {
		this.lastSold = lastSold;
	}
	public void setFavoris(List<Ad> favoris) {
		this.favoris = favoris;
	}
	public void setHistorique(List<Ad> historique) {
		this.historique = historique;
	}
	public void setVente(List<Ad> vente) {
		this.vente = vente;
	}
	public void setHrent(List<Ad> hrent) {
		Hrent = hrent;
	}
	public void setTrent(List<Ad> trent) {
		Trent = trent;
	}
	public void setRent(List<Ad> rent) {
		this.rent = rent;
	}
	public float getAreamin() {
		return areamin;
	}
	public void setAreamin(float areamin) {
		this.areamin = areamin;
	}
	public float getAreamax() {
		return areamax;
	}
	public void setAreamax(float areamax) {
		this.areamax = areamax;
	}
	public float getPricemin() {
		return pricemin;
	}
	public void setPricemin(float pricemin) {
		this.pricemin = pricemin;
	}
	public float getPricemax() {
		return pricemax;
	}
	public void setPricemax(float pricemax) {
		this.pricemax = pricemax;
	}
	public List<Ad> getLastSold() {
		lastSold = achatService.DernierBienVendu();
		return lastSold;
	}
	public List<Ad> getVente() {
		vente=null;
		vente = achatService.AnnonceVente();
		return vente;
	}
	public List<Ad> getHrent() {
		Hrent = achatService.AnnonceRentHoliday();
		return Hrent;
	}
	public List<Ad> getTrent() {
		Trent = achatService.AnnonceRentTemp();
		return Trent;
	}
	public List<Ad> getRent() {
		rent = achatService.AnnonceRent();
		return rent;
	}

	public String getSearch1() {
		search=null;
		if(rentingtype==null){
			search = achatService.RecherchePrimaireV(kindofgood.name(), Location, pricemin, pricemax);
		}
		else {
			search = achatService.RecherchePrimaireA(kindofgood.name(), Location, pricemin, pricemax);
		}
		return "/client/ResultSearch.xhtml?faces-redirect=true";
	}

	public String getSearchp1() {
		System.out.println("teeeeeeeeeeeest");System.out.println(rentingtype);System.out.println(rentingtype);

		if(rentingtype==null){
			searchp = achatService.FiltreMulticritèreV(kindofgood.name(), Location, areamin, areamax, pricemin, pricemax, nbBaths, nbRooms, nbGarage, 
					Terrace, SwimmingPool, Garden, AirConditioning, Heater, SousSol, Ascenseur);
		}
		else {
			searchp=achatService.FiltreMulticritèreA(kindofgood.name(), Location, areamin, areamax, pricemin, pricemax, nbBaths, nbRooms, nbGarage, 
					Terrace, SwimmingPool, Garden, AirConditioning, Heater, SousSol, Ascenseur, Furnished, rentingtype.name());
		}

		return "/client/ResultSearch2.xhtml?faces-redirect=true";

	}




	public List<Ad> getSearch() {
		return search;
	}
	public void setSearch(List<Ad> search) {
		this.search = search;
	}
	public List<Ad> getSearchp() {
		return searchp;
	}
	public void setSearchp(List<Ad> searchp) {
		this.searchp = searchp;
	}




	////////////////////////////////////Getters&Setters////////////////////////////////////////////////////////////




	public int getA() {
		return a;
	}





	public int getRating1() {
		return rating1;
	}





	public void setRating1(int rating1) {
		this.rating1 = rating1;
	}





	public List<Reclamation> getReclamations() {
		return reclamations;
	}





	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}





	public String getNewComment() {
		return newComment;
	}





	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}





	public MailService getMails() {
		return mails;
	}





	public void setMails(MailService mails) {
		this.mails = mails;
	}





	public NotificationService getNotificationService() {
		return NotificationService;
	}





	public void setNotificationService(NotificationService notificationService) {
		NotificationService = notificationService;
	}


	public Etat getEtat() {
		return etat;
	}



	public void setEtat(Etat etat) {
		this.etat = etat;
	}



	public UserRestController getUrc() {
		return urc;
	}



	public void setUrc(UserRestController urc) {
		this.urc = urc;
	}


	public ReclamationService getReclamationService() {
		return ReclamationService;
	}





	public void setReclamationService(ReclamationService reclamationService) {
		ReclamationService = reclamationService;
	}





	public Boolean getFurnished() {
		return Furnished;
	}



	public void setFurnished(Boolean furnished) {
		Furnished = furnished;
	}



	public Boolean getTerrace() {
		return Terrace;
	}



	public void setTerrace(Boolean terrace) {
		Terrace = terrace;
	}



	public Boolean getGarden() {
		return Garden;
	}



	public void setGarden(Boolean garden) {
		Garden = garden;
	}



	public Boolean getSwimmingPool() {
		return SwimmingPool;
	}



	public void setSwimmingPool(Boolean swimmingPool) {
		SwimmingPool = swimmingPool;
	}



	public Boolean getSousSol() {
		return SousSol;
	}



	public void setSousSol(Boolean sousSol) {
		SousSol = sousSol;
	}



	public Boolean getGarage() {
		return Garage;
	}



	public void setGarage(Boolean garage) {
		Garage = garage;
	}



	public Boolean getAirConditioning() {
		return AirConditioning;
	}



	public void setAirConditioning(Boolean airConditioning) {
		AirConditioning = airConditioning;
	}



	public Boolean getAscenseur() {
		return Ascenseur;
	}



	public void setAscenseur(Boolean ascenseur) {
		Ascenseur = ascenseur;
	}



	public Boolean getHeater() {
		return Heater;
	}



	public void setHeater(Boolean heater) {
		Heater = heater;
	}



	public static Logger getL() {
		return l;
	}



	public DonutChartModel getDonutModel2() {
		return donutModel2;
	}



	public void setDonutModel2(DonutChartModel donutModel2) {
		this.donutModel2 = donutModel2;
	}



	public DonutChartModel getDonutModel() {
		return donutModel;
	}



	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}



	public int getNbDisLikes() {
		return nbDisLikes;
	}



	public void setNbDisLikes(int nbDisLikes) {
		this.nbDisLikes = nbDisLikes;
	}



	public void setAds(Ad ads) {
		Ads = ads;
	}



	public RatingView getRatingview() {
		return ratingview;
	}



	public void setRatingview(RatingView ratingview) {
		this.ratingview = ratingview;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public Integer getCommetIdToBeUpdated() {
		return CommetIdToBeUpdated;
	}

	public void setCommetIdToBeUpdated(Integer commetIdToBeUpdated) {
		this.CommetIdToBeUpdated = commetIdToBeUpdated;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public int getNbRooms() {
		return nbRooms;
	}

	public void setNbRooms(int nbRooms) {
		this.nbRooms = nbRooms;
	}

	public int getNbGarage() {
		return nbGarage;
	}

	public void setNbGarage(int nbGarage) {
		this.nbGarage = nbGarage;
	}

	public void setA(int a) {
		this.a = a;
	}

	public Ad getAadDialog() {
		return aadDialog;
	}

	public void setAadDialog(Ad aadDialog) {
		this.aadDialog = aadDialog;
	}

	public int getIdComment() {
		return IdComment;
	}

	public void setIdComment(int idComment) {
		IdComment = idComment;
	}

	public IAdService getIadService() {
		return iadService;
	}

	public void setIadService(IAdService iadService) {
		this.iadService = iadService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public User getUser() {
		return user;
	}



	public int getIdPub() {
		return idPub;
	}

	public void setIdPub(int idPub) {
		this.idPub = idPub;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getDescCom() {
		return descCom;
	}

	public void setDescCom(String descCom) {
		this.descCom = descCom;
	}


	private Ad selectedAd;


	public Ad getSelectedAd() {
		return selectedAd;
	}

	public void setSelectedAd(Ad selectedAd) {
		this.selectedAd = selectedAd;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		this.Image = image;
	}

	public Comment getC() {
		return c;
	}

	public void setC(Comment c) {
		this.c = c;
	}

	public List<Comment> getCom() {
		return com;
	}

	public void setCom(List<Comment> com) {
		this.com = com;
	}

	public String getDescriptionComment() {
		return DescriptionComment;
	}

	public void setDescriptionComment(String descriptionComment) {
		DescriptionComment = descriptionComment;
	}

	public int getNumberLikes() {
		return NumberLikes;
	}

	public void setNumberLikes(int numberLikes) {
		NumberLikes = numberLikes;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}



	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public RentPeriod getRentperiod() {
		return rentperiod;
	}

	public void setRentperiod(RentPeriod rentperiod) {
		this.rentperiod = rentperiod;
	}
	public RentPeriod[] getrentperiods() {
		return RentPeriod.values();
	}
	public int getViewsNumber() {
		return ViewsNumber;
	}

	public void setViewsNumber(int viewsNumber) {
		ViewsNumber = viewsNumber;
	}

	public Boolean getSuccess() {
		return Success;
	}

	public void setSuccess(Boolean success) {
		Success = success;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public Integer getAdIdToBeUpdated() {
		return adIdToBeUpdated;
	}

	public void setAdIdToBeUpdated(Integer adIdToBeUpdated) {
		this.adIdToBeUpdated = adIdToBeUpdated;
	}

	public int getIdAd() {
		return IdAd;
	}

	public void setIdAd(int idAd) {
		IdAd = idAd;
	}


	public Date getAdDate() {
		return AdDate;
	}

	public void setAdDate(Date adDate) {
		AdDate = adDate;
	}

	public KindOfGood[] getKindofgoods() {
		return KindOfGood.values();
	}

	public KindOfGood getKindofgood() {
		return kindofgood;
	}

	public IAdService getAdService() {
		return adService;
	}

	public void setAdService(IAdService adService) {
		this.adService = adService;
	}

	public void setKindofgood(KindOfGood kindofgood) {
		this.kindofgood = kindofgood;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public int getArea() {
		return Area;
	}

	public void setArea(int area) {
		Area = area;
	}

	public Ad getAd() {
		return ad;
	}


	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public List<Ad> getAds() {
		ads = adService.retrieveAllAds();
		return ads;
	}

	public RentingType[] getrentingtypes() {
		return RentingType.values();
	}

	public RentingType getRentingtype() {
		return rentingtype;
	}

	public void setRentingtype(RentingType rentingtype) {
		this.rentingtype = rentingtype;
	}


	public int getNbBaths() {
		return nbBaths;
	}

	public void setNbBaths(int nbBaths) {
		this.nbBaths = nbBaths;
	}




	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






	public String getUpdatedComment() {
		return updatedComment;
	}



	public void setUpdatedComment(String updatedComment) {
		this.updatedComment = updatedComment;
	}



	public Ad addAd(Ad ad) { Ad a = iadService.addAd(ad); return a; }


	public String addad() {


		Client c=UserRestController.ClientConnecte;
		Ad tmp = new Ad(Description, Location, Area, kindofgood, c, StartDate, EndDate, rentperiod, Price, rentingtype, nbBaths, nbRooms, nbGarage, Terrace, 
				SwimmingPool, Garden, AirConditioning, Heater, SousSol, Ascenseur, Furnished);


		try {
			upload();
			TransferFile(file.getFileName(), file.getInputStream());
			tmp.setImage(file.getFileName());
			tmp.setEtat(Etat.Disponible);


			//tmp.setUser(c);



			l.info("uploaded");

		} catch (Exception e) {
			l.info(e.toString());
		}
		adService.addOrUpdateAd(tmp);
		//System.out.println("ededededede " + file.getFileName());
		//return "/gererAd/basic.xhtml?faces-redirect=true";

		User currentuser = urc.getClientConnecte();
		Date currentdate = new Date();
		Ad adnotif;

		adnotif = adService.addAd(tmp);

		//mails.sendEmail(user);
		//System.out.println("ededededede " + file.getFileName());

		NotificationService.addNotification(new Notification(" added a new property", currentdate, c, adnotif, 0));
		return "/client/basic.xhtml?faces-redirect=true";

	}



	public String SeeAllAds(){

		return "/client/basic.xhtml?faces-redirect=true";
	}

	/*public List<Ad> getKk() {


		kk=adService.filter();

		return kk;
	}
	 */


	public void deleteAd(int IdAd) {
		iadService.deleteAd(IdAd);
	}

	public List<Ad> getFilterprix() {
		filterprix=adService.filter();
		return filterprix;
	}

	public void setFilterprix(List<Ad> filterprix) {
		this.filterprix = filterprix;
	}


	public void removeAddash(int IdAd) {
		iadService.deleteAd(IdAd);
	}

	public String removeAd(int IdAd) {
		adService.deleteAd(IdAd);
		return "/client/basic.xhtml?faces-redirect=true";
	}

	public String displayAd(Ad ad) {
		this.setImage(ad.getImage());
		this.setKindofgood(ad.getKindofgood());
		this.setLocation(ad.getLocation());
		this.setArea(ad.getArea());
		this.setDescription(ad.getDescription());
		this.setUser(ad.getUser());
		this.setPrice(ad.getPrice());
		this.setNbRooms(ad.getNbRooms());
		this.setNbGarage(ad.getNbGarage());
		this.setAdIdToBeUpdated(ad.getIdAd());
		this.setAdDate(ad.getAdDate());
		this.setStartDate(ad.getStartDate());
		this.setEndDate(ad.getEndDate());
		this.setRentperiod(ad.getRentperiod());
		this.setRentingtype(ad.getRentingtype());
		this.setNbBaths(ad.getNbBaths());

		return "/gererAd/ModifyTemplate.xhtml?faces-redirect=true";

	}


	public String openDetail(Ad aad){
		ad = aad;
		//	setBonus(aad.getBonus());
		return "/gererAd/chaquead.xhtml?faces-redirect=true";
	}
	public String go() {
		return "/gererAd/ModifyTemplate.xhtml?faces-redirect=true";
	}
	public String go2() {
		return "/client/basic.xhtml?faces-redirect=true";
	}

	public String ClearAd() {
		//String navigateTo = "null";
		this.setKindofgood(null);
		this.setLocation(null);
		this.setArea(0);
		this.setDescription(null);
		this.setAdIdToBeUpdated(null);
		this.setPrice(0);
		this.setNbBaths(0);
		this.setNbGarage(0);
		this.setNbRooms(0);
		//navigateTo = "/webapp/gererAd/ModifyAd.xhtml?faces-redirect=true";
		return "/gererAd/AddHouseSell.xhtml?faces-redirect=true";

	}



	public String Clear() {
		//String navigateTo = "null";
		this.setKindofgood(null);
		this.setLocation(null);
		this.setArea(0);
		this.setDescription(null);
		this.setAdIdToBeUpdated(null);
		this.setPrice(0);
		this.setNbBaths(0);
		this.setNbGarage(0);
		this.setNbRooms(0);
		//navigateTo = "/webapp/gererAd/ModifyAd.xhtml?faces-redirect=true";
		return "/gererAd/ModifyTemplate.xhtml?faces-redirect=true";

	}

	public String ClearAdRent() {
		this.setKindofgood(null);
		this.setLocation(null);
		this.setArea(0);
		this.setStartDate(null);
		this.setEndDate(null);
		this.setDescription(null);
		this.setRentperiod(null);
		this.setRentingtype(null);
		this.setPrice(0);
		this.setNbBaths(0);
		this.setNbGarage(0);
		this.setNbRooms(0);
		this.setAdIdToBeUpdated(null);
		return "/gererAd/AddHouseRent.xhtml?faces-redirect=true";

	} 




	public String updateAd() {

		User currentuser = urc.getClientConnecte();
		Date currentdate = new Date();
		//Ad adnotif;

		adService.addOrUpdateAd(new Ad(adIdToBeUpdated,Description, Location, Area,currentdate,kindofgood,StartDate,EndDate,rentperiod,Price,rentingtype,nbGarage,nbRooms,Image,nbBaths,currentuser));


		NotificationService.addNotification(new Notification(" added a new property", currentdate, currentuser, ad, 1));
		return "/client/basic.xhtml?faces-redirect=true";

	}

	public Ad modifyAd(@RequestBody Ad ad) {
		return iadService.updateAd(ad);
	}

	public Ad getAdById(int adId) {
		return adService.getAdById(adId);
	}
	public void addMessage(String summary) {  //addMessage("Data saved");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);}

	public String getAdById1(int idAd) {

		l.info("aaaaaaaaaa"+idAd);
		adService.getAdById(idAd);
		//getAllCommentsByAd
		setIdPub(idAd);
		setCom(adService.DescriptionComments(idAd)); 
		return "/gererAd/DescriptionComment.xhtml?faces-redirect=true";
	}


	public String addComment() {


		Client c=urc.getClientConnecte();
		System.out.println("cmnt " + newComment);
		Comment cmnt = new Comment();
		cmnt.setDescriptionComment(newComment);
		cmnt.setIsBlocked(false);
		cmnt.setAds(ad);
		cmnt.setClient(c);
		adService.addCommentaire(cmnt);
		return "/client/basic.xhtml?faces-redirect=true";

	}

	public String addComment1(int idAd) {
		Client c=urc.getClientConnecte();

		adService.addComment(new Comment(descCom),c.getId(), idPub,false);
		return "/client/basic.xhtml?faces-redirect=true";
	}


	public String removeComment(int idComment) {
		//String navigateTo = "null";
		l.info("hhhhhhhhhhh"+ idComment);
		adService.deleteComment(idComment);
		return "/client/basic.xhtml?faces-redirect=true";


	}
	/*public String deleteComment(int idComment) {
		adService.deleteComment(idComment);
		return "/gererAd/basic.xhtml?faces-redirect=true";}

	 */
	public List<Comment> listCommentaire(Ad ad) {
		return ad.getComments();
	}

	public Comment UpdateComment(Comment comment) {
		return adService.UpdateComment(comment);

	}


	public String ModifyComment() {
		System.out.println("cccmmt "+updatedComment);
		adService.addOrUpdateComment(new Comment(CommetIdToBeUpdated,updatedComment,client,Ads));
		l.info("modifyyyyyy    "+ CommetIdToBeUpdated);
		return "/client/basic.xhtml?faces-redirect=true";
	}


	public void AssignCommentToanAd(int CommentId, int AdId) {
		iadService.AssignCommentToanAd(CommentId, AdId);
	}

	public List<String> getAllCommentsByAd(int AdId) {
		return adService.getAllCommentsByAd(AdId);
	}
	public void displayComment(Comment com) {

		this.setUpdatedComment(com.getDescriptionComment());
		this.setNumberLikes(com.getNumberLikes());
		this.setClient(com.getClient());
		this.setAds(com.getAds());
		this.setCommetIdToBeUpdated(com.getIdComment());
	}


	public String displayAdComment(Comment c) {

		this.setDescriptionComment(c.getDescriptionComment());
		this.setCommetIdToBeUpdated(c.getIdComment());
		return "/gererAd/showComments.xhtml?faces-redirect=true";

	}

	public int countComments() {

		return iadService.countComments();
	}

	public int nbrLike() {
		return iadService.nbrLike();
	}

	public List<Ad> getAdWithBestLikesOnCommentsJPQL() {
		return iadService.getAdWithBestLikesOnCommentsJPQL();
	}

	public int maxnblike() {
		return iadService.maxnblike();
	}

	public boolean succes() {
		return iadService.succes();
	}

	public double AVG_nbcomment() {
		return iadService.AVG_nbcomment();
	}

	public boolean BlockCommentsWithInsultingWords() {
		return iadService.BlockCommentsWithInsultingWords();
	}

	public void ScoreIncrement() {
		iadService.ScoreIncrement();
	}

	public int AdsForToday() {
		return iadService.AdsForToday();
	}

	public double AVG_Ads_Year() {
		return iadService.AVG_Ads_Year();
	}

	public double countAds() {
		return iadService.countAds();
	}

	public List<String> getAllCommentsBlockedJPQL() {
		return iadService.getAllCommentsBlockedJPQL();

	}

	public List<String> getAdsFromTheSameUserJPQL() {
		return iadService.getAdsFromTheSameUserJPQL();

	}

	public int countCommentsJPQL(int IdAd) {
		return iadService.countCommentsJPQL(IdAd);
	}
	public int getNumberView(int idad) {
		return iadService.getNumberView(idad);
	}
	public int getNumberViewId(int idad) {
		return adService.getNumberView(idad);
	}

	public boolean increment(int idad) {
		return iadService.increment(idad);

	}
	public boolean incrementad(int idAd) {
		a=ratingview.getRating8(idAd);
		ad = adService.getAdById(idAd);
		//setAadDialog(adService.getAdById(idAd)); 
		//adService.getAdById(idAd);
		l.info("mmmmmmm" + idAd);
		return adService.increment(idAd);

	}

	public Integer getRating8(int idad) {
		return ratingview.getRating8(idad);
	}



	public String BlockCommentsWithInsultingWords2(int idComment) {

		//c = adService.getCommentById(IdComment);
		l.info("Id Of Comment isssssss   " + idComment);
		adService.getCommentById(idComment);
		iadService.BlockCommentsWithInsultingWords2(idComment);
		//Long idclient =c.getClient().getId();
		//adService.BlockUserByBadComments(c.getClient().getId());
		return "/gererAd/DescriptionComment.xhtml?faces-redirect=true";

	}


	/*private List<Predicate<Ad>> configFilter(Filter<Ad> filter) {
    List<Predicate<Ad>> predicates = new ArrayList<>();
    if (filter.hasParam("id")) {
        Predicate<Ad> idPredicate = (Ad c) -> c.getIdAd().equals(filter.getParam("id"));
        predicates.add(idPredicate);
    }

    if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
        Predicate<Ad> minMaxPricePredicate = (Ad c) -> c.getPrice()
                >= Double.valueOf((String) filter.getParam("minPrice")) && c.getPrice()
                <= Double.valueOf((String) filter.getParam("maxPrice"));
        predicates.add(minMaxPricePredicate);
    } else if (filter.hasParam("minPrice")) {
        Predicate<Ad> minPricePredicate = (Ad c) -> c.getPrice()
                >= Double.valueOf((String) filter.getParam("minPrice"));
        predicates.add(minPricePredicate);
    } else if (filter.hasParam("maxPrice")) {
        Predicate<Ad> maxPricePredicate = (Ad c) -> c.getPrice()
                <= Double.valueOf((String) filter.getParam("maxPrice"));
        predicates.add(maxPricePredicate);
    }

    if (has(filter.getEntity())) {
    	Ad filterEntity = filter.getEntity();
        if (has(filterEntity.getModel())) {
            Predicate<Ad> modelPredicate = (Ad c) -> c.getModel().toLowerCase().contains(filterEntity.getModel().toLowerCase());
            predicates.add(modelPredicate);
        }

        if (has(filterEntity.getPrice())) {
            Predicate<Ad> pricePredicate = (Ad c) -> c.getPrice().equals(filterEntity.getPrice());
            predicates.add(pricePredicate);
        }

        if (has(filterEntity.getName())) {
            Predicate<Ad> namePredicate = (Ad c) -> c.getName().toLowerCase().contains(filterEntity.getName().toLowerCase());
            predicates.add(namePredicate);
        }
    }
    return predicates;
}*/
	public List<Ad> filter(){

		return iadService.filter();
	}

	public List<Comment> DescriptionComments(int idc) {
		return iadService.DescriptionComments(idc);
	}



	public void upload() {

		if (file != null) {
			FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else
			System.out.println("file is null");
	}

	public void handleFileUpload(FileUploadEvent event) {
		l.info("ddddddddddddddd " + event.getFile().getFileName());
		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void TransferFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(destination + fileName));
			int reader = 0;
			byte[] bytes = new byte[(int) file.getSize()];
			while ((reader = in.read(bytes)) != -1) {
				out.write(bytes, 0, reader);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


	public List<Ad> retrieveAllVillaJPQL(KindOfGood kindofgood) {
		return adService.retrieveAllVillaJPQL(kindofgood);
	}


	public List<Ad> retrieveAllAppartementJPQL(KindOfGood kindofgood) {
		return adService.retrieveAllAppartementJPQL(kindofgood);
	}

	public List<Ad> retrieveAllStudioJPQL(KindOfGood kindofgood) {
		return adService.retrieveAllStudioJPQL(kindofgood);
	}


	public List<Ad> retrieveAllWorkshopJPQL(KindOfGood kindofgood) {
		return adService.retrieveAllWorkshopJPQL(kindofgood);
	}


	public String donutGraph(Model model) {

		donutModel = new DonutChartModel();
		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(adService.retrieveAllAppartementJPQL(kindofgood).size());
		values.add(adService.retrieveAllStudioJPQL(kindofgood).size());
		values.add(adService.retrieveAllVillaJPQL(kindofgood).size());
		values.add(adService.retrieveAllWorkshopJPQL(kindofgood).size());
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(183, 185, 189)");
		bgColors.add("rgb(255, 205, 86)");
		bgColors.add("rgb(54, 162, 235)");

		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);

		List<String> labels = new ArrayList<>();
		labels.add("Villa");
		labels.add("Apartment");
		labels.add("Studio");
		labels.add("Workshop");
		data.setLabels(labels);
		donutModel.setData(data);
		return "/gererAd/donutGraph.xhtml?faces-redirect=true";

	}



	public String openDetail1(Ad a) {
		ad = a;
		//setBonus(a.getBonus());
		return "/gererAd/chaquead.xhtml?faces-redirect=true";
	}

	public String openDetaildashbord(Ad a) {
		ad = a;
		//setBonus(a.getBonus());
		return "/Login/chaqueaddashbord.xhtml?faces-redirect=true";
	}

	public String openDetailnotification(Ad a) {
		ad = a;
		//setBonus(a.getBonus());
		return "/Login/chaqueaddashbord.xhtml?faces-redirect=true";
	}
	public String reclamerComment(Comment comm) {

		Reclamation rec = new Reclamation();
		l.info("aaaaaaaaaaaaaaaah" + comm.getDescriptionComment());
		c = comm;
		rec.setCommentaire(comm);
		rec.setDescription("this user added comment with bad words");
		ReclamationService.addReclamation(rec);
		return "/gererAd/DescriptionComment.xhtml?faces-redirect=true";

	}
	public void ReclamerUser() {
		iadService.ReclamerUser();
	}




	public String BlockComments(int id) {
		//adService.BlockCommentsWithInsultingWords2(id);
		l.info("idddddddd "+id);
		// Users currentuser = UsersController.getAuthenticatedUser();
		// mails.sendEmail(comment.getUsers().getEmail());
		mails.sendEmail(c.getClient().getEmail(), c.getDescriptionComment(), c.getClient().getFirstName());

		//c.setIsBlocked(true);
		return "/Login/dashbordvente.xhtml?faces-redirect=true";
	}

	public String deleteReclamation(int id)  {
		l.info("aaaaaaaaaaaa" + id);
		ReclamationService.deleteReclamation(id);
		addMessage("System Error", "Please try again later.");
		return "/Login/dashbordvente.xhtml?faces-redirect=true";

	}
	public List<Reclamation> getreclamations() {
		reclamations = ReclamationService.retrieveAllReclamations();
		return reclamations;
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public void BlockUserByBadComments1(Long id){
		//Long idclient =c.getClient().getId();
		adService.BlockUserByBadComments(id);

	}
	public int Countads() {
		return adService.Countads();
	}
	public void BuyHouse(int idAd){
		//adService.getAdById(idAd);
		setEtat(Etat.Vendu);
	}

	/*public String incrementdislike(int idad) {
	adService.incrementdislike(idad);
	return "/gererAd/basic.xhtml?faces-redirect=true";
}*/
	/*public String incrementlike(int idad) {
	 adService.incrementlike(idad);
	return "/gererAd/basic.xhtml?faces-redirect=true";
}*/


	public void incrementlike(int idad) {
		l.info("aaaaaaaaaaaaaaaaaaaaaaaaa" + idad);
		Ad a = adService.getAdById(idad);

		User UserId;
		UserId = urc.getClientConnecte();
		List<User> us = a.getLike();
		List<User> disus = a.getDisLike();
		if (us == null)

			us = new ArrayList<User>();
		if (disus != null) {
			List<User> t = new ArrayList<User>();
			for (User users : disus) {
				if (users.getId() == UserId.getId()) {
					System.out.println("dislike removed");
					continue;
				}
				t.add(users);
			}
			a.setDisLike(t);
		}
		boolean found = false;
		for (User users : us) {
			if (users.getId() == UserId.getId()) {
				found = true;
			}
		}
		if (!found) {
			us.add(UserId);

			a.setLike(us);
			/*	if (us.size() == 1 || us.size() == 2 || us.size() == 3) {

			int k = a.getRating();
			k++;

			a.setRating(k);
		}*/
			System.out.println("size " + us.size() + " rating " + a.getRating());

		}
		adService.updateAd(a);
		//rating1 = a.getRating();

	}

	public boolean likeDislike(Ad a) {
		User UserId;
		UserId = urc.getClientConnecte();
		List<User> us = a.getLike();

		for (User users : us) {
			if (users.getId() == UserId.getId()) {
				return true;
			}
		}
		return false;
	}




	public int nbLikes(Ad a) {
		return a.getLike().size();
	}

	public int nbDisLikes(Ad a) {
		return a.getDisLike().size();
	}


	public void incrementdislike(int id) {
		Ad a = adService.getAdById(id);
		User UserId;
		UserId = urc.getClientConnecte();
		List<User> disus = a.getLike();
		List<User> us = a.getDisLike();
		if (us == null)
			us = new ArrayList<User>();
		if (disus != null) {
			List<User> t = new ArrayList<User>();
			for (User users : disus) {
				System.out.println("user " + users.getId() + " uu " + UserId.getId());
				if (users.getId() == UserId.getId()) {
					continue;
				}
				t.add(users);
			}
			a.setLike(t);
		}
		boolean found = false;
		for (User users : us) {
			if (users.getId() == UserId.getId()) {
				found = true;
			}
		}
		if (!found) {
			us.add(UserId);

			a.setDisLike(us);
			/*if (us.size() == 1 ) {

			int k = a.getRating();
			k--;
			a.setRating(k);
		}*/
			/*if (us.size()
				== 3) {
		//	a.setEtat(Etat.Bloquee);
		}*/
			adService.updateAd(a);
		}
		//rating1 = a.getRating();

	}
	public List<Ad> getAnnonces() {
		Client ClientConnecte=urc.getClientConnecte();
		return adService.afficherAnnonceFollow(ClientConnecte.getId());
	}
	/////////////////////// Fonction loua//////////////////////////////////

	public String RedirectToHistorique() {
		return "/client/Historique.xhtml?faces-redirect=true";
	}

	public List<Ad> getHistorique() {
		historique=achatService.BienVenduParClient(UserRestController.ClientConnecte.getId());
		return historique;
	}
	public String RedirectToFavorite() {
		return "/client/Favorite.xhtml?faces-redirect=true";
	}

	public List<Ad> getFavoris() {
		favoris=favService.getAllFavoritesByClient(UserRestController.ClientConnecte.getId());
		return favoris;
	}

	public void sold(int id){
		System.out.println("Teeeeeeeeeeeeeeeeeeeeeeeest");
		achatService.Archiver(id);
		//return "/Favorite.xhtml?faces-redirect=true";
	}

	public void favoris(int id){
		System.out.println("Teeeeeeeeeeeeeeeeeeeeeeeest");
		favService.favoriserAnnonce(id, UserRestController.ClientConnecte.getId());
	}

	public int getNbreannonce() {
		Client ClientConnecte=urc.getClientConnecte();
		return adService.Myannoncenombre(ClientConnecte.getId());
	}
	public int getNbrAd(Long Id) {

		return adService.Myannoncenombre(Id);
	}
	public void setNbreannonce(int nbreannonce) {
		this.nbreannonce = nbreannonce;
	}
	public int getNbrannonceuser() {
		Client ClientConnecte=urc.getInviteClient();
		return adService.Myannoncenombre(ClientConnecte.getId());

	}
	public void setNbrannonceuser(int nbrannonceuser) {
		this.nbrannonceuser = nbrannonceuser;
	}
	public List<Ad> getUserannonces() {
		long Id=urc.getInviteClient().getId();
		System.out.println(adService.annonceuser(Id));
		return adService.annonceuser(Id);
	}
	public void setUserannonces(List<Ad> userannonces) {
		this.userannonces = userannonces;
	}



}
