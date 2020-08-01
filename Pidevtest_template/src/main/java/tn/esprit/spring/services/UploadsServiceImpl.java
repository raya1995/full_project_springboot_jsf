package tn.esprit.spring.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Uploads;
import tn.esprit.spring.controller.UserRestController;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.UploadsRepository;


@Service
public class UploadsServiceImpl implements UploadsService {
	  public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

	@Autowired
	AppointmentRepository AppointmentRepository;
	@Autowired
	UploadsRepository UploadsRepository;
	@Override
	public void blockClientByAttendance() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void saveUploads(@RequestParam("files") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		Uploads up=new Uploads(); 
		Client id;
		id=UserRestController.getClientConnecte();
		  for (MultipartFile file : files) {
			  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			  fileNames.append(file.getOriginalFilename()+" ");

				up.setUploads(uploadDirectory);
				up.setClient(id);
				
				}
				UploadsRepository.save(up);
			  }		
	}


