package com.example.shortlink.project.common.enums;

import lombok.Getter;

public enum ValidDateTypeEnum {
    PERMANENT(0),
    CUSTOM(1);

    @Getter
    private final int type;

    ValidDateTypeEnum(int type){
        this.type = type;
    }
}
