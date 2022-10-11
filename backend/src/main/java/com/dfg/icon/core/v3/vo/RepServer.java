package com.dfg.icon.core.v3.vo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by lyj01702 on 2019-07-30.
 */
@Data
public class RepServer {
    RepLocation location;

    String server_type;

//    String p2p_endpoint;

//    String bnet_endpoint;

    String api_endpoint;

//    String ssl_endpoint;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
