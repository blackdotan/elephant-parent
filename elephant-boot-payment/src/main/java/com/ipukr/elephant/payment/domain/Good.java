package com.ipukr.elephant.payment.domain;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/25.
 */
public class Good {
    /**
     * 商品编号
     */
    private String no;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 单价
     */
    private Float price;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 分类
     */
    private String category;

    private Good(Builder builder) {
        setCategory(builder.category);
        setNo(builder.no);
        setName(builder.name);
        setPrice(builder.price);
        setQuantity(builder.quantity);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String category;
        private String no;
        private String name;
        private Float price;
        private Integer quantity;

        public Builder() {
        }

        public Builder category(String val) {
            category = val;
            return this;
        }

        public Builder no(String val) {
            no = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Float val) {
            price = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Good build() {
            return new Good(this);
        }
    }
}
