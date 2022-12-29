package org.example.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MachineTypes {
    N1_STANDARD_1("n1-standard-1", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-1"),
    N1_STANDARD_8("n1-standard-8", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8"),
    E2_STANDARD_2("e2-standard-2", "CP-COMPUTEENGINE-VMIMAGE-E2-STANDARD-2");

    private final String name;
    private final String value;
}
