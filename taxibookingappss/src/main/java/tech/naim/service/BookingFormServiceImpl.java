package tech.naim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.naim.dao.BookingFormCrud;
import tech.naim.model.BookingForm;

@Service
public class BookingFormServiceImpl implements BookingFormService{

	private BookingFormCrud bookingFormCrud;
	
	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}
	@Override
	public BookingForm bookingFromService(BookingForm bookingForm) {
		// TODO Auto-generated method stub
		
		   return bookingFormCrud.save(bookingForm);
	}
	@Override
	public List<BookingForm> readAllDataFromBookingServices() {
		// TODO Auto-generated method stub
		
		return bookingFormCrud.findAll();
	}
	@Override
	public void deleteDataFromBookingServices(int id) {
		// TODO Auto-generated method stub
		
		bookingFormCrud.deleteById(id);
	}

}
