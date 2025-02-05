package tech.naim.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.naim.model.ServiceForm;

@Repository
public interface ServiceFormCrud  extends JpaRepository<ServiceForm, Integer>{
	
	

}
