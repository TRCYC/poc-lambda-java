package com.rcyc.shoreside.procurement.util;

import com.rcyc.shoreside.procurement.model.AribaVendorPayload;
import com.rcyc.shoreside.procurement.model.D365VendorRequest;

public class AribaToD365Mapper {
    public D365VendorRequest mapToD365Request(AribaVendorPayload payload) {
        D365VendorRequest request = new D365VendorRequest();
        request.setDataAreaId("300");
        request.setCurrencyCode("USD");
        request.setVendorGroupId("INSURANCE");
        request.setVendorOrganizationName(payload.getSupplierName());
        request.setPrimaryURL("");
        request.setAddressValidTo("2154-12-31T23:59:59Z");
        request.setDefaultPaymentTermsName("NET30");
        request.setDefaultVendorPaymentMethodName("GLOBAL ACH");
        request.setPrimaryEmailAddress(payload.getPrimaryContactEmail());
        request.setPersonFirstName(payload.getPrimaryContactFirstName());
        request.setPersonLastName(payload.getPrimaryContactLastName());
        request.setVendorPartyType("Organization");
        request.setAddressCity(payload.getAddressCity());
        request.setAddressStateId("");
        request.setAddressCountryRegionId(payload.getAddressCountryCode());
        request.setAddressZipCode(payload.getAddressPostalCode());
        request.setNotes("Preferred vendor for electronics.");
        request.setSalesTaxGroupCode("V-DOM");
        request.setWillPurchaseOrderIncludePricesAndAmounts("Yes");
        request.setLanguageId("en-US");
        request.setDefaultPurchaseSiteId("01");
        request.setVendorKnownAsName(payload.getName2());
        return request;
    }
}