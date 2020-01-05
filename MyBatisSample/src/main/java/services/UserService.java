package services;

import daos.UserDao;
import entities.UserEntity;

import java.util.List;

public class UserService {
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

    public List<UserEntity> findAllUsers() {
        List<UserEntity> result = userDao.findAll();
        return result;
    }
}
