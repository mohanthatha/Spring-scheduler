
package com.multitenant.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.multitenant.model.City;

@FeignClient(name = "city", url = "http://localhost:8080")
public interface FeignServiceUtil {

	@GetMapping("/getcity/all")
	List<City> getAll(@RequestHeader(name="X-TenantID") String XTenantId);
	
	@PostMapping(value = "/createcity" , consumes= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<HttpStatus> save(@RequestBody City city,@RequestHeader(name="X-TenantID") String XTenantId);

}
