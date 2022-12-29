package framework.example.org.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProvisioningModels {
    REGULAR("Regular", "regular"),
    PREEMPTIBLE("Preemptible", "preemptible");

    private final String name;
    private final String value;
}
