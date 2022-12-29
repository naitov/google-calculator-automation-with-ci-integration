package framework.example.org.form;

import framework.example.org.enums.*;

public class GoogleCalculatorFormFactory {
    private static GoogleCalculatorForm form;

    public static GoogleCalculatorForm getCalcFormWithMinimumElements() {
        form = new GoogleCalculatorForm();
        form.setNumberOfInstances(NumberOfInstances.ONE);
        return form;
    }

    public static GoogleCalculatorForm getCalcFormWithAllElements() {
        form = new GoogleCalculatorForm();
        form.setNumberOfInstances(NumberOfInstances.FOUR);
        form.setOperatingSystem(OperatingSystems.FREE);
        form.setProvisioningModel(ProvisioningModels.REGULAR);
        form.setSeries(Series.N1);
        form.setMachineType(MachineTypes.N1_STANDART_8);
        form.setGpuType(GpuTypes.NVIDIA_P4);
        form.setNumberOfGpu(GpuNumbers.ONE_GPU);
        form.setLocalSsd(SsdNumbers.TWO_SSD);
        form.setDatacenterLocation(DatacenterLocations.FRANKFURT);
        form.setCommittedUsage(CommittedUsages.ONE_YEAR);
        return form;
    }

    public static GoogleCalculatorForm getCalcFormWithAllElementsExcludeGpu() {
        form = new GoogleCalculatorForm();
        form.setNumberOfInstances(NumberOfInstances.FOUR);
        form.setOperatingSystem(OperatingSystems.FREE);
        form.setProvisioningModel(ProvisioningModels.REGULAR);
        form.setSeries(Series.E2);
        form.setMachineType(MachineTypes.E2_STANDART_2);
        form.setLocalSsd(SsdNumbers.ONE_SSD);
        form.setDatacenterLocation(DatacenterLocations.WARSAW);
        form.setCommittedUsage(CommittedUsages.THREE_YEARS);
        return form;
    }
}
