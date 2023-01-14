package org.example.framework.enitities.forms;

import lombok.Data;
import org.example.framework.enitities.enums.*;

@Data
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
