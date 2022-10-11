package com.dfg.icon.core.v3.vo;

import lombok.Getter;

/**
 * hex string type enum
 */
public enum AddressType {
    Genesis(1, "제네시스 주소"),
    Treasury(2, "수수료 수신 주소"),
    Normal(0, "일반 주소");

    @Getter
    private int nodeType;

    @Getter
    private String description;

    private AddressType(int nodeType, String description) {
        this.nodeType = nodeType;
        this.description = description;
    }

    public static AddressType getType(int nodeType) {
        for(AddressType type : AddressType.values()) {
            if(type.getNodeType() == nodeType) {
                return type;
            }
        }
        return Normal;
    }
}
