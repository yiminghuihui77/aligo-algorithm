package com.huihui.aligo.hello;

import java.util.List;

/**
 * @author minghui.y
 * @create 2021-10-11 5:35 下午
 **/
public class BatchAddShopWarehouseVo {
    private Integer source;

    private List<ShopWarehouseBaseVo> shopWarehouseBaseVos;

    /**
     * Getter method for property source.
     *
     * @return property value of source
     */
    public Integer getSource() {
        return source;
    }

    /**
     * Setter method for property source.
     *
     * @param source value to be assigned to property source
     */
    public void setSource( Integer source ) {
        this.source = source;
    }

    /**
     * Getter method for property shopWarehouseBaseVos.
     *
     * @return property value of shopWarehouseBaseVos
     */
    public List<ShopWarehouseBaseVo> getShopWarehouseBaseVos() {
        return shopWarehouseBaseVos;
    }

    /**
     * Setter method for property shopWarehouseBaseVos.
     *
     * @param shopWarehouseBaseVos value to be assigned to property shopWarehouseBaseVos
     */
    public void setShopWarehouseBaseVos( List<ShopWarehouseBaseVo> shopWarehouseBaseVos ) {
        this.shopWarehouseBaseVos = shopWarehouseBaseVos;
    }
}
