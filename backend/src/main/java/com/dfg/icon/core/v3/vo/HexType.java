package com.dfg.icon.core.v3.vo;

import lombok.Getter;

/**
 * hex string type enum
 */
public enum HexType {
    T_HASH("0x", "0x + 64 digit HEX 문자열"),
    T_ADDR_EOA("hx", "hx + 40 digit HEX 문자열"),
    T_ADDR_SCORE("cx", "cx + 40 digit HEX 문자열"),
//    T_INT("0x", "0x + lowercase HEX 문자열"),
//    T_BIN_DATA("0x", "0x + lowercase HEX 짝수 문자열"),
    UNPREFIX("", "Nothing prefix");

    @Getter
    private String prefix;

    @Getter
    private String description;

    private HexType(String prefix, String description) {
        this.prefix = prefix;
        this.description = description;
    }

    public static HexType getType(String hex) {
        if(hex == null || hex.length() < 2) {
            return null;
        }
        String prefix = hex.substring(0, 2);
        for(HexType type : HexType.values()) {
            if(type.getPrefix().equals(prefix)) {
                return type;
            }
        }
        return UNPREFIX;
    }

    public static String toHexType(String hex, HexType type) {
        if(hex == null) {
            return null;
        } else if(hex.length() < 2) {
            return type.getPrefix() + hex;
        }
        String prefix = hex.substring(0,2);
        for(HexType hexType : HexType.values()) {
            // prefix가 붙어있는 경우 치환.
            if(hexType.getPrefix().equals(prefix)) {
                return type.getPrefix() + hex.substring(2);
            }
        }
        // prefix가 없으면 붙임.
        return type.getPrefix() + hex;
    }

    /**
     * DB에 입력하는 포멧으로 변경반환
     *  - hash, txHash : 0x제거? 첨부?
     *  - contract address : cx첨부?
     *  - address : hx첨부(돼있음)
     */
    public static String toDBformatString(String hex) {
        if(hex == null) {
            return null;
        }
        if(hex.length() < 2) {
            return hex;
        }
        String prefix = hex.substring(0, 2);
        for(HexType hexType : HexType.values()) {
            if(hexType.getPrefix().equals(prefix)) {
                return hex;
            }
        }
        return "0x" + hex;
    }
}
