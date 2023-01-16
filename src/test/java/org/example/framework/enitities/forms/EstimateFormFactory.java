package org.example.framework.enitities.forms;

import org.example.framework.enitities.enums.DatacenterLocations;
import org.example.framework.enitities.enums.MachineTypes;
import org.example.framework.enitities.enums.OperatingSystems;
import org.example.framework.enitities.enums.ProvisioningModels;

import static org.example.framework.utils.Logger.LOGGER;

public class EstimateFormFactory {
    private static GoogleEstimateForm estimateForm;

    public static GoogleEstimateForm getEstimatesDefaultFields() {
        estimateForm = new GoogleEstimateForm(
                DatacenterLocations.FRANKFURT.getName(),
                ProvisioningModels.REGULAR.getName(),
                MachineTypes.E2_STANDARD_2.getName(),
                OperatingSystems.FREE.getName());
        LOGGER.info("Created new estimate form with default fields");
        return estimateForm;
    }

    public static GoogleEstimateForm getUserFilledFields(GoogleCalculatorForm form) {
        estimateForm = new GoogleEstimateForm(
                form.getDatacenterLocation().getName(),
                form.getCommittedUsage().getName(),
                form.getProvisioningModel().getName(),
                form.getMachineType().getName(),
                form.getOperatingSystem().getName(),
                form.getNumberOfGpu().getName(),
                form.getGpuType().getName(),
                form.getLocalSsd().getName());
        LOGGER.info("Created new estimate form with user filled fields");
        return estimateForm;
    }
}
