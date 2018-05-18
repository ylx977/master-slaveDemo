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
@MapperScan(basePackages = {"com.example.demo.mapper.master"},sqlSessionFactoryRef = "masterSessionFactory")
public class SessionFactoryConfiguration {

    @Value("${mybatis.master.mybatis_config_file}")
    private String masterMybatisConfigureFilePath;

    @Value("${mybatis.master.mapper_path}")
    private String masterMapperPath;

    @Autowired
    @Qualifier(value = "datasource")
    private DataSource masterDataSource;

    @Value("${mybatis.master.entity_package}")
    private String masterEntityPackage;

    @Primary//主sqlSessionFactory也要加上Primary注解
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(masterMybatisConfigureFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + masterMapperPath;
        System.out.println("packagemasterSearchPath:"+ packageSearchPath);
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(masterDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(masterEntityPackage);
        return sqlSessionFactoryBean;
    }

    @Bean("masterSessionFactory")
    public SqlSessionFactory sqlSessionFactorySlave() throws Exception {
        return createSqlSessionFactoryBean().getObject();
    }
}
