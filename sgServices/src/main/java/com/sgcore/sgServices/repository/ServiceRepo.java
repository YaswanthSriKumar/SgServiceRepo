package com.sgcore.sgServices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sgcore.sgServices.entity.ServicesEntity;

public interface ServiceRepo extends JpaRepository<ServicesEntity, Integer> {

	List<ServicesEntity> findByServiceShow(boolean i);
	
	
	@Query("SELECT serviceName, serviceId FROM ServicesEntity")
	List<Object[]> findServiceNames();


}
