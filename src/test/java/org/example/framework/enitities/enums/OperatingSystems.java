package org.example.framework.enitities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperatingSystems {
    FREE("Free", "free"),
    UBUNTU_PRO("Paid", "ubuntu-pro"),
    WINDOWS("Paid", "win");

    private final String name;
    private final String value;
}
