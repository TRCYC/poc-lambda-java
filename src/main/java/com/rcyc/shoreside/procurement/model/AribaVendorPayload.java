package com.rcyc.shoreside.procurement.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AribaVendorPayload {
    private String supplierName;
    private String erpVendorId;
    private String smVendorId;
    private String anId;
    private String acmId;
    private String registrationStatus;
    private String qualificationStatus;
    private String integratedToErp;
    private String duplicateSmVendorId;
    private String lastIntegrationState;
    private String lastConfirmationState;
    private String sourceSystem;
    private String masterVendorId;
    private String formOfAddressCode;
    private String name2;
    private String name3;
    private String name4;
    private String dunsId;
    private String industryCode;
    private String recordCreatedDate;
    private String creator;
    private String blockedStatus;
    private String lastReviewDate;
    private String lastUpdateDate;
    private String lastStatusChangeDate;
    private String primarySupplierManager;
    private String relationshipEstablishedType;
    private String alternateSupplierManager;
    private boolean approved;
    private boolean transactionalSupplier;
    private boolean transactionalSupplierRequestStatus;
    private boolean mainVendorType;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressPoBox;
    private String addressCity;
    private String addressCountryCode;
    private String addressRegionCode;
    private String addressPostalCode;
    private String primaryContactFirstName;
    private String primaryContactMiddleName;
    private String primaryContactLastName;
    private String primaryContactEmail;
    private boolean isActive;
    private List<BankInfo> bankInfos;
    private List<TaxInfo> taxInfos;
    private List<CompanyCode> companyCodes;
    private GenericCustomFields genericCustomFields;

    @Data
    public static class BankInfo {
        private String accountName;
        private String accountNumber;
        private String bankAccountExternalID;
        private String bankAccountStandardID;
        private String bankInternalID;
        private String bankAccountType;
        private String bankBranch;
        private String controlKey;
        private String country;
        private Address address;
    }

    @Data
    public static class Address {
        private String line1;
        private String line2;
        private String line3;
        private String poBox;
        private String city;
        private String countryCode;
        private String region;
        private String postalCode;
    }

    @Data
    public static class TaxInfo {
        private String countryCode;
        private String partyTaxID;
        private String taxIdentificationNumberTypeCode;
        private String longPartyTaxID;
    }

    @Data
    public static class CompanyCode {
        private String companyCode;
        private String paymentMethodsCode;
        private String planningGroupCode;
        private String cashDiscountTermsCode;
        private String generalLedgerAccountReferenceID;
        private List<WithholdingTax> withholdingTaxs;
    }

    @Data
    public static class WithholdingTax {
        private boolean subjectToWithholdingTaxIndicator;
        private String withholdingTaxCode;
        private String withholdingTaxTypeCode;
    }

    @Data
    public static class GenericCustomFields {
        private List<SupplierGenericCustomField> supplierGenericCustomField;
        private List<BusinessPartnerGenericCustomField> businessPartnerGenericCustomField;
        private Map<String, List<CompanyCodeGenericCustomField>> companyCodeGenericCustomField;
        private Map<String, List<BankInfoGenericCustomField>> bankInfoGenericCustomField;
    }

    @Data
    public static class SupplierGenericCustomField {
        private String name;
        private String content;
        private boolean active;
    }

    @Data
    public static class BusinessPartnerGenericCustomField {
        private String name;
        private String content;
        private boolean active;
    }

    @Data
    public static class CompanyCodeGenericCustomField {
        private String name;
        private String content;
        private boolean active;
    }

    @Data
    public static class BankInfoGenericCustomField {
        private String name;
        private String content;
        private boolean active;
    }
}