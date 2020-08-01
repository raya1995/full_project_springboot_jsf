package tn.esprit.spring.controller;

import java.util.List;

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

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.AppointmentFeedBack;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Uploads;
import tn.esprit.spring.repository.AppointmentFeedBackRepository;
import tn.esprit.spring.services.AppointmentFeedBackService;

@Scope(value = "session")
@Controller(value = "appointmentFeedBackController")
@ELBeanName(value = "appointmentFeedBackController")
@Join(path = "/feed", to = "/feedBack.jsf")public class AppointmentFeedBackController {
	@Autowired
	AppointmentFeedBackService AppointmentFeedBackService;
	@Autowired
	AppointmentFeedBackRepository AppointmentFeedBackRepository;
	private Long idAppFeedBack;
	private String descriptionFeedBack;

	private boolean likes;
	private Client idd;
	
	
	
	
	//http://localhost:8081/SpringMVC/servlet/retrieve-all-Feed

		public Long getIdAppFeedBack() {
		return idAppFeedBack;
	}

	public void setIdAppFeedBack(Long idAppFeedBack) {
		this.idAppFeedBack = idAppFeedBack;
	}

	public String getDescriptionFeedBack() {
		return descriptionFeedBack;
	}

	public void setDescriptionFeedBack(String descriptionFeedBack) {
		this.descriptionFeedBack = descriptionFeedBack;
	}

	public boolean isLikes() {
		return likes;
	}

	public void setLikes(boolean likes) {
		this.likes = likes;
	}

	public Client getIdd() {
		return idd;
	}

	public void setIdd(Client idd) {
		this.idd = idd;
	}

		@GetMapping("/retrieve-all-Feed")
		 @ResponseBody
		 public List<AppointmentFeedBack> getAppointmentFeedBack() {
		 List<AppointmentFeedBack> list = AppointmentFeedBackService.retrieveAllAppointmentFeedBack();
		 return list;
		} 
		
		// Ajouter User : http://localhost:8081/SpringMVC/servlet/add-appointmentFeedBack
			  @PostMapping("/add-appointmentFeedBack")
			  @ResponseBody
			  public AppointmentFeedBack addAppointment(@RequestBody AppointmentFeedBack a) {
			  AppointmentFeedBack app = AppointmentFeedBackService.addAppointmentFeedBack(a);
			 return app;
			  }
			  
			  //http://localhost:8081/SpringMVC/servlet/remove-feed/{user-id}
			  @DeleteMapping("/remove-feed/{appointment-id}")
			   @ResponseBody
			   public void removeAppointment(@PathVariable("appointment-id") String id) {
			   AppointmentFeedBackService.deleteAppointmentFeedBack(id);
			   }
			  // http://localhost:8081/SpringMVC/servlet/modify-feed
			   @PutMapping("/modify-feed")
			   @ResponseBody
			   public AppointmentFeedBack modifyFeed(@RequestBody AppointmentFeedBack feed) {
			   return AppointmentFeedBackService.updateAppointmentFeedBack(feed);
			   }
			   
			   public String addAppointmentFeedBack() {
					List<AppointmentFeedBack> app = (List<AppointmentFeedBack>) AppointmentFeedBackRepository.findAll();
					

					


					AppointmentFeedBackService.addAppointmentFeedBack(new AppointmentFeedBack(idAppFeedBack, descriptionFeedBack,likes,idd));
					
					
					return "/thankYou.xhtml?faces-redirect=true";
					
					

				}	

			

		
				public String feedBackPage() {
					 String navigateTo = "null";

					  return navigateTo = "/feedBack.xhtml?faces-redirect=true";		
				}
			

}
