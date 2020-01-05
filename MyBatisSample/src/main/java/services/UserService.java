package services;

import daos.UserDao;
import entities.UserEntity;

public class UserService implements Service {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int createUser(UserEntity user) {
        int result = userDao.create(user);
        return result;
    }

    public UserEntity retrieveUser(int id) {
        UserEntity user = userDao.retrieve(id);
        return user;
    }

    public int updateUser(UserEntity user) {
        int result = userDao.update(user);
        return result;
    }

    public int deleteUser(int id) {
        int result = userDao.delete(id);
        return result;
    }

    public int updateUsername(int id, String name) {
        UserEntity user = userDao.retrieve(id);
        user.setName(name);
        int result = userDao.update(user);
        return result;
    }
}
