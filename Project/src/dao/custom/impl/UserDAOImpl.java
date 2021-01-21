package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.UserDAO;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(User u) throws Exception {
        String sql = "INSERT INTO user (user_name,user_password,user_type) values (?,?,?)";
        return CrudUtil.executeUpdate(sql, u.getUsername(), u.getPassword(), u.getUserType());
    }

    @Override
    public User search(String userName) throws Exception {
        String sql = "SELECT * FROM user WHERE user_name=?";
        ResultSet rst = CrudUtil.executeQuery(sql,userName);
        while (rst.next()){
            return new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean update(User u) throws Exception {
        String sql = "UPDATE user SET user_name=?,user_password=?,user_type=? WHERE user_id=?";
        return CrudUtil.executeUpdate(sql, u.getUsername(),u.getPassword(),u.getUserType(),u.getUserId());
    }

    @Override
    public boolean delete(String userName) throws Exception {
        String sql = "DELETE FROM user WHERE user_name=?";
        return CrudUtil.executeUpdate(sql,userName);
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        String sql = "SELECT * FROM user";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<User> allUsers = new ArrayList<>();
        while (rst.next()){
            allUsers.add(new User(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return allUsers;
    }

    public User login(User user) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM user WHERE user_name=? and user_password=? ";
        ResultSet rst=CrudUtil.executeQuery(sql, user.getUsername(), user.getPassword());
        if(rst.next()){
            return new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return null;
    }
}
