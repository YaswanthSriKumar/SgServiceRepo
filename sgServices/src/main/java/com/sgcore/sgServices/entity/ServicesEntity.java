package com.sgcore.sgServices.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="services")
public class ServicesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	private String serviceName;
	@Lob
	private byte[] serviceImage;
	@Column(name="service_discription")
	private String serviceDescription;
	
	private String serviceType;
	
	private boolean serviceShow;
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public byte[] getServiceImage() {
		return serviceImage;
	}
	public void setServiceImage(byte[] serviceImage) {
		this.serviceImage = serviceImage;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public boolean isServiceShow() {
		return serviceShow;
	}
	public void setServiceShow(boolean serviceShow) {
		this.serviceShow = serviceShow;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	@Override
	public String toString() {
		return "ServicesEntity [serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceImage="
				+ Arrays.toString(serviceImage) + ", serviceDescription=" + serviceDescription + ", serviceType="
				+ serviceType + ", serviceShow=" + serviceShow + "]";
	}
	
	
	
}
