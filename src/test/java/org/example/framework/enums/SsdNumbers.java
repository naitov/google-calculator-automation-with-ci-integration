package org.example.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SsdNumbers {
    ONE_SSD("1x375 GiB", "1"),
    TWO_SSD("2x375 GiB", "2");

    private final String name;
    private final String value;
}
