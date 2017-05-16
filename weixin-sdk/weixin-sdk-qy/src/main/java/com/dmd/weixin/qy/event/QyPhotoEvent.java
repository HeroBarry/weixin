package com.dmd.weixin.qy.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.dmd.weixin.common.event.PhotoEvent;

/**
 * Created by exizhai on 9/30/2015.
 */
public class QyPhotoEvent extends PhotoEvent {

    @JsonProperty("AgentID")
    private int agentId;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

}
