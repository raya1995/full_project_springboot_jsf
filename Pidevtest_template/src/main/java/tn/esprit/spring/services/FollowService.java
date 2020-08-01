package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.User;



public interface FollowService {
	public List<User> MyFollow(Long id);
	public List<User>following(Long id);
	public int nbreFollow(Long id);
	public int nbreFollowing(Long id);
	public void followService( long follow_id ,long followed_id);
	public boolean findFollow(long follow_id , long followed_id);

}
