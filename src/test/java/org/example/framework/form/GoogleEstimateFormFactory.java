package org.example.framework.form;

import org.example.framework.enums.DatacenterLocations;
import org.example.framework.enums.MachineTypes;
import org.example.framework.enums.OperatingSystems;
import org.example.framework.enums.ProvisioningModels;

public class GoogleEstimateFormFactory {
    private static GoogleEstimateForm estimateForm;

    public static GoogleEstimateForm withDefaultFields() {
        estimateForm = new GoogleEstimateForm(
                DatacenterLocations.IOWA.getName(),
                ProvisioningModels.REGULAR.getName(),
                MachineTypes.E2_STANDARD_2.getName(),
                OperatingSystems.FREE.getName());
        return estimateForm;
    }

    public static GoogleEstimateForm withUserFilledFields(GoogleCalculatorForm form) {
        estimateForm = new GoogleEstimateForm(
                form.getDatacenterLocation().getName(),
                form.getCommittedUsage().getName(),
                form.getProvisioningModel().getName(),
                form.getMachineType().getName(),
                form.getOperatingSystem().getName(),
                form.getNumberOfGpu().getName(),
                form.getGpuType().getName(),
                form.getLocalSsd().getName());
        return estimateForm;
    }
}