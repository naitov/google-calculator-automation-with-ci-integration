package framework.example.org.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Series {
    N1("N1", "n1"),
    E2("E2", "e2");

    private final String name;
    private final String value;
}