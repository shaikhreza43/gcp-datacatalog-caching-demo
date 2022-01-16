package com.gcp.datacatalog.caching.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GcpDatacatalogCachingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpDatacatalogCachingDemoApplication.class, args);
	}

}
