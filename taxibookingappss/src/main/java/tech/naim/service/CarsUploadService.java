package tech.naim.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.naim.model.CarsUpload;

public interface CarsUploadService {
	
	public CarsUpload carsUploadService(CarsUpload carsUpload , MultipartFile multipartFile) throws  Exception;
	
	public List<CarsUpload>  carsRead();
	public void  carsDelete(int id);

	

}
