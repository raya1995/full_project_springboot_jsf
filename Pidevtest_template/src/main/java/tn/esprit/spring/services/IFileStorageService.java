package tn.esprit.spring.services;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {

	
	public String storeFile(MultipartFile file);
	public Resource loadFileAsResource(String fileName);
}
