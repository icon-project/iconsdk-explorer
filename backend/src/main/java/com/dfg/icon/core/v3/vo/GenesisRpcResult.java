package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class GenesisRpcResult {
    @JsonProperty("version")
    String version;

    @JsonProperty("prev_block_hash")
    String prevBlockHash;

    @JsonProperty("merkle_tree_root_hash")
    String merkleTreeRootHash;

    @JsonProperty("time_stamp")
    Long timestamp;

    @JsonProperty("confirmed_transaction_list")
    List<GenesisTransactionVo> confrimedTransactionList;

    @JsonProperty("block_hash")
    String blockHash;

    @JsonProperty("height")
    Integer height;

    @JsonProperty("peer_id")
    String peerId;

    @JsonProperty("signature")
    String signature;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
