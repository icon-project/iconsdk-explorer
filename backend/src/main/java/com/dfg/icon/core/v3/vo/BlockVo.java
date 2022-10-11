package com.dfg.icon.core.v3.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.v2.vo.TransactionVo;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlockVo {
    @JsonProperty("version")
    String version;

    @JsonProperty("prev_block_hash")
    String prevBlockHash;

    @JsonProperty("merkle_tree_root_hash")
    String merkleTreeRootHash;

    @JsonProperty("time_stamp")
    Long timestamp;

    @JsonProperty("confirmed_transaction_list")
    List<TransactionVo> confirmed_transaction_list;
    
    @JsonProperty("height")
    Integer height;

    @JsonProperty("block_hash")
    String blockHash;

    @JsonProperty("peer_id")
    String peerId;

    @JsonProperty("signature")
    String signature;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
