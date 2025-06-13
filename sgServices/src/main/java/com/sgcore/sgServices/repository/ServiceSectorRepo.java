package com.sgcore.sgServices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sgcore.sgServices.entity.ServiceSectorEntity;

@Repository
public interface ServiceSectorRepo  extends JpaRepository<ServiceSectorEntity, String>{

	   @Query("SELECT sectorName FROM ServiceSectorEntity ") 
	    List<String> findAllSectorNames();
}
