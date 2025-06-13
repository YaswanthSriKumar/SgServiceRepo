package com.sgcore.sgServices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="serviceSector")
public class ServiceSectorEntity {

	@Id
	private String sectorName;
	private byte[] sectorImage;
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public byte[] getSectorImage() {
		return sectorImage;
	}
	public void setSectorImage(byte[] sectorImage) {
		this.sectorImage = sectorImage;
	}
	
	
}
