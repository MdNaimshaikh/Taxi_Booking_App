package tech.naim.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.naim.model.ServiceForm;

public interface ServiceFormService {
	
	public ServiceForm addService(ServiceForm serviceForm , MultipartFile multipartFile) throws Exception;
	public List<ServiceForm> readService();
	public void deleteServices(int id);

}
