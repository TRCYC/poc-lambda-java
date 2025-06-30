package com.rcyc.shoreside.procurement.model;

import lombok.Data;

@Data
public class D365VendorResponse {
    private String vendorAccountNumber;
    private String status;
    private String message;
}