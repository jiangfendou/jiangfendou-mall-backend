/**
  * Copyright 2022 bejson.com 
  */
package com.jiangfendou.mall.product.vo;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * Auto-generated: 2022-10-10 9:17:32
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Skus {

    private List<Attr> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Images> images;
    private List<String> descar;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}