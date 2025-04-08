package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration//konfigrasyon klasi oldugu gösteriliyor
@PropertySource("classpath:application.properties")// dosya yolu verilerek @PropertySource anotasyonuyla ulastik
public class RootConfig {

    @Autowired
    private Environment environment; //bununla dosyadaki bilgilere ulasiyoruz


    //JDBC - Hibernate için sessionFactory beanini oluşturup tüm uygulamada kullanacağız
    @Bean
    public LocalSessionFactoryBean sessionFactory() { //LocalSessionFactoryBean klasi ile  sessionFactory objesi olusturduk
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());//jdbc ile ilgli ayarlar icin olusturduk
        sessionFactory.setHibernateProperties(hibernateProperties());//hibernate ile ilgli ayarlar icin olusturduk
        sessionFactory.setPackagesToScan(new String[]{"com.tpe.domain"});//verilen packageleri scan eder
        return sessionFactory;
    }

    //sessionFactory nin JDBC ayarlarını dataSource objesinde topladık
    private DataSource dataSource() { //datasource interface okdugu icin obje üretemeyiz
        DriverManagerDataSource dataSource = new DriverManagerDataSource();//obje üretmek icin   DriverManagerDataSource clasini kullaniriz
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    } //burada fieldlar sabittir, obje ile onlari set etmek icin cagirdik ve environment objesi ile dosyadaki
    //veriyi icine yerlestirdik

    //sessionFactory nin Hibernate ayarlarını properties objesinde topladık
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

}

