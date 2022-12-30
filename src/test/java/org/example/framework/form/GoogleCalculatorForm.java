package org.example.framework.form;

import lombok.Getter;
import lombok.Setter;
import org.example.framework.enums.*;

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
