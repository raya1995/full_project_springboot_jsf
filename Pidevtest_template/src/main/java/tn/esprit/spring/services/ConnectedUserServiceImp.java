package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.repository.ConnectedUserRepository;


@Service
public class ConnectedUserServiceImp implements ConnectedUserService{

	@Autowired
	ConnectedUserRepository connectedUserRepository;
	
	
	@Override
	public void newUserConnected() {
		
		    long millis=System.currentTimeMillis();  
		    java.sql.Date date=new java.sql.Date(millis);  
		    System.out.println(date);
		   int x=0;
		    List<ConnectedUser> tables =(List<ConnectedUser>)connectedUserRepository.findAll(); 
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    int year=calendar.get(Calendar.YEAR);
		    int day=calendar.get(Calendar.DAY_OF_MONTH);
		    int month=calendar.get(Calendar.MONTH);
		     System.out.println(year+""+day+""+month);
		    
		    
		    
		    for(ConnectedUser Cnnec:tables) {
		    	 Calendar cal = Calendar.getInstance();
		    	 cal.setTime(Cnnec.getToday());
		    	 int year1=cal.get(Calendar.YEAR);
				 int day1=cal.get(Calendar.DAY_OF_MONTH);
				 int month1=cal.get(Calendar.MONTH);
				 System.out.println(year1+""+day1+""+month1);
				 if(year1==year && day1==day && month==month1 ) {
					 Cnnec.setNbrConnect(Cnnec.getNbrConnect()+1);
					 connectedUserRepository.save(Cnnec);
					 x=10+x;
				 }
		    	
		    
		    	
		    }
		    if(x==0) {
		    ConnectedUser CU=new ConnectedUser(null,date,1);
		    
			connectedUserRepository.save(CU);
		   }
		 
		   
		
		
	}


	@Override
	public List<Object> getconnectedmonthofyear() {
		
		 long millis=System.currentTimeMillis();  
		    java.sql.Date date=new java.sql.Date(millis); 
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    int thisyear=calendar.get(Calendar.YEAR);
		    
		    
		  List<Object> values = new ArrayList<>();
		 List<ConnectedUser> tables =(List<ConnectedUser>)connectedUserRepository.findAll();
		 int January=0;//1
		 int February=0;//2
		 int March=0;//3
		 int April=0;//4
		 int May =0;//5
		 int June=0;//6
		 int July=0;//7
		 int August=0;//8
		 int September=0;//9
		 int October=0;//10
		 int November=0;//11
		 int December=0;//12
		 for(ConnectedUser Cnnec:tables) {
			
			 Calendar cal = Calendar.getInstance();
	    	 cal.setTime(Cnnec.getToday());
	    	 
	    	 int month=cal.get(Calendar.MONTH);
	    	
	    	 int year=cal.get(Calendar.YEAR);	    	 
	    	 if(year==thisyear) {
	    		 if(month==0) {
	    			 
	    			 January=January+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==1) {
	    			
	    			 February=February+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==2) {
	    			 March=March+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==3) {
	    			 April=April+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==4) {
	    			 May=May+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==5) {
	    			 June=June+Cnnec.getNbrConnect();
	    		 } 
	    		 if(month==6) {
	    			 July=July+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==7) {
	    			 August=August+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==8) {
	    			 September=September+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==9) {
	    			 October=October+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==10) {
	    			 November=November+Cnnec.getNbrConnect();
	    		 }
	    		 if(month==11) {
	    			 December=December+Cnnec.getNbrConnect();
	    		 }
	    		 
	    		 
	    		 
	    		 
	    	 }
			 
		 }
		 values.add(January);
		 values.add(February);
		 values.add(March);
		 values.add(April);
		 values.add(May);
		 values.add(June);
		 values.add(July);
		 values.add(August);
		 values.add(September);
		 values.add(October);
		 values.add(November);
		 values.add(December);
		
		return values;
	}


	@Override
	public List<Number> getconnectedmonthofyearnumber() {
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis); 
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    int thisyear=calendar.get(Calendar.YEAR);
	    
	    
	  List<Number> values = new ArrayList<>();
	 List<ConnectedUser> tables =(List<ConnectedUser>)connectedUserRepository.findAll();
	 int January=0;//1
	 int February=0;//2
	 int March=0;//3
	 int April=0;//4
	 int May =0;//5
	 int June=0;//6
	 int July=0;//7
	 int August=0;//8
	 int September=0;//9
	 int October=0;//10
	 int November=0;//11
	 int December=0;//12
	 for(ConnectedUser Cnnec:tables) {
		
		 Calendar cal = Calendar.getInstance();
    	 cal.setTime(Cnnec.getToday());
    	 
    	 int month=cal.get(Calendar.MONTH);
    	
    	 int year=cal.get(Calendar.YEAR);	    	 
    	 if(year==thisyear) {
    		 if(month==0) {
    			 
    			 January=January+Cnnec.getNbrConnect();
    		 }
    		 if(month==1) {
    			
    			 February=February+Cnnec.getNbrConnect();
    		 }
    		 if(month==2) {
    			 March=March+Cnnec.getNbrConnect();
    		 }
    		 if(month==3) {
    			 April=April+Cnnec.getNbrConnect();
    		 }
    		 if(month==4) {
    			 May=May+Cnnec.getNbrConnect();
    		 }
    		 if(month==5) {
    			 June=June+Cnnec.getNbrConnect();
    		 } 
    		 if(month==6) {
    			 July=July+Cnnec.getNbrConnect();
    		 }
    		 if(month==7) {
    			 August=August+Cnnec.getNbrConnect();
    		 }
    		 if(month==8) {
    			 September=September+Cnnec.getNbrConnect();
    		 }
    		 if(month==9) {
    			 October=October+Cnnec.getNbrConnect();
    		 }
    		 if(month==10) {
    			 November=November+Cnnec.getNbrConnect();
    		 }
    		 if(month==11) {
    			 December=December+Cnnec.getNbrConnect();
    		 }
    		 
    		 
    		 
    		 
    	 }
		 
	 }
	 values.add(January);
	 values.add(February);
	 values.add(March);
	 values.add(April);
	 values.add(May);
	 values.add(June);
	 values.add(July);
	 values.add(August);
	 values.add(September);
	 values.add(October);
	 values.add(November);
	 values.add(December);
	
	return values;
	}

}
