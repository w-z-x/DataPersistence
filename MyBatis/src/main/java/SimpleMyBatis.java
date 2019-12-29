import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import mappers.UserMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.sql.*;

@Slf4j
public class SimpleMyBatis {
    public static void main(String[] args) throws SQLException {
//        final String URL = "jdbc:h2:tcp://localhost/~/test";
        final String URL = "jdbc:h2:mem:test";
        final String USER = "sa";
        final String PWD = "";
        try (Connection conn = DriverManager.getConnection(URL+";DB_CLOSE_DELAY=-1", USER, PWD);
             Statement statement = conn.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS USER;" +
                    "CREATE TABLE USER(ID INT PRIMARY KEY, NAME VARCHAR(255), _ADDRESS VARCHAR(255));" +
                    "INSERT INTO USER VALUES(0, '张三', '杭州');" +
                    "INSERT INTO USER VALUES(1, '李四', '宁波');" +
                    "INSERT INTO USER VALUES(2, '王五', '温州')");
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PWD);
        HikariDataSource dataSource = new HikariDataSource(config);

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAliases("entities");
        configuration.addMappers("mappers");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(0);
            log.info("{}", user);
        }
    }
}
