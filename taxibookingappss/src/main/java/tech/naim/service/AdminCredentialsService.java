package tech.naim.service;

public interface AdminCredentialsService {
	
	public String checkAdminCredentials(String oldusername,String oldpassword);
	public String updateAdminCredentials(String username,String newpassword,String oldusername);

}
