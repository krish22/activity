package com.athena.activity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.athena.activity.service.MarketoClient;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthTokenCache {

	@Autowired
	private MarketoClient marketoClient;
	
	@Cacheable(value="identity")
	public JsonNode getIdentityToken() {
		log.info("Calling getIdentityToken and caching");
		return marketoClient.getIdentity();
	}
	
	@CacheEvict(value="identity",allEntries = true)
	public void removeIdentityFromCache() { log.info("Identity token is expired and removing it from cache");}
}
