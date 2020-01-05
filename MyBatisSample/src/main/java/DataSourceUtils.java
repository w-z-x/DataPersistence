import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceUtils {
    public static DataSource createHikariDataSource(String jdbcUrl, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    public static void initializeH2(String url, String user, String password) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url + ";DB_CLOSE_DELAY=-1", user, password);
             Statement statement = conn.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS USER;" +
                    "CREATE TABLE USER(ID INT PRIMARY KEY, NAME VARCHAR(255), _ADDRESS VARCHAR(255));" +
                    "INSERT INTO USER VALUES(0, '张三', '杭州');" +
                    "INSERT INTO USER VALUES(1, '李四', '宁波');" +
                    "INSERT INTO USER VALUES(2, '王五', '温州')");
        }
    }
}
