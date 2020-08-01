package tn.esprit.spring.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.controller.UserRestController;
import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.ReclamtionRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	AppointmentRepository AppointmentRepository;
	@Autowired
	ClientRepository ClientRepository;
	@Autowired
	ReclamtionRepository ReclamationRepository;
	private static final Logger l = LogManager.getLogger(AppointmentServiceImpl.class);

	@Override
	public List<Appointment> retrieveAllAppointment() {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		for (Appointment appointment : app) {
			l.info("appointments +++++++++" + appointment);
		}

		return app;
	}

	@Override
	public Appointment addAppointment(Appointment a, Date date, int heure) {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();

		for (Appointment aa : app) {
			// Appointment aa=new Appointment();

			if ((aa.getHeure() != a.getHeure())) {

				a.setState("confirmed");
				l.info("appointment is1111111111" + a.getState());

			} else {
				a.setState("not confirmed you have an appointment at this hour");

				l.info("appointment is333333333" + a.getState());

			}

		}
		AppointmentRepository.save(a);

		return a;
	}

	@Override
	public void deleteAppointment(String id) {

		AppointmentRepository.deleteById(Long.parseLong(id));

	}

	@Override
	public Appointment retrieveAppointment(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ConfrmerAppointment(Date date) {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();

		for (Appointment a : app) {
			// Appointment aa=new Appointment();

			if (a.getDateAppointement().compareTo(date) > 0) {

				a.setState("confirmed");
				l.info("appointment is1111111111" + a.getState());
			} else if (a.getDateAppointement().compareTo(date) < 0) {
				a.setState("confirmed");
				l.info("appointment is22222222" + a.getState());

			} else {
				l.info("appointment is333333333" + a.getState());

			}
			AppointmentRepository.save(a);

		}

	}

	@Override
	public int countAppointment() {
		int max = 0;
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		for (Appointment appointment : app) {
			max++;

		}
		l.info(" you have " + max + "appointments");

		return max;

	}

	/*
	 * @Override public void RechercheConfirmedApp() {
	 * AppointmentRepository.retrieveAllAppointmentByState("confirmed");
	 * l.info("appointment is+++++++++++" +
	 * AppointmentRepository.retrieveAllAppointmentByState("confirmed"));
	 * 
	 * 
	 * }
	 */

	@Override
	public List<Appointment> rechercheAppointment() {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.retrieveAllAppointmentByState("confirmed");
		for (Appointment appointment : app) {

			l.info("appointment is+++++++++++" + appointment);
		}
		return app;

	}

	@Override
	public List<Appointment> AppointmentToday() {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		

		int max = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		l.info("********" + dateFormat.format(date));
		List<Appointment> lis = new ArrayList <Appointment>();



		for (Appointment a : app) {

			if ((a.getDateAppointement().getDay() == date.getDay())
					&& (a.getDateAppointement().getMonth() == date.getMonth())
					&& (a.getDateAppointement().getYear() == date.getYear())) {
			      lis.add(a);

				max++;


				l.info("********" + dateFormat.format(date) + a);
			}
		}				

		l.info("you have " + max + " appointments today++++++++++++");
		return lis;

	}

	
	@Override
	public void CancelAppointment() {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		List<Appointment> ap = (List<Appointment>) AppointmentRepository.retrieveAllAppointmentBypurchasing(false);

		// System.out.println("11111111111"+ap);}

		int idd;
		for (Appointment a : app) {
			if (a.isPurchased()) {
			
				idd = a.getAd().getIdAd();
				for (Appointment aa : ap) {
					if (aa.getAd().getIdAd() == idd) {

						aa.setState("appointment is canceled this house has already purchased");
						l.info("++++++++" + aa.getState() + "++++++++++++++++++++");
						AppointmentRepository.save(aa);

					}
				}

			}

		}

	}

	@Override
	public void ReclamerUser() {
		List<Reclamation> appp = (List<Reclamation>) ReclamationRepository.findAll();
		Client iddd;

		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		for (Appointment a : app) {
			if (a.getAttendance().equals("absent")) {
				iddd = a.getClient();

				Reclamation r = new Reclamation();
				r.setDescription("this user fixe an appointment but he didn't come");
				r.setUserId(iddd);
				r.setId(1);
				ReclamationRepository.save(r);

				l.info("*****1*11*1*1*1*1**" + r.getDescription());

			}

		}

	}

	@Override
	public void purchased() {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		for(Appointment a:app) {
			a.setPurchased(true);
			
		}
		

	}

	

	@Override
	public Long addOrUpdateEmploye(Appointment appointment) {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		List<Appointment> ap = (List<Appointment>) AppointmentRepository.retrieveAllAppointmentBypurchasing(false);
		List<Client> cc = (List<Client>) ClientRepository.findAll();
		List<Reclamation> appp = (List<Reclamation>) ReclamationRepository.findAll();

		
		 




		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Long Id=0L;
		int max=0;
		
			
		Client id;
		id=UserRestController.getClientConnecte();
		 System.out.println("\\\\\\\\\\"+id);

		Ad ad=new Ad(1);
		
		Appointment aaa=new Appointment();

			if(aaa.getHeure() != appointment.getHeure() && ((aaa.getDateAppointement()!=appointment.getDateAppointement()))) {
				appointment.setState("confirmed");
				 appointment.setClient(id);
				 appointment.setAd(ad);
}
			else {
				appointment.setState("not confirmed");
				 appointment.setClient(id);
				 appointment.setAd(ad);


			
		}
		for(Appointment apa:app) {
			if(appointment.isPurchased()) {
				appointment.setDatePurchasing(date);
				
			}
		}

					
		

			AppointmentRepository.save(appointment);
			

			// System.out.println("11111111111"+ap);}

			int idd;
			Client iddd;
			for (Appointment a : app) {
				
				if (a.isPurchased()) {
					
					idd = a.getAd().getIdAd();
					for (Appointment aa : ap) {
						if(aa.getAd().getIdAd()==idd) {
						 

							aa.setState("appointment is canceled this house has already purchased");
							AppointmentRepository.save(aa);

						}}
					

				}}


		return appointment.getIdAppointement();
		
		
	}

	@Override
	public Long addOrUpdateEmploye2(Appointment appointment) {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		List<Appointment> ap = (List<Appointment>) AppointmentRepository.retrieveAllAppointmentBypurchasing(false);
		List<Client> cc = (List<Client>) ClientRepository.findAll();
		List<Reclamation> appp = (List<Reclamation>) ReclamationRepository.findAll();

		
		 




		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Long Id=0L;
		int max=0;
		
			
		
		for(Appointment apa:app) {
			if(appointment.isPurchased()) {
				appointment.setDatePurchasing(date);
				
			}
		}

					
		

			AppointmentRepository.save(appointment);
			

			// System.out.println("11111111111"+ap);}

			int idd;
			Client iddd;
			for (Appointment a : app) {
				
				if (a.isPurchased()) {
					
					idd = a.getAd().getIdAd();
					for (Appointment aa : ap) {
						if(aa.getAd().getIdAd()==idd) {
						 

							aa.setState("appointment is canceled this house has already purchased");
							AppointmentRepository.save(aa);

						}}
					

				}}


		return appointment.getIdAppointement();
		
		
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointment=(List<Appointment>)AppointmentRepository.MyAppointment(UserRestController.getClientConnecte());
		return appointment;
	}

	@Override
	public String show() {
		 String navigateTo = "null";

		  return navigateTo = "/pages/appointment/showAppointment.xhtml?faces-redirect=true";		
	}
	public void mettreAjourPurchByAppoint(boolean purchased, Long Id) {
		Appointment employe = AppointmentRepository.findById(Id).get();
		employe.setPurchased(true);
		AppointmentRepository.save(employe);

	}

	@Override
	public void absent(Long id) {
		int x = 0;
		List<Client> clients =(List<Client>)ClientRepository.findAll();
		List<Appointment> appoinlents=(List<Appointment>)AppointmentRepository.findAll();
		for(Appointment ap:appoinlents) {
			
			if(ap.getClient().getId().equals(id)) {
				
				if(ap.getAttendance().equals("absent")) {
					x=x+1;
					Reclamation rec=new Reclamation();
					rec.setDescription("this user fixed an appointment but he is absent");
					rec.setUserId(ap.getClient());
					rec.setId(5);

					ReclamationRepository.save(rec);
					
					
				}
			}
		}
		if(x>=2) {
			for(Client cl:clients) {
				if(cl.getId().equals(id)) {
					cl.setBlock(true);
					cl.setDescriptionBlock("this user was blocked because of his absence at the appointment");
					ClientRepository.save(cl);
					
				}
				
			}
			
		}
	}

	@Override
	public void cleanAppointments() {
		List<Appointment> appointment=(List<Appointment>)AppointmentRepository.findAll();
		for(Appointment app:appointment) {
			if(app.getState().equals("not confirmed")) {
				String id;
				id=app.getIdAppointement().toString();
				AppointmentRepository.deleteById(Long.parseLong(id));

				
			}
		}

		
	}
	public String username() {
		Client id;
		id=UserRestController.getClientConnecte();
		return id.getFirstName();
		
	}

	@Override
	public List<Appointment> getAllAppointmentts() {
		List<Appointment> appointment=(List<Appointment>)AppointmentRepository.ReceivedAppointment(UserRestController.getClientConnecte());
		return appointment;
	}
	

	public String notification() {
		List<Appointment> app=(List<Appointment>)AppointmentRepository.findAll();
		String a=null;
		for(Appointment ap:app) {
			if(ap.getJustification()!=null) {
				a="+++this user"+ap.getClient().getFirstName()+"add a justification"+ap.getJustification()+"for the appointment fixed at"+ap.getDateAppointement();
				
			}
		}
	 
	return a;
	 
 }

	@Override
	public void confirmAppointment(Appointment appointment) {
		Client id;
		id=UserRestController.getClientConnecte();
		 System.out.println("\\\\\\\\\\"+id);

		Ad ad=new Ad(1);
		
		Appointment aaa=new Appointment();

			if(aaa.getHeure() != appointment.getHeure() && ((aaa.getDateAppointement()!=appointment.getDateAppointement()))) {
				appointment.setState("confirmed");
				 appointment.setClient(id);
				 appointment.setAd(ad);
}
			else {
				appointment.setState("not confirmed");
				 appointment.setClient(id);
				 appointment.setAd(ad);


			
		}
		

					
		

			AppointmentRepository.save(appointment);
					
	}

	

	

	}
