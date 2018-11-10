package com.athena.activity.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.athena.activity.config.AuthTokenCache;
import com.athena.activity.config.RestTemplateLoggingInterceptor;
import com.athena.activity.utils.MarketoConstants;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MarketoClient {

	private final RestTemplate rest;
	
	@Value("${developer.marketo.baseUrl}")
    private String baseUrl;
	
	@Value("${developer.marketo.clientId}")
    private String clientId;

	@Value("${developer.marketo.secretId}")
    private String secretId;
	
	@Autowired
	private AuthTokenCache authTokenCache;
	
	public MarketoClient() {
		rest = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        rest.setInterceptors(Collections.singletonList(new RestTemplateLoggingInterceptor()));
	}
	
	private JsonNode post(String url,String requestBody) {
		HttpHeaders headers = getHttpHeaders();
	    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		
	    ResponseEntity<JsonNode> response = rest.exchange(url, HttpMethod.POST, requestEntity, JsonNode.class); 
	     
		return response.getBody();
		
	}

	public JsonNode getIdentity() {
		
		HttpHeaders headers = new HttpHeaders();
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
	    
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + MarketoConstants.IDENTITY_URL)
				.queryParam(MarketoConstants.GRANT_TYPE, MarketoConstants.CLIENT_CREDENTIALS)
				.queryParam(MarketoConstants.CLIENT_ID, clientId)
				.queryParam(MarketoConstants.CLIENT_SECRET, secretId);
		
		ResponseEntity<JsonNode> response = rest.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, JsonNode.class);
		return response.getBody();
	}
	
	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		JsonNode resp = authTokenCache.getIdentityToken();
		
		StringBuilder token = new StringBuilder(resp.get("token_type").textValue())
				.append(" ")
				.append(resp.get("access_token").textValue());
		log.info("header token , {} ",token);
		headers.add("Authorization",token.toString());
		return headers;
	}
}
