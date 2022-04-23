package com.tio.common.dto.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author
 */
@Data
@TableName("cmf_order")
public class OrderDTO implements Serializable {

    /**
     *
     */
    @TableId(value = "order_id")
    private Integer orderId;


    /**
     *
     */
    @TableField("order_sn")
    private String orderSn;


    /**
     *
     */
    @TableField("car_number")
    private String carNumber;


    /**
     *
     */
    @TableField("user_id")
    private Integer userId;


    /**
     *
     */
    @TableField("title")
    private String title;


    /**
     * 1充值；2:月租  3;临时
     */
    @TableField("type")
    private Integer type;


    /**
     *
     */
    @TableField("fee")
    private BigDecimal fee;


    /**
     *
     */
    @TableField("pay_fee")
    private BigDecimal payFee;


    /**
     *
     */
    @TableField("is_pay")
    private Integer isPay;


    /**
     *
     */
    @TableField("pay_time")
    private Integer payTime;


    /**
     *
     */
    @TableField("transaction_id")
    private String transactionId;


    /**
     *
     */
    @TableField("pay_type")
    private String payType;


    /**
     *
     */
    @TableField("create_time")
    private Integer createTime;


    /**
     * 公司id
     */
    @TableField("company_id")
    private Integer companyId;


    /**
     * 是否查车(0:是  1：否)
     */
    @TableField("is_open")
    private Integer isOpen;


    /**
     * 是否检查完成（0：否  1:是）
     */
    @TableField("is_checked")
    private Integer isChecked;


    /**
     * 是否已发票
     */
    @TableField("is_invoice")
    private Integer isInvoice;


    /**
     *
     */
    @TableField("month")
    private Integer month;


    /**
     *
     */
    @TableField("is_syn")
    private Integer isSyn;


    /**
     *
     */
    @TableField("park_order_id")
    private String parkOrderId;


    /**
     *
     */
    @TableField("parking_serial")
    private String parkingSerial;

}
