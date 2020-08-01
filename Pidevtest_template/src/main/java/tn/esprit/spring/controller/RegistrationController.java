package tn.esprit.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.CryptWithMD5;
import tn.esprit.spring.services.NotificationServeur;



@Controller(value = "RegistrationController")
@ELBeanName(value = "RegistrationController")
@Join(path = "/register", to = "/register.jsf")
@RestController
public class RegistrationController {
	@Autowired
	private NotificationServeur notificationServeur;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CryptWithMD5 cryptWithMD5;
	private String FirstName;

	private String Lastname;

	private String date_naissance;
	private String Email;
	private static Client client;

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	

	@RequestMapping("/signup")
	public String signup() {
		return "please sign up for our service";
	}

	@RequestMapping("/registration/{nom}/{prenom}/{email}")
	@ResponseBody
	public String signupsucces(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom,
			@PathVariable("email") String email) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date d;
		int existe = 0;
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String end = "tnak you for your registration";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			end = "invalide email";
		} else {
			try {
				d = dateFormat.parse("01/02/2015");
				Client u = new Client(null, "Malek", "Hamida", d, "oussama.boubaker@esprit.tn", null, null,
						"oussama.jpg", "nothing", 0, false);

				List<User> users = (List<User>) userRepository.findAll();
				for (User user : users) {
					if (user.getEmail().equals(email)) {
						existe = existe + 1;
					}
				}
				if (existe == 0) {

					u.setFirstName(nom);
					u.setLastname(prenom);
					u.setEmail(email);
					userRepository.save(u);
					notificationServeur.sendNotification(u);
					String password = cryptWithMD5.cryptWithMD5(u.getPassword());

					u.setPassword(password);
					userRepository.save(u);
				} else {
					end = "you have already an accompte";
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return (end);
	}
	

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		RegistrationController.client = client;
	}

	public String signupsucces2(String nom, String prenom, String email, String date)  {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		
		Date d;
		
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			return "invalid date";
		}
		int existe = 0;
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String end = "tnak you for your registration";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
	
		if (!matcher.matches()) {
			end = "invalide email";
		} else {

			Client u = new Client(null, "Malek", "Hamida", d, "oussama.boubaker@esprit.tn", null, null,
					"oussama.jpg", "nothing", 0, false);

			List<User> users = (List<User>) userRepository.findAll();
			for (User user : users) {
				if (user.getEmail().equals(email)) {
					existe = existe + 1;
				}
			}
			if (existe == 0) {
				
				u.setFirstName(nom);
				u.setLastname(prenom);
				u.setEmail(email);
				userRepository.save(u);
				notificationServeur.sendNotification(u);
				String password = cryptWithMD5.cryptWithMD5(u.getPassword());

				
			client = u;
			} else {
				end = "you have already an accompte";
			}

		}

		return (end);
	}

	public String addClient() throws ParseException {
		String end = signupsucces2(FirstName, Lastname, Email, date_naissance);
		FacesMessage facesMessage = new FacesMessage(end);
		FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		if(end.equals("tnak you for your registration")) {
			return "/uploadview.xhtml?faces-redirect=true";
		}else {
			return null;
		}

	}
}
