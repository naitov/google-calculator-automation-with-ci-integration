package org.example.framework.enitities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommittedUsages {
    ONE_YEAR("1 Year", "select_option_128"),
    THREE_YEARS("3 Years", "select_option_129");

    private final String name;
    private final String value;

}
