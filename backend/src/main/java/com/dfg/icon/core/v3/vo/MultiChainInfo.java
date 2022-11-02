package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultiChainInfo {
    private List<ChainInfo> chainInfos;
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ChainInfo {
        private String name;
        private String host;
        private String api;
        private String channel;
        private String version;
    }

}
