package com.gcp.datacatalog.caching.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class GcpDatacatalogCachingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpDatacatalogCachingDemoApplication.class, args);
	}

	@Bean(name = "datacatalogCM")
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("datacatalogentry");
	}

}
