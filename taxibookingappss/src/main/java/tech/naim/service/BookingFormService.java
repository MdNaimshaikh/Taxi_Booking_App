package tech.naim.service;

import java.util.List;

import tech.naim.model.BookingForm;

public interface BookingFormService {
	
	public BookingForm bookingFromService(BookingForm bookingForm);
	public List<BookingForm>  readAllDataFromBookingServices();
	public void  deleteDataFromBookingServices(int id);

}
