package com.tio.common.socket.base;

/**
 * @author
 */
public interface Message {

    /**
     * 接口名称
     *
     * @return
     */
    String getService();

    /**
     * 接口名称
     *
     * @param service
     */
    void setService(String service);

}
