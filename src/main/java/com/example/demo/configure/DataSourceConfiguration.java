package com.example.demo.configure;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ylx
 * Created by fuzamei on 2018/3/11.
 */
@Configuration//告诉spring来这个类下面检索相关的bean
@PropertySource(value = {"classpath:application.properties"},encoding="utf-8")
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.master.url}")
    private String masterJdbcUrl;
    @Value("${jdbc.matser.username}")
    private String masterJdbcUsername;
    @Value("${jdbc.master.password}")
    private String masterJdbcPassword;

    @Value("${jdbc.slave.url}")
    private String slaveJdbcUrl;
    @Value("${jdbc.slave.username}")
    private String slaveJdbcUsername;
    @Value("${jdbc.slave.password}")
    private String slaveJdbcPassword;

    @Value("${jdbc.connectionTimeout}")
    private Long jdbcConnectionTimeout;
    @Value("${jdbc.idleTimeout}")
    private Long jdbcIdleTimeout;
    @Value("${jdbc.maxLifetime}")
    private Long jdbcMaxLifetime;
    @Value("${jdbc.maximumPoolSize}")
    private Integer jdbcMaximumPoolSize;
    @Value("${jdbc.minimumIdle}")
    private Integer jdbcMinimumIdle;
    @Value("${jdbc.connectionTestQuery}")
    private String connectionTestQuery;

    @Primary//主数据库要加Primary注解
    @Bean(name = "datasource")
    public HikariDataSource createDataSource(){
        HikariDataSource datasource = new HikariDataSource();
        datasource.setDriverClassName(jdbcDriver);
        datasource.setJdbcUrl(masterJdbcUrl);
        datasource.setUsername(masterJdbcUsername);
        datasource.setPassword(masterJdbcPassword);
        //主库增删改查都能操作
        datasource.setReadOnly(false);
        datasource.setConnectionTimeout(jdbcConnectionTimeout);
        datasource.setIdleTimeout(jdbcIdleTimeout);
        datasource.setMaxLifetime(jdbcMaxLifetime);
        datasource.setMaximumPoolSize(jdbcMaximumPoolSize);
        datasource.setMinimumIdle(jdbcMinimumIdle);
        datasource.setAutoCommit(false);
        datasource.setConnectionTestQuery(connectionTestQuery);
        return datasource;
    }
    @Bean(name = "slaveDatasource")
    public HikariDataSource createSlaveDataSource(){
        HikariDataSource datasource = new HikariDataSource();
        datasource.setDriverClassName(jdbcDriver);
        datasource.setJdbcUrl(slaveJdbcUrl);
        datasource.setUsername(slaveJdbcUsername);
        datasource.setPassword(slaveJdbcPassword);
        //从库只读
        datasource.setReadOnly(true);
        datasource.setConnectionTimeout(jdbcConnectionTimeout);
        datasource.setIdleTimeout(jdbcIdleTimeout);
        datasource.setMaxLifetime(jdbcMaxLifetime);
        datasource.setMaximumPoolSize(jdbcMaximumPoolSize);
        datasource.setMinimumIdle(jdbcMinimumIdle);
        datasource.setAutoCommit(true);
        datasource.setConnectionTestQuery(connectionTestQuery);
        return datasource;
    }
}
