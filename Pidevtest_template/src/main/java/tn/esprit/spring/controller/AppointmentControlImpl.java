package tn.esprit.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.AppointmentFeedBack;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Uploads;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.AppointmentService;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.AppointmentFeedBackRepository;

import tn.esprit.spring.repository.UploadsRepository;


@Scope(value = "session")
@Controller(value = "appointmentController")
@ELBeanName(value = "appointmentController")
@Join(path = "/add", to = "/oumayma/addAppointment.jsf")
public class AppointmentControlImpl {
	@Autowired
	AppointmentService AppointmentService;
	@Autowired
	AppointmentRepository AppointmentRepository;
	@Autowired
	ClientRepository clientRepository;
	
	
	@Autowired
	UploadsRepository UploadsRepository;

	private Date dateAppointement;
	private boolean visibility;
	private String state;
	private int heure;
	private String attendance;
	private boolean purchased;
	private String justification;
	private Date datePurchasing;
	private Appointment appointment;
	private Long idAppointement;
	private List<Appointment> appointments;
	private List<Appointment> appointmentts;

	private List<Appointment> appointmentConfirmed;
	private List<Appointment> appointmentToDay;
	private Client client;
	private Ad ad;
	private String files;
	private List<Appointment> filteredAppointmentList;
	
	
	
	
	
	// Ajouter User : http://localhost:8081/SpringMVC/servlet/add-appointment
		@PostMapping("/add-appointment")
		@ResponseBody
		public Appointment addAppointment(@RequestBody Appointment a, Date date, int heure) {
			
			Appointment app = AppointmentService.addAppointment(a, date, heure);
			return app;
		}

		// http://localhost:8081/remove-user/{user-id}
		@DeleteMapping("/remove-user/{appointment-id}")
		@ResponseBody
		public void removeAppointment(@PathVariable("appointment-id") String id) {
			AppointmentService.deleteAppointment(id);
			;
		}

		// http://localhost:8081/count
		@GetMapping("/count")
		@ResponseBody
		public int countAppointment() {
			return AppointmentService.countAppointment();
		}
		// http://localhost:8081/SpringMVC/servlet/today
			@GetMapping("/today")
			@ResponseBody
		public List<Appointment> AppointmentToday() {
			List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
			

			int max = 0;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			List<Appointment> lis = new ArrayList <Appointment>();



			for (Appointment a : app) {

				if ((a.getDateAppointement().getDay() == date.getDay())
						&& (a.getDateAppointement().getMonth() == date.getMonth())
						&& (a.getDateAppointement().getYear() == date.getYear())) {
				      lis.add(a);

					max++;


				}
			}				

			return lis;

		}
			
			// http://localhost:8081/retrieve-myApp/{App-id}
			 @GetMapping("/retrieve-myApp/{App-id}")
			 @ResponseBody
			 public List<Appointment> retrieveApp(@PathVariable("App-id") String AppId) {
			 return AppointmentService.getAllAppointments();
			 } 
	

	public void updateAppointment() {
		
		
		
		
		AppointmentService.addOrUpdateEmploye(

			
		new Appointment( idAppointement, dateAppointement, visibility,  state, heure,
				attendance,  purchased, justification, datePurchasing,client,ad,files));
			
		List<Appointment> appoinlents=(List<Appointment>)AppointmentRepository.findAll();
		for(Appointment ap:appoinlents) {
		AppointmentService.absent(ap.getClient().getId());
		AppointmentService.confirmAppointment(ap);}

	
	


			

}
public void updateAppointment2() {
		
		
		
		
		AppointmentService.addOrUpdateEmploye2(

			
		new Appointment( idAppointement, dateAppointement, visibility,  state, heure,
				attendance,  purchased, justification, datePurchasing,client,ad,files));
			
		List<Appointment> appoinlents=(List<Appointment>)AppointmentRepository.findAll();
		for(Appointment ap:appoinlents) {
		AppointmentService.absent(ap.getClient().getId());
		}

	
	


			

}
	
	@PostConstruct
	  public void postConstruct() {
		appointments = AppointmentService.getAllAppointments();
	  }
	

	
	






	public List<Appointment> getFilteredAppointmentList() {
		return filteredAppointmentList;
	}

	public void setFilteredAppointmentList(List<Appointment> filteredAppointmentList) {
		this.filteredAppointmentList = filteredAppointmentList;
	}

	public String getFiles() {
		List<Appointment>app=(List<Appointment>) AppointmentRepository.findAll();
		String f = "hello";
		Long id;
		for(Appointment a:app) {
			id=a.getIdAppointement();
			f=a.getFiles();
		
		System.out.println(a.getFiles());}

		return f;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public List<Appointment> getAppointmentToDay() {
		appointmentToDay=AppointmentService.AppointmentToday();
		return appointmentToDay;
	}

	public void setAppointmentToDay(List<Appointment> appointmentToDay) {
		this.appointmentToDay = appointmentToDay;
	}

	public List<Appointment> getAppointmentConfirmed() {
		appointmentConfirmed = (List<Appointment>) AppointmentRepository.retrieveAllAppointmentByState("confirmed");

		return appointmentConfirmed;
	}

	public void setAppointmentConfirmed(List<Appointment> appointmentConfirmed) {
		this.appointmentConfirmed = appointmentConfirmed;
	}

	public Date getDateAppointement() {
		return dateAppointement;
	}

	public void setDateAppointement(Date dateAppointement) {
		this.dateAppointement = dateAppointement;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Date getDatePurchasing() {
		return datePurchasing;
	}

	public void setDatePurchasing(Date datePurchasing) {
		this.datePurchasing = datePurchasing;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Long getIdAppointement() {
		return idAppointement;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Appointment> getAppointments() {
		appointments = AppointmentService.getAllAppointments();
		return appointments;
	}

	public void setIdAppointement(Long idAppointement) {
		this.idAppointement = idAppointement;
	}

	public String addAppointment() {
		AppointmentService.addOrUpdateEmploye(new Appointment(dateAppointement, heure));
		
		 return "/pages/uploadview2.xhtml?faces-redirect=true";
		

	}

	public List<Appointment> getApointments() {
		appointments = AppointmentService.getAllAppointments();
		return appointments;
	}
	
	

	public List<Appointment> getAppointmentts() {
		appointmentts = AppointmentService.getAllAppointmentts();

		return appointmentts;
	}

	public void setAppointmentts(List<Appointment> appointmentts) {
		this.appointmentts = appointmentts;
	}

	public String show() {
		//String navigateTo = "null";

		return "/oumayma/showAppointment.xhtml?faces-redirect=true";

	}
	public String show2() {
		

		return "/oumayma/showAppointment2.xhtml?faces-redirect=true";

	}

	public void displayAppointment(Appointment app) {
		this.setVisibility(app.isVisibility());
		this.setState(app.getState());
		this.setPurchased(app.isPurchased());
		this.setAttendance(app.getAttendance());
		this.setHeure(app.getHeure());
		this.setDateAppointement(app.getDateAppointement());
		this.setIdAppointement(app.getIdAppointement());
		this.setJustification(app.getJustification());
		this.setClient(app.getClient());
		this.setAd(app.getAd());
		this.setFiles(app.getFiles());
	}

	

	
		public void purchased() {
			List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
			for(Appointment a:app) {
				a.setPurchased(true);
				AppointmentRepository.save(a);
			}
			

		}

		public String addDocument() {
			 String navigateTo = "null";

			  return navigateTo = "/pages/uploadview.xhtml?faces-redirect=true";		
		}
		public String chat() {
			 String navigateTo = "null";

			  return navigateTo = "/static/index.xhtml?faces-redirect=true";		
		}
		public String virtualVisit() {
			 String navigateTo = "null";

			  return navigateTo = "/oumayma/virtualVisit.xhtml?faces-redirect=true";		
		}
		public String filePage() {
			 String navigateTo = "null";

			  return navigateTo = "/file.xhtml?faces-redirect=true";		
		}
		
		public String likes() {
			 String navigateTo = "null";

			  return navigateTo = "/oumayma/likes.xhtml?faces-redirect=true";		
		}
		public String add() {
			 String navigateTo = "null";

			  return navigateTo = "/oumayma/addAppointment.xhtml?faces-redirect=true";		
		}
		public String back() {
			 String navigateTo = "null";

			  return navigateTo = "/oumayma/showAppointment.xhtml?faces-redirect=true";		
		}
		
		public void cleanApp() {
			AppointmentService.cleanAppointments();	
			}
		
		
		
		 public ArrayList<String> notificationJustification() {
				List<Appointment> app = (List<Appointment>) AppointmentService.getAllAppointmentts();
				String a=null;
				ArrayList<String> array = new ArrayList<String>() ;
				for(Appointment ap:app) {
					if(ap.getJustification()!=null) {
						a="this user "+ ap.getClient().getFirstName() +" add a justification "+"<<"+ ap.getJustification()+">>"+" for the appointment fixed at "+ap.getDateAppointement();
						array.add(a);
					}
				}
				System.out.println("+++++"+a);

			 
			return array;
			 
		 }
		 
		 public ArrayList<String> notificationConfirmation() {
				List<Appointment> app = (List<Appointment>) AppointmentService.getAllAppointments();
				String a=null;
				ArrayList<String> array = new ArrayList<String>() ;
				for(Appointment ap:app) {
					if(ap.getState().equals("confirmed")) {
						a="the appointment fixed at the date "+ ap.getDateAppointement() +" is confirmed ";
						array.add(a);
					}
				}
				System.out.println("+++++"+a);

			 
			return array;
			 
		 }
		 
		 public ArrayList<String> notificationAnnulation() {
				List<Appointment> app = (List<Appointment>) AppointmentService.getAllAppointments();
				String a=null;
				ArrayList<String> array = new ArrayList<String>() ;
				for(Appointment ap:app) {
					if(ap.getState().equals("appointment is canceled this house has already purchased")) {
						a="the appointment fixed at the date "+ ap.getDateAppointement() +" is canceled this house has already purchased ";
						array.add(a);
					}
				}
				System.out.println("+++++"+a);

			 
			return array;
			 
		 }
		 
		 public String userConnectedName() {
				String firstName;
				String lastName;
				String Name;
				firstName=UserRestController.getClientConnecte().getFirstName();
				lastName=UserRestController.getClientConnecte().getLastname();
				Name=firstName+""+lastName;

				

			 
			return Name;
			 
		 }
		


}
		
		
	
