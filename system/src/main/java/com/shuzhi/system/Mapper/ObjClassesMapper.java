package com.shuzhi.system.Mapper;

import com.shuzhi.entity.ObjClassesEntity;
import com.shuzhi.system.Info.ObjClassesInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ObjClassesMapper {

    @Select("SELECT * FROM object_classes")
    public List<ObjClassesEntity> findAllClasses();

    @Insert("INSERT INTO object_classes(classes_parent_id, classes_name) VALUES (#{classesParentId}, #{classesName})")
    public Boolean addObjectClasses(ObjClassesInfo objClassesInfo);
}
