package com.rcyc.shoreside.procurement.util;

import com.rcyc.Env;
import com.rcyc.shoreside.procurement.model.AribaVendorPayload;
import com.rcyc.shoreside.procurement.model.D365VendorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class D365HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(D365HttpClient.class);

    public String getAccessToken() {
        logger.info("Requesting D365 Access Token");
        // Implement the logic to get D365 access token
        return "d365_access_token";
    }

    public String getVendorData(String smVendorId, String accessToken) {
        logger.info("Checking if vendor exists in D365 for Vendor ID: {}", smVendorId);
        try {
            String path = Env.get("D365_VENDOR") + "?$filter=VendorAccountNumber eq '" + smVendorId + "'";
            URI uri = new URI("https", Env.get("RECURRING_IMPORT_DYNAMICS_HOST"), path, null);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Accept", "application/json");

            try (InputStream is = connection.getInputStream()) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error("Failed to check vendor data in D365", e);
            return null;
        }
    }

    public D365VendorResponse createOrUpdateVendor(AribaVendorPayload payload, String accessToken) {
        logger.info("Creating or Updating Vendor in D365");
        // Implement the logic to create or update vendor in D365
        return new D365VendorResponse();
    }
}