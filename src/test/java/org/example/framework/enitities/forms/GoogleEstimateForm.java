package org.example.framework.enitities.forms;

import lombok.Getter;

@Getter
public class GoogleEstimateForm {
    private final String region;
    private final String provisioningModel;
    private final String instanceType;
    private final String operatingSystem;
    private String commitmentTerm;
    private String gpuDies;
    private String localSsd;

    public GoogleEstimateForm(String region, String provisioningModel, String instanceType, String operatingSystem) {
        this.region = "Region: " + region;
        this.provisioningModel = "Provisioning model: " + provisioningModel;
        this.instanceType = "Instance type: " + instanceType;
        this.operatingSystem = "Operating System / Software: " + operatingSystem;
    }

    public GoogleEstimateForm(String region, String commitmentTerm, String provisioningModel, String instanceType, String operatingSystem, String gpuNumber, String gpuType, String localSsd) {
        this.region = "Region: " + region;
        this.commitmentTerm = "Commitment term: " + commitmentTerm;
        this.provisioningModel = "Provisioning model: " + provisioningModel;
        this.instanceType = "Instance type: " + instanceType;
        this.operatingSystem = "Operating System / Software: " + operatingSystem;
        this.gpuDies = "GPU dies: " + gpuNumber + " " + gpuType;
        this.localSsd = "Local SSD: " + localSsd;
    }
}
