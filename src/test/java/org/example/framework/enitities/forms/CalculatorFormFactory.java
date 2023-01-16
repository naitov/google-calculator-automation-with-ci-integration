package org.example.framework.enitities.forms;

import org.example.framework.enitities.enums.*;

import static org.example.framework.utils.Logger.LOGGER;

public class CalculatorFormFactory {

    public static GoogleCalculatorForm getFormWithMinElements() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(NumberOfInstances.ONE);
        form.setDatacenterLocation(DatacenterLocations.FRANKFURT);
        LOGGER.info(String.format("New form entity with minimum number of elements created: {%s}", form));
        return form;
    }

    public static GoogleCalculatorForm getFormWithAllElements() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(NumberOfInstances.FOUR);
        form.setOperatingSystem(OperatingSystems.FREE);
        form.setProvisioningModel(ProvisioningModels.REGULAR);
        form.setSeries(Series.N1);
        form.setMachineType(MachineTypes.N1_STANDARD_8);
        form.setGpuType(GpuTypes.NVIDIA_P4);
        form.setNumberOfGpu(GpuNumbers.ONE_GPU);
        form.setLocalSsd(SsdNumbers.TWO_SSD);
        form.setDatacenterLocation(DatacenterLocations.FRANKFURT);
        form.setCommittedUsage(CommittedUsages.ONE_YEAR);
        LOGGER.info(String.format("New form entity with maximum number of elements created: {%s}", form));
        return form;
    }

    public static GoogleCalculatorForm getFormWithExtendedElements() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(NumberOfInstances.FOUR);
        form.setOperatingSystem(OperatingSystems.FREE);
        form.setProvisioningModel(ProvisioningModels.REGULAR);
        form.setSeries(Series.E2);
        form.setMachineType(MachineTypes.E2_STANDARD_2);
        form.setGpuType(GpuTypes.WITHOUT_TYPE);
        form.setNumberOfGpu(GpuNumbers.WITHOUT_GPU);
        form.setLocalSsd(SsdNumbers.ONE_SSD);
        form.setDatacenterLocation(DatacenterLocations.WARSAW);
        form.setCommittedUsage(CommittedUsages.THREE_YEARS);
        LOGGER.info(String.format("New form entity with extended number of elements created: {%s}", form));
        return form;
    }
}
