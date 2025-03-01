package com.eduardo.reposiroty;

import com.eduardo.model.modelHelper.FieldCartographer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DBManager<T extends FieldCartographer> {

    Connection makeConnection(String url, String username, String password) throws SQLException;
    Connection makeConnection(String url, String username) throws SQLException;
    Connection makeConnection(String url) throws SQLException;
    String create(T value);
    String retrive(T t, String condition);
    String update(T value, String condition);
    String delete(T t, String condition);
    String conditionBuilder(List<String> m);
}
