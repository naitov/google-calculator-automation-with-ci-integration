package com.epam.training.service;

public class FormXpathStorage {
    enum Instances {
        ONE(1),
        FOUR(4);
        final int value;

        Instances(int value) {
            this.value = value;
        }
    }

    enum OperatingSystems {
        FREE("//md-option[@value='free']"),
        UBUNTU_PRO("//md-option[@value='ubuntu-pro']"),
        WINDOWS("//md-option[@value='win']");
        final String value;

        OperatingSystems(String value) {
            this.value = value;
        }
    }

    enum ProvisioningModels {
        REGULAR("//md-option[@value='regular']"),
        PREEMPTIBLE("//md-option[@value='preemptible']");
        final String value;

        ProvisioningModels(String value) {
            this.value = value;
        }
    }

    enum Series {
        N1("//md-option[@value='n1']"),
        E2("//md-option[@value='e2']");
        final String value;

        Series(String value) {
            this.value = value;
        }
    }

    enum MachineTypes {
        N1_STANDART_1("//*[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-1']"),
        N1_STANDART_8("//*[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']"),
        E2_STANDART_2("//*[@value='CP-COMPUTEENGINE-VMIMAGE-E2-STANDARD-2']");
        final String value;

        MachineTypes(String value) {
            this.value = value;
        }
    }

    enum GpuTypes {
        NVIDIA_P4("//*[@value='NVIDIA_TESLA_P4']"),
        NVIDIA_V100("//*[@value='NVIDIA_TESLA_V100']");
        final String value;

        GpuTypes(String value) {
            this.value = value;
        }
    }

    enum GpuNumbers {
        ONE_GPU("//*[@value='1'][@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]']"),
        TWO_GPU("//*[@value='2'][@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]']");
        final String value;

        GpuNumbers(String value) {
            this.value = value;
        }
    }

    enum SsdNumbers {
        ONE_SSD("//*[@value='1'][@ng-repeat='item in listingCtrl.dynamicSsd.computeServer']"),
        TWO_SSD("//*[@value='2'][@ng-repeat='item in listingCtrl.dynamicSsd.computeServer']");
        final String value;

        SsdNumbers(String value) {
            this.value = value;
        }
    }

    enum DatacenterLocations {
        FRANKFURT("//*[@id='select_option_228']"),
        WARSAW("//*[@id='select_option_229']");
        final String value;

        DatacenterLocations(String value) {
            this.value = value;
        }
    }

    enum CommittedUsages {
        ONE_YEAR("//*[@id='select_option_128']"),
        THREE_YEARS("//*[@id='select_option_129']");
        final String value;

        CommittedUsages(String value) {
            this.value = value;
        }
    }
}
