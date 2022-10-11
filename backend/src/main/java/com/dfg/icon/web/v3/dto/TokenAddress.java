package com.dfg.icon.web.v3.dto;

import lombok.Data;

/**
 * tokenAddress dto
 * @author bsm
 *
 */
@Data
public class TokenAddress {
    String contractAddr;

    String contractName;

    String contractSymbol;

    String quantity;
    
    String totalTokenPrice;
    
    String tokenPrice;
    
}
