package com.dfg.icon.core.v2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BlockDataJsonVo {
    @JsonProperty("prev_block_hash")
    String prevBlockHash;

    @JsonProperty("height")
    String height;

    @JsonProperty("block_hash")
    String blockHash;
}
