package tn.esprit.spring.services;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Notification;
import tn.esprit.spring.repository.NotificationRepository;



@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository NotificationRepository;

	private static final Logger l = LogManager.getLogger(NotificationServiceImpl.class);

	@Override
	public Notification addNotification(Notification u) {
		return NotificationRepository.save(u);
	}

	@Override
	public List<Notification> retrieveAllNotifications() {
		return (List<Notification>) NotificationRepository.findAll();

	}

	@Override
	public void deleteNotification(String id) {
		NotificationRepository.deleteById(Long.parseLong(id));		
	}

	@Override
	public int nbrNotif() {
		int like = 0;
		List<Notification> com = (List<Notification>) NotificationRepository.findAll();
		for (Notification aa : com) {

			like += aa.getAction();

		}

		return like;

	}

}
