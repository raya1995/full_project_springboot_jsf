package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.AppointmentFeedBack;


public interface AppointmentFeedBackService {
	List<AppointmentFeedBack> retrieveAllAppointmentFeedBack();
	AppointmentFeedBack addAppointmentFeedBack(AppointmentFeedBack af);

	void deleteAppointmentFeedBack(String id);
	AppointmentFeedBack updateAppointmentFeedBack(AppointmentFeedBack af);
	AppointmentFeedBack retrieveAppointmentFeedBack(String id);
	int countLikes();

}
