package tech.naim.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.naim.model.CarsUpload;

@Repository
public interface CarsDao extends JpaRepository<CarsUpload, Integer> {

}
