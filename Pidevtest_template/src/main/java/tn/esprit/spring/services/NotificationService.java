package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Notification;


public interface NotificationService {

	public Notification addNotification(Notification u);

	public List<Notification> retrieveAllNotifications();

	void deleteNotification(String id);

	int nbrNotif();
	

}
