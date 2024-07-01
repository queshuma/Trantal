# （微服务毕业设计）数派π品牌电商网站

#### 基于SpringCloud的微服务项目，技术栈包含：SpringBoot，MongoDB，MySQL，Nacos，Spring Admin，Gateway
#### 项目介绍

###### 主要针对新媒体的盛行，品牌电商的势力和市场逐渐形成，数派π就是这样一个品牌电商系统，通过自有电商平台，将公域流量转化成私域流量，实现一站式的购物体验，借助品牌背书提高消费者的粘性。
###### 该项目还包括商品订单数据可视化，订单状态总览，商品信息处理，订单数据处理、购物车数据处理、消费者信息处理等。主要用户为品牌方、加盟方、消费者，用户根据需求去加购下单， 加盟方可以优化产品信息、处理订单信息，品牌方可以统筹管理整体数据。

#### 项目预计整体架构
![image](https://github.com/queshuma/Trantal/assets/59001306/807c3710-a6ba-4584-8a94-88442c33753c)

#### 技术路线
###### 前端方面基于Node.js的配置下，使用Vue技术进行开发，通过Vue_cli脚手架进行敏捷开发，组件库方面选择阿里巴巴旗下的Ant Design框架进行开发。
###### 后端方面基于微服务的架构，使用Spring全家桶进行开发，搭配Spring Cloud及相关的中间件进行服务管理及相关负载均衡，持久层方面选择Mybatis框架进行数据库的数据交互。
###### 数据库方面使用MySQL作为持久数据库，MongoDB作为日志数据库，Redis作为缓存数据库，对数据进行合理对处理。
###### 开发工具方面使用Jetbrains IDEA进行开发，数据库管理使用Navicat，接口测试通过Postman

#### 接口标准
###### 所有的接口均使用Reslful API标准，接口地址中不出现update/add/find等相关的字符，进一步提高代码的可读性
###### GetMapping: 获取信息 ｜ /info(为所有信息)/id(根据相关属性查询)
###### PostMapping: 新增信息 ｜
###### PutMapping: 修改信息 ｜
###### DeleteMapping: 删除信息 ｜ 

###### 例如
```` @GetMapping("/info/objectStatus")
    public ResponseResult findInfoObjectStatus() {
        List<ObjClassesEntity> objectEntityList = null;
        List<ObjClassesShow> objClassesShowList = objClassesService.getInfoObjectStatus();
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objClassesShowList);
    }
````

##### 反馈信息
###### 接口反馈信息标准化输出，统一风格为
