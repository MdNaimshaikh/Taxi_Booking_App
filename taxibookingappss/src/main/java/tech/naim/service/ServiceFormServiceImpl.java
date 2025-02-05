package tech.naim.service;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import tech.naim.dao.ServiceFormCrud;
import tech.naim.model.ServiceForm;

@Service
public class ServiceFormServiceImpl implements ServiceFormService{

	private ServiceFormCrud serviceFormCrud;
	
	@Autowired
	public void setServiceFormCrud(ServiceFormCrud serviceFormCrud) {
		this.serviceFormCrud = serviceFormCrud;
	}

	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception {
		// TODO Auto-generated method stub
		
			ServiceForm save=null;
		try {
			
		save = serviceFormCrud.save(serviceForm);
		
		if(save!=null) {
		String path="C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.24.0.RELEASE\\taxibookingappss\\src\\main\\resources\\static\\myimage\\"+multipartFile.getOriginalFilename();
			
		byte[] bytes = multipartFile.getBytes();
		
		
		FileOutputStream fos = new FileOutputStream(path);
		
		fos.write(bytes);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			save=null;
			throw e;
		}
		return save;
	}


	@Override
	public List<ServiceForm> readService() {
		
		return serviceFormCrud.findAll();
	}


	@Override
	public void deleteServices(int id) {
		// TODO Auto-generated method stub
		
		serviceFormCrud.deleteById(id);
	}

}
