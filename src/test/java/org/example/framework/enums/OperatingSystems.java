package framework.example.org.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperatingSystems {
    FREE("Free", "free"),
    UBUNTU_PRO("Paid", "//md-option[@value='ubuntu-pro']"),
    WINDOWS("Paid", "//md-option[@value='win']");

    final String value;
    private final String name;
}
