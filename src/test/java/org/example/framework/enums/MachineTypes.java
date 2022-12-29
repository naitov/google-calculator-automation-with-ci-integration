package framework.example.org.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MachineTypes {
    N1_STANDART_1("n1-standart-1", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-1"),
    N1_STANDART_8("n1-standart-8", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8"),
    E2_STANDART_2("e2-standart-2", "CP-COMPUTEENGINE-VMIMAGE-E2-STANDARD-2");

    private final String name;
    private final String value;
}
