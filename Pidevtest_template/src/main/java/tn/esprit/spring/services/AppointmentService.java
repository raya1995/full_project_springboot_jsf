package tn.esprit.spring.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Appointment;


public interface AppointmentService {
	List<Appointment> retrieveAllAppointment();
	Appointment addAppointment(Appointment a,Date date,int heure);

	void deleteAppointment(String id);
	Appointment retrieveAppointment(String id);
	void ConfrmerAppointment(Date date);
	int countAppointment();
	//void RechercheConfirmedApp();
	public List<Appointment> rechercheAppointment();
	public List<Appointment> AppointmentToday();
	public void CancelAppointment();
	public void ReclamerUser();
	public void purchased();
	public Long addOrUpdateEmploye(Appointment appointment);
	public List<Appointment> getAllAppointments();
	public String show();
	public void absent(Long id);
	public void cleanAppointments();
	public String username();
	public List<Appointment> getAllAppointmentts();
	public String notification();
	public void confirmAppointment(Appointment appointment);
	public Long addOrUpdateEmploye2(Appointment appointment);



}
