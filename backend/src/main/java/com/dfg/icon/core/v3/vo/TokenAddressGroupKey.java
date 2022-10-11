package com.dfg.icon.core.v3.vo;

import lombok.Data;

@Data
public class TokenAddressGroupKey {
    String tokenAddr;

    String contractAddr;

    Integer decimals;

    public TokenAddressGroupKey() {}

    public TokenAddressGroupKey(String tokenAddr, String contractAddr, Integer decimals) {
        this.tokenAddr = tokenAddr;
        this.contractAddr = contractAddr;
        this.decimals = decimals;
    }
}
