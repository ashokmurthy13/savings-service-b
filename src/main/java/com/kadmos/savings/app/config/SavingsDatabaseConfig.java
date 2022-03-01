package com.kadmos.savings.app.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.kadmos.savings.app.dal", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class SavingsDatabaseConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SavingsDatabaseConfig.class);

    @Value("${savings.db.url}")
    private String URL;

    @Value("${savings.db.user}")
    private String user;

    @Value("${savings.db.password:#{null}}")
    private String password;

    @Value("${savings.db.driver}")
    private String driver;

    @Bean(name = "datasource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        LOG.info("Datasource successfully initialized.");
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource((dataSource()));
        SqlSessionFactory factory = factoryBean.getObject();
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }
}