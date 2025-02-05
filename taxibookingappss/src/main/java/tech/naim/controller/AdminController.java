package tech.naim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import tech.naim.model.About;
import tech.naim.model.CarsUpload;
import tech.naim.model.ServiceForm;
import tech.naim.service.AdminCredentialsServiceImp;
import tech.naim.service.BookingFormServiceImpl;
import tech.naim.service.CarsUploadServiceImpl;
import tech.naim.service.ContactFormServiceImpl;
import tech.naim.service.ServiceFormServiceImpl;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private CarsUploadServiceImpl carsUploadServiceImpl;

	@Autowired
	public void setCarsUploadServiceImpl(CarsUploadServiceImpl carsUploadServiceImpl) {
		this.carsUploadServiceImpl = carsUploadServiceImpl;
	}


	private ServiceFormServiceImpl serviceFormServiceImpl;
	
	@Autowired
	public void setServiceFormServiceImpl(ServiceFormServiceImpl serviceFormServiceImpl) {
		this.serviceFormServiceImpl = serviceFormServiceImpl;
	}


	private AdminCredentialsServiceImp adminCredentialsServiceImp;
	
	@Autowired
	public void setAdminCredentialsServiceImp(AdminCredentialsServiceImp adminCredentialsServiceImp) {
		this.adminCredentialsServiceImp = adminCredentialsServiceImp;
	}


	private BookingFormServiceImpl serviceImpl;

	@Autowired
	public void setServiceImpl(BookingFormServiceImpl serviceImpl) {
		this.serviceImpl = serviceImpl;
	}


	private ContactFormServiceImpl contactFormServiceImpl;
	
	@Autowired
	public void setContactFormServiceImpl(ContactFormServiceImpl contactFormServiceImpl) {
		this.contactFormServiceImpl = contactFormServiceImpl;
	}
	
	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
		
	}
	
	@GetMapping("readallcontacts")
	public String readAllContacts(Model model) {
		
		model.addAttribute("allcontacts", contactFormServiceImpl.readAllDataFromContactServices());
	
		
		return "admin/readallcontacts";
		
	}
	
	@GetMapping("readallbookings")
	public String readAllBooking(Model model) {
		
		
		
		model.addAttribute("allbookings",serviceImpl.readAllDataFromBookingServices());
	
		
		return "admin/readallbookings";
		
	}
	
	@GetMapping("about")
	public String about(Model model) {
		
			
		
		return "admin/aboutus";
		
	}
	
	@InitBinder
	public void stopBindingabout(WebDataBinder binder) {
		
		binder.setDisallowedFields("mypic");
	}
	
	@PostMapping("aboutus")
	public String aboutus(@Valid  @ModelAttribute About about, @RequestParam("mypic") MultipartFile multipartFile, Model model) {
		
		String originalFilename = multipartFile.getOriginalFilename();
		System.out.println(originalFilename);
		about.setMypic(originalFilename);
		
		System.out.println(originalFilename);
		
		return "redirect:/admin/aboutus";
		
	}
	
	@GetMapping("readservices")
	public String readAllServices(Model model) {
		
		
		model.addAttribute("readservices", serviceFormServiceImpl.readService());
		
	
	
		
		return "admin/readservices";
		
	}
	
	
	@GetMapping("carsread")
	public String carsReadView(HttpServletRequest httpRequest, Model model)
	{
		model.addAttribute("carreads",carsUploadServiceImpl.carsRead());
		return "admin/carsread";
	
}
	
	@GetMapping("deletereadcar/{id}")
	public String deleteCars(@PathVariable int id,RedirectAttributes attributes) {
		

		carsUploadServiceImpl.carsDelete(id);
		
		attributes.addFlashAttribute("message", "Car  Deleted Successfully");
		
		return "redirect:/admin/carsread";
		
	}

	@GetMapping("deletecontact/{id}")
	public String deleteContact(@PathVariable int id,RedirectAttributes attributes) {
		
		contactFormServiceImpl.deleteDataFromContactServices(id);
		
		attributes.addFlashAttribute("message", "Contact Deleted Successfully");
		
		return "redirect:/admin/readallcontacts";
		
		
	}
	@GetMapping("deletebooking/{id}")
	public String deleteBooking(@PathVariable int id,RedirectAttributes attributes) {
		

		    serviceImpl.deleteDataFromBookingServices(id);
		
		attributes.addFlashAttribute("message", "Ride Deleted Successfully");
		
		return "redirect:/admin/readallbookings";
		
	}
		
	
	@GetMapping("deleteservices/{id}")
	public String deleteServices(@PathVariable int id,RedirectAttributes attributes) {
		

		serviceFormServiceImpl.deleteServices(id);
		
		attributes.addFlashAttribute("message", "Service Deleted Successfully");
		
		return "redirect:/admin/readservices";
		
	}
		
	
	@GetMapping("changecredentials")
	public String changeCredentialsView() {
		
		return "admin/changecredentials";
		
			
	}
	@PostMapping("changecredentials")
	public String changeCredentials(
			@RequestParam("oldusername") String oldusername,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newusername") String newusername,
			@RequestParam("newpassword") String newpassword,
			RedirectAttributes attributes
			) {
		
		String result = adminCredentialsServiceImp.checkAdminCredentials(oldusername, oldpassword);
		
		//System.out.println(result);
		
		if(result.equals("Success"))
		{
			result=adminCredentialsServiceImp.updateAdminCredentials(newusername, newpassword, oldusername);
			
			attributes.addFlashAttribute("message", result);
		}else {
			attributes.addFlashAttribute("message", result);
		}
		
		return "redirect:/admin/dashboard" ;
		
	}
	@GetMapping("addservice")
	public String addServiceView() {
		
		return "admin/addservice";
		
			
	}
	
	@GetMapping("carsupload")
	public String carsuploadView()
	{
		
		return "admin/carsupload";
		
	}
	
	@InitBinder
	public void stopBinding(WebDataBinder binder) {
		
		binder.setDisallowedFields("image");
	}
	@PostMapping("addservice")
	public String addService(@Valid  @ModelAttribute ServiceForm serviceForm, @RequestParam("image")  MultipartFile multipartFile ,RedirectAttributes attributes,BindingResult bindingResult,Model model) {
		
		
//		if(bindingResult.hasErrors())
//		{
//			model.addAttribute("bindingResult", bindingResult);
//			
//			return "addservice";
//		}
		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		
		try {
			ServiceForm service = serviceFormServiceImpl.addService(serviceForm, multipartFile);
			if(service!=null)
			{
				attributes.addFlashAttribute("msg", "Service Added Successfully");
			}
			else {
				
				attributes.addFlashAttribute("msg", "Something Went Wrong");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/addservice";
		
			
	}

	
	@InitBinder
	public void stopBindingcars(WebDataBinder binder) {
		
		binder.setDisallowedFields("image");
	}
	@PostMapping("carsupload")
	public String carsUpload(@Valid @ModelAttribute CarsUpload carsUpload,@RequestParam("image") MultipartFile multipartFile,Model model,RedirectAttributes attributes) {
		
		String originalFilename = multipartFile.getOriginalFilename();
		carsUpload.setImage(originalFilename);
		
		System.out.println(originalFilename);
		
		
		try {
			CarsUpload carsUploadService = carsUploadServiceImpl.carsUploadService(carsUpload ,multipartFile);
		
			
			if(carsUploadService!=null)
			{
				attributes.addFlashAttribute("msg", "Service Added Successfully");
			}
			else {
				
				attributes.addFlashAttribute("msg", "Something Went Wrong");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/carsupload";
		
		
	}

}
