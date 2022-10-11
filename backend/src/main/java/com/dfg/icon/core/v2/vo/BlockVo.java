package com.dfg.icon.core.v2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

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
    List<TransactionVo> confrimedTransactionList;

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
