package com.athena.activity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/marketo")
@Slf4j
public class MarketoAPIController {

	@PostMapping("/uploadLeadData")
	public ResponseEntity<ObjectNode> uploadLeadData(@RequestParam("file") MultipartFile file){
		log.info("Start uploadLeadData");
		return null;	
	}

}
