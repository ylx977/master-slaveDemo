package com.example.demo.configure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author ylx
 * Created by fuzamei on 2018/3/11.
 */
@Configuration
@PropertySource(value = {"classpath:application.properties"},encoding="utf-8")
//这个sqlSessionFactoryRef是跟着bean的名字走的
@MapperScan(basePackages = {"com.example.demo.mapper.slave"},sqlSessionFactoryRef = "slaveSessionFactory")
public class SessionFactoryConfiguration2 {

    @Value("${mybatis.slave.mybatis_config_file}")
    private String slaveMybatisConfigureFilePath;

    @Value("${mybatis.slave.mapper_path}")
    private String slaveMapperPath;

    @Autowired
    @Qualifier(value = "slaveDatasource")
    private DataSource slaveDatasource;


    @Value("${mybatis.slave.entity_package}")
    private String slaveEntityPackage;

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactoryBean createSlaveSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(slaveMybatisConfigureFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + slaveMapperPath;
        System.out.println("packageslaveSearchPath:"+ packageSearchPath);
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(slaveDatasource);
        sqlSessionFactoryBean.setTypeAliasesPackage(slaveEntityPackage);
        return sqlSessionFactoryBean;
    }

    @Bean("slaveSessionFactory")
    public SqlSessionFactory sqlSessionFactoryMaster() throws Exception {
        return createSlaveSqlSessionFactoryBean().getObject();
    }
}
