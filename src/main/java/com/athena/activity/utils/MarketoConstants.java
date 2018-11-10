package com.athena.activity.utils;

public interface MarketoConstants {
	
	String IDENTITY_URL = "/oauth/token";

	String CLIENT_SECRET = "client_secret";

	String CLIENT_ID = "client_id";

	String GRANT_TYPE = "grant_type";
	
	String CLIENT_CREDENTIALS = "client_credentials";
	
	String CREATE_LEAD_EXPORT_JOB_URL = "/bulk/v1/leads/export/create.json";
	String START_LEAD_EXPORT_JOB_URL = "/bulk/v1/leads/export/{exportId}/enqueue.json";
	String POLL_STATUS_LEAD_EXPORT_JOB_URL = "/bulk/v1/leads/export/{exportId}/status.json";
	String RETRIEVE_LEAD_EXPROT_JOB_URL = "/bulk/v1/leads/export/{exportId}/file.json";
}
