/**
 * 
 */
package com.gcp.datacatalog.caching.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gcp.datacatalog.caching.demo.common.Document;
import com.google.cloud.datacatalog.v1.DataCatalogClient;
import com.google.cloud.datacatalog.v1.DataCatalogClient.SearchCatalogPagedResponse;
import com.google.cloud.datacatalog.v1.Entry;
import com.google.cloud.datacatalog.v1.EntryGroup;
import com.google.cloud.datacatalog.v1.EntryGroupName;
import com.google.cloud.datacatalog.v1.EntryName;
import com.google.cloud.datacatalog.v1.SearchCatalogRequest;
import com.google.cloud.datacatalog.v1.SearchCatalogResult;

/**
 * @author Shaikh Ahmed Reza
 *
 */
@Service
public class DataCatalogServiceImpl implements DataCatalogService {

	String projectId = "prj-ford-amp";
	String location = "us-central-1";
	String entryGroupId = "ahmed_test_enter_group";
	String entryId = "employee_fileset";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Cacheable(cacheNames = "datacatalogentry")
//	@Cacheable(value = "datacatalogentryvalue",cacheManager = "datacatalogCM")
	public Document getDataFromDataCatalog() {

		Document doc = new Document<>();

		try {

//			EntryName entryName = EntryName.of(projectId,location,entryGroupId,entryId);
//			Entry entry = getEntry(entryName);

			List<String> listOfEntries = new ArrayList<>();

			DataCatalogClient client = DataCatalogClient.create();

			SearchCatalogRequest.Scope scope = SearchCatalogRequest.Scope.newBuilder()
					.addAllIncludeProjectIds(Arrays.asList("prj-ford-amp")).build();

			SearchCatalogPagedResponse searchCatalogPagedResponse = client.searchCatalog(scope, entryGroupId);

			for (SearchCatalogResult searchCatalogResult : searchCatalogPagedResponse.iterateAll()) {

				String relativeResourceName = searchCatalogResult.getRelativeResourceName();
				listOfEntries.add(relativeResourceName);
			}

//			EntryGroupName entryGroupName = EntryGroupName.of(projectId, location, entryGroupId);
//			EntryGroup entryGroup = getEntryGroup(entryGroupName);

//			Map<String, List<String>> map = new HashMap<>();
//
//			map.put("datacatalogKey", listOfEntries);

			doc.setData(listOfEntries);
			doc.setMessage("Entry Group retrieved successfully");
			doc.setStatusCode(200);

		} catch (Exception ex) {
			doc.setData("Request Processing Failed");
			doc.setMessage(ex.getLocalizedMessage());
			doc.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		return doc;

	}

	@Cacheable(value = "datacatalogentrygroup")
	public static EntryGroup getEntryGroup(EntryGroupName entryGroupName) throws IOException {
		// Initialize client that will be used to send requests. This client only needs
		// to be created
		// once, and can be reused for multiple requests. After completing all of your
		// requests, call
		// the "close" method on the client to safely clean up any remaining background
		// resources.
		try (DataCatalogClient client = DataCatalogClient.create()) {
			EntryGroup entryGroup = client.getEntryGroup(entryGroupName);
			System.out.println("Entry group retrieved successfully: " + entryGroup.getName());
			return entryGroup;
		}
	}

	@Cacheable(value = "datacatalogentry")
	public static Entry getEntry(EntryName entryName) throws IOException {
		// Initialize client that will be used to send requests. This client only needs
		// to be created
		// once, and can be reused for multiple requests. After completing all of your
		// requests, call
		// the "close" method on the client to safely clean up any remaining background
		// resources.
		System.err.println(entryName);
		try (DataCatalogClient client = DataCatalogClient.create()) {
			Entry entry = client.getEntry(entryName);
			System.out.println("Entry retrieved successfully: " + entry.getName());
			return entry;
		}
	}

}
