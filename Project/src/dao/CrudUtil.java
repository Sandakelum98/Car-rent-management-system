package dao;

import db.DBConnection;

import java.sql.*;

public class CrudUtil {

    private static PreparedStatement getPreparedStatement(String sql, Object... params) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject((i + 1), params[i]);
        }
        return pstm;
    }

    public static boolean executeUpdate(String sql, Object... params) throws ClassNotFoundException, SQLException {
        PreparedStatement pstm = getPreparedStatement(sql,params);
        return pstm.executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object... params) throws ClassNotFoundException, SQLException {
        PreparedStatement pstm = getPreparedStatement(sql, params);
        return pstm.executeQuery();
    }

    private static PreparedStatement getPreparedStatement(String sql,int key, Object... params) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql,key);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject((i + 1), params[i]);
        }
        return pstm;
    }

    public static int execute(String sql, Object... params) throws ClassNotFoundException, SQLException {
        PreparedStatement pstm = getPreparedStatement(sql,Statement.RETURN_GENERATED_KEYS, params);
        pstm.executeUpdate() ;
        pstm.getGeneratedKeys();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
}
