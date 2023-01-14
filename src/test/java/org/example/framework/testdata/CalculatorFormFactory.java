package org.example.framework.testdata;

import org.example.framework.enitities.enums.*;
import org.example.framework.enitities.forms.GoogleCalculatorForm;

import static org.example.framework.utils.Logger.LOGGER;

public class CalculatorFormFactory {
    private static GoogleCalculatorForm form;

    public static GoogleCalculatorForm getCalcFormWithMinimumElements() {
        form = new GoogleCalculatorForm();
        form.setNumberOfInstances(NumberOfInstances.ONE);
        form.setDatacenterLocation(DatacenterLocations.FRANKFURT);
        LOGGER.info("""
                Setting new form:
                Number of instances: 1
                Datacenter location: Frankfurt""");
        return form;
    }

    public static GoogleCalculatorForm getCalcFormWithAllElements() {
        form = new GoogleCalculatorForm();
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
        LOGGER.info("""
                Setting new form:
                Number of instances: 4
                Operating system: Free
                Provisioning model: Regular
                Series: N1
                Machine type: N1 Standard 8
                GPU type: Nvidia p4
                Number of GPU: 1
                Number of SSD: 2
                Datacenter location: Frankfurt
                Committed usage: 1 year""");
        return form;
    }

    public static GoogleCalculatorForm getCalcFormWithAllElementsExcludeGpu() {
        form = new GoogleCalculatorForm();
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
        LOGGER.info("""
                Setting new form:
                Number of instances: 4
                Operating system: Free
                Provisioning model: Regular
                Series: E2
                Machine type: E2 Standard 2
                Number of SSD: 1
                Datacenter location: Warsaw
                Committed usage: 3 years""");
        return form;
    }
}
