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

//	@Bean
//	public RedisCacheConfiguration cacheConfiguration() {
//	    return RedisCacheConfiguration.defaultCacheConfig()
//	      .entryTtl(Duration.ofMinutes(60))
//	      .disableCachingNullValues()
//	      .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//	}

//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		return new JedisConnectionFactory();
//	}
//
//	@Bean
//	RedisTemplate<String, Document> redisTemplate() {
//		RedisTemplate<String, Document> redisTemplate = new RedisTemplate<String, Document>();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		return redisTemplate;
//	}
//
//	@Bean(name = "datacatalogCM")
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("datacatalogentryvalue");
//	}

}
