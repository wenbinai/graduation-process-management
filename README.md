# graduation-process-management

就这点功能，不必分模块了吧
没加swagger，前后端无法联网联调。并行开发，使用md文档，github分享请求/响应数据结构  
component包下为原工具类，按单例组件设计  
entity直接当do层  
修改application.yml/generatorConfig.xml数据源配置  
在Maven视图/plugins/mybatis-generator，运行mybatis-generator:generate生成model/mapper  
每次均创建model覆盖，但仅为新数据表创建mapper  

mybatis-plus全局雪花算法生成ID；注入乐观锁拦截器  
Jackson序列化时忽略空值属性  
redis后期看情况加  
其他自己加  



