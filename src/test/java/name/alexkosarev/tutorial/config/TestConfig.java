package name.alexkosarev.tutorial.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import name.alexkosarev.tutorial.repository.FrameworkRepository;
import name.alexkosarev.tutorial.repository.FrameworkRowMapper;
import name.alexkosarev.tutorial.repository.FrameworkSimpleJdbcInsert;
import name.alexkosarev.tutorial.repository.SpringJdbcFrameworkRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public JdbcDatabaseContainer<?> jdbcDatabaseContainer() {
        return new PostgreSQLContainer<>("postgres:11")
                .withInitScript("db.sql")
                .waitingFor(Wait.forListeningPort());
    }

    @Bean
    public DataSource dataSource(JdbcDatabaseContainer<?> jdbcDatabaseContainer) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcDatabaseContainer.getJdbcUrl());
        hikariConfig.setUsername(jdbcDatabaseContainer.getUsername());
        hikariConfig.setPassword(jdbcDatabaseContainer.getPassword());

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public FrameworkRepository frameworkRepository(DataSource dataSource) {
        return new SpringJdbcFrameworkRepository(new FrameworkSimpleJdbcInsert(dataSource),
                new JdbcTemplate(dataSource), FrameworkRowMapper.getInstance());
    }
}
