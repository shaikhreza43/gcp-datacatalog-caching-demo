package com.gcp.datacatalog.caching.demo.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ImpersonatedCredentials;
import com.google.cloud.datacatalog.v1.DataCatalogSettings;

@Component
public class DataCatalogHelper {

	static String accessToken = "ya29.A0ARrdaM82_JQrFDVG9K6jE1DgkEg9JexxT8cqfXXGZiY3OaV59SVyBu_Ak_Lt__gYkcUzMmhvwAnn3K8G1HRKVD1F3uk9IMyOFrbvGQKkG5sZMrZ626riNvyVVWFOhwMjJcLXrP1_k1Iaelm4mGARg9D9x4ksUA";

	// Connect to data catalog using impersonation-- only applicable for local env

	public static DataCatalogSettings getDataCatalogSettings() throws IOException {

		GoogleCredentials googleCredentials = new GoogleCredentials(
				new AccessToken(accessToken, new Date(new Date().getTime() + 86400000)));

		ImpersonatedCredentials targetCredentials = ImpersonatedCredentials.create(googleCredentials,
				"ahmedreza@prj-ford-amp.iam.gserviceaccount.com", null,
				Arrays.asList("https://www.googleapis.com/auth/cloud-platform"), 300);

		DataCatalogSettings dataCatalogSettings = DataCatalogSettings.newBuilder()
				.setCredentialsProvider(FixedCredentialsProvider.create(targetCredentials)).build();

		return dataCatalogSettings;
	}

}
