package tech.naim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import tech.naim.model.BookingForm;
import tech.naim.model.CarsUpload;
import tech.naim.model.ContactForm;
import tech.naim.model.ServiceForm;
import tech.naim.service.BookingFormServiceImpl;
import tech.naim.service.CarsUploadServiceImpl;
import tech.naim.service.ContactFormServiceImpl;
import tech.naim.service.ServiceFormServiceImpl;

@Controller
public class MyController {
	
	private CarsUploadServiceImpl uploadServiceImpl;
	
	@Autowired
	public void setUploadServiceImpl(CarsUploadServiceImpl uploadServiceImpl) {
		this.uploadServiceImpl = uploadServiceImpl;
	}


	private ServiceFormServiceImpl serviceFormServiceImpl;
	
	private BookingFormServiceImpl serviceImpl;


	@Autowired
	public void setServiceFormServiceImpl(ServiceFormServiceImpl serviceFormServiceImpl) {
		this.serviceFormServiceImpl = serviceFormServiceImpl;
	}

	@Autowired
	public void setServiceImpl(BookingFormServiceImpl serviceImpl) {
		this.serviceImpl = serviceImpl;
	}


	private ContactFormServiceImpl contactFormServiceImpl;
	
	@Autowired
	public void setContactFormServiceImpl(ContactFormServiceImpl contactFormServiceImpl) {
		this.contactFormServiceImpl = contactFormServiceImpl;
	}

	@GetMapping(path= {"/","home","index"})
	public String welcomeView(HttpServletRequest httpRequest, Model model)
	{
		String requestURI = httpRequest.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		model.addAttribute("bookingForm", new BookingForm());
		return "index";
		
	}
	
	@GetMapping("/login")
	public String adminLoginView(HttpServletRequest httpRequest , Model model)
	{
	
		ServletContext servletContext = httpRequest.getServletContext();
		
		Object attribute = servletContext.getAttribute("logout");
		
		if(attribute instanceof Boolean) {
			
			model.addAttribute("logout", attribute);
			servletContext.removeAttribute("logout");
		}
	
		return "adminlogin";
		
	}
	
	@GetMapping("about")
	public String aboutView(HttpServletRequest httpRequest, Model model)
	{
		String requestURI = httpRequest.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		return "about";
		
	}
	
	@GetMapping("cars")
	public String carsView(HttpServletRequest httpRequest, Model model)
	{
		String requestURI = httpRequest.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		List<CarsUpload> carsRead = uploadServiceImpl.carsRead();
		model.addAttribute("carupload", carsRead);
		return "cars";
	}
	
	
	@GetMapping("service")
	public String servicesView(HttpServletRequest httpRequest, Model model)
	{
		String requestURI = httpRequest.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		List<ServiceForm> service = serviceFormServiceImpl.readService();
		
		model.addAttribute("allservices", service);
		
		return "service";
		
		
	}
	@GetMapping("contacts")
	public String contactsView(HttpServletRequest httpRequest, Model model)
	{
		String requestURI = httpRequest.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		model.addAttribute("contactForm",new ContactForm());
		return "contacts";
		
	}

	@PostMapping("contactform")
	public String contactsForm(@Valid  @ModelAttribute ContactForm contactForm , BindingResult bindingResult ,Model model, RedirectAttributes attributes)
	{
		if(bindingResult.hasErrors())
		{
			model.addAttribute("bindingResult", bindingResult);
			
			return "contacts";
		}
	
		//System.out.println(contactForm);
		
		ContactForm saveContactFormService = contactFormServiceImpl.saveContactFormService(contactForm);
		
		if(saveContactFormService !=null) {
			
			attributes.addFlashAttribute("message", "Message Sent Successfully");
		}
		else {
			attributes.addFlashAttribute("message", "Something went wrong");
		}
		
		return "redirect:/contacts";
		
	}
	
	
	@PostMapping("bookingform")
	public String bookingForm(@Valid  @ModelAttribute BookingForm bookingForm , BindingResult bindingResult ,Model model, RedirectAttributes attributes)
	{
		if(bindingResult.hasErrors())
		{
			model.addAttribute("bindingResult", bindingResult);
			
			return "index";
		}
		else if(bookingForm.getAdult()+bookingForm.getChildren() >4) {
			
				model.addAttribute("message", "adult and child must be less than or equal 4");
			
			     return "index";
		}
		
		//System.out.println(bookingForm);
		
		BookingForm bookingFromService = serviceImpl.bookingFromService(bookingForm);
		
if(bookingFromService !=null) {
			
			attributes.addFlashAttribute("message", " Ride Booking Successfully");
		}
		else {
			attributes.addFlashAttribute("message", "Something went wrong");
		}
		
		
		return "redirect:/index";
		
	}
}
