package com.jucaipen.main.payinfo;

public class OrderParamBean extends ApiBaseBean{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String appid;                         //商户号
    private String            no_order;             // 商家订单号
    private String            oid_partner;          // 商户号
    private String            money_order;          // 订单金额
    private String            user_id;              // 操作员号
    private String            name_goods;           // 商品名称
    private String            dt_order;             // 商家订单时间
    private String            notify_url;           // 商家通知地址
    private String            info_order;           // 订单描述
    private String            memo2;                // 商家预留
    private String            pay_type;             // 支付方式
    private String            risk_item;            // 风控参数
    private String            channel_order;        // 渠道单号
    private String            oid_order;            // 银+订单号
    private String            dimension_url;        // 二维码地址
    private String            pay_status;           // 支付状态
    private String            desc_goods;           // 订单描述
    private String            dt_finish;            // 订单完成时间

    public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getDt_finish()
    {
        return dt_finish;
    }

    public void setDt_finish(String dtFinish)
    {
        dt_finish = dtFinish;
    }

    public String getDesc_goods()
    {
        return desc_goods;
    }

    public void setDesc_goods(String descGoods)
    {
        desc_goods = descGoods;
    }

    public String getChannel_order()
    {
        return channel_order;
    }

    public void setChannel_order(String channelOrder)
    {
        channel_order = channelOrder;
    }

    public String getOid_order()
    {
        return oid_order;
    }

    public void setOid_order(String oidOrder)
    {
        oid_order = oidOrder;
    }

    public String getDimension_url()
    {
        return dimension_url;
    }

    public void setDimension_url(String dimensionUrl)
    {
        dimension_url = dimensionUrl;
    }

    public String getPay_status()
    {
        return pay_status;
    }

    public void setPay_status(String payStatus)
    {
        pay_status = payStatus;
    }

    public String getNo_order()
    {
        return no_order;
    }

    public void setNo_order(String noOrder)
    {
        no_order = noOrder;
    }

    public String getOid_partner()
    {
        return oid_partner;
    }

    public void setOid_partner(String oidPartner)
    {
        oid_partner = oidPartner;
    }

    public String getMoney_order()
    {
        return money_order;
    }

    public void setMoney_order(String moneyOrder)
    {
        money_order = moneyOrder;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String userId)
    {
        user_id = userId;
    }

    public String getName_goods()
    {
        return name_goods;
    }

    public void setName_goods(String nameGoods)
    {
        name_goods = nameGoods;
    }

    public String getDt_order()
    {
        return dt_order;
    }

    public void setDt_order(String dtOrder)
    {
        dt_order = dtOrder;
    }

    public String getNotify_url()
    {
        return notify_url;
    }

    public void setNotify_url(String notifyUrl)
    {
        notify_url = notifyUrl;
    }

    public String getInfo_order()
    {
        return info_order;
    }

    public void setInfo_order(String infoOrder)
    {
        info_order = infoOrder;
    }

    public String getMemo2()
    {
        return memo2;
    }

    public void setMemo2(String memo2)
    {
        this.memo2 = memo2;
    }

    public String getPay_type()
    {
        return pay_type;
    }

    public void setPay_type(String payType)
    {
        pay_type = payType;
    }

    public String getRisk_item()
    {
        return risk_item;
    }

    public void setRisk_item(String riskItem)
    {
        risk_item = riskItem;
    }

}
