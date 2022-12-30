package org.example.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NumberOfInstances {
    ONE("1", "1"),
    FOUR("4", "4");

    private final String name;
    private final String value;
}
