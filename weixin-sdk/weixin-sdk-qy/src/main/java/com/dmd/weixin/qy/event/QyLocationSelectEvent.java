package com.dmd.weixin.qy.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.dmd.weixin.common.event.LocationSelectEvent;

/**
 * Created by exizhai on 9/30/2015.
 */
public class QyLocationSelectEvent extends LocationSelectEvent {

    @JsonProperty("AgentID")
    private int agentId;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

}
