package com.anysinsa.common.config;

import com.anysinsa.product.presentation.api.ProductController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    @Bean
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.hikari.maximum-pool-size}") int maximumPoolSize,
            @Value("${spring.datasource.hikari.minimum-idle}") int minimumIdle,
            @Value("${spring.datasource.hikari.connection-timeout}") long connectionTimeout,
            @Value("${spring.datasource.hikari.idle-timeout}") long idleTimeout,
            @Value("${spring.datasource.hikari.max-lifetime}") long maxLifetime) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("io.opentelemetry.instrumentation.jdbc.OpenTelemetryDriver");

        // HikariCP 설정
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);
        config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);

        // 원래 드라이버 설정
        config.addDataSourceProperty("opentelemetry.jdbc.wrapped-driver", "com.mysql.cj.jdbc.Driver");

        // 복제 관련 설정(local)
//        config.addDataSourceProperty("replicationEnableJMX", "true");
//        config.addDataSourceProperty("allowMasterDownConnections", "true");
//        config.addDataSourceProperty("allowReplicaDownConnections", "true");

        return new HikariDataSource(config);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.MYSQL);
        return adapter;
    }
}