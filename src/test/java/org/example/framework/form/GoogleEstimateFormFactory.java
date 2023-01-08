package org.example.framework.form;

import org.example.framework.enums.DatacenterLocations;
import org.example.framework.enums.MachineTypes;
import org.example.framework.enums.OperatingSystems;
import org.example.framework.enums.ProvisioningModels;

import static org.example.framework.test.BaseTest.logger;

public class GoogleEstimateFormFactory {
    private static GoogleEstimateForm estimateForm;

    public static GoogleEstimateForm withEstimatesDefaultFields() {
        estimateForm = new GoogleEstimateForm(
                DatacenterLocations.FRANKFURT.getName(),
                ProvisioningModels.REGULAR.getName(),
                MachineTypes.E2_STANDARD_2.getName(),
                OperatingSystems.FREE.getName());
        logger.info("Created new estimate form with default fields");
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
        logger.info("Created new estimate form with user filled fields");
        return estimateForm;
    }
}
