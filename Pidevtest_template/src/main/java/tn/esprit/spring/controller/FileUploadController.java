package tn.esprit.spring.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.repository.UserRepository;


@Join(path = "/uploadview", to = "/uploadview.jsf")
@Controller(value = "FileUploadController")
@ELBeanName(value = "FileUploadController")
public class FileUploadController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RegistrationController registrationController;

	// http://localhost:8081/SpringMVC/servlet/file
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/resources/images";
	// http://localhost:8081/SpringMVC/servlet/up

	@RequestMapping("/file")
	public String UploadPage(Model model) {
		return "uploadview";
	}

	// http://localhost:8081/SpringMVC/servlet/upload
	@RequestMapping("/upload")
	public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
		
		
		
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename() + "");
			

			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		Client cc = new Client();
		cc = registrationController.getClient();
		cc.setPicture(fileNames.toString());
		userRepository.save(cc);
		model.addAttribute("msg", "Successfully uploaded files " + fileNames.toString()+"you are reseved a password in your email you can now connect in our application after a login with our compte ");
		return "uploadstatusview";
		
	}

}
