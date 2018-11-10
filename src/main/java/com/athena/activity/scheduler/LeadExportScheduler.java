package com.athena.activity.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeadExportScheduler extends BaseScheduler{

	@Scheduled(fixedRate=1000*60*60)
	@Override
	public void run() {
		log.info("Running LeadExportScheduler");
	}

}
