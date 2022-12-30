package org.example.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GpuNumbers {
    ONE_GPU("1", "1"),
    TWO_GPU("2", "2"),
    WITHOUT_GPU("0", "0");

    private final String name;
    private final String value;
}
