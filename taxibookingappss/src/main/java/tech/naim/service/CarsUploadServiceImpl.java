package tech.naim.service;


import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import tech.naim.dao.CarsDao;
import tech.naim.model.CarsUpload;

@Service
public class CarsUploadServiceImpl implements CarsUploadService{
	
	private CarsDao carsDao;

	@Autowired
	public void setCarsDao(CarsDao carsDao) {
		this.carsDao = carsDao;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public CarsUpload carsUploadService(CarsUpload carsUpload, MultipartFile multipartFile) throws Exception {
		// TODO Auto-generated method stub
		
		CarsUpload  save = null;
		
		try {
			
			 save = carsDao.save(carsUpload);
			 
			 if(save!=null)
			 {
				 String path="C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.24.0.RELEASE\\taxibookingappss\\src\\main\\resources\\static\\myimage\\"+multipartFile.getOriginalFilename();
				 
				 byte[] bytes=multipartFile.getBytes();
				 
				 FileOutputStream fos=new FileOutputStream(path);
				 
				 fos.write(bytes);
				 fos.close();
			 }
			
			
		} catch (Exception e) {
			// TODO: handle exception
			save=null;
			throw e;
		}
		
		return save;
	}

	@Override
	public List<CarsUpload> carsRead() {
		// TODO Auto-generated method stub
		return carsDao.findAll();
	}

	@Override
	public void carsDelete(int id) {
		// TODO Auto-generated method stub
		
		carsDao.deleteById(id);
		
	}

}
