package tech.naim.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.naim.dao.AdminDao;
import tech.naim.model.Admin;

@Service
public class AdminCredentialsServiceImp implements  AdminCredentialsService {
	
private AdminDao adminDao;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public String checkAdminCredentials(String oldusername, String oldpassword) {
		
	
		Optional<Admin> byUsername = adminDao.findByUsername(oldusername);	
		
		if(byUsername.isPresent()) {
			
			Admin admin = byUsername.get();
			
			boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
			if(matches) {
					
				return "Success";
			}
			else {
				return "Wrong old Credentials";
			}
		}
		else {
			
			return "Wrong old Credentials";
		}
	}

	@Override
	public String updateAdminCredentials(String username, String newpassword, String oldusername) {
		// TODO Auto-generated method stub
		
		int updateCredentialsDao = adminDao.updateCredentialsDao(username,passwordEncoder.encode(newpassword) , oldusername);
		
		if(updateCredentialsDao==1) {
			
			return "Credentials Updated Successfully";
		}
		else {
			
			return "Failed to Update";
		}
	}

}
