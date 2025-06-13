package com.sgcore.sgServices.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sgcore.sgServices.Dto.ServiceDto;
import com.sgcore.sgServices.Dto.SubserviceDto;
import com.sgcore.sgServices.service.AddService;

import jakarta.ws.rs.PathParam;


@RestController
public class Controller {

	@Autowired
	AddService addService;
	
	@PostMapping("/uploadservice")
	public ResponseEntity<String> uploadService( @RequestParam("serviceName") String serviceName,
	        @RequestParam("serviceDescription") String serviceDescription,
	        @RequestParam("serviceShow") Boolean serviceShow,
	        @RequestParam("serviceType") String serviceType,
	        @RequestParam("serviceImage") MultipartFile serviceImage) throws IOException
	{
		System.out.println("got in add");
		System.out.println("service name"+serviceName);
		System.out.println("service image"+serviceImage);
		return addService.addService(serviceName,serviceDescription,serviceShow,serviceImage,serviceType);
		
	}
	
	@GetMapping("/getServices")
	public ResponseEntity<List<ServiceDto>> getServices()
	{
		System.out.println("getall services");
		return addService.getServices();
	}
	@GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
    	System.out.println("getting image");
        return addService.findById(id);
    }
    @GetMapping("/getSelected")
    public ResponseEntity<List<ServiceDto>> getSelectedServices()
    {
    	return addService.getSelectedServices();
    }
    @PutMapping("/updateShow/{id}")
    public ResponseEntity<String> updateShow(@PathVariable int id, @RequestBody ServiceDto servicesDto )
    {
    	System.out.println("got into update");
    	System.out.println("in controller"+servicesDto.isServiceShow());
    	return addService.updateShow(id,servicesDto);
    }
    @PutMapping("/updateservice")
	public ResponseEntity<String> updateService( @RequestParam("serviceId")int serviceId, @RequestParam("serviceName") String serviceName,
	        @RequestParam("serviceDescription") String serviceDescription,
	        @RequestParam("serviceShow") Boolean serviceShow,
	        @RequestParam("serviceImage") MultipartFile serviceImage,
	        @RequestParam("serviceType")String serviceType) throws IOException
	{
		System.out.println("got in update");
		System.out.println("service id"+serviceId);
		System.out.println("service name"+serviceName);
		System.out.println("service image"+serviceImage);
		return addService.updateService(serviceId,serviceName,serviceDescription,serviceShow,serviceImage,serviceType);
	}
    // get all the service names
    @GetMapping("/ServiceNames")
    public ResponseEntity<List<Object[]>> getServiceNames()
    {
		return addService.getServiceNames();
    }
    
    @DeleteMapping("/deleteSerices/{serviceIds}")
    public ResponseEntity<String> deleteService(@PathVariable List<Integer>serviceIds)
    {
    	return addService.deleteServices(serviceIds);
    }
    
    // methods for subservices_________________________________________________
    @PostMapping("/uploadsubservice")
    public ResponseEntity<String> uploadSubService(
    		 @RequestParam("subserviceId") Integer subserviceId,
    	    @RequestParam("subserviceName") String serviceName,
    	    @RequestParam("subserviceDescription") String serviceDescription,
    	    @RequestParam("subserviceCost") Double subserviceCost,
    	    @RequestParam("serviceId") Integer serviceId,
    	    @RequestParam("subserviceImage") MultipartFile serviceImage) throws IOException {

    	    System.out.println("Received request to add service");
    	    return addService.uploadSubService(subserviceId, serviceName,serviceDescription,subserviceCost,serviceImage,serviceId);
    	    
//            return ResponseEntity.status(HttpStatus.OK).body(serviceName+serviceDescription+subserviceCost+serviceId);

    	    }
    @GetMapping("/getsubservices")
	public ResponseEntity<List<SubserviceDto>> getSubServices()
	{
		System.out.println("getall services");
		return addService.getSubServices();
	}
    @GetMapping("/getsubservices/{id}")
	public ResponseEntity<List<SubserviceDto>> getSubServicesById(@PathVariable int id)
    {
    	System.out.println("inside getting some");
    	return addService.getSubServicesById(id);
    }
    @GetMapping("/subimage/{id}")
    public ResponseEntity<byte[]> getSubImage(@PathVariable int id) {
    	System.out.println("getting image");
        return addService.findBySubId(id);
    }
    @DeleteMapping("/deleteSubService/{subserviceIds}")
    public ResponseEntity<String> deleteSubService(@PathVariable List<Integer> subserviceIds )
    {
		return addService.deleteSubservicesByIds(subserviceIds);
    	
    }
    
    // methods to upload and delete and get sectors_________________________
    
    @PostMapping("/uploadSector")
    public ResponseEntity<String> uploadServiceSector(@RequestParam String sectorName, @RequestParam MultipartFile sectorImage) throws IOException
    {
		return addService.uploadServiceSector(sectorName,sectorImage);
    	
    }
    
    @GetMapping("/getSectorNames")
    public ResponseEntity<List<String>> getSectorNames()
    {
    	return addService.getSectorNames();
    }
    
    @DeleteMapping("deleteSector/{sectorname}")
    public ResponseEntity<String> deleteSectorName(@PathVariable String sectorname)
    {
		return addService.deleteSector(sectorname);
    	
    }
 
}


