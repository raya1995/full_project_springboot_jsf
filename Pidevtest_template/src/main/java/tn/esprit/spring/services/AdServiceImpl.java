package tn.esprit.spring.services;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.KindOfGood;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.ReclamationRepository;


@Service
public class AdServiceImpl  implements IAdService {
	@Autowired
	AdRepository adRepository;
	@Autowired
	MailService mails;
	@Autowired
	ReclamationRepository ReclamationRepository;
	@Autowired
	ClientRepository ClientRepository;

	@Autowired
	RatingView ratingview;
	@Autowired
	Filter filter;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	WishListService ws;
	@Autowired
	FavoriteAdService fs;

	public static final Logger l = LogManager.getLogger(AdServiceImpl.class);
	public static Integer rating2=0;




	public MailService getMails() {
		return mails;
	}

	public void setMails(MailService mails) {
		this.mails = mails;
	}

	@Override
	public Ad addAd(Ad ad) {
		adRepository.save(ad);	
		ws.NotifSms(ad);
		l.info("AAA++++AAAAAAA+++++AAAA+++++AAA++++");
		return ad;
	}
	
	@Override
	public List<Ad> retrieveAllAds() {
		List<Ad> ads=(List<Ad>) adRepository.findAll();
		return ads;	
		}

	@Override
	public Ad updateAd(Ad ad) {
		adRepository.save(ad);
		return ad;
	}

	@Override
	public void deleteAd(int IdAd) {
		adRepository.deleteById(IdAd);
	}


	@Override
	public List<String> getAllCommentsByAd(int AdId) {
		Ad aa = adRepository.findById(AdId).get();
		List<String> CommentsDescription = new ArrayList<>();
		for(Comment com : aa.getComments()){
			CommentsDescription.add(com.getDescriptionComment());	
		}

		return CommentsDescription;
	}


	@Override
	public Ad getAdById(int adId) {
		return adRepository.findById(adId).get();	

	}


	@Override
	public Comment addComment(Comment comment,Long idClient,Integer idPub,Boolean isBlocked) {
		Client client = ClientRepository.findById(idClient).get();
		Ad ad =adRepository.findById(idPub).get();
		comment.setClient(client);
		comment.setAds(ad);
		comment.setIsBlocked(false);
		List<Comment> com=(List<Comment>) commentRepository.findAll();
		commentRepository.save(comment);
		return comment;
	}
	@Override
	public Comment addCommentaire(Comment u) {
		commentRepository.save(u);
		return null;
	}

	@Override
	public void deleteComment(int idComment) {
		commentRepository.deleteById(idComment);

	}


	@Override
	public Comment UpdateComment(Comment comment) {
		commentRepository.save(comment);
		return comment;
	}

	@Override
	public void AssignCommentToanAd(int CommentId, int AdId) {

	}

	@Override
	public int countComments() {
		int max=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();
		for(Comment comments :com) {
			max++;
		}
		l.info(" you have "+ max + "comments");

		return max;

	}

	@Override
	public int nbrLike() {	
		int max1=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();		
		for(Comment aa : com ) {

			max1 +=aa.getNumberLikes();	 

		}
		l.info(" you have "+ max1 + "comments");

		return max1;
	}

	@Override
	public List<Ad>getAdWithBestLikesOnCommentsJPQL() {

		return adRepository. getAdWithBestLikesOnCommentsJPQL();
	}

	//max des nbres de likes dans commentaires
	@Override
	public int maxnblike() {	
		int k=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();		
		for(Comment aa : com ) {

			if(aa.getNumberLikes()> k) {
				k=aa.getNumberLikes();	  
			}  
		}

		l.info(" you have "+k+ " comments");
		return k;

	}


	public boolean succes() {

		List<Comment> com=(List<Comment>) commentRepository.findAll();
		List<Ad> zz=(List<Ad>) adRepository.findAll();	

		for(Comment aa :com) {
			for(Ad ee :zz) {
				if(aa.getNumberLikes()>=100 && ee.getViewsNumber()>=100) {

					ee.setSuccess(true); 
					adRepository.save(ee);
					l.info("True");

				} 
				else  {ee.setSuccess(false);
				l.info("False");}
			}


		}return true; 

	}

	//moyenne nombre commantaires par ans
	public double AVG_nbcomment() {	
		int A=0;
		double b=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();
		for(Comment comments :com) {
			A++;
		}
		b=(A*360)/100;
		l.info(" you have "+ b + "comments");

		return b;
	}

	public void ScoreIncrement()  {
		List<Ad> zz=(List<Ad>)adRepository.findAll();
		int b=0;  
		for(Ad aa :zz) {

			if(aa.getViewsNumber()>=1000) {
				b = aa.getScore()+100;
				aa.setScore(b);
				adRepository.save(aa);
			}

		}
	}

	//total des ads par jour
	@Override
	public int AdsForToday() {
		List<Ad> ads=(List<Ad>)adRepository.findAll();
		int nbr_ads_for_day=0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		l.info("******" + dateFormat.format(date));
		for(Ad a:ads) {	
			if ((a.getAdDate().getDay()== date.getDay() ) && (a.getAdDate().getMonth()== date.getMonth()) && (a.getAdDate().getYear()== date.getYear())) {
				nbr_ads_for_day++;

				l.info("******" + dateFormat.format(date) + a);

			}} 

		l.info("you have "+ nbr_ads_for_day +" ads today" );
		return nbr_ads_for_day;	
	}



	//Bloquer le comments avec des mots insultants	
	//String input= reclamation.getDescription();
	//String output = filter.getCensoredText(input);

	public boolean BlockCommentsWithInsultingWords()  {

		List<Comment> com=(List<Comment>) commentRepository.findAll();
		List<String> c = new ArrayList<>();
		java.nio.file.Path path= Paths.get("C:\\Users\\user\\Desktop\\Full_Bad_Word.txt");


		try {
			for(String line :Files.readAllLines(path)) {
				c.add(line);

			}
			System.out.println("\n");
		} catch (IOException e) {
		}

		for(Comment aa :com) {

			if(c.contains(aa.getDescriptionComment())) {
				aa.setIsBlocked(true);
				commentRepository.save(aa);
				//return true;
			} else 

				aa.setIsBlocked(false);
			commentRepository.save(aa);

		}return true;

	}


	public boolean BlockCommentsWithInsultingWords2(int id) {
		
		Comment	aa=commentRepository.findById(id).get();
		int x = 0;
		String input= aa.getDescriptionComment();
		String output = Filter.getCensoredText(input);
		
		
		List<Reclamation> appp=(List<Reclamation>) ReclamationRepository.findAll();
		Long iddd;
		iddd=aa.getClient().getId();
		
					
		if(output.contains("*")) {
			
			//x++;
			aa.setIsBlocked(true);
			
			
			
			mails.sendEmail(aa.getClient().getEmail(), aa.getDescriptionComment(), aa.getClient().getFirstName());
			l.info("***************"+     iddd);
			l.info("*************** x="+     x);
			
			
			Reclamation r = new Reclamation();
			r.setDescription("this user added comment with bad words");
			r.setCommentaire(aa);
			//r.setUserId(iddd);
			//r.setId(1L);
			
			
			ReclamationRepository.save(r);
			l.info("*****"+r.getDescription());
			commentRepository.save(aa);		
			
			
			
			List<Client> clients =(List<Client>)ClientRepository.findAll();
			List<Comment> com=(List<Comment>)commentRepository.findAll();
			for(Comment c:com) {
				if(c.getClient().getId().equals(iddd)) {

					if(c.getIsBlocked().equals(true)) {
						x=x+1;
					}
				}
			}
			if(x>=2) {
				for(Client cl:clients) {
					
					if(cl.getId().equals(iddd)) {
						cl.setBlock(true);
						cl.setDescriptionBlock("this user is blocked because he has added a lot of bad comments");
						mails.sendEmail2(aa.getClient().getEmail(),aa.getClient().getFirstName());
						ClientRepository.save(cl);

					}

				}

			}




		}
		
		else 
		{
		aa.setIsBlocked(false);
		commentRepository.save(aa);
		}
		
		return true;
	}


	//aa.setAds(null);
	//aa.setClient(null);
	// mails.sendEmail(aa.getClient().getEmail(), aa.getDescriptionComment(), aa.getClient().getFirstName());







	@Override
	public void BlockUserByBadComments(Long id) {

		int x = 0;
		Client client = new Client();
		List<Client> clients =(List<Client>)ClientRepository.findAll();
		List<Comment> com=(List<Comment>)commentRepository.findAll();
		List<Reclamation> appp = (List<Reclamation>) ReclamationRepository.findAll();
		Client iddd;
		for(Comment c:com) {
			if(c.getClient().getId().equals(id)) {

				if(c.getIsBlocked().equals(true)) {
					x=x+1;
				}
			}
		}
		if(x>=2) {
			for(Client cl:clients) {
				
				if(cl.getId().equals(id)) {
					cl.setBlock(true);
					cl.setDescriptionBlock("this user is blocked because he has added a lot of bad comments");
					ClientRepository.save(cl);

				}

			}

		}
	}


	/*List<Comment> app=(List<Comment>) commentRepository.findAll();
		List<Comment> ap=(List<Comment>) commentRepository.retrieveAllBadCommentByClient();

		List<Client> cc=(List<Client>) ClientRepository.findAll();
		int max=0;
		Long idd;
		for(Comment c:app) {
			//l.info("-------"+ap);
			if(c.getIsBlocked().equals(true) ) {  
						max++;
						l.info("***************"+max);
						if (max>=2) {
							idd=c.getClient().getId();
							for(Comment aa:ap) {
								if(c.getClient().getId()==idd) {
									c.getClient().setBlock(true);
									c.getClient().setDescriptionBlock("this user is blocked because he has added a lot of bad comments");

									max=0;
									for(Client client:cc) {
							ClientRepository.save(c.getClient());
						}

						l.info("user is Blocked+++++++" + c.getClient().getId()+ "+++++++"+ c.getClient().isBlock());

	}
							}		}}}


					}*/




	public void BlockCommentsWithInsultingWords3(int id) {

		List<Comment> com = (List<Comment>) commentRepository.findAll();
		for (Comment aa : com) {
			if (aa.getIdComment() == id) {
				String input = aa.getDescriptionComment();
				String output = Filter.getCensoredText(input);

				if (output.contains("*")) {
					aa.setIsBlocked(true);
					commentRepository.save(aa);
					// return true;
				} else

					aa.setIsBlocked(false);
			}
			commentRepository.save(aa);

		}

	}




	@Override
	public double AVG_Ads_Year() {
		int A=0;
		double b=0;
		List<Ad> ad=(List<Ad>) adRepository.findAll();
		for(Ad aa :ad) {
			A++;
		}
		b=(A*360)/100;
		l.info(" you have "+ b + "ads");

		return  b;
	}

	@Override
	public double countAds() {
		int a=0;

		List<Ad> aa=(List<Ad>) adRepository.findAll();
		for(Ad ads :aa) {
			a++;
		}
		return a;
	}

	@Override
	public List<String> getAllCommentsBlockedJPQL() {
		return adRepository.getAllCommentsBlockedJPQL();
	}

	@Override
	public List<String> getAdsFromTheSameUserJPQL() {
		return adRepository.getAdsFromTheSameUserJPQL();

	}

	@Override
	public int countCommentsJPQL(int IdAd) {
		return commentRepository.countCommentsJPQL(IdAd);
	}

	@Override
	public int addOrUpdateAd(Ad ad) {
		adRepository.save(ad);
		return ad.getIdAd();	
	}

	@Override
	public int getNumberView(int idad) {

		return adRepository.getNumberView(idad);

	}


	@Override
	public boolean increment(int idad) {

		int A=0;
		int k=0;

		List<Ad> ads=(List<Ad>)adRepository.findAll();

		for(Ad aa: ads) {
			if(aa.getIdAd()==idad) {
				A=adRepository.getNumberView(idad);
				A++;
				aa.setViewsNumber(A);
				if(aa.getViewsNumber()==20 || aa.getViewsNumber()==50) {

					k=aa.getRating2();
					k++;
					aa.setRating2(k);
				}
				adRepository.save(aa);
			}
		}
		return true;
	}




	public void ReclamerUser() {
		List<Reclamation> appp=(List<Reclamation>) ReclamationRepository.findAll();
		List<Comment> com=(List<Comment>) commentRepository.findAll();
		Client iddd;
		for(Comment a:com) {
			if(a.getIsBlocked().equals(true) ) {
				iddd=a.getClient();
				Reclamation r = new Reclamation();
				r.setDescription("this user added comment with bad words");
				r.setCommentaire(a);
				r.setUserId(iddd);
				//r.setId(1L);

				ReclamationRepository.save(r);

				l.info("*****"+r.getDescription());


			}


		}

	}





	@Override
	public List<Comment> retrieveAllComments() {
		return (List<Comment>)commentRepository.findAll();	
	}

	public List<Ad> filter(){
		return (List<Ad>)adRepository.filter();
	}
	
	
	@Override
	public List<Comment> DescriptionComments(int idc) {
		List<Ad> aa=(List<Ad>) adRepository.findAll();	
		for(Ad zz:aa) {
			if( zz.getIdAd()==idc){
				return (List<Comment>)commentRepository.DescriptionComments(idc);
			}
		}
		return null;

	}
	@Override
	public String Image() {
		String k=null;
		List<String> c = new ArrayList<String>();	
		List<Ad> zz=(List<Ad>) adRepository.findAll();	


		java.nio.file.Path path= Paths.get("C:\\Users\\user\\Desktop\\4infoB-S2\\pidev\\Full_Bad_Word.txt");

		try {
			for(Ad aa:zz) {
				for(String line :Files.readAllLines(path)) {
					c.add(line);
					k=c.get(0);
					aa.setImage(k);
					l.info("line " +line);
					Files.delete(Paths.get("C:\\Users\\user\\Desktop\\4infoB-S2\\pidev\\Full_Bad_Word.txt\\"));

				}

				c.clear();

				Files.delete(path);

			}
			l.info("dffdf"+c);
		} catch (IOException e) {

			e.printStackTrace();
		}

		/*for(Ad aa:zz) { 
		int i=0;
	 k=c.get(i);

	 l.info("a9rali K ya satek " +k);
	  aa.setImage(k);
	// adRepository.save(aa);


 }*/
		return k;
	}






	@Override
	public boolean incrementdislike(int idad) {

		int A = 0;
		int k = 0;

		List<Ad> ads = (List<Ad>) adRepository.findAll();

		for (Ad aa : ads) {
			if (aa.getIdAd() == idad) {
				A = adRepository.getNumberDislike(idad);
				A++;
				aa.setNbDisLikes(A);
			}
			adRepository.save(aa);
		}
		return true;

	}

	public boolean incrementlike(int idad) {

		int A = 0;
		int k = 0;

		List<Ad> ads = (List<Ad>) adRepository.findAll();

		for (Ad aa : ads) {
			if (aa.getIdAd() == idad) {
				A = adRepository.getNumberLike(idad);
				A++;
				aa.setNbLikes(A);
			}
			adRepository.save(aa);
		}
		return true;

	}
	@Override
	public int addOrUpdateComment(Comment comment) {
		commentRepository.save(comment);
		return comment.getIdComment();	
	}

	public Comment getCommentById(int comId) {
		return commentRepository.findById(comId).get();
	}


	@Override
	public int nbrLikeAd() {
		int like = 0;
		List<Ad> a = (List<Ad>) adRepository.findAll();
		for (Ad aa : a) {


			like+= aa.getNbLikes();

		}

		l.info(" you have " + like + "likes");

		return like;

	}

	@Override
	public int nbrDislikeAd() {
		int dislike = 0;
		List<Ad> a = (List<Ad>) adRepository.findAll();
		for (Ad aa : a) {

			dislike += aa.getNbDisLikes();

		}

		l.info(" you have " + dislike + "dislikes");

		return dislike;
	}

	@Override
	public List<Ad> MyAds(User user) {
		
		return adRepository.MyAds(user);
	}

	@Override
	public List<Ad> SelectedAd() {
		return null;
//adRepository.SelectedAd();
	}

	@Override
	public List<Ad> retrieveAllVillaJPQL(KindOfGood kindofgood) {
		return adRepository.retrieveAllVillaJPQL();
	}

	@Override
	public List<Ad> retrieveAllAppartementJPQL(KindOfGood kindofgood) {
		return adRepository.retrieveAllAppartementJPQL();
	}

	@Override
	public List<Ad> retrieveAllStudioJPQL(KindOfGood kindofgood) {
		return adRepository.retrieveAllStudioJPQL();
	}

	@Override
	public List<Ad> retrieveAllWorkshopJPQL(KindOfGood kindofgood) {
		return adRepository.retrieveAllWorkshopJPQL();
	}
	
	
	@Override
	public int addAd1(Ad ad) {
		int i=0;
		adRepository.save(ad);
		i++;
		return i;
	}

	@Override
	public int Countads() {
		return adRepository.Countads();
	}

	//loua
		@Override
		public Ad retrieveAdById(String id) {
			// TODO Auto-generated method stub
			Ad a= adRepository.findById(Integer.parseInt(id)).orElse(null);
			l.info("retrive user by id ++++:"+a);
			return a;
					
		}

		@Override
		public List<Ad> afficherAnnonceFollow(Long Id) {

			return adRepository.affFollow(Id);

		}

		@Override
		public int Myannoncenombre(Long Id) {

			return adRepository.nombreFollowing(Id);
		}

		@Override
		public List<Ad> annonceuser(Long Id) {
			
			return adRepository.annonceuser(Id);
		}


}




