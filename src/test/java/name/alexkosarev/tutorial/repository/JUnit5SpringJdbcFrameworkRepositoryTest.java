package name.alexkosarev.tutorial.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import name.alexkosarev.tutorial.Framework;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
class JUnit5SpringJdbcFrameworkRepositoryTest {

    @Container
    private final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:11")
                    .withInitScript("db.sql");

    private SpringJdbcFrameworkRepository repository;

    @BeforeEach
    void setUp() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(POSTGRESQL_CONTAINER.getJdbcUrl());
        hikariConfig.setUsername(POSTGRESQL_CONTAINER.getUsername());
        hikariConfig.setPassword(POSTGRESQL_CONTAINER.getPassword());
        var dataSource = new HikariDataSource(hikariConfig);
        repository = new SpringJdbcFrameworkRepository(new FrameworkSimpleJdbcInsert(dataSource),
                new JdbcTemplate(dataSource), FrameworkRowMapper.getInstance());
    }

    @Test
    void findAllFrameworks_ReturnsFrameworksList() {
        // when
        var frameworks = repository.findAllFrameworks();

        // then
        assertNotNull(frameworks);
        assertEquals(3, frameworks.size());
        assertEquals(new Framework(1, "Spring Framework", "Java", "https://spring.io"),
                frameworks.get(0));
    }

    @Test
    void persistFramework_ReturnsGeneratedId( ){
        // given
        var framework = new Framework("Testcontainers Java", "Java", "https://www.testcontainers.org/");

        // when
        var newId = repository.persistFramework(framework);

        // then
        assertEquals(4, newId);
    }
}
