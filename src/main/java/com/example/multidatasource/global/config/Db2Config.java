package com.example.multidatasource.global.config;

import com.example.multidatasource.domain.productdetail.model.entity.ProductdetailEntity;
import com.example.multidatasource.domain.provider.model.entity.ProviderEntity;
import com.example.multidatasource.global.annotation.Db2Repository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
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

	@Bean(name = "db2DataSource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url(System.getenv("DB_URL2"))
				.username(System.getenv("DB_USERNAME2"))
				.password(System.getenv("DB_PASSWORD2"))
				.build();
	}

	@Bean(name = "db2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("db2DataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages(DB_ENTITIES)
				.persistenceUnit("db2")
				.build();
	}

	@Bean(name = "db2TransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
