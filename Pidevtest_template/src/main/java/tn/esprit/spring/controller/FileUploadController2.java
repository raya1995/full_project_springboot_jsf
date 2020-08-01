package tn.esprit.spring.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Uploads;
import tn.esprit.spring.repository.UploadsRepository;
import tn.esprit.spring.repository.AppointmentRepository;
@Join(path = "/uploadview2", to = "/uploadview2.jsf")
@Controller(value = "FileUploadController2")
@ELBeanName(value = "FileUploadController2")

public class FileUploadController2 {
		
		@Autowired
		UploadsRepository UploadsRepository;
		@Autowired
		AppointmentRepository AppointmentRepository;
		
		//http://localhost:8081/SpringMVC/servlet/file
	  public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/webapp/uploads";
		//http://localhost:8081/SpringMVC/servlet/up


		

	  
	  @RequestMapping("/file2")
	  public String UploadPage(Model model) {
		  return "uploadview2";
	  }
	  //http://localhost:8081/SpringMVC/servlet/upload
	  @RequestMapping("/upload2")
	  public String upload(Model model,@RequestParam("files") MultipartFile[] files) {
		  List<Appointment> app = (List<Appointment>) AppointmentRepository.lastAppointmentAdded();
			List<Uploads> uplo = (List<Uploads>) UploadsRepository.findAll();
			Long id = 0L;
	  


		  Uploads up=new Uploads();
		  
		  StringBuilder fileNames = new StringBuilder();
		  for (MultipartFile file : files) {
			  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			  fileNames.append(file.getOriginalFilename());
			  up.setUploads(file.getOriginalFilename());
			  Client cc;
			  cc=UserRestController.getClientConnecte();
			  up.setClient(cc);
			  UploadsRepository.save(up);
			  
			  
			  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  
		  Client cc;
		  cc=UserRestController.getClientConnecte();
		  
		  for(Appointment a:app) {
						  a.setFiles(fileNames.toString());
						  AppointmentRepository.save(a);}
		  model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
		  return "uploadstatusview";
	  
		  
	  }public String acceuil() {
			 String navigateTo = "null";

			  return navigateTo = "/oumayma/addAppointment.xhtml?faces-redirect=true";		
		}
				  }
