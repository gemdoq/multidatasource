package com.example.multidatasource.global.config;

import com.example.multidatasource.domain.product.model.entity.ProductEntity;
import com.example.multidatasource.global.annotation.Db1Repository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.example.multidatasource.domain",
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Db1Repository.class),
		entityManagerFactoryRef = "db1EntityManagerFactory",
		transactionManagerRef = "db1TransactionManager"
)
public class Db1Config {

	// 관련 엔티티 클래스 배열
	private static final Class<?>[] DB_ENTITIES = {
			ProductEntity.class
	};

	@Primary
	@Bean(name = "db1DataSource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url(System.getenv("DB_URL1"))
				.username(System.getenv("DB_USERNAME1"))
				.password(System.getenv("DB_PASSWORD1"))
				.build();
	}

	@Primary
	@Bean(name = "db1EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("db1DataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages(DB_ENTITIES)
				.persistenceUnit("db1")
				.build();
	}

	@Primary
	@Bean(name = "db1TransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("db1EntityManagerFactory")EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
