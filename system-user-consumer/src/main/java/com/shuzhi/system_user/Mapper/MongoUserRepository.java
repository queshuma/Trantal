package com.shuzhi.system_user.Mapper;

import com.shuzhi.system_user.Entity.MongoUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/4/1
 *
 * @version 1.0
 */

public interface MongoUserRepository extends MongoRepository<MongoUser, ObjectId> {


}
