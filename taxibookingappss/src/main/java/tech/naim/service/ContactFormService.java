package tech.naim.service;

import java.util.List;

import tech.naim.model.ContactForm;

public interface ContactFormService {
	
	public ContactForm saveContactFormService(ContactForm contactForm);
	public List<ContactForm> readAllDataFromContactServices();
	public void deleteDataFromContactServices(int id);

}
