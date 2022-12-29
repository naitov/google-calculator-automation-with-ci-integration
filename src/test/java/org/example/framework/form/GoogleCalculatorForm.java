package framework.example.org.form;

import framework.example.org.enums.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleCalculatorForm {
    private NumberOfInstances numberOfInstances;
    private OperatingSystems operatingSystem;
    private ProvisioningModels provisioningModel;
    private Series series;
    private MachineTypes machineType;
    private GpuTypes gpuType;
    private GpuNumbers numberOfGpu;
    private SsdNumbers localSsd;
    private DatacenterLocations datacenterLocation;
    private CommittedUsages committedUsage;
}
