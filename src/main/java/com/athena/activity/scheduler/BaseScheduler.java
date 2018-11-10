package com.athena.activity.scheduler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class BaseScheduler {

	private String createJobUrl;
	private String startJobUrl;
	private String pollStatusJobUrl;
	private String retrieveDataUrl;
	
	public abstract void run();
	
	public void createExportJob() {
		
	}
	
	public void startExportJob() {
		
	}
	
	public void pollingExportJobStatus() {
		
	}
	
	public void retrieveExportData() {
		
	}
}
