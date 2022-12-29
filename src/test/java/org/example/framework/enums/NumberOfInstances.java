package framework.example.org.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NumberOfInstances {
    ONE("1", "1"),
    FOUR("4", "1");

    private final String name;
    private final String value;
}
