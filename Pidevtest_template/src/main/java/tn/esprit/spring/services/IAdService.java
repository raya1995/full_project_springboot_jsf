package tn.esprit.spring.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.KindOfGood;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.User;

public interface IAdService {
	Ad addAd(Ad ad);
	public Comment addCommentaire(Comment u);
	public Comment addComment(Comment comment,Long idClient,Integer idPub,Boolean isBlocked);
	List<Ad> retrieveAllAds();
	public void deleteAd(int IdAd);
	Ad updateAd(Ad ad); 
	Ad getAdById(int adId);
	public List<String> getAllCommentsByAd(int AdId);
	public void deleteComment(int idComment);
	Comment UpdateComment(Comment comment);
	void AssignCommentToanAd(int CommentId, int AdId);
	public int countComments();
	public double  countAds();
	public List<Ad> getAdWithBestLikesOnCommentsJPQL();
	public int nbrLike();
	public boolean succes();
	public int maxnblike();
	public double AVG_nbcomment();
	public boolean BlockCommentsWithInsultingWords();
	public void BlockCommentsWithInsultingWords3(int id);
	public void ScoreIncrement();
	public int AdsForToday();
	public double AVG_Ads_Year();
	public List<String> getAdsFromTheSameUserJPQL();
	public List<String> getAllCommentsBlockedJPQL();
	public int countCommentsJPQL(int IdAd);
	public int addOrUpdateAd(Ad ad);
	public int getNumberView(int idad);
	public boolean increment(int idad);
	public boolean BlockCommentsWithInsultingWords2(int id);
	public void ReclamerUser();
	public void BlockUserByBadComments(Long id);
	List<Comment> retrieveAllComments();
	public List<Ad> filter();
	public Comment getCommentById(int comId);
	public List<Comment> DescriptionComments(int idc);
	public String Image();
	public boolean incrementdislike(int id);
	public boolean incrementlike(int id);
	public int addOrUpdateComment(Comment comment);
	public int nbrDislikeAd();
	public int nbrLikeAd();
	List<Ad> MyAds(User user);
	List<Ad> SelectedAd();
	public int addAd1(Ad ad);
	public int Countads();
	public List<Ad> retrieveAllVillaJPQL(KindOfGood kindofgood);
	public List<Ad> retrieveAllAppartementJPQL(KindOfGood kindofgood);
	public List<Ad> retrieveAllStudioJPQL(KindOfGood kindofgood);
	public List<Ad> retrieveAllWorkshopJPQL(KindOfGood kindofgood);
	
	
	/////oussamaaa
	public List<Ad> afficherAnnonceFollow(Long Id);
	public int Myannoncenombre(Long Id);
	public List<Ad> annonceuser(Long Id);
	//loua
		Ad retrieveAdById(String id);
	
	
	}
