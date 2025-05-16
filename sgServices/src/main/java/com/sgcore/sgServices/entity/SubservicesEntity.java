package com.sgcore.sgServices.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="subservices")
public class SubservicesEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subserviceId;

    @Column(nullable = false)
    private String subserviceName;

    @Lob
    private byte[] subserviceImage;

    @Column(columnDefinition = "TEXT")
    private String subserviceDescription;

    @Column(nullable = false)
    private Double subserviceCost;

    @ManyToOne
    @JoinColumn(name ="serviceId", nullable = false)
    private ServicesEntity service;

	public Integer getSubserviceId() {
		return subserviceId;
	}

	public void setSubserviceId(Integer subserviceId) {
		this.subserviceId = subserviceId;
	}

	public String getSubserviceName() {
		return subserviceName;
	}

	public void setSubserviceName(String subserviceName) {
		this.subserviceName = subserviceName;
	}

	public byte[] getSubserviceImage() {
		return subserviceImage;
	}

	public void setSubserviceImage(byte[] subserviceImage) {
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

	public ServicesEntity getService() {
		return service;
	}

	public void setService(ServicesEntity service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "SubservicesEntity [subserviceId=" + subserviceId + ", subserviceName=" + subserviceName
				+ ", subserviceImage=" + Arrays.toString(subserviceImage) + ", subserviceDescription="
				+ subserviceDescription + ", subserviceCost=" + subserviceCost + ", service=" + service + "]";
	}
    
    
}
