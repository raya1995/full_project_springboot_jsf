package tn.esprit.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.repository.FurnitureRepository;

@Async
@Scope(value = "session")
@ELBeanName(value = "FileUploadController1")
@RestController
@Join(path= "/uploadview1", to = "/uploadview1.jsf")
@Controller(value="FileUploadController1")
public class FileUploadController1 {
	private String Image;
	
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	@Autowired
	FurnitureRepository furnitureRepository;
	@Autowired
	FurnitureControllerJSF furnitureController1;
	
	//http://localhost:8081/SpringMVC/servlet/fileuploads
  public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/webapp/ressources/img/bg-img";
  @RequestMapping("/fileuploads")
  public String UploadPage(Model model) {
	  return "uploadview1";
  }
  //http://localhost:8081/SpringMVC/servlet/upload
  @RequestMapping("/upload1")
  public String upload(Model model,@RequestParam("files") MultipartFile[] files) {
	  StringBuilder fileNames = new StringBuilder();
	  for (MultipartFile file : files) {
		  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		  fileNames.append(file.getOriginalFilename()+" ");
		
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
		Furniture furn = new Furniture();
		furn = furnitureController1.getF2();
		 furn.setImage(fileNames.toString());
		  furnitureRepository.save(furn);
	  model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
	  return "product has been added with success";
  }
  
  
  
}
