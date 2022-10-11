package com.dfg.icon.core.v3.vo;

import lombok.Getter;

/**
 * hex string type enum
 */
public enum DecimalType {
    LOOP("loop", 0),
    KWEI("kwei", 3),
    MWEI("mwei", 6),
    GWEI("gwei", 9),
    SZABO("szabo", 12),
    FINNEY("finney", 15),
    ETHER("ether", 18),
    ICX("icon", 18),
    KETHER("kether", 21),
    METHER("mether", 24),
    GETHER("gether", 27);

    @Getter
    private String type;

    @Getter
    private Integer value;

    private DecimalType(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public static DecimalType getType(String type) {
        if(type == null) {
            return null;
        }
        for(DecimalType d : DecimalType.values()) {
            if(d.getType().equals(type)) {
                return d;
            }
        }
        return null;
    }
}
