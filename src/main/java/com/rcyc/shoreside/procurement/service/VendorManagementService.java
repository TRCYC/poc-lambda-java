package com.rcyc.shoreside.procurement.service;

import com.rcyc.shoreside.procurement.model.AribaVendorPayload;
import com.rcyc.shoreside.procurement.model.D365VendorResponse;
import com.rcyc.shoreside.procurement.util.D365HttpClient;
import com.rcyc.shoreside.procurement.util.SAPAribaHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;

public class VendorManagementService {
    private static final Logger logger = LoggerFactory.getLogger(VendorManagementService.class);
    private final D365HttpClient d365HttpClient;
    private final SAPAribaHttpClient sapAribaHttpClient;

    public VendorManagementService() {
        this.d365HttpClient = new D365HttpClient();
        this.sapAribaHttpClient = new SAPAribaHttpClient();
    }

    public D365VendorResponse processVendor(AribaVendorPayload payload) {
        logger.info("Processing vendor");

        // Get SAP Ariba Access Token
        String sapAribaAccessToken = sapAribaHttpClient.getAccessToken();

        // Fetch Questionnaires from SAP Ariba
        String questionnaires = sapAribaHttpClient.getQuestionnaires(payload.getSmVendorId(), sapAribaAccessToken);
        logger.info("Questionnaires: {}", questionnaires);

        // Extract necessary variables from questionnaires
        String questionnaireId = extractQuestionnaireId(questionnaires);
        String primaryPhoneNumber = extractPrimaryPhoneNumber(questionnaires);
        String website = extractWebsite(questionnaires);
        String vendorType = extractVendorType(questionnaires);
        String notes = extractNotes(questionnaires);

        // Get Vendor Details from SAP Ariba
        String vendorDetails = sapAribaHttpClient.getVendorDetails(payload, sapAribaAccessToken);
        logger.info("Vendor Details: {}", vendorDetails);

        // Get Vendor Contact Details from SAP Ariba
        String vendorContactDetails = sapAribaHttpClient.getVendorContactDetails(payload, sapAribaAccessToken);
        logger.info("Vendor Contact Details: {}", vendorContactDetails);

        // Get D365 Access Token
        String d365AccessToken = d365HttpClient.getAccessToken();

        // Check if vendor exists in D365
        boolean vendorExists = checkVendorExistsInD365(payload, d365AccessToken);

        D365VendorResponse response;
        if (!vendorExists) {
            // Create Vendor in D365
            response = d365HttpClient.createOrUpdateVendor(payload, d365AccessToken);
            logger.info("Vendor created in D365: {}", response);

            // Additional logic for vendor creation
            handleVendorCreation(response, primaryPhoneNumber, website);
        } else {
            logger.info("Vendor already exists in D365");
            response = new D365VendorResponse(); // Populate with existing vendor data
        }

        return response;
    }

    private String extractQuestionnaireId(String questionnaires) {
        // Logic to extract questionnaireId from questionnaires JSON
        JSONObject json = new JSONObject(questionnaires);
        return json.getJSONArray("questionnaires")
                   .getJSONObject(0)
                   .getString("questionnaireId");
    }

    private String extractPrimaryPhoneNumber(String questionnaires) {
        // Logic to extract primaryPhoneNumber from questionnaires JSON
        JSONObject json = new JSONObject(questionnaires);
        return json.getJSONArray("_embedded")
                   .getJSONObject(0)
                   .getJSONArray("questionAnswerList")
                   .getJSONObject(0)
                   .getJSONObject("questionAnswer")
                   .getString("answer");
    }

    private String extractWebsite(String questionnaires) {
        // Logic to extract website from questionnaires JSON
        JSONObject json = new JSONObject(questionnaires);
        return json.getJSONArray("_embedded")
                   .getJSONObject(0)
                   .getJSONArray("questionAnswerList")
                   .getJSONObject(1)
                   .getJSONObject("questionAnswer")
                   .getString("answer");
    }

    private String extractVendorType(String questionnaires) {
        // Logic to extract vendorType from questionnaires JSON
        JSONObject json = new JSONObject(questionnaires);
        return json.getJSONArray("_embedded")
                   .getJSONObject(0)
                   .getJSONArray("questionAnswerList")
                   .getJSONObject(2)
                   .getJSONObject("questionAnswer")
                   .getString("answer");
    }

    private String extractNotes(String questionnaires) {
        // Logic to extract notes from questionnaires JSON
        JSONObject json = new JSONObject(questionnaires);
        return json.getJSONArray("_embedded")
                   .getJSONObject(0)
                   .getJSONArray("questionAnswerList")
                   .getJSONObject(3)
                   .getJSONObject("questionAnswer")
                   .getString("answer");
    }

    private boolean checkVendorExistsInD365(AribaVendorPayload payload, String d365AccessToken) {
        // Logic to check if vendor exists in D365
        String vendorData = d365HttpClient.getVendorData(payload.getSmVendorId(), d365AccessToken);
        return vendorData != null && !vendorData.isEmpty();
    }

    private void handleVendorCreation(D365VendorResponse response, String primaryPhoneNumber, String website) {
        // Logic to handle additional steps after vendor creation
        logger.info("Handling additional steps for vendor creation");
        // Example: Publish to a message queue or update a database
    }
}