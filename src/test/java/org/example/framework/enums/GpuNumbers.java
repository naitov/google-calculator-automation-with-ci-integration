package framework.example.org.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GpuNumbers {
    ONE_GPU("1", "1"),
    TWO_GPU("2", "2");

    private final String name;
    private final String value;
}
