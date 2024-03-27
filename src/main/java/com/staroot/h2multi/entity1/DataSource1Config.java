package com.staroot.h2multi.entity1;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.staroot.h2multi.entity1",
        entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "transactionManager1"
)

@EntityScan("com.staroot.h2multi.entity1")
public class DataSource1Config {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(@Qualifier("dataSource1") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.staroot.h2multi.entity1");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class); // Hibernate 영속성 공급자 설정

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create"); // 테이블 자동 생성 설정
        emf.setJpaProperties(properties);


        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager1(EntityManagerFactory entityManagerFactory1) {
        return new JpaTransactionManager(entityManagerFactory1);
    }

//    private Map<String, Object> hibernateProperties() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        properties.put("hibernate.hbm2ddl.auto", "create"); // 첫 번째 데이터베이스의 DDL 자동 생성 설정
//        return properties;
//    }
}
