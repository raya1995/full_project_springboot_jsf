package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Notification;
import tn.esprit.spring.services.IAdService;
import tn.esprit.spring.services.NotificationServiceImpl;
import tn.esprit.spring.services.UserService;

@Scope(value = "session")
@Controller(value = "notificationController")
@ELBeanName(value = "notificationController")
public class NotificationController {
	@Autowired
	IAdService AdService;
	@Autowired
	UserService UserService;
	@Autowired
	UserRestController UserController;
	@Autowired
	NotificationServiceImpl notificationservice;

	private Integer notificationId;
	private List<Notification> notifications;
	private String message;

	private Date createdAt;
	private boolean isRead;

	public IAdService getAdService() {
		return AdService;
	}

	public void setAdService(IAdService adService) {
		AdService = adService;
	}

	public UserService getUserService() {
		return UserService;
	}

	public void setUserService(UserService userService) {
		UserService = userService;
	}

	public UserRestController getUserController() {
		return UserController;
	}

	public void setUserController(UserRestController userController) {
		UserController = userController;
	}

	public NotificationServiceImpl getNotificationservice() {
		return notificationservice;
	}

	public void setNotificationservice(NotificationServiceImpl notificationservice) {
		this.notificationservice = notificationservice;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	

	public List<Notification> getNotifications() {

		List<Notification> notifications = notificationservice.retrieveAllNotifications();
		return notifications;

	}

	public void setAnnonces(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Date currentDate() {
		Date currdate = new Date();
		return currdate;
	}

	public List<Notification> getNotificationsSameDay() {
		Date currdate = new Date();

		notifications = notificationservice.retrieveAllNotifications();

		for (int i = 0, j = notifications.size() - 1; i < j; i++) {

			notifications.add(i, notifications.remove(j));
		}

		return notifications;
	}

	public Date getDate() {
		Date currdate = new Date();
		return currdate;

	}

}