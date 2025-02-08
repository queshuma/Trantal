package com.shuzhi.system_order.Info;

import com.shuzhi.system_order.DTO.OrderDTO;
import org.springframework.format.annotation.DateTimeFormat;
import com.shuzhi.system_order.Entity.ObjectWithBussVO;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/2/14
 *
 * @version 1.0
 */

public class OrderWithObjectUser extends OrderDTO {
    private ObjectWithBussVO objectWithBussVO;

    private String receiveAddress;
    private String receivePhone;
    private String receiveName;
    private String remark;

    public ObjectWithBussVO getObjectWithBussVO() {
        return objectWithBussVO;
    }

    public void setObjectWithBussVO(ObjectWithBussVO objectWithBussVO) {
        this.objectWithBussVO = objectWithBussVO;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String   toString() {
        return "OrderWithObjectUser{" +
                "objectWithBussVO=" + objectWithBussVO +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", receivePhone='" + receivePhone + '\'' +
                ", receiveName='" + receiveName + '\'' +
                '}';
    }
}
