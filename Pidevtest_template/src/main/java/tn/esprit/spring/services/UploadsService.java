package tn.esprit.spring.services;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface UploadsService {
	public void blockClientByAttendance();
	public void saveUploads(@RequestParam("files") MultipartFile[] files);

}
