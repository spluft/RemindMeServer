package ru.spluft.remindme.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("ru.spluft.remindme.server.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("ru.spluft.remindme.server")
public class DatabaseConfig {

    @Resource
    private Environment env;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(env.getRequiredProperty("db.enity.package"));
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(getHybernateProperties());

        return entityManagerFactory;
    }

    public Properties getHybernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find 'hibernate.properties' in classpath", e);
        }
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(env.getRequiredProperty("db.url"));
        basicDataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        basicDataSource.setUsername(env.getRequiredProperty("db.username"));
        basicDataSource.setPassword(env.getRequiredProperty("db.password"));

        basicDataSource.setInitialSize(Integer.parseInt(env.getRequiredProperty("db.initialSize")));
        basicDataSource.setMinIdle(Integer.parseInt(env.getRequiredProperty("db.minIdle")));
        basicDataSource.setMaxIdle(Integer.parseInt(env.getRequiredProperty("db.maxIdle")));
        basicDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        basicDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getRequiredProperty("db.minEvictableIdleTimeMills")));
        basicDataSource.setTestOnBorrow(Boolean.parseBoolean(env.getRequiredProperty("db.testOnBorrow")));
        basicDataSource.setValidationQuery(env.getRequiredProperty("db.validationQuery"));
        return basicDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return jpaTransactionManager;
    }

}
