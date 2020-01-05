import com.sun.net.httpserver.HttpServer;
import controllers.UserController;
import daos.UserDao;
import lombok.extern.slf4j.Slf4j;
import services.UserService;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

@Slf4j
public class ServerEntry {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:h2:mem:test";
        String username = "sa";
        String password = "";

        DataSourceUtils.initializeH2(url, username, password);

        DataSource dataSource = DataSourceUtils.createHikariDataSource(url, username, password);

        UserDao userDao = new UserDao(dataSource);
        UserService userService = new UserService(userDao);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/user", new UserController(userService));
        server.start();
    }
}
