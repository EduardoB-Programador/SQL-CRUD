package com.eduardo.reposiroty;

import com.eduardo.model.modelHelper.FieldCartographer;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.sql.*;
import java.util.*;

@FunctionalInterface
public interface SQLRepo<T extends FieldCartographer> extends DBManager<T> {

    Connection makeConnection(String url, String username, String password) throws SQLException;

    default Connection makeConnection(String url, String username) throws SQLException {
        return this.makeConnection(url, username, "");
    }

    default Connection makeConnection(String url) throws SQLException {
        return this.makeConnection(url, "", "");
    }

    @Override
    default String create(T value) {
        if (value == null)
            return null;

        StringBuilder INSERT = new StringBuilder();
        INSERT.append("INSERT INTO ")
                .append(value.getClass().getSimpleName().toLowerCase())
                .append("(");

        List<String> fieldList = new ArrayList<>();

        if (value.getClass().isRecord()) {
            for (RecordComponent rc : value.getClass().getRecordComponents())
                fieldList.add(rc.getName());
        } else {
            for (Field f : value.getClass().getFields())
                fieldList.add(f.getName());
        }

        for (String s : fieldList)
            INSERT.append(s).append(",");

        INSERT.replace(INSERT.length() -1, INSERT.length(), "");
        INSERT.append(") VALUES(");

        Map valuesMap = value.fieldToMap();
        for (String s : fieldList) {
            INSERT.append("'")
                    .append(valuesMap.get(s))
                    .append("',");
        }

        INSERT.replace(INSERT.length() -1, INSERT.length(), "");
        INSERT.append(");");

        String query;
        if (INSERT.toString().contains("'null'")) {
            query = INSERT.toString().replaceAll("'null'", "NULL");
        } else {
            query = INSERT.toString();
        }

        return query;
    }

    @Override
    default String retrive(T t, String condition) {

        return "SELECT * FROM " +
                t.getClass()
                        .getSimpleName()
                        .toLowerCase() +
                " " +
                condition;
    }

    @Override
    default String update(T value, String condition) {
        //We don't want to update all entries, do we?
        if (value == null || condition.equals(";"))
            return null;

        StringBuilder UPDATE = new StringBuilder();
        UPDATE.append("UPDATE ")
                .append(value.getClass()
                        .getSimpleName()
                        .toLowerCase())
                .append(" SET ");

        for (Map.Entry<String, Object> entry : value.fieldToMap().entrySet()) {
            if (entry.getValue() == null)
                continue;

            UPDATE.append(entry.getKey())
                    .append(" = '")
                    .append(entry.getValue())
                    .append("', ");
        }

        UPDATE.append("awa");

        if (!UPDATE.toString().contains("', "))
            return null;

        return UPDATE.toString().replaceAll("', awa", "' ") + condition;
    }

    @Override
    default String delete(T t, String condition) {
        if (condition.equals(";"))
            return null;

        return "DELETE FROM " + t.getClass()
                .getSimpleName()
                .toLowerCase() + " " + condition;
    }

    @Override
    default String conditionBuilder(List<String> l) {
        StringBuilder WHERE = new StringBuilder();

        if (!l.isEmpty()) {
            WHERE.append("WHERE ");

            for (String s : l) {
                WHERE.append(s).append(" AND ");
            }
        }
        if (WHERE.toString().contains("AND")) {
            String finalWhere = WHERE.toString()
                    .replaceAll(" AND ", ";")
                    .replaceFirst(";", " AND ");

            if (!finalWhere.endsWith(";"))
                return finalWhere.replaceFirst(" AND ", ";");
        }

        return WHERE.append(";").toString();
    }

}
