package name.alexkosarev.tutorial.repository;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public final class FrameworkSimpleJdbcInsert extends SimpleJdbcInsert {

    public FrameworkSimpleJdbcInsert(DataSource dataSource) {
        super(dataSource);
        this.withSchemaName("test")
                .withTableName("framework")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "language", "link")
                .compile();
    }
}
