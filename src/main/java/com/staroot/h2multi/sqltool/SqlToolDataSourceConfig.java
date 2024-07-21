package com.staroot.h2multi.sqltool;

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
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.staroot.h2multi.sqltool",
        entityManagerFactoryRef = "sqlToolEntityManagerFactory",
        transactionManagerRef = "sqlToolTransactionManager"
)

@EntityScan("com.staroot.h2multi.sqltool.entity")
public class SqlToolDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.sqltooldb")
    public DataSource sqlToolDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean sqlToolEntityManagerFactory(@Qualifier("sqlToolDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.staroot.h2multi.sqltool");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class); // Hibernate 영속성 공급자 설정

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create"); // 테이블 자동 생성 설정
        emf.setJpaProperties(properties);


        return emf;
    }

    @Bean
    @Qualifier("sqlToolTransactionManager")
    public JpaTransactionManager sqlToolTransactionManager(EntityManagerFactory sqlToolEntityManagerFactory) {
        return new JpaTransactionManager(sqlToolEntityManagerFactory);
    }
}
