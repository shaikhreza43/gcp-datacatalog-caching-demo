/**
 * 
 */
package com.gcp.datacatalog.caching.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.datacatalog.caching.demo.common.Document;
import com.gcp.datacatalog.caching.demo.service.DataCatalogService;

/**
 * @author Shaikh Ahmed Reza
 *
 */
@RestController
@RequestMapping("/api/v1/gcp")
public class DataCatalogController {

	@Autowired
	private DataCatalogService dataCatalogService;
	
	@Autowired
	private StringRedisTemplate template;

	@GetMapping("/welcome")
	public String sayHello() {
		return "Hello! Welcome to Gcp with Spring Boot";
	}

	@GetMapping("/fetch-dc-entries")
//	@Cacheable(key = "#datacatalogentrykey",value = "datacatalogentryvalue",cacheManager = "datacatalogCM")
	public ResponseEntity<Document> getDataFromDataCatalog() {

		Document response = dataCatalogService.getDataFromDataCatalog();

		return ResponseEntity.status(response.getStatusCode()).body(response);

	}
}
