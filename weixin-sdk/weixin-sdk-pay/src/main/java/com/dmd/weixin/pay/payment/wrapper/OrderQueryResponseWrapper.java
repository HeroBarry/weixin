package com.dmd.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.dmd.weixin.pay.payment.bean.OrderQueryResponse;

/**
 * @borball on 1/13/2017.
 */
public class OrderQueryResponseWrapper extends BaseSettings {

    @JsonUnwrapped
    private OrderQueryResponse response;

    public OrderQueryResponse getResponse() {
        return response;
    }

    public void setResponse(OrderQueryResponse response) {
        this.response = response;
    }
}