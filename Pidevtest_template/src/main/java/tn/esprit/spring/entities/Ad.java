package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "T_AD")


public class Ad implements Serializable{
		private static final long serialVersionUID = 1L;
        
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int IdAd;
		private int nbLikes;
		private int nbDisLikes;
		private String Description;
		private String Image;
		private String Location;
		private int Area;
		@Enumerated(EnumType.STRING)
		private Region region;
		@Column(name = "rating", nullable = true)
		private Integer rating;
		@Column(name = "rating2", nullable = true)
		private Integer rating2 =0;
		@Column()
		@Enumerated(EnumType.STRING)
		private Etat etat;
		@Temporal(TemporalType.DATE)
		private Date AdDate;
		@Column(name = "ViewsNumber", nullable = true)
		private int ViewsNumber;
		private Boolean Success;
		@Column(name = "Score", nullable = true)
		private int Score;
		@Enumerated(EnumType.STRING)
		//@NotNull
		private KindOfGood kindofgood;
		@OneToMany(mappedBy = "Ads",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
		private List<Comment> comments;
		@ManyToOne
		private User user;

		@Temporal(TemporalType.DATE)
		private Date StartDate;
		@Temporal(TemporalType.DATE)
		private Date EndDate;
		@Enumerated(EnumType.STRING)
		//@NotNull
		private RentPeriod rentperiod;
		@Column(name = "Price", nullable = true)
		private float Price;
		@Enumerated(EnumType.STRING)
		//@NotNull
		private RentingType rentingtype;
		@OneToMany(mappedBy = "ad", cascade = CascadeType.REMOVE)
		private List<Notification> notifications = new ArrayList<>();
		/*@OneToMany(mappedBy = "ad", cascade = CascadeType.REMOVE)
		private List<Appointment> appointment=new ArrayList<>();
		*/
		
		//oumayma
		@OneToMany(cascade = CascadeType.ALL, mappedBy="client")
		private Set<Appointment> Appointment;
		
		
		@LazyCollection(LazyCollectionOption.FALSE)
		@ManyToMany
		List<User> like;
		@LazyCollection(LazyCollectionOption.FALSE)
		@ManyToMany
		List<User> disLike;
		
		
		
		
	/*	
		public void setAppointment(List<Appointment> appointment) {
			this.appointment = appointment;
		}
*/
				//louu
				private int nbBaths;
				private int nbRooms;
				private int nbGarage;
				//options
				private boolean Terrace;
			
				private boolean SwimmingPool;
			
				private boolean Garden;	
				private boolean AirConditioning;
				private boolean heater;
				public boolean SousSol;		
				private boolean ascenseur;
				
				private boolean Furnished;// cet attribut uniquement pour les annonce de location c'est à dire meublé ou nn
				@OneToOne(mappedBy="ad") 
				FavoriteAd favoriteAd; 

				@OneToOne(cascade = CascadeType.ALL,mappedBy="ad") 
				Prices price;
				
				private long idAcheteur;
				
				private String garantie;
				@Temporal(TemporalType.DATE)
				private Date dateAchat;

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		public List<Notification> getNotifications() {
					return notifications;
				}

				public void setNotifications(List<Notification> notifications) {
					this.notifications = notifications;
				}

				/*public List<Appointment> getAppointment() {
					return appointment;
				}

				public void setAppointment(List<Appointment> appointment) {
					this.appointment = appointment;
				}*/

				public boolean isTerrace() {
					return Terrace;
				}

				public void setTerrace(boolean terrace) {
					Terrace = terrace;
				}

				public boolean isSwimmingPool() {
					return SwimmingPool;
				}

				public void setSwimmingPool(boolean swimmingPool) {
					SwimmingPool = swimmingPool;
				}

				public boolean isGarden() {
					return Garden;
				}

				public void setGarden(boolean garden) {
					Garden = garden;
				}

				public boolean isAirConditioning() {
					return AirConditioning;
				}

				public void setAirConditioning(boolean airConditioning) {
					AirConditioning = airConditioning;
				}

				public boolean isHeater() {
					return heater;
				}

				public void setHeater(boolean heater) {
					this.heater = heater;
				}

				public boolean isSousSol() {
					return SousSol;
				}

				public void setSousSol(boolean sousSol) {
					SousSol = sousSol;
				}

				public boolean isAscenseur() {
					return ascenseur;
				}

				public void setAscenseur(boolean ascenseur) {
					this.ascenseur = ascenseur;
				}

				public boolean isFurnished() {
					return Furnished;
				}

				public void setFurnished(boolean furnished) {
					Furnished = furnished;
				}

				public FavoriteAd getFavoriteAd() {
					return favoriteAd;
				}

				public void setFavoriteAd(FavoriteAd favoriteAd) {
					this.favoriteAd = favoriteAd;
				}

				public long getIdAcheteur() {
					return idAcheteur;
				}

				public void setIdAcheteur(long idAcheteur) {
					this.idAcheteur = idAcheteur;
				}

				public String getGarantie() {
					return garantie;
				}

				public void setGarantie(String garantie) {
					this.garantie = garantie;
				}

				public Date getDateAchat() {
					return dateAchat;
				}

				public void setDateAchat(Date dateAchat) {
					this.dateAchat = dateAchat;
				}

				public void setPrice(Prices price) {
					this.price = price;
				}

		public List<User> getLike() {
			return like;
		}

		public void setLike(List<User> like) {
			this.like = like;
		}

		public List<User> getDisLike() {
			return disLike;
		}

		public void setDisLike(List<User> disLike) {
			this.disLike = disLike;
		}

		public Ad() {
			super();
			// TODO Auto-generated constructor stub
		}

			public List<Notification> getNotification() {
			return notifications;
		}




		public void setNotification(List<Notification> notification) {
			this.notifications = notification;
		}




		public Ad(String description, String location, int area, Date adDate,KindOfGood kindofgood) {
			super();
			Description = description;
			Location = location;
			Area = area;
			AdDate = adDate;
			this.kindofgood = kindofgood;
		}
		


		

		public Ad(int idAd, String description, String location, int area, Date adDate,
				KindOfGood kindofgood, Date startDate, Date endDate, RentPeriod rentperiod, float price,
				RentingType rentingtype, int nbGarage, int nbRooms ,String image,int nbBaths,User user) {
			super();
			IdAd = idAd;
			Description = description;
			Location = location;
			Area = area;
			AdDate = adDate;
			this.kindofgood = kindofgood;
			StartDate = startDate;
			EndDate = endDate;
			this.rentperiod = rentperiod;
			Price = price;
			this.rentingtype = rentingtype;
			this.nbGarage = nbGarage;
			Image = image;
			this.nbRooms = nbRooms;
			this.user = user;
			
		}


		public Ad(int idAd) {
			super();
			IdAd = idAd;
		}




	public Ad(String description, String location, int area, KindOfGood kindofgood, Date startDate, Date endDate,
				RentPeriod rentperiod, float price, RentingType rentingtype , int nbGarage, int nbRooms ,int nbBaths,User user) {
			super();
			Description = description;
			Location = location;
			Area = area;
			this.kindofgood = kindofgood;
			StartDate = startDate;
			EndDate = endDate;
			this.rentperiod = rentperiod;
			Price = price;
			this.rentingtype = rentingtype;
			this.nbGarage = nbGarage;
			this.nbRooms = nbRooms;
			this.nbBaths = nbBaths;
			this.user = user;
		}


		public int getIdAd() {
			return IdAd;
		}

		public void setIdAd(int idAd) {
			IdAd = idAd;
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

		

		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public int getArea() {
			return Area;
		}


		public void setArea(int area) {
			Area = area;
		}

		@PrePersist
		protected void onCreate() {
		    if (AdDate== null) { AdDate= new Date(); }
		}
		public Date getAdDate() {
			return AdDate;
		}

		public void setAdDate(Date adDate) {
			AdDate = adDate;
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


		public KindOfGood getKindofgood() {
			return kindofgood;
		}


		public void setKindofgood(KindOfGood kindofgood) {
			this.kindofgood = kindofgood;
		}


		public List<Comment> getComments() {
			return comments;
		}

		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}


		public Integer getRating2() {
			return rating2;
		}


		public void setRating2(Integer rating2) {
			this.rating2 = rating2;
		}


		public Date getStartDate() {
			return StartDate;
		}


		public void setStartDate(Date startDate) {
			StartDate = startDate;
		}


		public Date getEndDate() {
			return EndDate;
		}


		public void setEndDate(Date endDate) {
			EndDate = endDate;
		}


		public RentPeriod getRentperiod() {
			return rentperiod;
		}


		public void setRentperiod(RentPeriod rentperiod) {
			this.rentperiod = rentperiod;
		}


		public float getPrice() {
			return Price;
		}


		public void setPrice(float price) {
			Price = price;
		}


		public RentingType getRentingtype() {
			return rentingtype;
		}


		public void setRentingtype(RentingType rentingtype) {
			this.rentingtype = rentingtype;
		}


	

		public Integer getRating() {
			return rating;
		}


		public void setRating(Integer rating) {
			this.rating = rating;
		}


		public String getImage() {
			return Image;
		}


		public void setImage(String image) {
			Image = image;
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

		public int getNbBaths() {
			return nbBaths;
		}


		public void setNbBaths(int nbBaths) {
			this.nbBaths = nbBaths;
		}



		public int getNbLikes() {
			return nbLikes;
		}


		public void setNbLikes(int nbLikes) {
			this.nbLikes = nbLikes;
		}


		public int getNbDisLikes() {
			return nbDisLikes;
		}


		public void setNbDisLikes(int nbDisLikes) {
			this.nbDisLikes = nbDisLikes;
		}


		

		public Region getRegion() {
			return region;
		}


		public void setRegion(Region region) {
			this.region = region;
		}


		public Etat getEtat() {
			return etat;
		}


		public void setEtat(Etat etat) {
			this.etat = etat;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		

		public Ad(int idAd, int nbRooms, int nbGarage, int nbBaths, int nbLikes, int nbDisLikes, String description,
				String image, String location, int area, Region region, Integer rating, Integer rating2, Etat etat,
				Date adDate, int viewsNumber, Boolean success, int score, KindOfGood kindofgood, List<Comment> comments,
				 User user, Date startDate, Date endDate,
				RentPeriod rentperiod, float price, RentingType rentingtype) {
			super();
			IdAd = idAd;
			this.nbRooms = nbRooms;
			this.nbGarage = nbGarage;
			this.nbBaths = nbBaths;
			this.nbLikes = nbLikes;
			this.nbDisLikes = nbDisLikes;
			Description = description;
			Image = image;
			Location = location;
			Area = area;
			this.region = region;
			this.rating = rating;
			this.rating2 = rating2;
			this.etat = etat;
			AdDate = adDate;
			ViewsNumber = viewsNumber;
			Success = success;
			Score = score;
			this.kindofgood = kindofgood;
			this.comments = comments;
			this.user = user;
			StartDate = startDate;
			EndDate = endDate;
			this.rentperiod = rentperiod;
			Price = price;
			this.rentingtype = rentingtype;
		}

		public Ad(int idAd, int nbLikes, int nbDisLikes, String description, String image, String location, int area,
				Region region, Integer rating, Integer rating2, Etat etat, Date adDate, int viewsNumber,
				Boolean success, int score, KindOfGood kindofgood, List<Comment> comments, User user,
				Date startDate, Date endDate, RentPeriod rentperiod, float price, RentingType rentingtype,
				List<Notification> notifications, List<Appointment> appointment, List<User> like, List<User> disLike,
				int nbBaths, int nbRooms, int nbGarage, boolean terrace, boolean swimmingPool, boolean garden,
				boolean airConditioning, boolean heater, boolean sousSol, boolean ascenseur, boolean furnished,
				FavoriteAd favoriteAd, long idAcheteur, String garantie, Date dateAchat) {
			super();
			IdAd = idAd;
			this.nbLikes = nbLikes;
			this.nbDisLikes = nbDisLikes;
			Description = description;
			Image = image;
			Location = location;
			Area = area;
			this.region = region;
			this.rating = rating;
			this.rating2 = rating2;
			this.etat = etat;
			AdDate = adDate;
			ViewsNumber = viewsNumber;
			Success = success;
			Score = score;
			this.kindofgood = kindofgood;
			this.comments = comments;
			this.user = user;
			StartDate = startDate;
			EndDate = endDate;
			this.rentperiod = rentperiod;
			Price = price;
			this.rentingtype = rentingtype;
			this.notifications = notifications;
			this.like = like;
			this.disLike = disLike;
			this.nbBaths = nbBaths;
			this.nbRooms = nbRooms;
			this.nbGarage = nbGarage;
			Terrace = terrace;
			SwimmingPool = swimmingPool;
			Garden = garden;
			AirConditioning = airConditioning;
			this.heater = heater;
			SousSol = sousSol;
			this.ascenseur = ascenseur;
			Furnished = furnished;
			this.favoriteAd = favoriteAd;
			this.idAcheteur = idAcheteur;
			this.garantie = garantie;
			this.dateAchat = dateAchat;
		}


		
		public Ad(String description, String location, int area, KindOfGood kindofgood, User user, Date startDate,
				Date endDate, RentPeriod rentperiod, float price, RentingType rentingtype, int nbBaths, int nbRooms,
				int nbGarage, Boolean terrace, Boolean swimmingPool, Boolean garden, Boolean airConditioning,
				Boolean heater, Boolean sousSol, Boolean ascenseur, Boolean furnished) {
			super();
			Description = description;
			Location = location;
			Area = area;
			this.kindofgood = kindofgood;
			this.user = user;
			StartDate = startDate;
			EndDate = endDate;
			this.rentperiod = rentperiod;
			Price = price;
			this.rentingtype = rentingtype;
			this.nbBaths = nbBaths;
			this.nbRooms = nbRooms;
			this.nbGarage = nbGarage;
			Terrace = terrace;
			SwimmingPool = swimmingPool;
			Garden = garden;
			AirConditioning = airConditioning;
			this.heater = heater;
			SousSol = sousSol;
			this.ascenseur = ascenseur;
			Furnished = furnished;
		
		}

		
		public Set<Appointment> getAppointment() {
			return Appointment;
		}

		public void setAppointment(Set<Appointment> appointment) {
			Appointment = appointment;
		}

		@Override
		public String toString() {
			return "Ad [IdAd=" + IdAd + ", nbLikes=" + nbLikes + ", nbDisLikes=" + nbDisLikes + ", Description="
					+ Description + ", Image=" + Image + ", Location=" + Location + ", Area=" + Area
					+ ", region=" + region + ", rating=" + rating + ", rating2=" + rating2 + ", etat=" + etat
					+ ", AdDate=" + AdDate + ", ViewsNumber=" + ViewsNumber + ", Success=" + Success
					+ ", Score=" + Score + ", kindofgood=" + kindofgood + ", comments=" + comments + ", user="
					+ user  + ", StartDate=" + StartDate + ", EndDate=" + EndDate
					+ ", rentperiod=" + rentperiod + ", Price=" + Price + ", rentingtype=" + rentingtype
					+ ", notifications=" + notifications  + ", like=" + like
					+ ", disLike=" + disLike + ", nbBaths=" + nbBaths + ", nbRooms=" + nbRooms + ", nbGarage="
					+ nbGarage + ", Terrace=" + Terrace + ", SwimmingPool=" + SwimmingPool + ", Garden="
					+ Garden + ", AirConditioning=" + AirConditioning + ", heater=" + heater + ", SousSol="
					+ SousSol + ", ascenseur=" + ascenseur + ", Furnished=" + Furnished + ", favoriteAd="
					+ favoriteAd + ", price=" + price + ", idAcheteur=" + idAcheteur + ", garantie=" + garantie
					+ ", dateAchat=" + dateAchat + "]";
		}		



	


	

	
	
		
		
		
}
