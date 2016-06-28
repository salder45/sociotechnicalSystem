/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author laboratoriointerface
 */
@Configuration
@EnableTransactionManagement
public class DataConfig {

    private static final Logger log = LoggerFactory.getLogger(DataConfig.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Configuration
    @Profile("production")
    @Import(PropertyPlaceholderConfig.class)
    static class Production {

        @Value("${hibernate.dialect}")
        protected String hibernateDialect;
        @Value("${hibernate.show_sql}")
        protected String hibernateShowSql;
        @Value("${hibernate.hbm2ddl.auto}")
        protected String hibernateHbm2DDL;
        @Value("${hibernate.cache.use_second_level_cache}")
        protected String hibernateSecondLevelCache;
        @Value("${hibernate.cache.provider_class}")
        protected String hibernateCacheClass;
        @Value("${hibernate.default_schema}")
        protected String hibernateSchema;
        @Value("${jdbc.driverClassName}")
        protected String jdbcDriver;
        @Value("${jdbc.username}")
        protected String jdbcUsername;
        @Value("${jdbc.password}")
        protected String jdbcPassword;
        @Value("${jdbc.url}")
        protected String jdbcUrl;
        
        @Bean
        public SessionFactory sessionFactory(){
            
            LocalSessionFactoryBean factoryBean;
            
            try{
                factoryBean=new LocalSessionFactoryBean();
                //
                Properties pp=new Properties();
                pp.setProperty("hibernate.dialect", hibernateDialect);
                pp.setProperty("hibernate.show_sql", hibernateShowSql);
                pp.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DDL);
                pp.setProperty("hibernate.cache.use_second_level_cache", hibernateSecondLevelCache);
                pp.setProperty("hibernate.cache.provider_class", hibernateCacheClass);
                pp.setProperty("hibernate.default_schema", hibernateSchema);
                
                factoryBean.setDataSource(dataSource());
                factoryBean.setPackagesToScan("mx.edu.um.dii.labinterfaces.diasetproject.model");
                factoryBean.setHibernateProperties(pp);
                factoryBean.afterPropertiesSet();
                return factoryBean.getObject();                
                
            }catch(Exception e){
                log.error("Couldn't configure the sessionFactory bean",e);
            }
            throw  new RuntimeException("Couldn't configure the sessionFactory bean");
        }
        
        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource ds=new DriverManagerDataSource();
            ds.setDriverClassName(jdbcDriver);
            ds.setUsername(jdbcUsername);
            ds.setPassword(jdbcPassword);
            ds.setUrl(jdbcUrl);
            return ds;
        }
    }

    @Configuration
    @Profile("tests")
    @Import(PropertyPlaceholderConfig.class)
    static class Tests {
        
        @Value("${test.hibernate.dialect}")
        protected String hibernateDialect;
        @Value("${test.hibernate.show_sql}")
        protected String hibernateShowSql;
        @Value("${test.hibernate.hbm2ddl.auto}")
        protected String hibernateHbm2DDL;
        @Value("${test.hibernate.cache.use_second_level_cache}")
        protected String hibernateSecondLevelCache;
        @Value("${test.hibernate.cache.provider_class}")
        protected String hibernateCacheClass;
        @Value("${test.hibernate.default_schema}")
        protected String hibernateSchema;
        @Value("${test.jdbc.driverClassName}")
        protected String jdbcDriver;
        @Value("${test.jdbc.username}")
        protected String jdbcUsername;
        @Value("${test.jdbc.password}")
        protected String jdbcPassword;
        @Value("${test.jdbc.url}")
        protected String jdbcUrl;
        
        @Bean
        public SessionFactory sessionFactory(){
            
            LocalSessionFactoryBean factoryBean;
            
            try{
                factoryBean=new LocalSessionFactoryBean();
                //
                Properties pp=new Properties();
                pp.setProperty("hibernate.dialect", hibernateDialect);
                pp.setProperty("hibernate.show_sql", hibernateShowSql);
                pp.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DDL);
                pp.setProperty("hibernate.cache.use_second_level_cache", hibernateSecondLevelCache);
                pp.setProperty("hibernate.cache.provider_class", hibernateCacheClass);
                pp.setProperty("hibernate.default_schema", hibernateSchema);
                
                factoryBean.setDataSource(dataSource());
                factoryBean.setPackagesToScan("");
                factoryBean.setHibernateProperties(pp);
                factoryBean.afterPropertiesSet();
                return factoryBean.getObject();                
                
            }catch(Exception e){
                log.error("Couldn't configure the sessionFactory bean",e);
            }
            throw  new RuntimeException("Couldn't configure the sessionFactory bean");
        }
        
        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource ds=new DriverManagerDataSource();
            ds.setDriverClassName(jdbcDriver);
            ds.setUsername(jdbcUsername);
            ds.setPassword(jdbcPassword);
            ds.setUrl(jdbcUrl);
            return ds;
        }
    }
}
