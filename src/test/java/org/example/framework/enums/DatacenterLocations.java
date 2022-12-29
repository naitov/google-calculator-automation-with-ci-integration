package org.example.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatacenterLocations {
    FRANKFURT("Frankfurt", "select_option_228"),
    WARSAW("Warsaw", "select_option_229"),
    IOWA("Iowa", "select_option_217");

    private final String name;
    private final String value;
}
