package com.shuzhi.system_objclasses.Info;

import javax.persistence.Column;
import java.security.PrivateKey;
import java.util.List;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/1/24
 *
 * @version 1.0
 */

public class ObjClassesShow {
    private Long id;

    private String label;

    private List<ObjClassesShow> children;

    private int status;

    private String parent;

    private int weightId;

    private int listStatus;

    private int objectStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ObjClassesShow> getChildren() {
        return children;
    }

    public void setChildren(List<ObjClassesShow> children) {
        this.children = children;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getWeightId() {
        return weightId;
    }

    public void setWeightId(int weightId) {
        this.weightId = weightId;
    }

    public int getListStatus() {
        return listStatus;
    }

    public void setListStatus(int listStatus) {
        this.listStatus = listStatus;
    }

    public int getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(int objectStatus) {
        this.objectStatus = objectStatus;
    }
}
