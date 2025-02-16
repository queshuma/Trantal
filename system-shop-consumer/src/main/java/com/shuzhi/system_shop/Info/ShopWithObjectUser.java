package com.shuzhi.system_shop.Info;

import com.shuzhi.system_shop.DTO.ShopDTO;
import com.shuzhi.system_shop.Entity.ObjectWithBussVO;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/2/14
 *
 * @version 1.0
 */

public class ShopWithObjectUser extends ShopDTO {
    private ObjectWithBussVO objectWithBussVO;

    public ObjectWithBussVO getObjectWithBussVO() {
        return objectWithBussVO;
    }

    public void setObjectWithBussVO(ObjectWithBussVO objectWithBussVO) {
        this.objectWithBussVO = objectWithBussVO;
    }
}
