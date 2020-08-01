package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IAdService;

@RestController
public class RestControlAd {
	@Autowired
	IAdService iadService;


	// localhost:8081/SpringMVC/servlet/retrieve-all-ads
	//GET
	@GetMapping("/retrieve-all-ads") 
	@ResponseBody 
	public List<Ad> getAds() { 
		List<Ad> list = iadService.retrieveAllAds(); 
		return list;  } 

	// localhost:8081/SpringMVC/servlet/getAdById-ad/{ad-id} 
	//GET
	@GetMapping("/getAdById-ad/{ad-id}") 
	@ResponseBody  
	public Ad getAdById
	(@PathVariable("ad-id") int adId) {
		return iadService.getAdById(adId);} 

	// Ajouter AD : 
	// localhost:8081/SpringMVC/servlet/add-ad
	//POST
	/*{
        "kindofgood": "Villa",
        "comments": [],
        "multimedias": [],
        "rentperiod": "DAY",
        "rentingtype": "TEMPORARY_RENTING",
        "endDate": "2020-03-31",
        "startDate": "2020-03-25",
        "location": "Marsa",
        "description": "Dari",
        "viewsNumber": 2000,
        "success": true,
        "score": 200,
        "adDate": "2020-03-25",
        "idAd": 1,
        "area": 250
    },*/
	/* {
        "kindofgood": "Apartment",
        "comments": [],
        "multimedias": [],
        "user": {
            "id": 1,
            "dateNaissance": "1995-01-25",
            "password": "123",
            "lastname": "hadhri",
            "firstName": "raya",
            "email": "raya.hadhri@esprit.tn"
        },
        "rentperiod": "DAY",
        "rentingtype": "TEMPORARY_RENTING",
        "startDate": "2020-04-21",
        "endDate": "2020-04-11",
        "location": "bardo",
        "description": "house love",
        "viewsNumber": 57,
        "success": false,
        "score": 100,
        "idAd": 47,
        "area": 100,
        "adDate": "2020-04-11"
    }*/
	@PostMapping("/add-ad") 
	@ResponseBody 
	public Ad addAd(@RequestBody Ad ad) { 
		iadService.addAd(ad); 
		return ad; }

	// /localhost:8081/SpringMVC/servlet/deleteAd/{id_ad} 
	//DELETE
	@DeleteMapping("/deleteAd/{id_ad}") 
	@ResponseBody 
	void deleteAd(@PathVariable("id_ad") int IdAd){ 
		iadService.deleteAd(IdAd);}  

	//////////////////////////////////////////////////////////COMMENT/////////////////////////////////////////////////////////////////////////////////////
	// Ajouter Comment : 
	// localhost:8081/SpringMVC/servlet/add-comment
	//POST

	@PostMapping("/add-comment/{idClient}/{idPub}/{blocked}") 
	@ResponseBody 
	public Comment addComment(@RequestBody Comment comment,@PathVariable("idClient") Long idClient,@PathVariable("idPub") Integer idPub,@PathVariable("blocked")Boolean isBlocked) { 
		iadService.addComment(comment,idClient,idPub,isBlocked); 

		return comment; }

	// localhost:8081/SpringMVC/servlet/modify-ad  
	//PUT
	@PutMapping("/modify-ad") 
	@ResponseBody 
	public Ad modifyAd(@RequestBody Ad ad) 
	{ 	return iadService.updateAd(ad); }

	// /localhost:8081/deleteComment/{IdComment} 
	//DELETE
	@DeleteMapping("/deleteComment/{idComment}") 
	@ResponseBody 
	public void deleteComment(@PathVariable("idComment") int idComment) { 
		iadService.deleteComment(idComment);}  

	// localhost:8081/SpringMVC/servlet/modify-comment  
	//PUT
	@PutMapping("/modify-comment") @ResponseBody 
	public Comment modifyComment(@RequestBody Comment comment) { 
		return iadService.UpdateComment(comment); }


	// http://localhost:8081/SpringMVC/servlet/AssignCommentToanAd/1/1
	@PutMapping(value = "/AssignCommentToanAd/{idcomment}/{idad}") 
	public void AssignCommentToanAd(@PathVariable("idcomment")int CommentId, @PathVariable("idad")int AdId) {
		iadService.AssignCommentToanAd(CommentId, AdId); 
	}



	// http://localhost:8081/SpringMVC/servlet/getAllCommentsByAd/1
	@GetMapping(value = "getAllCommentsByAd/{idad}")
	@ResponseBody
	public List<String> getAllCommentsByAd(@PathVariable("idad") int AdId) {
		return iadService.getAllCommentsByAd(AdId);
	}

	// http://localhost:8081/getComments
	@GetMapping(value = "/getComments")
	@ResponseBody
	public List<Comment> getComments() {
		List<Comment> list = iadService.retrieveAllComments(); 
		return list;  } 	


	// http://localhost:8081/SpringMVC/servlet/countComments
	@GetMapping(value = "/countComments")
	@ResponseBody
	public int countComments() {
		return iadService.countComments();
	}


	// http://localhost:8081/SpringMVC/servlet/nbrLike
	@GetMapping(value = "/nbrLike")
	@ResponseBody
	public int nbrLike()  {
		return iadService.nbrLike();
	}


	// http://localhost:8081/SpringMVC/servlet/getAdWithBestLikesOnCommentsJPQL/100
	@GetMapping(value = "/getAdWithBestLikesOnCommentsJPQL")
	@ResponseBody
	public List<Ad>getAdWithBestLikesOnCommentsJPQL()  {
		return iadService.getAdWithBestLikesOnCommentsJPQL();
	}


	// http://localhost:8081/SpringMVC/servlet/maxnblike
	@GetMapping(value = "/maxnblike")
	@ResponseBody
	public int maxnblike()  {
		return iadService.maxnblike();
	}



	// http://localhost:8081/SpringMVC/servlet/succes
	@PutMapping(value = "/succes")
	@ResponseBody
	public boolean succes() {
		return iadService.succes();
	}

	// http://localhost:8081/SpringMVC/servlet/AVG_nbcomment
	@GetMapping(value = "/AVG_nbcomment")
	@ResponseBody
	public double AVG_nbcomment() {
		return iadService.AVG_nbcomment();
	}

	// http://localhost:8081/SpringMVC/servlet/BlockCommentsWithInsultingWords
	@PutMapping(value = "/BlockCommentsWithInsultingWords")
	@ResponseBody
	public boolean BlockCommentsWithInsultingWords()  {
		return iadService.BlockCommentsWithInsultingWords();
	}

	// http://localhost:8081/SpringMVC/servlet/BlockCommentsWithInsultingWords2
	@PutMapping(value = "/BlockCommentsWithInsultingWords2/{id}")
	@ResponseBody
	public boolean BlockCommentsWithInsultingWords2(@PathVariable("id")int id) {
		return iadService.BlockCommentsWithInsultingWords2(id);
	}


	@PutMapping(value = "/BlockCommentsWithInsultingWords3/{id}")
	@ResponseBody
	public void BlockCommentsWithInsultingWords3(@PathVariable("id")int id) {
		iadService.BlockCommentsWithInsultingWords3(id);
	}


	// http://localhost:8081/SpringMVC/servlet/ScoreIncrement
	@PutMapping(value = "/ScoreIncrement")
	@ResponseBody
	public void ScoreIncrement() {
		iadService.ScoreIncrement();
	}

	// http://localhost:8081/SpringMVC/servlet/AdsForToday
	@GetMapping(value = "/AdsForToday")
	@ResponseBody
	public int AdsForToday() {
		return iadService.AdsForToday();
	}
	// http://localhost:8081/SpringMVC/servlet/countAds
	@GetMapping(value = "/countAds")
	@ResponseBody
	public double  countAds() {
		return iadService.countAds();
	}
	// http://localhost:8081/SpringMVC/servlet/getAllCommentsJPQL
	@GetMapping(value = "/getAllCommentsJPQL")
	@ResponseBody
	public List<String> getAllCommentsBlockedJPQL() {
		return iadService.getAllCommentsBlockedJPQL();

	}
	// http://localhost:8081/SpringMVC/servlet/getAdsFromTheSameUserJPQL
	@GetMapping(value = "/getAdsFromTheSameUserJPQL")
	@ResponseBody
	public List<String> getAdsFromTheSameUserJPQL() {
		return iadService.getAdsFromTheSameUserJPQL();
	}

	// http://localhost:8081/SpringMVC/servlet/countCommentsJPQL/1
	@GetMapping(value = "/countCommentsJPQL/{idad}")
	@ResponseBody
	public int countCommentsJPQL(@PathVariable("idad")int IdAd) {
		return iadService.countCommentsJPQL(IdAd);
	}
	// http://localhost:8081/getNumberView/1
	@GetMapping(value = "/getNumberView/{idad}")
	@ResponseBody
	public int getNumberView(@PathVariable("idad")int idad) {
		return iadService.getNumberView(idad);
	}
	// http://localhost:8081/increment
	@PutMapping(value = "/increment/{idad}")
	@ResponseBody	
	public void increment(@PathVariable("idad")int idad) {
		iadService.increment(idad);

	}

	// http://localhost:8081/ReclamerUser
	@PutMapping(value = "/ReclamerUser")
	@ResponseBody	
	public void ReclamerUser() {
		iadService.ReclamerUser();
	}
	// http://localhost:8081/BlockUserByBadComments1
	@PutMapping(value = "/BlockUserByBadComments1/{idu}")
	@ResponseBody	
	public void BlockUserByBadComments1(@PathVariable("idu") Long id){
		iadService.BlockUserByBadComments(id);
	}


	// http://localhost:8081/filtre
	@GetMapping(value = "/filtre")
	@ResponseBody	
	public List<Ad> filter() {
		return iadService.filter();
	}

	// http://localhost:8081/DescriptionComments
	@GetMapping(value = "/DescriptionComments/{idc}")
	@ResponseBody	
	public List<Comment> DescriptionComments(@PathVariable("idc")int idc) {
		return 	(List<Comment>) iadService.DescriptionComments(idc);

	}
	// http://localhost:8081/Image
	@GetMapping(value = "/Image")
	@ResponseBody	
	public String Image() {
		return   iadService.Image();
	}

	// http://localhost:8081/MyAds

	@GetMapping(value = "/MyAds/{ClientConnecte}")
	@ResponseBody
	public List<Ad> MyAds(@PathVariable("ClientConnecte")User user){
		return iadService.MyAds(user);
	}
	// http://localhost:8081/SelectedAd
	@GetMapping(value = "/SelectedAd")
	@ResponseBody
	List<Ad> SelectedAd(){
		return iadService.SelectedAd();
	}
}
