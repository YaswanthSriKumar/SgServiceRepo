package com.sgcore.sgServices.Dto;

public class ServiceDto {

	private int serviceId;
	private String serviceName;
	private String serviceImage;
	private String serviceDescription;
	private boolean serviceShow;
	private String serviceType;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}
	
	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int i) {
		this.serviceId = i;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceImage() {
		return serviceImage;
	}
	public void setServiceImage(String serviceImage) {
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
	
	
	
}
