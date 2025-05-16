package com.sgcore.sgServices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgcore.sgServices.entity.SubservicesEntity;

@Repository
public interface SubserviceRepo extends JpaRepository<SubservicesEntity, Integer> {

	List<SubservicesEntity> findByService_ServiceId(int id);

	
}
