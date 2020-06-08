package name.alexkosarev.tutorial.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import name.alexkosarev.tutorial.Framework;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public final class SpringJdbcFrameworkRepository implements FrameworkRepository {

    @NonNull
    private final SimpleJdbcInsertOperations insertOperations;

    @NonNull
    private final JdbcOperations jdbcOperations;

    @NonNull
    private final RowMapper<Framework>  frameworkRowMapper;

    @Override
    public List<Framework> findAllFrameworks() {
        return this.jdbcOperations.query("select * from test.framework", this.frameworkRowMapper);
    }

    @Override
    public int persistFramework(@NonNull Framework framework) {
        return this.insertOperations.executeAndReturnKey(Map.of(
                "name", framework.getName(),
                "language", framework.getLanguage(),
                "link", framework.getLink()
        )).intValue();
    }
}
