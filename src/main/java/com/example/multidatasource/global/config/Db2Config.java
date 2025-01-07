package com.example.multidatasource.global.config;

import com.example.multidatasource.domain.productdetail.model.entity.ProductdetailEntity;
import com.example.multidatasource.domain.provider.model.entity.ProviderEntity;
import com.example.multidatasource.global.annotation.Db2Repository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.example.multidatasource.domain",
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Db2Repository.class),
		entityManagerFactoryRef = "db2EntityManagerFactory",
		transactionManagerRef = "db2TransactionManager"
)
public class Db2Config {

	// 관련 엔티티 클래스 배열
	private static final Class<?>[] DB_ENTITIES = {
			ProviderEntity.class,
			ProductdetailEntity.class
	};

	// application.yml에 jdbc-url을 사용하고, hikariCP를 datasource로 사용(위 제한선 위)
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public HikariConfig secondHikariConfig() {
		HikariConfig config = new HikariConfig();
		System.out.println("DB2 JDBC URL: " + config.getJdbcUrl());
		return config;
	}

	@Bean(name = "db2DataSource")
	public DataSource db2DataSource() {
		HikariConfig config = secondHikariConfig();
		System.out.println("DB2 JDBC URL in DataSource: " + config.getJdbcUrl());
		return new HikariDataSource(config);
	}
	// application.yml에 jdbc-url을 사용하고, hikariCP를 datasource로 사용(아래 제한선 아래)

//	// 스프링부트 기본 데이터소스 구성이 아닌 builder 패턴을 사용하여 직접 구성하는 방식(위 제한선 위)
//	@Bean(name = "db2DataSource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create()
//				.driverClassName("org.postgresql.Driver")
//				.url(System.getenv("DB_URL2"))
//				.username(System.getenv("DB_USERNAME2"))
//				.password(System.getenv("DB_PASSWORD2"))
//				.build();
//	}
//	// 스프링부트 기본 데이터소스 구성이 아닌 builder 패턴을 사용하여 직접 구성하는 방식(위 제한선 위)

	@Bean(name = "db2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("db2DataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages(DB_ENTITIES)
				.persistenceUnit("db2")
				.properties(jpaProperties())
				.build();
	}

	private Map<String, Object> jpaProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
		properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
		return properties;
	}

	@Bean(name = "db2TransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
