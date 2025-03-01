package com.eduardo.reposiroty;

import java.sql.*;

public interface JDBCMethods {

    Statement getStatement() throws SQLException;
    void executeUpdate(Statement st, String sql) throws SQLException;
    ResultSet executeQuery(Statement st, String sql) throws SQLException;
    ResultSet execute(Statement st, String sql) throws SQLException;
}
