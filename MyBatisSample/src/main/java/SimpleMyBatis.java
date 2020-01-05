import daos.UserDao;
import entities.UserEntity;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class SimpleMyBatis {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:test";
        String username = "sa";
        String password = "";

        DataSourceUtils.initializeH2(url, username, password);

        DataSource dataSource = DataSourceUtils.createHikariDataSource(url, username, password);

        UserDao userDao = new UserDao(dataSource);
        userDao.retrieve(0);

        UserEntity user = new UserEntity();
        user.setId(3);
        user.setName("马六");
        user.setAddress("金华");
        userDao.create(user);
        userDao.retrieve(3);

        user.setAddress("上海");
        userDao.update(user);
        userDao.retrieve(3);

        userDao.delete(3);
        userDao.retrieve(3);
    }
}
