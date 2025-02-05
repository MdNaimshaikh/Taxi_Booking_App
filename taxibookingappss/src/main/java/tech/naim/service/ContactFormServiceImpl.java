package tech.naim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.naim.dao.ContactFormCrud;
import tech.naim.model.ContactForm;

@Service
public class ContactFormServiceImpl implements ContactFormService {
	
	private ContactFormCrud contactFormCrud;

	@Autowired
	public void setContactFormCrud(ContactFormCrud contactFormCrud) {
		this.contactFormCrud = contactFormCrud;
	}

	@Override
	public ContactForm saveContactFormService(ContactForm contactForm) {
		// TODO Auto-generated method stub
		
		return contactFormCrud.save(contactForm);
		
		
		
	}

	@Override
	public List<ContactForm> readAllDataFromContactServices() {
		// TODO Auto-generated method stub

		
		return contactFormCrud.findAll();
	}

	@Override
	public void deleteDataFromContactServices(int id) {
		// TODO Auto-generated method stub
		
		contactFormCrud.deleteById(id);
	}

}
