package com.tio.common.socket.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tio.common.socket.base.RequestMessage;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 心跳
 *
 * @author
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthMessage implements RequestMessage {
    /**
     * 接口名称
     */
    @NotEmpty(message = "接口名称不能为空")
    private String service;
    /**
     * 车场ID
     */
    @NotEmpty(message = "车场ID不能为空")
    @JsonProperty("parkid")
    private String parkId;
    /**
     * 车场ID
     */
    @NotEmpty(message = "parkkey不能为空")
    @JsonProperty("parkkey")
    private String parkKey;

    @Override
    public String getService() {
        return this.service;
    }

    @Override
    public void setService(String service) {
        this.service = service;
    }

}