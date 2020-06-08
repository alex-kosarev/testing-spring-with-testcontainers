package name.alexkosarev.tutorial.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import name.alexkosarev.tutorial.Framework;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkRowMapper implements RowMapper<Framework> {

    private static final FrameworkRowMapper INSTANCE = new FrameworkRowMapper();

    public static FrameworkRowMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Framework mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Framework(rs.getInt("id"), rs.getString("name"),
                rs.getString("language"), rs.getString("link"));
    }
}
