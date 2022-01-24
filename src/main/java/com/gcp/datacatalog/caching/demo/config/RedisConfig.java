/**
 * 
 */
package com.gcp.datacatalog.caching.demo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Shaikh Ahmed Reza
 *
 */
@Configuration
@EnableCaching
@PropertySource("classpath:application.properties")
public class RedisConfig {

	@Value("${spring.redis.host}")
	String redisHostname;

	@Value("${spring.redis.port}")
	String redisPort;

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
		redisConf.setHostName(redisHostname);
		redisConf.setPort(Integer.parseInt(redisPort));
		return new LettuceConnectionFactory(redisConf);
	}

//	@Bean
//	public RedisCacheConfiguration cacheConfiguration(ObjectMapper objectMapper) {
//		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//				.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
//				.serializeValuesWith(
//						SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)))
//				.entryTtl(Duration.ofSeconds(600)).disableCachingNullValues();
//		return cacheConfig;
//	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(SerializationPair
						.fromSerializer(new JdkSerializationRedisSerializer(getClass().getClassLoader())))
				.entryTtl(Duration.ofSeconds(600)).disableCachingNullValues();
		return cacheConfig;
	}

	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(cacheConfiguration())
				.transactionAware().build();
		return rcm;
	}
}
