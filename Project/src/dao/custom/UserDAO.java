package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User,String> {
    public User login(User user) throws Exception;
}
