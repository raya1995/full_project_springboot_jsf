package tn.esprit.spring.controller;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.FollowRepository;
import tn.esprit.spring.services.FollowService;


@Scope(value = "session")
@Controller(value = "FollowRestController")
@ELBeanName(value = "FollowRestController")

@RestController
public class FollowRestController {
	@Autowired
	FollowRepository followRepository;
	@Autowired
	FollowService followServiceImpl;
	@Autowired
	UserRestController userRestController;
	List<User> follows;
	List<User> following;
	private int nbrefollowuserconnecte;
	private int nbrefollow;
	private int nbrefollowing;
	private int nbreFollowinguserconnecte;
	List<User> followsInvite;
	List<User> followingInvite;
	
	
	
	
	private String firstname;
	private String lastname;

	private String email;
	private String picture;
	
	
	
	public List<User> getFollowsInvite() {
		Client ClientConnecte=userRestController.getInviteClient();
		follows=followServiceImpl.MyFollow(ClientConnecte.getId());
		System.out.println(follows);
		return follows;
	}

	public void setFollowsInvite(List<User> followsInvite) {
		this.followsInvite = followsInvite;
	}

	public List<User> getFollowingInvite() {
		Client ClientConnecte=userRestController.getInviteClient();
		followingInvite=followServiceImpl.following(ClientConnecte.getId());
		return followingInvite;
	}

	public void setFollowingInvite(List<User> followingInvite) {
		this.followingInvite = followingInvite;
	}
	public int getNbrefollow() {
		Client ClientConnecte=userRestController.getInviteClient();
		return followServiceImpl.nbreFollow(ClientConnecte.getId());
	}

	public void setNbrefollow(int nbrefollow) {
		this.nbrefollow = nbrefollow;
	}

	public int getNbrefollowing() {
		Client ClientConnecte=userRestController.getInviteClient();
		return followServiceImpl.nbreFollowing(ClientConnecte.getId());
	}

	public void setNbrefollowing(int nbrefollowing) {
		this.nbrefollowing = nbrefollowing;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	

	public void setFollows(List<User> follows) {
		this.follows = follows;
	}

	@PostMapping("/add-Follow/{follow_id}/{followed_id}") //
	@ResponseBody
	public void addUser(@PathVariable("follow_id") int follow_id, @PathVariable("followed_id") int followed_id) { //
		//followRepository.insertFollow(follow_id, followed_id);
		//

	}

	@GetMapping("/allFollow")
	@ResponseBody
	// http://localhost:8081/SpringMVC/servlet/retrieve-all-users
	public List<User> getUsers() {
		List<User> list = followRepository.MyFollow();
		return list;
	}

	public List<User> getFollowsuser(Long id) {
		return null;

	}

	public List<User> getFollows() {
		Client ClientConnecte=userRestController.getClientConnecte();
		follows=followServiceImpl.MyFollow(ClientConnecte.getId());
		System.out.println(follows);
		return follows;
	}

	public List<User> getFollowing() {
		Client ClientConnecte=userRestController.getClientConnecte();
		following=followServiceImpl.following(ClientConnecte.getId());
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public int getNbrefollowuserconnecte() {
		Client ClientConnecte=userRestController.getClientConnecte();
		return followServiceImpl.nbreFollow(ClientConnecte.getId());
	}

	public void setNbrefollowuserconnecte(int nbrefollowuserconnecte) {
		this.nbrefollowuserconnecte = nbrefollowuserconnecte;
	}

	

	public int getNbreFollowinguserconnecte() {
		Client ClientConnecte=userRestController.getClientConnecte();
		return followServiceImpl.nbreFollowing(ClientConnecte.getId());
	}

	public void setNbreFollowinguserconnecte(int nbreFollowinguserconnecte) {
		this.nbreFollowinguserconnecte = nbreFollowinguserconnecte;
	}
	public int getfollow(Long id) {
		return followServiceImpl.nbreFollow(id);
	}
	public int getfollowing(Long id) {
		return followServiceImpl.nbreFollowing(id);
	}
	public void Follow(Long followed_id) { //
		System.out.println(followed_id);
		
		Client ClientConnecte=userRestController.getClientConnecte();
		
		followServiceImpl.followService(ClientConnecte.getId(), followed_id);
		//

	}
	public String value(Long followed_id) {
		System.out.println(followed_id);
		Client ClientConnecte=userRestController.getClientConnecte();
		
		if(followServiceImpl.findFollow(ClientConnecte.getId(), followed_id)) {
			return "follow";
		}
		return "unfloow";
	}
	
	
	
	

}
