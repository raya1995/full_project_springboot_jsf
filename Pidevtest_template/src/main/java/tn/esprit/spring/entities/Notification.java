package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer notificationId;
	@Column
	private String message;
	@Column
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	private int action;
	@ManyToOne
	private User user;
	@ManyToOne
	private Ad ad;

	

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
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

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	



	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", message=" + message + ", createdAt=" + createdAt
				+ ", action=" + action + ", user=" + user + ", ad=" + ad + "]";
	}

	public Notification( String message, Date createdAt, int action, User user) {
		super();
		this.message = message;
		this.createdAt = createdAt;
		this.action = action;
		this.user = user;
	}

	
	
	
	
	
	public Notification(Integer notificationId, String message, Date createdAt, User user) {
		super();
		this.notificationId = notificationId;
		this.message = message;
		this.createdAt = createdAt;
		this.user = user;
	}

	public Notification(String message, Date createdAt) {
		super();
		this.message = message;
		this.createdAt = createdAt;

	}

	public Notification(String message, Date createdAt, User user) {
		super();
		this.message = message;
		this.createdAt = createdAt;
		this.user = user;
	}

	
	

	public Notification(Integer notificationId, String message, Date createdAt, int action, User user, Ad ad) {
		super();
		this.notificationId = notificationId;
		this.message = message;
		this.createdAt = createdAt;
		this.action = action;
		this.user = user;
		this.ad = ad;
	}

	public Notification(String message, Date createdAt,User user, Ad ad , int action) {
		super();
		this.message = message;
		this.createdAt = createdAt;
		this.user = user;
		this.ad = ad;
		this.action = action;
	}

	public Notification() {
		super();
	}


}