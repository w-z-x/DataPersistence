import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class SimpleMyBatis {
    public static void main(String[] args) throws SQLException {
        final String H2_INITIAL_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        final String H2_USER = "sa";
        final String H2_PWD = "c2#D1";
        final String H2_URL = "jdbc:h2:mem:test";

        try (Connection conn = DriverManager.getConnection(H2_INITIAL_URL, H2_USER, H2_PWD);
             Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE user(id INT PRIMARY KEY, name VARCHAR(255))");
            statement.execute("INSERT INTO user VALUES(0, 'Jack Jones')");
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(H2_URL);
        config.setUsername(H2_USER);
        config.setPassword(H2_PWD);
        HikariDataSource dataSource = new HikariDataSource(config);

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(0);
            log.info("{}", user);
        }
    }
}
