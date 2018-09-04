package com.ipukr.elephant.payment.domain.ali;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/20.
 */
public class GoodsDetail {
    /**
     * 必填
     * 商品的编号
     * eg: apple-01
     */
    @JsonProperty(value = "goods_id")
    private String goodsId;

    /**
     * 必填
     * 商品名称
     */
    @JsonProperty(value = "goods_name")
    private String goodsName;

    /**
     * 必填
     * 商品数量
     */
    @JsonProperty(value = "quantity")
    private Integer quantity;
    /**
     * 必填
     * 商品单价，单位为元
     */
    @JsonProperty(value = "price")
    private float price;
    /**
     * 可选
     * 商品类目
     */
    @JsonProperty(value = "goods_category")
    private String goodsCategory;

    /**
     * 可选
     * 商品描述信息
     */
    @JsonProperty(value = "body")
    private String body;
    /**
     * 可选
     * 商品的展示地址
     */
    @JsonProperty(value = "show_url")
    private String showUrl;
}
