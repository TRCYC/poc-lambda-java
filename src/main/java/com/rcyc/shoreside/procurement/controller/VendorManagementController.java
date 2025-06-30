package com.rcyc.shoreside.procurement.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.rcyc.shoreside.procurement.model.AribaVendorPayload;
import com.rcyc.shoreside.procurement.model.D365VendorResponse;
import com.rcyc.shoreside.procurement.service.VendorManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VendorManagementController {
    private static final Logger logger = LoggerFactory.getLogger(VendorManagementController.class);
    private final VendorManagementService service;

    public VendorManagementController() {
        this.service = new VendorManagementService();
    }

    public D365VendorResponse process(Object input, Context context) {
        logger.info("VendorManagementController invoked");

        // Create input request
        AribaVendorPayload aribaVendorPayload = new AribaVendorPayload();

        return service.processVendor(aribaVendorPayload);
       
    }
}