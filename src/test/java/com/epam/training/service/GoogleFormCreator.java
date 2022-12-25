package com.epam.training.service;

import com.epam.training.form.GoogleCalculatorForm;

public class GoogleFormCreator {
    public static GoogleCalculatorForm withMinimumElements() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(FormXpathStorage.Instances.ONE.value);
        form.setSeriesXpath(FormXpathStorage.Series.N1.value);
        form.setMachineTypeXpath(FormXpathStorage.MachineTypes.N1_STANDART_8.value);
        return form;
    }

    public static GoogleCalculatorForm withAllElements() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(FormXpathStorage.Instances.FOUR.value);
        form.setOperatingSystemXpath(FormXpathStorage.OperatingSystems.FREE.value);
        form.setProvisioningModelXpath(FormXpathStorage.ProvisioningModels.REGULAR.value);
        form.setSeriesXpath(FormXpathStorage.Series.N1.value);
        form.setMachineTypeXpath(FormXpathStorage.MachineTypes.N1_STANDART_8.value);
        form.setGpuTypeXpath(FormXpathStorage.GpuTypes.NVIDIA_P4.value);
        form.setNumberOfGpuXpath(FormXpathStorage.GpuNumbers.ONE_GPU.value);
        form.setLocalSsdXpath(FormXpathStorage.SsdNumbers.TWO_SSD.value);
        form.setDatacenterLocationXpath(FormXpathStorage.DatacenterLocations.FRANKFURT.value);
        form.setCommittedUsageXpath(FormXpathStorage.CommittedUsages.ONE_YEAR.value);
        return form;
    }

    public static GoogleCalculatorForm withAllElementsExcludeGpu() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(FormXpathStorage.Instances.FOUR.value);
        form.setOperatingSystemXpath(FormXpathStorage.OperatingSystems.FREE.value);
        form.setProvisioningModelXpath(FormXpathStorage.ProvisioningModels.REGULAR.value);
        form.setSeriesXpath(FormXpathStorage.Series.E2.value);
        form.setMachineTypeXpath(FormXpathStorage.MachineTypes.E2_STANDART_2.value);
        form.setLocalSsdXpath(FormXpathStorage.SsdNumbers.ONE_SSD.value);
        form.setDatacenterLocationXpath(FormXpathStorage.DatacenterLocations.WARSAW.value);
        form.setCommittedUsageXpath(FormXpathStorage.CommittedUsages.THREE_YEARS.value);
        return form;
    }
}
