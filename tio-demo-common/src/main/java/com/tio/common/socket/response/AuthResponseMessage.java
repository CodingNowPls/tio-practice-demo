package com.tio.common.socket.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tio.common.socket.base.RequestMessage;
import lombok.Data;

/**
 * 认证
 *
 * @author
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseMessage implements RequestMessage {
    /**
     * 接口名称
     */
    private String service;
    /**
     * 返回代码 0，1，2，3, 0成功,其他失败
     */
    @JsonProperty("result_code")
    private Integer resultCode;
    /**
     * 返回描述	 0获取成功，1未查询到停车信息，2月卡车辆不允许缴费
     */
    private String message;

    @Override
    public String getService() {
        return this.service;
    }

    @Override
    public void setService(String service) {
        this.service = service;
    }

}