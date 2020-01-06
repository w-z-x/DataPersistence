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
        return userDao.create(user);
    }

    public UserEntity retrieveUser(int id) {
        return userDao.retrieve(id);
    }

    public int updateUser(UserEntity user) {
        return userDao.update(user);
    }

    public int deleteUser(int id) {
        return userDao.delete(id);
    }

    public List<UserEntity> findAllUsers() {
        return userDao.findAll();
    }
}
