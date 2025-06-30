package com.rcyc.shoreside.procurement.model;

import lombok.Data;

@Data
public class D365VendorRequest {
    private String dataAreaId;
    private String currencyCode;
    private String vendorGroupId;
    private String vendorOrganizationName;
    private String primaryURL;
    private String addressValidTo;
    private String defaultPaymentTermsName;
    private String defaultVendorPaymentMethodName;
    private String primaryEmailAddress;
    private String personFirstName;
    private String personLastName;
    private String vendorPartyType;
    private String addressCity;
    private String addressStateId;
    private String addressCountryRegionId;
    private String addressZipCode;
    private String notes;
    private String salesTaxGroupCode;
    private String willPurchaseOrderIncludePricesAndAmounts;
    private String languageId;
    private String defaultPurchaseSiteId;
    private String vendorKnownAsName;
}