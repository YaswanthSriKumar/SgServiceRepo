package com.sgcore.sgServices.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sgcore.sgServices.Dto.ServiceDto;
import com.sgcore.sgServices.Dto.SubserviceDto;
import com.sgcore.sgServices.entity.ServiceSectorEntity;
import com.sgcore.sgServices.entity.ServicesEntity;
import com.sgcore.sgServices.entity.SubservicesEntity;
import com.sgcore.sgServices.repository.ServiceRepo;
import com.sgcore.sgServices.repository.ServiceSectorRepo;
import com.sgcore.sgServices.repository.SubserviceRepo;

@Service
public class AddService {

	@Autowired
	ServiceRepo serviceRepo ;
	
	@Autowired
	SubserviceRepo subserviceRepo;
	
	@Autowired
	ServiceSectorRepo serviceSectorRepo;
	
	public ResponseEntity<String> addService(String name,String description,boolean show,MultipartFile image, String serviceType ) throws IOException
	{	
			ServicesEntity servicesEntity = new ServicesEntity();
			servicesEntity.setServiceName(name);
			servicesEntity.setServiceDescription(description);
			servicesEntity.setServiceShow(show);
			servicesEntity.setServiceType(serviceType);
			if (image != null && !image.isEmpty()) {
	            byte[] imageBytes = image.getBytes(); // Read file as bytes
	            servicesEntity.setServiceImage(imageBytes);
	        } else {
	            return ResponseEntity.badRequest().body("Image path is missing or invalid");
	        }
			Optional<ServicesEntity> result =Optional.of(serviceRepo.save(servicesEntity)) ;
			 if(result.isPresent())
		            return ResponseEntity.status(HttpStatus.CREATED).body("service uploaded successfully");
				else
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload service");
	
	}

	public ResponseEntity<List<ServiceDto>> getServices() {
		Optional<List<ServicesEntity>>  result =Optional.of(serviceRepo.findAll()) ;
		if(result.isPresent())
		{
			 // Convert entities to DTOs with image URLs
		    List<ServiceDto> resultDto = result.get().stream().map(service -> {
		    	ServiceDto dto = new ServiceDto();
		        dto.setServiceId(service.getServiceId());
		        dto.setServiceName(service.getServiceName());
		        dto.setServiceDescription(service.getServiceDescription());
		        dto.setServiceShow(service.isServiceShow());
		        dto.setServiceType(service.getServiceType());
		       
		        dto.setServiceImage("http://localhost:8088/SGSERVICES/image/" + service.getServiceId()); // Construct image URL
		        return dto;
		    }).toList();
		    return ResponseEntity.status(HttpStatus.OK).body(resultDto);
		}
           
	
		else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	public ResponseEntity<byte[]> findById(int id) {
		Optional<ServicesEntity> service = serviceRepo.findById(id);
		
		if(service.isPresent())
		{
			System.out.println("sending ");
			 return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust to IMAGE_PNG or other type if needed
	                .body(service.get().getServiceImage());
		}
		else
		{
			System.out.println("notsending");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(service.get().getServiceImage());
		}
		
	}

	public ResponseEntity<List<ServiceDto>> getSelectedServices() {
		Optional<List<ServicesEntity>>  result =Optional.of(serviceRepo.findByServiceShow(true)) ;
		if(result.isPresent())
		{
			 // Convert entities to DTOs with image URLs
		    List<ServiceDto> servicesDtos = result.get().stream().map(service -> {
		    	ServiceDto dto = new ServiceDto();
		        dto.setServiceId(service.getServiceId());
		        dto.setServiceName(service.getServiceName());
		        dto.setServiceDescription(service.getServiceDescription());
		        dto.setServiceShow(service.isServiceShow());
		        dto.setServiceType(service.getServiceType());
		        dto.setServiceImage("http://localhost:8088/SGSERVICES/image/" + service.getServiceId()); // Construct image URL
		        System.out.println( dto.getServiceImage());
		        return dto;
		    }).toList();
		    return ResponseEntity.status(HttpStatus.CREATED).body(servicesDtos);
		}
           
	
		else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	public ResponseEntity<String> updateShow(int id, ServiceDto servicesDto) {
		Optional<ServicesEntity> result =serviceRepo.findById(id) ;
		if(result.isPresent())
		{
			result.get().setServiceShow(servicesDto.isServiceShow());
			Optional<ServicesEntity>  serviceEntity =Optional.of(serviceRepo.save(result.get()) );
			if (serviceEntity.isPresent())
			{
				 return ResponseEntity.status(HttpStatus.OK).body("update successfull");
			}
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("update fail");
				
			
		}
		
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("record not found");
	}
	public ResponseEntity<String> updateService(int id,String name,String description,boolean show,MultipartFile image, String serviceType ) throws IOException
	{	Optional<ServicesEntity> present=serviceRepo.findById(id);
		
	if(present.isPresent())
	{		System.out.println("yes is present");

		ServicesEntity servicesEntity = new ServicesEntity();
		servicesEntity.setServiceId(id);
		servicesEntity.setServiceName(name);
		servicesEntity.setServiceDescription(description);
		servicesEntity.setServiceType(serviceType);
		servicesEntity.setServiceShow(show);
		if (image != null && !image.isEmpty()) {
            byte[] imageBytes = image.getBytes(); // Read file as bytes
            servicesEntity.setServiceImage(imageBytes);
        } else {
            return ResponseEntity.badRequest().body("Image path is missing or invalid");
        }
		Optional<ServicesEntity> result =Optional.of(serviceRepo.save(servicesEntity)) ;
		 if(result.isPresent())
	            return ResponseEntity.status(HttpStatus.CREATED).body("service uploaded successfully");
			else
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload service");
	}
	else
	{
		System.out.println("no not present");
	}
			
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("service not found");
	}

	public ResponseEntity<List<Object[]>> getServiceNames() {
		Optional<List<Object[]>>listOfServiceNames= Optional.of(serviceRepo.findServiceNames());
		if(listOfServiceNames.isPresent())
		{
            return ResponseEntity.status(HttpStatus.OK).body(listOfServiceNames.get());

		}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(listOfServiceNames.get());
	}
	public ResponseEntity<String> deleteServices(List<Integer>serviceIds) {
		serviceRepo.deleteAllById(serviceIds);
        return ResponseEntity.status(HttpStatus.OK).body("succesfully deleted");
	}

	// sub service methods from here ______________________________________________
	public ResponseEntity<String> uploadSubService(Integer subserviceId,String serviceName, String serviceDescription, Double subserviceCost,
			MultipartFile serviceImage, Integer serviceId) throws IOException {

		System.out.println("it is Subservice id: "+subserviceId);
		Optional<ServicesEntity> result=  Optional.of(serviceRepo.getById(serviceId));
		
		if(result.isPresent())
		{
			
			SubservicesEntity serviceEntity= new SubservicesEntity();
			if(subserviceId !=null&& subserviceRepo.existsById(subserviceId) )
			{
				serviceEntity.setSubserviceId(subserviceId);
			}
			
			serviceEntity.setSubserviceName(serviceName);
			serviceEntity.setSubserviceDescription(serviceDescription);
			serviceEntity.setSubserviceCost(subserviceCost);
			if (serviceImage != null && !serviceImage.isEmpty()) {
	            byte[] imageBytes = serviceImage.getBytes(); // Read file as bytes
	            serviceEntity.setSubserviceImage(imageBytes);
	        } else {
	            return ResponseEntity.badRequest().body("Image path is missing or invalid");
	        }
			ServicesEntity service = new ServicesEntity();
			service.setServiceId(serviceId);
			serviceEntity.setService(service);
			Optional<SubservicesEntity> value =Optional.of(subserviceRepo.save(serviceEntity)) ;
			 if(value.isPresent())
		            return ResponseEntity.status(HttpStatus.CREATED).body("service uploaded successfully");
				else
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload service");
		}
		else
		{
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sector not found");
		}
		
	}

	public ResponseEntity<List<SubserviceDto>> getSubServices() {
		Optional<List<SubservicesEntity>>  result =Optional.of(subserviceRepo.findAll()) ;
		if(result.isPresent())
		{
		List<SubserviceDto> subdto= result.get().stream().map(service->{
			SubserviceDto dto= new SubserviceDto();
			dto.setSubserviceId(service.getSubserviceId());
			dto.setSubserviceName(service.getSubserviceName());
			dto.setSubserviceDescription(service.getSubserviceDescription());
			dto.setSubserviceCost(service.getSubserviceCost());
			dto.setSubserviceImage("http://localhost:8088/SGSERVICES/subimage/"+service.getSubserviceId());
			dto.setServiceName(service.getService().getServiceName());
			return dto;
			
		}).toList();
		return ResponseEntity.status(HttpStatus.OK).body(subdto);
		}
	    else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	public ResponseEntity<List<SubserviceDto>> getSubServicesById(int id) {

		List<SubservicesEntity> result = subserviceRepo.findByService_ServiceId(id);
		if(!result.isEmpty())
		{
		List<SubserviceDto> subdto= result.stream().map(service->{
			SubserviceDto dto= new SubserviceDto();
			dto.setSubserviceId(service.getSubserviceId());
			dto.setSubserviceName(service.getSubserviceName());
			dto.setSubserviceDescription(service.getSubserviceDescription());
			dto.setSubserviceCost(service.getSubserviceCost());
			dto.setSubserviceImage("http://localhost:8088/SGSERVICES/subimage/"+service.getSubserviceId());
			dto.setServiceName(service.getService().getServiceName());
			return dto;
			
		}).toList();
		return ResponseEntity.status(HttpStatus.OK).body(subdto);
		}
	    else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	public ResponseEntity<byte[]> findBySubId(int id) {
		Optional<SubservicesEntity> service = subserviceRepo.findById(id);
		
		if(service.isPresent())
		{
			System.out.println("sending ");
			 return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust to IMAGE_PNG or other type if needed
	                .body(service.get().getSubserviceImage());
		}
		else
		{
			System.out.println("notsending");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(service.get().getSubserviceImage());
		}
		
	}

	public ResponseEntity<String> deleteSubservicesByIds(List<Integer> ids) {
		subserviceRepo.deleteAllById(ids);	
		System.out.println("deleted ids");
		return ResponseEntity.status(HttpStatus.OK).body("deleted succesfully");
	}

	// methods to upload get and update service sectors
	public ResponseEntity<String> uploadServiceSector(String sectorName, MultipartFile sectorImage) throws IOException {
		ServiceSectorEntity sectorEntity= new ServiceSectorEntity();
		sectorEntity.setSectorName(sectorName);
		sectorEntity.setSectorImage(sectorImage.getBytes());
		
		Optional<ServiceSectorEntity> result =Optional.of(serviceSectorRepo.save(sectorEntity)) ;
		if(result.isPresent())
		{
			return ResponseEntity.status(HttpStatus.CREATED).body("uploaded successfully");
		}
		else
		return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("uploading failed");
	}

	public ResponseEntity<List<String>> getSectorNames() {

		Optional<List<String>> result= Optional.ofNullable(serviceSectorRepo.findAllSectorNames());
		
		if(result.isPresent())
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(result.get());
		}
		else
		return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.get());
	}

	public ResponseEntity<String> deleteSector(String sectorname) {
		serviceSectorRepo.deleteById(sectorname);
		return ResponseEntity.status(HttpStatus.OK).body("deleted succesfully");
	}

	

	
	
}
