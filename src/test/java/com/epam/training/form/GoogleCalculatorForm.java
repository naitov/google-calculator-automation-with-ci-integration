package com.epam.training.form;

import java.util.Objects;

public class GoogleCalculatorForm {
    private int numberOfInstances;
    private String operatingSystemXpath;
    private String provisioningModelXpath;
    private String seriesXpath;
    private String machineTypeXpath;
    private String gpuTypeXpath;
    private String numberOfGpuXpath;
    private String localSsdXpath;
    private String datacenterLocationXpath;
    private String committedUsageXpath;

    public String getOperatingSystemXpath() {
        return operatingSystemXpath;
    }

    public void setOperatingSystemXpath(String operatingSystemXpath) {
        this.operatingSystemXpath = operatingSystemXpath;
    }

    public String getProvisioningModelXpath() {
        return provisioningModelXpath;
    }

    public void setProvisioningModelXpath(String provisioningModelXpath) {
        this.provisioningModelXpath = provisioningModelXpath;
    }

    public String getSeriesXpath() {
        return seriesXpath;
    }

    public void setSeriesXpath(String seriesXpath) {
        this.seriesXpath = seriesXpath;
    }

    public String getMachineTypeXpath() {
        return machineTypeXpath;
    }

    public void setMachineTypeXpath(String machineTypeXpath) {
        this.machineTypeXpath = machineTypeXpath;
    }

    public String getGpuTypeXpath() {
        return gpuTypeXpath;
    }

    public void setGpuTypeXpath(String gpuTypeXpath) {
        this.gpuTypeXpath = gpuTypeXpath;
    }

    public String getNumberOfGpuXpath() {
        return numberOfGpuXpath;
    }

    public void setNumberOfGpuXpath(String numberOfGpuXpath) {
        this.numberOfGpuXpath = numberOfGpuXpath;
    }

    public String getLocalSsdXpath() {
        return localSsdXpath;
    }

    public void setLocalSsdXpath(String localSsdXpath) {
        this.localSsdXpath = localSsdXpath;
    }

    public String getDatacenterLocationXpath() {
        return datacenterLocationXpath;
    }

    public void setDatacenterLocationXpath(String datacenterLocationXpath) {
        this.datacenterLocationXpath = datacenterLocationXpath;
    }

    public String getCommittedUsageXpath() {
        return committedUsageXpath;
    }

    public void setCommittedUsageXpath(String committedUsageXpath) {
        this.committedUsageXpath = committedUsageXpath;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    @Override
    public String toString() {
        return "GoogleCalculatorForm{" +
               "numberOfInstances=" + numberOfInstances +
               ", operatingSystemXpath='" + operatingSystemXpath + '\'' +
               ", provisioningModelXpath='" + provisioningModelXpath + '\'' +
               ", seriesXpath='" + seriesXpath + '\'' +
               ", machineTypeXpath='" + machineTypeXpath + '\'' +
               ", gpuTypeXpath='" + gpuTypeXpath + '\'' +
               ", numberOfGpuXpath='" + numberOfGpuXpath + '\'' +
               ", localSsdXpath='" + localSsdXpath + '\'' +
               ", datacenterLocationXpath='" + datacenterLocationXpath + '\'' +
               ", committedUsageXpath='" + committedUsageXpath + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoogleCalculatorForm that = (GoogleCalculatorForm) o;
        return numberOfInstances == that.numberOfInstances && Objects.equals(operatingSystemXpath, that.operatingSystemXpath) && Objects.equals(provisioningModelXpath, that.provisioningModelXpath) && Objects.equals(seriesXpath, that.seriesXpath) && Objects.equals(machineTypeXpath, that.machineTypeXpath) && Objects.equals(gpuTypeXpath, that.gpuTypeXpath) && Objects.equals(numberOfGpuXpath, that.numberOfGpuXpath) && Objects.equals(localSsdXpath, that.localSsdXpath) && Objects.equals(datacenterLocationXpath, that.datacenterLocationXpath) && Objects.equals(committedUsageXpath, that.committedUsageXpath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, operatingSystemXpath, provisioningModelXpath, seriesXpath, machineTypeXpath, gpuTypeXpath, numberOfGpuXpath, localSsdXpath, datacenterLocationXpath, committedUsageXpath);
    }
}
