package com.nefu.se.graduationprocessmanagement.config;

//@Configuration(proxyBeanMethods = false)
//@Slf4j
public class DatabaseInitializationConfiguration {


    // 初始化数据系统自动直接导入？后期手动导入？查询指定表是否为空，导入初始数据
    /*@Autowired
    void initializeDatabase(UserMapper userMapper, DataSource ds) {

        log.info("{}", userMapper);
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource scripts = resourceLoader.getResource("classpath:schema-mysql.sql");
        new ResourceDatabasePopulator(scripts).execute(ds);
    }*/
}
