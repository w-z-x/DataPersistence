package daos;

import entities.Entity;
import entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import mappers.UserMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

@Slf4j
public class UserDao implements Dao {
    private SqlSessionFactory sqlSessionFactory;

    public UserDao(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAlias(UserEntity.class);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        this.sqlSessionFactory = builder.build(configuration);
    }

    @Override
    public int create(Entity user) {
        int result = 0;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            result = mapper.insertUser((UserEntity) user);
            session.commit();
        }
        log.info("{}", result);
        return result;
    }

    @Override
    public UserEntity retrieve(int id) {
        UserEntity result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            result = mapper.selectUser(id);
        }
        log.info("{}", result);
        return result;
    }

    @Override
    public int update(Entity user) {
        int result = 0;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            result = mapper.updateUser((UserEntity) user);
            session.commit();
        }
        log.info("{}", result);
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            result = mapper.deleteUser(id);
            session.commit();
        }
        log.info("{}", result);
        return result;
    }
}
