package com.xonro.weixinpay.bean;

/**
 * 使用微信订单号来获取退款模型
 * @author Alex
 * @date 2018/1/4
 */
public class RefundByTransactionId {
    /**
     * 微信订单号
     */
    private String transactionId;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 订单金额
     */
    private Integer totalFee;
    /**
     * 退款金额
     */
    private Integer refundFee;
    /**
     * 货币种类
     */
    private String refundFeeType="CNY";
    /**
     * 退款原因
     */
    private String refundDesc;

    /**
     * 退款资金来源
     * 仅针对老资金流商户使用
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    private String refundAccount;

    public String getRefundAccount() {
        return refundAccount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public static class Builder {
        /**
         * 微信订单号
         */
        private String transactionId;
        /**
         * 商户退款单号
         */
        private String outRefundNo;
        /**
         * 订单金额
         */
        private Integer totalFee;
        /**
         * 退款金额
         */
        private Integer refundFee;
        /**
         * 货币种类
         */
        private String refundFeeType="CNY";
        /**
         * 退款原因
         */
        private String refundDesc;
        /**
         * 退款资金来源
         * 仅针对老资金流商户使用
         * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
         * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
         */
        private String refundAccount;

        public Builder(String transactionId, String outRefundNo, Integer totalFee, Integer refundFee){
            this.transactionId = transactionId;
            this.outRefundNo = outRefundNo;
            this.totalFee = totalFee;
            this.refundFee = refundFee;
        }

        public Builder refundFeeType(String val){
            refundFeeType = val;
            return this;
        }

        public Builder refundDesc(String val){
            refundDesc = val;
            return this;
        }

        public Builder refundAccount(String val){
            refundAccount = val;
            return this;
        }

        public RefundByTransactionId build() {
            return new RefundByTransactionId(this);
        }
    }

    private RefundByTransactionId(Builder builder) {
        transactionId = builder.transactionId;
        outRefundNo = builder.outRefundNo;
        totalFee = builder.totalFee;
        refundFee = builder.refundFee;
        refundFeeType = builder.refundFeeType;
        refundDesc = builder.refundDesc;
        refundAccount = builder.refundAccount;
    }
}
