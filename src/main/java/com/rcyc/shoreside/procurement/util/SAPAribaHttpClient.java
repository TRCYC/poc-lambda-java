package com.rcyc.shoreside.procurement.util;

import com.rcyc.shoreside.procurement.model.AribaVendorPayload;
import com.rcyc.Env;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SAPAribaHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(SAPAribaHttpClient.class);

    public String getAccessToken() {
        logger.info("Requesting SAP Ariba Access Token");
        try {
            URL url = new URI("https://" + Env.get("SAPARIBA_TOKEN_HOST") + Env.get("SAPARIBA_TOKEN_PATH")).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            String body = "grant_type=" + Env.get("SAPARIBA_TOKEN_GRANT_TYPE") +
                          "&client_id=" + Env.get("SAPARIBA_VENDOR_TOKEN_CLIENT_ID") +
                          "&client_secret=" + Env.get("SAPARIBA_VENDOR_TOKEN_CLIENT_SECRET");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (InputStream is = connection.getInputStream()) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error("Failed to get SAP Ariba Access Token", e);
            return null;
        }
    }

    public String getQuestionnaires(String smVendorId, String accessToken) {
        logger.info("Fetching Questionnaires from SAP Ariba for Vendor ID: {}", smVendorId);
        try {
            String path = Env.get("SAPARIBA_QUESTIONNAIRES").replace("{smVendorId}", smVendorId);
            URI uri = new URI("https", Env.get("SAPARIBA_VENDOR_HOST"), path, null);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("apiKey", Env.get("SAPARIBA_VENDOR_API_KEY"));
            connection.setRequestProperty("Accept", "application/json");

            try (InputStream is = connection.getInputStream()) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error("Failed to fetch Questionnaires from SAP Ariba", e);
            return null;
        }
    }
    
    public String getVendorDetails(AribaVendorPayload payload, String accessToken) {
        logger.info("Fetching Vendor Details from SAP Ariba");
        try {
            URI uri = new URI("https", Env.get("SAPARIBA_VENDOR_HOST"), Env.get("SAPARIBA_VENDOR_DETAILS_PATH"), null);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("apiKey", Env.get("SAPARIBA_VENDOR_API_KEY"));
            connection.setRequestProperty("Accept", "application/json");

            try (InputStream is = connection.getInputStream()) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error("Failed to fetch Vendor Details from SAP Ariba", e);
            return null;
        }
    }

    public String getVendorContactDetails(AribaVendorPayload payload, String accessToken) {
        logger.info("Fetching Vendor Contact Details from SAP Ariba");
        try {
            URI uri = new URI("https", Env.get("SAPARIBA_VENDOR_HOST"), Env.get("SAPARIBA_VENDOR_CONTACTS_PATH"), null);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("apiKey", Env.get("SAPARIBA_VENDOR_API_KEY"));
            connection.setRequestProperty("Accept", "application/json");

            try (InputStream is = connection.getInputStream()) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error("Failed to fetch Vendor Contact Details from SAP Ariba", e);
            return null;
        }
    }
}