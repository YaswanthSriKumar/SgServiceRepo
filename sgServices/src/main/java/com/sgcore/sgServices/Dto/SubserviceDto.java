package com.sgcore.sgServices.Dto;

import com.sgcore.sgServices.entity.ServicesEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

public class SubserviceDto {

	
    private int subserviceId;
    private String subserviceName;
    private String subserviceImage;
    private String subserviceDescription;
    private Double subserviceCost;
    private String serviceName;
	public int getSubserviceId() {
		return subserviceId;
	}
	public void setSubserviceId(int subserviceId) {
		this.subserviceId = subserviceId;
	}
	public String getSubserviceName() {
		return subserviceName;
	}
	public void setSubserviceName(String subserviceName) {
		this.subserviceName = subserviceName;
	}
	public String getSubserviceImage() {
		return subserviceImage;
	}
	public void setSubserviceImage(String subserviceImage) {
		this.subserviceImage = subserviceImage;
	}
	public String getSubserviceDescription() {
		return subserviceDescription;
	}
	public void setSubserviceDescription(String subserviceDescription) {
		this.subserviceDescription = subserviceDescription;
	}
	public Double getSubserviceCost() {
		return subserviceCost;
	}
	public void setSubserviceCost(Double subserviceCost) {
		this.subserviceCost = subserviceCost;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	

    
}
