package com.eduardo.reposiroty.databases;

import com.eduardo.model.modelHelper.FieldCartographer;
import com.eduardo.reposiroty.JDBCMethods;
import com.eduardo.reposiroty.SQLRepo;

import java.sql.*;

public class SQLiteRepo<T extends FieldCartographer> implements SQLRepo<T>, JDBCMethods {
    private Connection con;

    public SQLiteRepo(String url) {
        this.con = this.makeConnection(url, null, null);
    }

    @Override
    public Statement getStatement() throws SQLException {
        return con.createStatement();
    }

    @Override
    public void executeUpdate(Statement st, String sql) throws SQLException {
        st.executeUpdate(sql);
    }

    @Override
    public ResultSet executeQuery(Statement st, String sql) throws SQLException {
        return st.executeQuery(sql);
    }

    @Override
    public ResultSet execute(Statement st, String sql) throws SQLException {
        if (st.execute(sql))
            return st.getResultSet();

        return null;
    }

    @Override
    public Connection makeConnection(String url, String username, String password) {
        Connection con;

        try {
            con = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println("Connection failed, check if the url, username or password are correct");
            con = null;
        }

        return con;
    }
}
